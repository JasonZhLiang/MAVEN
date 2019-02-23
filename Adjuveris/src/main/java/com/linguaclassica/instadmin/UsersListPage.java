package com.linguaclassica.instadmin;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.model.TermModel;
import com.linguaclassica.service.ClassService;
import com.linguaclassica.service.TermService;
import com.linguaclassica.service.UserService;

public class UsersListPage extends InstAdminBasePage
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	TermService termService;
	
	@SpringBean
	UserService userService;
	
	@SpringBean
	ClassService classService;
	
	private int termid;
	private Permission permission;
	
	public UsersListPage(Integer termId, Permission permit)
	{
		// Copy passed parameters
		termid = termId;
		permission = permit;

		// Customize and initialize the labels
		String headerStr = new String("View Users in Terms");
		switch (permission)
		{
			case TEACHER:
				headerStr   = "View Teachers in Terms";
				break;
			case TA:
				headerStr   = "View TAs in Terms";
				break;
			case STUDENT:
				headerStr   = "View Students in Terms";
				break;
			default:
				System.out.format("UsersListPage(%d, %s) invalid Permission%n", termid, permit.name());
		}
		add(new Label("usertypeh", headerStr));
		TermModel termModel = termService.findTermById(termid);
		add(new Label("termnameh", new PropertyModel<TermModel>(termModel, "termname")));

		// Create the form
		UsersForm form = new UsersForm();
		add(form);
	}
/*
	public UsersListPage(IModel<?> model)
	{
		super(model);
		// TODO Auto-generated constructor stub
	}

	public UsersListPage(PageParameters parameters)
	{
		super(parameters);
		// TODO Auto-generated constructor stub
	}
*/
	class UsersForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;
		
		public UsersForm()
		{
			super("userslistform");

			// Set the user type labels and get user IDs
			List<Integer> useridlist = new ArrayList<Integer>();
			String codeStr = new String("User Code");
			switch (permission)
			{
				case TEACHER:
					useridlist = termService.getListOfTeacherIdsByTermId(termid);
					codeStr   = "Employee Code";
					break;
				case TA:
					useridlist = termService.getListOfTaIdsByTermId(termid);
					codeStr   = "Employee Code";
					break;
				case STUDENT:
					useridlist = termService.getListOfStudentIdsByTermId(termid);
					codeStr   = "Student Code";
					break;
				default:
					System.out.format("UsersForm term %d, invalid Permission '%s'%n", termid, permission.name());
				break;
			}
			add(new Label("usercodeh", codeStr));

			List<EntityUserModel> userlist = new ArrayList<EntityUserModel>();
			for (int i = 0; i < useridlist.size(); i++)
			{
				userlist.add((EntityUserModel) userService.findUserById(useridlist.get(i)));
			}
			
			add(new ListView<EntityUserModel>("useritem", userlist)
			{
				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(ListItem<EntityUserModel> item)
				{
					System.out.format("populateItem %d%n", item.getModelObject().getId());
					item.add(new Label("firstnamet", item.getModelObject().getFirstName()));
					item.add(new Label("lastnamet", item.getModelObject().getLastName()));
					item.add(new Label("emailt", item.getModelObject().getEmailAddress()));
					item.add(new Label("usercodet", item.getModelObject().getStudentnumber()));
				}
			});
		}
	}
}
