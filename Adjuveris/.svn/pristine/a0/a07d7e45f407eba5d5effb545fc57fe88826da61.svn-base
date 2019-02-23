package com.linguaclassica.instadmin;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.googlecode.wicket.jquery.ui.form.button.Button;
import com.linguaclassica.entity.EntityClassModel;
import com.linguaclassica.entity.EntityClassUsersModel;
import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.service.ClassService;
import com.linguaclassica.service.TermService;
import com.linguaclassica.service.UserService;

public class ClassUsersPage extends InstAdminBasePage
{
	private static final long serialVersionUID = 1L;

	@SpringBean
	private TermService termService;

	@SpringBean
	private ClassService classService;

	@SpringBean
	private UserService userService;

	@SpringBean
	ModelFactory modelFactory;
		
	EntityClassModel classModel;
	
	int classId = 0;
	int termid = 0;
	Permission permission = null;
	
	private ArrayList<EntityUserModel> usersSelect = new ArrayList<EntityUserModel>(); 
	private ArrayList<EntityUserModel> usersAtStart = new ArrayList<EntityUserModel>();
	
    public ClassUsersPage(Integer instId, Integer classId, Permission permit)
	{
		this.classId = classId;
		this.permission = permit;
		
		System.out.println("ClassUsersPage:  classId = " + classId);
		
		classModel = classService.findClassById(classId);
		termid = classModel.getTermid();

		//populate list of users selected
		List<EntityClassUsersModel> useridlist = null;
		String userstring = new String("");
		
		if (permit == Permission.TEACHER)
		{
			useridlist = classService.getListOfUsersInAClassByUserType(classId, "TEACHER");
			userstring = "Teacher";
		}
		else if (permit == Permission.TA)
		{
			useridlist = classService.getListOfUsersInAClassByUserType(classId, "TA");
			userstring = "TA";
		}
		else if (permit == Permission.STUDENT)
		{
			useridlist = classService.getListOfUsersInAClassByUserType(classId, "STUDENT");
			userstring = "Student";
		}

		add (new Label("userlabel", userstring));
		add (new Label("classlabel", classModel.getClassname()));
		add (new Label("termlabel", termService.findTermById(termid).getTermname()));

		int userlistlen = useridlist.size();
		for (int i = 0; i < userlistlen; i++)
		{
			EntityUserModel userModel = (EntityUserModel) userService.findUserById(useridlist.get(i).getUserId());
			usersSelect.add(userModel);
			usersAtStart.add(userModel);
		}
		
		buildForm();
	}

	/**
	 * Remove all users that were removed from the starting list
	 * @param selectStart
	 * @param selectEnd
	 */
	private void removeDeselectedUsers(List<EntityUserModel> selectStart, List<EntityUserModel> selectEnd, 
			String userType)
	{
		for (int priorix = 0; priorix < selectStart.size(); priorix++)
		{
			int priorid = selectStart.get(priorix).getId();
			
			// Check against users that are still selected
			boolean bstill = false;
			int assignedix = 0;
			while (assignedix < selectEnd.size())
			{
				int selectedid = selectEnd.get(assignedix).getId();
				if (priorid == selectedid)
				{
					bstill = true;
					break;
				}
				assignedix++;
			}
			if (!bstill)
			{
				// Was selected, is not selected
				System.out.println("removeUser " + priorid);
				classService.removeClassUser(priorid, classId, userType);
			}
		}
	}
	
	/**
	 * Add all of the users that were added to the starting list
	 * @param selectStart
	 * @param selectEnd
	 * @param userType
	 */
	private void addNewselectedUsers(List<EntityUserModel> selectStart, List<EntityUserModel> selectEnd, 
			String userType)
	{
		for (int pickedix = 0; pickedix < selectEnd.size(); pickedix++)
		{
			int selectid = selectEnd.get(pickedix).getId();
			
			// Check against initially selected teachers
			boolean bexists = false;
			int previousix = 0;
			while (previousix < selectStart.size())
			{
				int previousid = selectStart.get(previousix).getId();
				if (selectid == previousid)
				{
					bexists = true;
					break;
				}
				previousix++;
			}
			if (!bexists)
			{
				// Is now selected
				EntityClassUsersModel usermodel = (EntityClassUsersModel) modelFactory.getNewClassUsersModel();
				usermodel.setUserId(selectid);
				usermodel.setClassId(classId);
				usermodel.setUserType(userType);
				int userid = classService.addClassUser(usermodel);
				System.out.println("addClassUser " + userid);
			}
		}
	}
    
	private void buildForm() {
		System.out.println("ClassesUserPage: beginning of buildform" );

		Form<?> form = new EditClassUsersForm("editclassusersform");

		System.out.println("ClassesUserPage: classModel not null, classId, classModel.getClassname() = " + 
				classId + ", " + classModel.getClassname());
		
        IModel<List<EntityUserModel>> useridArrayList = new LoadableDetachableModel<List<EntityUserModel>>()
        {
        	private static final long serialVersionUID = 1L;

			protected List<EntityUserModel> load()
			{
				return getUsersList(termid);
			}

			private List<EntityUserModel> getUsersList(int tid)
			{
				List<EntityUserModel> useridArrayList = new ArrayList<EntityUserModel>();
				List<Integer> useridlist = null;
				
				if (permission == Permission.TEACHER)
				{
					useridlist = termService.getListOfTeacherIdsByTermId(tid);
				}
				else if (permission == Permission.TA)
				{
					useridlist = termService.getListOfTaIdsByTermId(tid);
				}
				else if (permission == Permission.STUDENT)
				{
					useridlist = termService.getListOfStudentIdsByTermId(tid);
				}

				if ((useridlist != null) && (useridlist.size() > 0))
				{
					useridArrayList = userService.getListOfStudentsByIdList(useridlist);
				}
				
				return useridArrayList;
			}
        };
		
        final CheckBoxMultipleChoice<EntityUserModel> listUsers = 
        	new CheckBoxMultipleChoice<EntityUserModel>("userchecks", 
        		new Model<ArrayList<EntityUserModel>>(usersSelect), useridArrayList, new UsernameRenderer());
        listUsers.setSuffix("<br/>");
		form.add(listUsers);
		
		form.add(new Button("saveclassbutton") {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onSubmit() {
				System.out.println("ClassUsersPage.saveclassbutton.onSubmit");
				
				if (permission == Permission.TEACHER)
				{
					// Update the users
					// Remove all de-selected teachers
					removeDeselectedUsers(usersAtStart, usersSelect, "TEACHER");
				
					// Add the newly selected teachers
					addNewselectedUsers(usersAtStart, usersSelect, "TEACHER");
				}
				else if (permission == Permission.TA)
				{
					// Update the users
					// Remove all de-selected TAs
					removeDeselectedUsers(usersAtStart, usersSelect, "TA");
				
					// Add the newly selected TAs
					addNewselectedUsers(usersAtStart, usersSelect, "TA");
				}
				else if (permission == Permission.STUDENT)
				{
					// Update the users
					// Remove all de-selected students
					removeDeselectedUsers(usersAtStart, usersSelect, "STUDENT");
				
					// Add the newly selected students
					addNewselectedUsers(usersAtStart, usersSelect, "STUDENT");
				}
				
				setResponsePage(ClassesListPage.class);
				
			}
			
		});
		
		add(form);
	}	

	class EditClassUsersForm extends Form<Object> 
	{
		private static final long serialVersionUID = 1L;

		public EditClassUsersForm(String id) {
			super(id);

		}

		public void onSubmit()
		{
			System.out.println("EditClassUsersForm.onSubmit");
		}
	}

}
