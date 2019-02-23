package com.linguaclassica.instadmin;

import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.entity.EntityTermModel;
import com.linguaclassica.entity.EntityTermStudentsModel;
import com.linguaclassica.entity.EntityTermTaModel;
import com.linguaclassica.entity.EntityTermTeachersModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.service.ClassService;
import com.linguaclassica.service.TermService;
import com.linguaclassica.service.UserService;

public class UserCopyPage extends InstAdminBasePage
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	ModelFactory modelFactory;
	
	@SpringBean
	TermService termService;

	@SpringBean
	private ClassService classService;
	
	@SpringBean
	UserService userService;
	    
	private Permission permission;
	private PageParameters myParams;
	
	public UserCopyPage()
	{
		permitPreferenceSelection();
		
		Form<Object> form = new UserCopyForm();
		add(form);
	}

	public UserCopyPage(Permission permission)
	{
		this.permission = permission;
		
		permitPreferenceSelection();
		
		Form<Object> form = new UserCopyForm();
		add(form);
	}

	public UserCopyPage(IModel<?> model)
	{
		super(model);
	}

	public UserCopyPage(PageParameters parameters)
	{
		super(parameters);

		myParams = parameters;
		
		StringValue sv = parameters.get("usertype");
		if (sv != null)
		{
			String usertype = sv.toString();
			if (usertype !=null)
			{
				permission = Permission.valueOf(usertype);
			}
		}
		
		permitPreferenceSelection();
		
		Form<Object> form = new UserCopyForm();
		add(form);
	}

	class UserCopyForm extends Form<Object> 
	{
		private static final long serialVersionUID = 1L;

		private EntityTermModel fromSourceTerm;
		private EntityTermModel toDestinationTerm;
	    private AlphPlusSession session = AlphPlusSession.get();
	    
		public UserCopyForm()
		{
			super("usercopyform");
			
			Integer institutionId = AlphPlusSession.get().getCurrentInstitution();
			
			Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());

			// Get a list of terms to copy from
			List<EntityTermModel> sourceTermsList = termService.getListOfNewAndExistingTerms(institutionId, now);
			List<EntityTermModel> priorTermsList = termService.getListOfPastTerms(institutionId, now, 
					AlphPlusSession.get().getDateRange());
			if (priorTermsList.size() > 0)
			{
				sourceTermsList.addAll(priorTermsList);
			}
			
			// Create a drop-down control for the term to add students to
			fromSourceTerm = (EntityTermModel) modelFactory.getNewTermModel();
			if (sourceTermsList.size() > 0)
			{
				// Initialize to the newest item unless term retrieved from alph-session
				if (session.getUserPageTermId() == -1)
				{	
					fromSourceTerm = sourceTermsList.get(sourceTermsList.size()-1);
				}
				else
				{
					fromSourceTerm = termService.findTermById(session.getUserPageTermId());
				}
			}
			final DropDownChoice<EntityTermModel> sourcetermselect = new DropDownChoice<EntityTermModel>("sourcetermselect",
					new PropertyModel<EntityTermModel>(this, "fromSourceTerm"), sourceTermsList, new ChoiceRenderer<EntityTermModel>("termname", "id"));
			sourcetermselect.setNullValid(false);
			sourcetermselect.setRequired(true);
			
			add(sourcetermselect);

			// DESTINATION TERM
			List<EntityTermModel> destinationTermsList = termService.getListOfNewAndExistingTerms(institutionId, now);

			// Create a drop-down control for the term to add students to
			toDestinationTerm = (EntityTermModel) modelFactory.getNewTermModel();
			if (destinationTermsList.size() > 0)
			{
/*				// Initialize to the newest item unless term retrieved from alph-session
				if (session.getUserPageTermId() == -1)
				{	
					fromSourceTerm = sourceTermsList.get(sourceTermsList.size()-1);
				}
				else
				{
					fromSourceTerm = termService.findTermById(session.getUserPageTermId());
				}*/
			}
			final DropDownChoice<EntityTermModel> desttermselect = new DropDownChoice<EntityTermModel>("desttermselect",
					new PropertyModel<EntityTermModel>(this, "toDestinationTerm"), destinationTermsList, new ChoiceRenderer<EntityTermModel>("termname", "id"));
			desttermselect.setNullValid(false);
			desttermselect.setRequired(true);
			
			add(desttermselect);

			String role = "User";
			if (permission == Permission.STUDENT)
			{
				role = "Student";
			}
			else if (permission == Permission.TEACHER)
			{
				role = "Teacher";
			}
			else if (permission == Permission.TA)
			{
				role = "TA";
			}
			add (new Label("userlabel", role));
			add (new Label("userlabel2", role));
 		}

		public void onSubmit()
		{
			System.out.println("UserCopyForm.onSubmit()    source.termname= " + fromSourceTerm.getTermname());
			System.out.println("UserCopyForm.onSubmit()    dest.termname=   " + toDestinationTerm.getTermname());
			            
			if ((toDestinationTerm == null) || (fromSourceTerm == null) || (toDestinationTerm.getId() <= 0) || (fromSourceTerm.getId() <= 0))
			{
				Session.get().error("New term and previous term must be selected to copy teachers.");
				return;
			}
			if (toDestinationTerm.getId() == fromSourceTerm.getId())
			{
				Session.get().error("Cannot copy a term's teachers to itself. Change one of the term selections.");
				return;
			}
				
			if (permission == Permission.TEACHER)
			{
				//get list of teachers from old term to be copied
				List<Integer> newTeacherIdList = new ArrayList<Integer>();
				newTeacherIdList = termService.getListOfTeacherIdsByTermId(fromSourceTerm.getId());

				//save new list to terms-teachers table in database
				int newuserlistlen = newTeacherIdList.size();
				for (int m = 0; m < newuserlistlen; m++) {
					EntityTermTeachersModel newTermTeachersModel = 
						(EntityTermTeachersModel) modelFactory.getNewTermTeachersModel();
					newTermTeachersModel.setTermid(toDestinationTerm.getId());
					newTermTeachersModel.setTeacherid(newTeacherIdList.get(m));
					termService.createTermTeacher(newTermTeachersModel);
				}
			}
			else if (permission == Permission.TA)
			{			
				//get list of TAs from old term to be copied
				List<Integer> newTaIdList = new ArrayList<Integer>();
				newTaIdList = termService.getListOfTaIdsByTermId(fromSourceTerm.getId());

				//save new list to terms-teachers table in database
				int newtalistlen = newTaIdList.size();
				for (int m = 0; m < newtalistlen; m++) {
					EntityTermTaModel newTermTaModel = 
						(EntityTermTaModel) modelFactory.getNewTermTaModel();
					newTermTaModel.setTermid(toDestinationTerm.getId());
					newTermTaModel.setTaid(newTaIdList.get(m));
					termService.createTermTa(newTermTaModel);
				}
			}
			else if (permission == Permission.STUDENT)
			{
				//get list of users from old term to be copied
				List<Integer> newStudentIdList = new ArrayList<Integer>();
				newStudentIdList = termService.getListOfStudentIdsByTermId(fromSourceTerm.getId());

				//save new list to terms-teachers table in database
				int newstudentlistlen = newStudentIdList.size();
				for (int m = 0; m < newstudentlistlen; m++) {
					EntityTermStudentsModel newTermStudentsModel = 
						(EntityTermStudentsModel) modelFactory.getNewTermStudentsModel();
					newTermStudentsModel.setTermid(toDestinationTerm.getId());
					newTermStudentsModel.setStudentid(newStudentIdList.get(m));
					termService.createTermStudents(newTermStudentsModel);
				}
			}
			
			setResponsePage(UserEditPage.class, myParams);
		}
	}

}
