package com.linguaclassica.teacher;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.googlecode.wicket.jquery.ui.form.button.ConfirmButton;
import com.linguaclassica.entity.EntityClassModel;
import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.access.ClassChoice;
import com.linguaclassica.entity.EntityAssignmentModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.service.AssignmentService;
import com.linguaclassica.service.ClassService;
import com.linguaclassica.service.UserService;
//import com.linguaclassica.teacher.TeacherNotificationsPage.AnnouncementForm;

/**
 * The page where teachers can choose to create, read, update
 * and delete assignments from their selected listed classes.
 * @author Kristina Wirtanen
 * @Copyright("2015 Lingua Classica")
 */

public class TeacherAssignmentsPage extends TeacherBasePage {
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	private AssignmentService assignmentService;
	
	@SpringBean
	private UserService userService;
	
	@SpringBean
	private ClassService classService;
	
	ModelFactory modelFactory;
	
	List<EntityClassModel> classSelection;
	
	List<String> classnameList;
	
	List<String> classesList;
	
	ArrayList<String> classesSelected;
	
	ArrayList<String> selClassesSelected;
	
	List<Integer> classIdList;
	
	List<EntityClassModel> classList;	
	
	String classListStr;
	
	private final UserModel userModel = AlphPlusSession.get().getUser(getRequest());
	
	public TeacherAssignmentsPage()
	{
		super();

		classSelection = AlphPlusSession.get().getSelectedClasses();
		
		AnnouncementForm annform = new AnnouncementForm();
		add(annform);
    }

	class AnnouncementForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;

		ClassChoice<String> checkboxes;
		
		AnnouncementForm()
		{
		
		super("teacherAssignments");
			
		checkboxes = new ClassChoice<String>("teacherAssignments", userModel.getId(), ClassChoice.DISPLAY,0);
		add(checkboxes);
			
		System.out.println("TeacherAssignmentsPage: beginning of annocuncement" );		
		
		// Creates string of selected classes
		// String to be displayed at the top of the panel
		if(classSelection!=null && classSelection.size()!=0){
			classListStr = classSelection.get(0).getClassname();
			for(int i = 1; i < classSelection.size(); i++){
				classListStr+=", " + classSelection.get(i).getClassname();
			}
		}else{
			classListStr = "No classes selected";
		}
		
		// Adds the names of the selected classes to the panel
		String labelstr = "Classes selected:  " + classListStr; 
		add(new Label("classesSelected",labelstr));  

		/*
		 * Adds 'create assignment' button to page
		 * Currently the user is directed to the CreateNotificationPage
		 * The user will need to be directed to the CreateAssignmentPage
		 * once implemented.
		 */
		/*add(new Link<Object>("createassignbutton") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new CreateAssignmentPage());
			}
		});*/
		
		// Adds each selected class with its corresponding assignments to page
		add(new ListView<EntityClassModel>("classnameList", classSelection) {
			private static final long serialVersionUID = 1L;

			protected void populateItem(ListItem<EntityClassModel> item)
			{
				EntityClassModel classItem = item.getModelObject();
				List<Integer> assignIdList = new ArrayList<Integer>();
				List<EntityAssignmentModel> assignList = new ArrayList<EntityAssignmentModel>();
				
				item.add(new Label("classnameLab",classItem.getClassname()));
						 
				// Retrieves all assignment ids for selected class
				assignIdList = assignmentService.getListOfAssignmentIdsByClassId(item.getModelObject().getId());
				
				// Iterates through all assignment ids and adds the assignment objects to an ArrayList
				if (!assignIdList.isEmpty()) {
					for(int i =0; i< assignIdList.size(); i++){
						assignList.add(assignmentService.findAssignmentById(assignIdList.get(i)));
					}
					
					System.out.println("Number of assignments for class id " + item.getModelObject().getId() + ": " +assignList.size());
					
				}

				// Adds all of the assignments to the page for the selected class
				item.add(new ListView<EntityAssignmentModel>("assignList",assignList) {
					private static final long serialVersionUID = 1L;

					
					Label nodeletelabel;
					
					@Override
					protected void populateItem(final ListItem<EntityAssignmentModel> subitem) {
						nodeletelabel = new Label("nodeleteLab","Already started.");
						nodeletelabel.setVisible(false);
						
						ConfirmButton deletebutton = new ConfirmButton("deletebutton","Delete",
								"Please Confirm Deletion", "The selected assignment will be permanently deleted") {
							private static final long serialVersionUID = 1L;

								@Override
								public void onError() {
									this.error("Validation failed!");
								}

							@Override
							public void onSubmit() {
								System.out.println("delete assignment clicked");
								assignmentService.deleteAssignmentById(subitem.getModelObject().getId());
							}																														
							
																																					
						};	
						
						subitem.add(deletebutton);
						
						Timestamp now = new Timestamp(System.currentTimeMillis());
						if(subitem.getModelObject().getStartDate().before(now)) {
							nodeletelabel.setVisible(true);
							deletebutton.setVisible(false);
							
						}
						subitem.add(new Label("assignLab",subitem.getModelObject().getAssignmentName()));
						subitem.add(nodeletelabel);
						subitem.add(new Label("createddateLab",subitem.getModelObject().getCreatedDate()));
						subitem.add(new Label("startdateLab",subitem.getModelObject().getStartDate()));
						subitem.add(new Label("duedateLab",subitem.getModelObject().getDueDate()));
						subitem.add(new Label("timezoneLab",subitem.getModelObject().getTimeZone()));
						subitem.add(new Label("statusLab",subitem.getModelObject().getStatus()));
						
						/*
						 * Adds 'edit' button to page per assignment
						 * Currently the user is directed to the CreateNotificationPage
						 * The user will need to be directed to the EditAssignmentPage
						 * once implemented.
						 */
						subitem.add(new Link<Object> ("editbutton") {
							private static final long serialVersionUID = 1L;

							@Override
							public void onClick() {
								System.out.println("Edit assignment clicked.");
								PageParameters pageParameters = new PageParameters();
								pageParameters.add("AssignmentId",subitem.getModelObject().getId());
								//setResponsePage(EditAssignmentPage.class, pageParameters);
							}
						});
						/*jw2017-04-10
						subitem.add(new Link ("deletebutton") {
							private static final long serialVersionUID = 1L;

							@Override
							public void onClick() {
								System.out.println("Delete assignment clicked.");
								PageParameters pageParameters = new PageParameters();
								pageParameters.add("AssignmentId",subitem.getModelObject().getId());
								Timestamp creationDate = subitem.getModelObject().getCreatedDate();
								System.out.println("CreationDateDelete: "+creationDate);
								setResponsePage(EditAssignmentPage.class, pageParameters);
							}
						});
						endjw2017-04-10*/
						}
					});
				}
			});
		}
	}
}
