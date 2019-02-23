package com.linguaclassica.teacher;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;

import com.googlecode.wicket.jquery.ui.form.datepicker.DatePicker;
import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.access.ClassChoice;
import com.linguaclassica.access.Selections;
import com.linguaclassica.entity.EntityClassModel;
import com.linguaclassica.entity.EntityExerCategoryModel;
import com.linguaclassica.entity.EntityExerciseModel;
import com.linguaclassica.model.AssignmentClassesModel;
import com.linguaclassica.model.AssignmentModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.service.AssignmentService;
import com.linguaclassica.service.ClassService;
import com.linguaclassica.service.ExerciseService;
import com.linguaclassica.service.TimeService;


/**
 * The page to which users are directed after successfully logging in.
 * From this page, users may proceed direct to the Parser or to the
 * root log-in page
 * @author Martha Gongora
 * @Copyright("2015 Lingua Classica")
 */
public class CreateAssignmentPage extends TeacherBasePage {
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	private AssignmentService assignmentService;

	@SpringBean
	private ExerciseService exerciseService;
	
	@SpringBean
	private ClassService classService;
	
	@SpringBean
	TimeService timeService;

	@SpringBean
	private ModelFactory modelFactory;
		
	AssignmentModel assignmentModel;
	
	AssignmentClassesModel assignmentClassesModel;

	
	ClassChoice<String> checkboxes;
	
	DatePicker startdatepicker;
	
	DatePicker enddatepicker;
	
	DateTextField startDateField;
	
	Date startdate = new Date();
	
	String starttext = "00:00";

	String endtime = "00:00";
	
	IModel<String> collabRG = new Model<String>();	
	
	int exerciseId = 0;

	List<EntityExerciseModel> exerlistAll; 

	String var22;

	String exerciseName;

	RadioGroup<String> collaborativeGroup;
	
