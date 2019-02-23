package com.linguaclassica.instadmin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.entity.EntityClassModel;
import com.linguaclassica.entity.EntityTermModel;
import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.service.ClassService;
import com.linguaclassica.service.TermService;
import com.linguaclassica.service.UserService;

public class UserEditPage extends InstAdminBasePage
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

	public UserEditPage()
	{
		permission = Permission.STUDENT;
		
		permitPreferenceSelection();

		Form<Object> form = new UserEditForm();
		add(form);
	}

	public UserEditPage(IModel<?> model)
	{
		super(model);
	}

	public UserEditPage(PageParameters parameters)
	{
		super(parameters);

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

		Form<Object> form = new UserEditForm();
		add(form);
	}

	public UserEditPage(Permission permission)
	{
		this.permission = permission;
		
		permitPreferenceSelection();

		Form<Object> form = new UserEditForm();
		add(form);
	}

	class UserEditForm extends Form<Object> 
	{
		private static final long serialVersionUID = 1L;

		private EntityTermModel destinationTerm;
	    private AlphPlusSession session = AlphPlusSession.get();
		
		public UserEditForm()
		{
			super("editusersform");
			
			Integer institutionId = AlphPlusSession.get().getCurrentInstitution();
			
			java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			List<EntityTermModel> termsList = termService.getListOfNewAndExistingTerms(institutionId, now);
			List<EntityTermModel> termsPast = termService.getListOfPastTerms(institutionId, now, 
					AlphPlusSession.get().getDateRange());
			if (termsPast.size() > 0)
			{
				termsList.addAll(termsPast);
			}
			
			// Create a drop-down control for the term to add students to
			destinationTerm = (EntityTermModel) modelFactory.getNewTermModel();
			if (termsList.size() > 0)
			{
				// Initialize to the newest item unless term retrieved from alph-session
				if (session.getUserPageTermId() == -1)
				{	
					destinationTerm = termsList.get(termsList.size()-1);
				}
				else
				{
					destinationTerm = termService.findTermById(session.getUserPageTermId());
				}
			}
			final DropDownChoice<EntityTermModel> termselect = new DropDownChoice<EntityTermModel>(
					"selectterm",
					new PropertyModel<EntityTermModel>(this, "destinationTerm"), 
					termsList, 
					new ChoiceRenderer<EntityTermModel>("termname", "id") )
			{
				private static final long serialVersionUID = 1L;
				
				public boolean isVisible()
				{
					return true;
				}
				
				protected boolean wantOnSelectionChangedNotifications()
				{
					return true;
				}
				
				protected void onSelectionChanged(final EntityTermModel newSelection)
				{
					System.out.format("onSelectionChanged(%s)%n", newSelection.getTermname());
					session.setUserPageTermId(destinationTerm.getId());
					setResponsePage(new UserEditPage(permission));
				}
			};
			
			termselect.setNullValid(false);
			termselect.setRequired(true);
			
			add(termselect);
			
			add(new Label("termnamelab",destinationTerm.getTermname()));
			
			List<Integer> useridlist = new ArrayList<Integer>();
			String userstring = new String("");
			String codestring = new String("");

			if (permission == Permission.STUDENT)
			{
				useridlist = termService.getListOfStudentIdsByTermId(destinationTerm.getId());
				userstring = "Student";
				codestring = "Student";
			}
			else if (permission == Permission.TEACHER)
			{
				useridlist = termService.getListOfTeacherIdsByTermId(destinationTerm.getId());
				userstring = "Teacher";
				codestring = "Employee";
			}
			else if (permission == Permission.TA)
			{
				useridlist = termService.getListOfTaIdsByTermId(destinationTerm.getId());
				userstring = "TA";
				codestring = "Empl/Student";
			}
			add (new Label("userlabel", userstring));
			add (new Label("codelabel", codestring));
			
			List<EntityUserModel> userlist = new ArrayList<EntityUserModel>();
			int listlen = useridlist.size();
			for (int i = 0; i < listlen; i++) {
				userlist.add((EntityUserModel) userService.findUserById(useridlist.get(i)));
			}
			
			add(new ListView<EntityUserModel>("termlist", userlist){
				private static final long serialVersionUID = 1L;			

				@Override
				protected void populateItem(ListItem<EntityUserModel> item) {
					final EntityUserModel userModel = item.getModelObject();
					final int userid = userModel.getId();

					item.add(new Label("lastnamelab",item.getModelObject().getLastName()));
					item.add(new Label("firstnamelab",item.getModelObject().getFirstName()));
					item.add(new Label("emailaddresslab",item.getModelObject().getEmailAddress()));
					item.add(new Label("studentnumberlab",item.getModelObject().getStudentnumber()));			
                    
					Button editButton = new Button("editbutton") {
                        private static final long serialVersionUID = 1L;
                        
                        public void onSubmit() {
                                System.out.println("editbutton clicked");
                                
                                PageParameters pageParams = new PageParameters();
                                pageParams.add("usertype", permission);
                                pageParams.add("userid", userid);

                                setResponsePage(UserCreatePage.class, pageParams);
                        }

						@Override
						public boolean isEnabled()
						{
							// Enable current and future terms
							LocalDate enddate = destinationTerm.getEndDate().toLocalDate();
							LocalDate today = LocalDate.now();
							return super.isEnabled() && today.compareTo(enddate) <= 0;
						}
					};
                    editButton.setDefaultFormProcessing(false);
					item.add(editButton);
					
					// DELETE BUTTON
					Button deleteButton = new Button("deletebutton") {
						private static final long serialVersionUID = 1L;

						public void onSubmit() {
							System.out.println("deletebutton clicked");

							// delete should be allowed only if the selected user is not listed 
							// in any class in this term.
							
							Integer selectedUserid = userModel.getId();
							
					    	List<EntityClassModel> classList = new ArrayList<EntityClassModel>();
					    	classList = classService.getListOfClassesByTermId(destinationTerm.getId());
					    	
					    	// loop thru and examine each class list in this term to check if the user is present.
					    	for (int i=0; i<classList.size(); i++) {
					    		List<Integer> cuseridlist;
					    		
					    		// get the user (teacher, TA or student) list for this class
					    		switch (permission)
					    		{
					    			case TEACHER:
					    				cuseridlist = classService.getListOfUserIdsInAClassByUserType(classList.get(i).getId(), "TEACHER");
					    				break;
					    			case TA:
					    				cuseridlist = classService.getListOfUserIdsInAClassByUserType(classList.get(i).getId(), "TA");
					    				break;
					    			case STUDENT:
					    				cuseridlist = classService.getListOfUserIdsInAClassByUserType(classList.get(i).getId(), "STUDENT");
					    				break;
					    			default:
					    				Session.get().error("error retrieving " + permission.name() + " list");
					    				return;
					    		}
					    		
					    		// check if the selected user is present in this class
					    		if (cuseridlist.contains(selectedUserid))
					    		{
					    			Session.get().error("'" + userModel.getFirstName() + " " + userModel.getLastName() + 
					    					"' is assigned to class '" + classList.get(i).getClassname() + 
					    					"'.  Please delete this " + permission.name() + 
					    					" from the class before deleting from the '" + destinationTerm.getTermname() + "' term.");
					    			
					    			return;
					    		}
					    	}
					    	
					    	// user is not listed in any class for this term, so can be deleted if desired.
					    	// But let's ask for confirmation first...
							final JDialog dialog = new JDialog();
					    	dialog.setAlwaysOnTop(true);    
					    					    	
					    	Object[] options = {"Cancel","Delete"};
					    	int option = JOptionPane.showOptionDialog(
					    			dialog, 
					    			"Please confirm... delete '" + userModel.getEmailAddress() + "'", 
					    			"Warning",
					    			JOptionPane.DEFAULT_OPTION, 
					    			JOptionPane.PLAIN_MESSAGE,
					    			null,
					    			options,
					    			options[0]);

					    	if (option == 0)
					    		return;
					    	
							switch (permission)
							{
								case TEACHER:
									termService.deleteTermTeacher(userModel.getId(), destinationTerm.getId());
									break;
								case TA:
									termService.deleteTermTa(userModel.getId(), destinationTerm.getId());
									break;
								case STUDENT:
									termService.deleteTermStudent(userModel.getId(), destinationTerm.getId());
									break;
								default:
									// Should never occur
									return;
							}
							setResponsePage(new UserEditPage(permission));
						}					

						@Override
						public boolean isEnabled()
						{
							// Enable current and future terms
							LocalDate enddate = destinationTerm.getEndDate().toLocalDate();
							LocalDate today = LocalDate.now();
							return super.isEnabled() && today.compareTo(enddate) <= 0;
						}
					};	
                    deleteButton.setDefaultFormProcessing(false);
					item.add(deleteButton);
				}
			});		
		}
	}
}