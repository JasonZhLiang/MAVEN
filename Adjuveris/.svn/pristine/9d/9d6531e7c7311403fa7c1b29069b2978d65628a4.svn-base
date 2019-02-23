package com.linguaclassica.instadmin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.entity.EntityClassModel;
import com.linguaclassica.entity.EntityTermModel;
import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.service.ClassService;
import com.linguaclassica.service.TermService;
import com.linguaclassica.service.UserService;

public class ClassesListPage extends InstAdminBasePage
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	TermService termService;
	
	@SpringBean
	UserService userService;
	
	@SpringBean
	ClassService classService;
	
	private Logger logger = LoggerFactory.getLogger("ClassesListPage");
	private int institutionId;

	public ClassesListPage()
	{
		institutionId = AlphPlusSession.get().getCurrentInstitution();

		permitPreferenceSelection();

		// Create the form
		ClassesForm form = new ClassesForm();
		add(form);
	}

	class ClassesForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;
		
		public ClassesForm()
		{
			super("classlistform");
			
			java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			
			List<EntityTermModel> currenttermList = termService.getListOfExistingTerms(institutionId, now);
			
			List<EntityTermModel> newtermList = termService.getListOfNewTerms(institutionId, now); 
			
			List<EntityTermModel> pasttermList = termService.getListOfPastTerms(institutionId, now, 
					AlphPlusSession.get().getDateRange()); 

			addRows("currentList", "current", true, currenttermList);
			
			addRows("newList", "new", true, newtermList);
			
			addRows("pastList", "past", false, pasttermList);
			
		}
		
		private void addRows(String rowid, String prefix, boolean buttons, List<EntityTermModel> termsList)
		{
			List<EntityClassModel> classEntities = new ArrayList<EntityClassModel>();
			for (int index = 0; index < termsList.size(); index++)
			{
				EntityTermModel oneterm = termsList.get(index);
				List<EntityClassModel> classesList = classService.getListOfClassesByTermId(oneterm.getId());
				classEntities.addAll(classesList);
			}
			
			ListView<EntityClassModel> listview = new ListView<EntityClassModel>(rowid, classEntities)
			{
				private static final long serialVersionUID = 1L;

				protected void populateItem(final ListItem<EntityClassModel> classitem)
				{
					String termId = prefix + "termLab";
					String nameId = prefix + "classnameLab";
					String codeId = prefix + "classcodeLab";
					String teacherId = prefix + "teachernameLab";
					String taId = prefix + "tanameLab";
					String studentId = prefix + "studentLab";
					String editId = prefix + "editclass";
					String deleteId = prefix + "deleteclass";
					String editTeachersId = prefix + "editteachersclass";
					String editTAsId = prefix + "edittasclass";
					String editStudentsId = prefix + "editstudentsclass";
					
					EntityClassModel oneclass  = classitem.getModelObject();
					EntityTermModel oneterm = termService.findTermById(oneclass.getTermid());
					classitem.add(new Label(termId, oneterm.getTermname()));
					classitem.add(new Label(codeId, oneclass.getClasscode()));
					classitem.add(new Label(nameId, oneclass.getClassname()));

					// Display all of the teachers
					List<Integer> teacheridlist = classService.getListOfUserIdsInAClassByUserType(oneclass.getId(), "TEACHER");
					String tenames = getUserNames(teacheridlist);
					classitem.add(new MultiLineLabel(teacherId, tenames));

					// Display all of the TAs
					List<Integer> taidlist = classService.getListOfUserIdsInAClassByUserType(oneclass.getId(), "TA");
					String tanames = getUserNames(taidlist);
					classitem.add(new Label(taId, tanames));

					List<Integer> studentidlist = classService.getListOfUserIdsInAClassByUserType(oneclass.getId(), "STUDENT");
					classitem.add(new Label(studentId, studentidlist.size()));
					
					if (buttons)
					{
						Button editButton = new Button(editId)
						{
							private static final long serialVersionUID = 1L;
							
							public void onSubmit()
							{
								setResponsePage(new ClassPage(oneclass));
							}
						};
						classitem.add(editButton);

						Button deleteButton = new Button(deleteId)
						{
							private static final long serialVersionUID = 1L;
							
							@Override
							public boolean isEnabled()
							{
								Long allUsers = 0L;
								try
								{
									allUsers = classService.getCountOfUsers(oneclass.getId());
								}
								catch (Exception e)
								{
									logger.error("Error in ClassesForm delete isEnabled class " + oneclass.getId() + ": " + e.toString());
									return false;
								}
								return super.isEnabled() && allUsers == 0;
							}
							
							public void onSubmit()
							{
								try
								{
									Long allUsers = classService.getCountOfUsers(oneclass.getId());
									if (0 == allUsers)
									{
										// Class does not have any users
										final JDialog dialog = new JDialog();
										dialog.setAlwaysOnTop(true);
										String messageStr = String.format("Delete the class '%s(%s)'?", oneclass.getClassname(), oneclass.getClasscode());
										int option = JOptionPane.showConfirmDialog(dialog, messageStr, "Confirmation", JOptionPane.YES_NO_OPTION);
										if (option == 0)
										{
											// Selected Yes
											System.out.println("Yes");
											classService.deleteClass(oneclass.getId());
											System.out.println("ClassesForm.onSubmit deleted classId = " + oneclass.getId());
											
											// Refresh the page
											setResponsePage(ClassesListPage.class);
										}
										else
										{
											// Selected No
											System.out.println("No");
										}
									}
								}
								catch (Exception e)
								{
									// Something happened
									logger.error("Error when deleting class " + oneclass.getId() + ": " + e.toString());
									error("An unexpected error occurred. If the problem continues, please contact your system administrator");
								}
							}
						};
						classitem.add(deleteButton);

						Button editTeachersButton = new Button(editTeachersId)
						{
							private static final long serialVersionUID = 1L;
							
							public void onSubmit()
							{
								setResponsePage(new ClassUsersPage(institutionId, oneclass.getId(), Permission.TEACHER));
							}
						};
						classitem.add(editTeachersButton);

						Button editTAsButton = new Button(editTAsId)
						{
							private static final long serialVersionUID = 1L;
							
							public void onSubmit()
							{
								setResponsePage(new ClassUsersPage(institutionId, oneclass.getId(), Permission.TA));
							}
						};
						classitem.add(editTAsButton);

						Button editStudentsButton = new Button(editStudentsId)
						{
							private static final long serialVersionUID = 1L;
							
							public void onSubmit()
							{
								setResponsePage(new ClassUsersPage(institutionId, oneclass.getId(), Permission.STUDENT));
							}
						};
						classitem.add(editStudentsButton);
					}
				}
			};
			add(listview);
		}

		/**
		 * Get the full names of the users with one name per row
		 * @param userIdList
		 * @return
		 */
		private String getUserNames(List<Integer> userIdList)
		{
			StringBuilder people = new StringBuilder();
			for (int index = 0; index < userIdList.size(); index++)
			{
				EntityUserModel teacher = (EntityUserModel) userService.findUserById(userIdList.get(index));
				if (people.length() > 0)
				{
					people.append("\n");
				}
				people.append(teacher.getFirstName());
				people.append(' ');
				people.append(teacher.getLastName());
			}
			return people.toString();
		}
	}
}