	// Active user
	private final UserModel userModel = AlphPlusSession.get().getUser(getRequest());

	
	public CreateAssignmentPage() {
		super();
		
		collabRG.setObject("No");
		
		Form<Object> panelform = new Form<Object>("createassignform");
		
		assignmentModel = modelFactory.getNewAssignmentModel();
		assignmentModel.setType("NORMAL");
		
		TextField<String> assignmenttext = new TextField<String>("assignmenttext", new PropertyModel<String>(assignmentModel, "assignmentname"));
		assignmenttext.setRequired(true);
		assignmenttext.add(StringValidator.maximumLength(65));
		panelform.add(assignmenttext);
		
		/*
		 * gets list of all classes for userId
		 * and then uses this to compare with list of selected class ids to generate
		 * list of EntityClassModels for selected classes
		 */
		checkboxes = new ClassChoice<String>("classcheckboxes", userModel.getId(), ClassChoice.CREATE,0); 
		checkboxes.setRequired(true);
		panelform.add(checkboxes);

		TextField<String> starttimetf = new TextField<String>("starttimefield", new PropertyModel<String>(this, "starttext"));
		panelform.add(starttimetf);
	
		TextField<String> endtimetf = new TextField<String>("endtimefield", new PropertyModel<String>(this, "endtime"));
		panelform.add(endtimetf);

		//Choices in radio button 
		collaborativeGroup = new RadioGroup<String>("CollaborativeGroup", collabRG);
		collaborativeGroup.add(new Radio<String>("CollaborativeYes", new Model<String>("Yes")));   
		collaborativeGroup.add(new Radio<String>("CollaborativeNo", new Model<String>("No")));
		collaborativeGroup.setRequired(true);
		panelform.add(collaborativeGroup);
		
		final Label exeridtext = new Label("exeridtext", new PropertyModel<String>(this,"exerciseName")); 
		exeridtext.setOutputMarkupId(true);
		panelform.add(exeridtext);
		
		/*
		 * Populate exercises 
		 */
		List<EntityExerCategoryModel> categoriesList;
		categoriesList = exerciseService.getListOfExerCategoriesByType("PRACTICE");
		categoriesList.addAll(exerciseService.getListOfPrivateExerCategoriesCreatedByUser(userModel.getId()));
		categoriesList.addAll(exerciseService.getListOfSharedExerCategoriesForAUser(AlphPlusSession.get().getCurrentInstitution(), 
				userModel.getId(), AlphPlusSession.get().getCurrentPermission()));			
		
		exerlistAll = exerciseService.getListOfExercises();
		int exerlistlen = exerlistAll.size();
		
		List<String> variable1list = new ArrayList<String>();
		
		for (int k = 0; k < exerlistlen; k++) {
			variable1list.add(exerlistAll.get(k).getVar1string());
		}
		
		panelform.add(new ListView<EntityExerCategoryModel>("categoriesList",categoriesList) {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void populateItem(final ListItem<EntityExerCategoryModel> catitem) {
				
				final EntityExerCategoryModel categoryModel = catitem.getModelObject();
				catitem.add(new Label("exercatlab",categoryModel.getExerciseName()));
				
				String catexerurl = categoryModel.getUrlbase();
				System.out.println("catexerurl = " + catexerurl);
				
				if (!catexerurl.equals("None")) {
					final int catid = categoryModel.getId();
					List<EntityExerciseModel> subexerModelList = new ArrayList<EntityExerciseModel>();
					subexerModelList = exerciseService.getListOfExercisesByCategory(catid);
					System.out.println("ExercisesAvailablePanel, line 79: subexerModelList.size() = " +
							subexerModelList.size());
					ArrayList<EntityExerciseModel> exerList = new ArrayList<EntityExerciseModel>();
					ArrayList<String> var1strList = new ArrayList<String>();
					int listlen = subexerModelList.size();
					for (int i = 0; i < listlen; i++) {
						exerList.add(subexerModelList.get(i));
						String var2 = subexerModelList.get(i).getVar2string();
						if (var2 != null) {
							var1strList.add(subexerModelList.get(i).getVar1string() + ": " + var2);
						}
						else {
							var1strList.add(subexerModelList.get(i).getVar1string());
						}
					}
				
				final Map<String, List<String>> varsMap = new HashMap<String, List<String>>();
				
					final Selections selections = new Selections();
					
					List<String> var1list = new ArrayList<String>();
					for (int m = 0; m < listlen; m++) {
						String var1str = subexerModelList.get(m).getVar1string();
						var1list.add(var1str);
					}
					int var1listlen = var1list.size();
	
					
					for (int n = 0; n < var1listlen; n++) {
						String var1 = var1list.get(n);
						if (var1 != null) {
						List<String> var2list = new ArrayList<String>();
						for (int j = 0; j < listlen; j++) {
							String var2 = "None";
							if (subexerModelList.get(j).getVar1string().equals(var1)) {
								var2 = subexerModelList.get(j).getVar2string();
								if (var2.equals("") || var2 == null) {
									var2 = "None";
								}
								var2list.add(var2);
							}
							varsMap.put(var1, var2list);
						}
					}
					}

			    IModel<List<? extends String>> var1Choices = new AbstractReadOnlyModel<List<? extends String>>()
			    {
					private static final long serialVersionUID = 1L;

					@Override
			        public List<String> getObject()
			        {
			            return new ArrayList<String>(varsMap.keySet());
			        }
			    };
				
			    
			    final DropDownChoice<String> var1DDC = new DropDownChoice<String>("var1DDC",
			            new PropertyModel<String>(selections, "selectedVar1"), var1Choices);
			    



			    IModel<List<? extends String>> var2Choices = new AbstractReadOnlyModel<List<? extends String>>()
			    {
					private static final long serialVersionUID = 1L;

					@Override
			        public List<String> getObject()
			        {
			        	String selectedVar1 = var1DDC.getModelObject();
			            List<String> var2s = varsMap.get(selectedVar1);
			            if (var2s == null)
			            {
			                var2s = Collections.emptyList();
			            }
			            return var2s;
			        }

			    };

				final DropDownChoice<String> var2DDC = new DropDownChoice<String>("var2DDC",
		        		 new PropertyModel<String>(selections,"selectedVar2"),var2Choices);
		        
		        var2DDC.setOutputMarkupId(true);
	        	

		        catitem.add(var1DDC);
		        catitem.add(var2DDC);
		        

			    var1DDC.add(new AjaxFormComponentUpdatingBehavior("onchange")
		        {
					private static final long serialVersionUID = 1L;

					@Override
		            protected void onUpdate(AjaxRequestTarget target)
		            {
		                target.add(var2DDC);
			/*			String var1str = selections.getSelectedVar1();
						String var2str = selections.getSelectedVar2();*/
		            }
		        });
			    
			    var2DDC.add(new  AjaxFormComponentUpdatingBehavior("onchange")
			    {
					private static final long serialVersionUID = 1L;

					@Override
		            protected void onUpdate(AjaxRequestTarget target)
		            {
						System.out.println("var2DDC.getModelObject() = " + var2DDC.getModelObject());
						String var1str = selections.getSelectedVar1();
						String var2str = var2DDC.getModelObject();
						System.out.println("var1str, var2str = " + var1str + ", " + var2str);
		            }
		           
		        });
		        
		        
		     System.out.println("subexerModelList.get(0).getVar1string() = " + subexerModelList.get(0).getVar1string());
		        if (subexerModelList.get(0).getVar1string() != null &&
		        		!subexerModelList.get(0).getVar1string().equals("None") &&
		        		!subexerModelList.get(0).getVar1string().equals("")) {
		        	var1DDC.setVisible(true);
		       	}
		        else {
		        	var1DDC.setVisible(false);		        	
		        }
		        
		        
		     System.out.println("subexerModelList.get(0).getVar2string() = " + subexerModelList.get(0).getVar2string());
		        if (subexerModelList.get(0).getVar2string() != null &&
		        		!subexerModelList.get(0).getVar2string().equals("None") &&
		        		!subexerModelList.get(0).getVar2string().equals("")) {
		        	var2DDC.setVisible(true);
		       	}
		        else {
		        	var2DDC.setVisible(false);		        	
		        }
                 
		        Button selectExer = new Button("selectExer");
		        selectExer.setDefaultFormProcessing(false);
		        selectExer.add(new AjaxEventBehavior("onclick") {
					private static final long serialVersionUID = 1L;

					@Override
					protected void onEvent(AjaxRequestTarget target) {
 						System.out.println("got to onClick");
 						

						
 					 	exerciseId= 0;
 						if(selections.getSelectedVar1() != null) { 							
 							if (selections.getSelectedVar2() == null ) {
 	 							var22 = "";
 	 						}
 	 						else {
 	 							var22 = selections.getSelectedVar2();
 	 						}
 	 							
 							
 	 						for (int i = 0; i< exerlistAll.size(); i++) {
 	 							if (exerlistAll.get(i).getVar1string() !=null &&
 	 								exerlistAll.get(i).getVar2string() !=null) {

 	 								System.out.println("CreateAssignmentPage, line 386:  "
 	 										+ "selections.getSelectedVar1() = " + selections.getSelectedVar1());
 	 								if (exerlistAll.get(i).getVar1string().equals(selections.getSelectedVar1()) &&
 	 	 	 							exerlistAll.get(i).getVar2string().equals(var22)) {
 	 	 	 	 						exerciseId = exerlistAll.get(i).getId(); 
 	 	 	 	 						i = exerlistAll.size(); 
 	 	 	 							}
 	 							}
 	 		                 }	
 						}
 						else {
 							System.out.println("CreateAssignment, line 397:  selections.getSelectedVar1() is NULL");
 							for (int i = 0; i< exerlistAll.size(); i++) {
 								if (exerlistAll.get(i).getExercisecategory() == categoryModel.getId()) {
 									exerciseId = exerlistAll.get(i).getId();
 								}
 							}
 						}
 						exerciseName = categoryModel.getExerciseName();
 						target.add(exeridtext);
 					}
					
		        });
	            catitem.add(selectExer);
			}//////////////////closes if categoryUrl != null				
		}   ///PopulateItem

		});  ///ListView


		
		panelform.add(new Button("CreateAssignment") {
			
			private static final long serialVersionUID = 1L;
			
			/*
			 * onSubmit used for 'create Assignments' function
			 */
			@Override
			public void onSubmit() {

				System.out.println("CreateAssignmentPage.onSubmit, classesSelected = " + 
						 checkboxes.getSelection());
				
				// Validate selected inputs 
				System.out.println("CreateAssignmentPage.onSubmit: exerciseId = " + 
						exerciseId);
				if (exerciseId == 0) {
					info("Please select variable and then click 'Select exercise'.");
				}	
				else {
					try {
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						Date parsedDate = dateFormat.parse(starttext); 
						Timestamp starttime = new Timestamp(parsedDate.getTime()); 
						assignmentModel.setStartDate(starttime);
						
						String timezone = starttext.substring(17);
						assignmentModel.setTimeZone(timezone);
						
						parsedDate = dateFormat.parse(endtime); 
						Timestamp duetime = new Timestamp(parsedDate.getTime());
						
						ZonedDateTime startzdt = timeService.getTimeByTimeZone(starttime, timezone);
						ZonedDateTime duezdt = timeService.getTimeByTimeZone(duetime, timezone);

						ZonedDateTime nowtz = timeService.getOtherTimeZoneNow(timezone);
						
						if(startzdt.isAfter(duezdt))

						//if (duetime.before(starttime))
						if(duezdt.isAfter(startzdt))

						//if (duetime.before(starttime))
						if(startzdt.isAfter(duezdt))

						{
							error("Due date cannot be before Start date.");
							return;
						}
						
						if(nowtz.isAfter(startzdt)) {
							System.out.println("start time before now");
							Session.get().error("The Start date can not be set in the past.");
							return;
						}
						
						assignmentModel.setDueDate(duetime);
                        
						assignmentModel.setTeacherId(userModel.getId());
						
						if (collabRG.getObject().equals("Yes")) {
							assignmentModel.setType("COLLABORATIVE");
						}
						assignmentModel.setExerciseId(exerciseId);
						
						assignmentService.createAssignment(assignmentModel);
					
						/*
						 * Create selected classes in AssignmenClasses
						 */
						List<EntityClassModel> classesSelected = checkboxes.getSelection();
						boolean createAll = false;
						for (int i = 0; i < classesSelected.size(); i++) {
							if (classesSelected.get(i).equals("All")) {
		                       	createAll = true;			
								i = classesSelected.size() + 1;	
							}
						}
						List<EntityClassModel> classlist = checkboxes.getSelection();			
						if (createAll) {
							// The assignment is for all classes
							for (int j = 0; j < classlist.size(); j++) {
								assignmentClassesModel = modelFactory.getNewAssignmentClassesModel(); 
								assignmentClassesModel.setAssignmentId(assignmentModel.getId());
								assignmentClassesModel.setClassId(classlist.get(j).getId());
								assignmentService.createAssignmentClasses(assignmentClassesModel);
							}						
						}
						else {
							// The assignment is for selected classes
								
							for(int i=0; i< classesSelected.size(); i++) {
								for (int j= 0; j< classlist.size(); j++) {
									if (classlist.get(j).getClassname().equals(classesSelected.get(i))) {
										assignmentClassesModel = modelFactory.getNewAssignmentClassesModel(); 
										assignmentClassesModel.setAssignmentId(assignmentModel.getId());
										assignmentClassesModel.setClassId(classlist.get(j).getId());
										assignmentService.createAssignmentClasses(assignmentClassesModel);
								        j = classlist.size() + 1;	
									}
								}
							}	
						}

						//setResponsePage(TeacherTALandingPage.class);
						
					} catch (ParseException e) {
						// Auto-generated catch block
						error("Parsing error: " + e.getMessage());
						e.printStackTrace();
					}
				}
			}
		});	
		
		
		add(panelform);
	}	
	@SuppressWarnings("unused")
	private void info(Component component, Form<?> form)
	{
		this.info(component.getMarkupId() + " has been clicked");
		this.info("The model object is: " + form.getModelObject());	
	}
}	


