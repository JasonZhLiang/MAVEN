package com.linguaclassica.teacher;

import org.apache.wicket.markup.html.basic.Label;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.http.handler.RedirectRequestHandler;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.model.AssignmentModel;
import com.linguaclassica.model.ExerCategoryModel;
import com.linguaclassica.model.ExerciseModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.access.Selections;
import com.linguaclassica.entity.EntityExerCategoryModel;
import com.linguaclassica.entity.EntityExerciseModel;
import com.linguaclassica.exercises.CreateExercisePage;
import com.linguaclassica.service.AssignmentFactService;
import com.linguaclassica.service.AssignmentService;
import com.linguaclassica.service.ExerciseService;
import com.linguaclassica.service.ResultsService;

public class TeacherExercisesPage extends TeacherBasePage {
	private static final long serialVersionUID = 1L;

		@SpringBean
		private AssignmentFactService assignmentfactService;
		
		@SpringBean
		private AssignmentService assignmentService;

		@SpringBean
		private ExerciseService exerciseService;
		
		@SpringBean
		ResultsService resultsService;
		
		@SpringBean
		private ModelFactory modelFactory;
		
		UserModel userModel = AlphPlusSession.get().getUser(getRequest());
		
		String selectedVar1;
		String selectedVar2; 
		int var1listlen = 0;
		int var2listlen = 0;
		
		public TeacherExercisesPage() {
			super();
			buildForm();
	    }
		
		class PracticeExercisesForm extends Form<Object> {
			private static final long serialVersionUID = 1L;


			public PracticeExercisesForm(String id) {
				super(id);
			}
		}

		private void buildForm() {
			System.out.println("Exercises Available Panel: beginning of buildform");
			
			Form<?> form = new PracticeExercisesForm("exeravailform");
			
			/*
			 * Section below creates a list of available exercises
			 */	
			List<EntityExerCategoryModel> practiceList;
			List<EntityExerCategoryModel> privateList;
			List<EntityExerCategoryModel> sharedList;

			practiceList = exerciseService.getListOfExerCategoriesByType("PRACTICE");
			privateList = exerciseService.getListOfPrivateExerCategoriesCreatedByUser(userModel.getId());
			sharedList = exerciseService.getListOfSharedExerCategoriesForAUser(AlphPlusSession.get().getCurrentInstitution(), 
					userModel.getId(), AlphPlusSession.get().getCurrentPermission());
			
			List<EntityExerciseModel> exerlist = exerciseService.getListOfExercises();
			int exerlistlen = exerlist.size();
			
			List<String> variable1list = new ArrayList<String>();
			
			for (int k = 0; k < exerlistlen; k++) {
				variable1list.add(exerlist.get(k).getVar1string());
			}
			
			form.add(new ListView<EntityExerCategoryModel>("practiceList",practiceList) {
				private static final long serialVersionUID = 1L;			
				
				@Override
				protected void populateItem(final ListItem<EntityExerCategoryModel> catitem) {
					
					final EntityExerCategoryModel categoryModel = catitem.getModelObject();
					catitem.add(new Label("exercatlab",categoryModel.getExerciseName()));
					
					String catexerurl = categoryModel.getUrlbase();
					System.out.println("catexerurl = " + catexerurl);
					
					//create a new Selections object to populate the item
					final Selections selections = new Selections();			
					
					//create a list of exercises for this exercisecategory
					final int catid = categoryModel.getId();
					List<EntityExerciseModel> subexerModelList = new ArrayList<EntityExerciseModel>();
					subexerModelList = exerciseService.getListOfExercisesByCategory(catid);	
					
					List<String> exervar1list = new ArrayList<String>();
					String var1prim = subexerModelList.get(0).getVar1string();
					exervar1list.add(var1prim);
					var1listlen = exervar1list.size();
					if(var1listlen > 0){
						selections.setVariables(1);
					}
					
					List<String> exervar2list = new ArrayList<String>();
					String var2prim = subexerModelList.get(0).getVar2string();
					exervar2list.add(var2prim);
					if (exervar2list.get(0) != null && !exervar2list.get(0).equals("")) {
					}
					
					int listlen = subexerModelList.size();

					//'LinkedHashMap' is needed to preserve order in which var2s were inserted into map
					final Map<String, List<String>> varsMap = new LinkedHashMap<String, List<String>>();
						
						//create a list of var1strs for all exercises in the exercisecategory
						List<String> var1list = new ArrayList<String>();
						for (int m = 0; m < listlen; m++) {
							String var1str = subexerModelList.get(m).getVar1string();
							
							if (var1str != null) {
								var1list.add(var1str);
							}
							else {
								var1list.add("None");
							}
							System.out.println("var1list.get("+m+") = " + var1list.get(m));
						}
						int var1listlen = var1list.size();
		
						//create a list of var2strs for each var1str and put both in the varsMap
						for (int n = 0; n < var1listlen; n++) {
							String var1 = var1list.get(n);
							List<String> var2list = new ArrayList<String>();
							for (int j = 0; j < listlen; j++) {
								String var2 = "None";
								if (!var1.equals("None") && var1.equals(subexerModelList.get(j).getVar1string())) {
									var2 = subexerModelList.get(j).getVar2string();
									
									if (var2 != null) {
										var2list.add(var2);
									}
									else {
										var2list.add("None");
									}
								}
								varsMap.put(var1, var2list);
							}
							var2listlen = var2list.size();
							if(var2listlen > 1){
								selections.setVariables(2);
							}
						}
						
					//create the IModel list for var1s to be used in var1DDC
				    IModel<List<? extends String>> var1Choices = new AbstractReadOnlyModel<List<? extends String>>()
				    {
						private static final long serialVersionUID = 1L;

						@Override
				        public List<String> getObject()
				        {
							List<String> var1sList = new ArrayList<String>(varsMap.keySet());						
				            return var1sList;
				        }
				    };
					
				    
				    final DropDownChoice<String> var1DDC = new DropDownChoice<String>("var1DDC",
				            new PropertyModel<String>(selections, "selectedVar1"), var1Choices) {
								private static final long serialVersionUID = 1L;

						@Override
				        protected String getNullKeyDisplayValue() {
				            return "Choose one";
				        }
				    };
				    


				    //create a list of var2 lists to be used in var2DDC
				    IModel<List<? extends String>> var2Choices = new AbstractReadOnlyModel<List<? extends String>>()
				    {
						private static final long serialVersionUID = 1L;

						@Override
				        public List<String> getObject()
				        {
				        	selectedVar1 = var1DDC.getModelObject();
				            List<String> var2s = varsMap.get(selectedVar1);
				            if (var2s == null)
				            {
				                var2s = Collections.emptyList();
				            }
				            return var2s;
				        }

				    };

					final DropDownChoice<String> var2DDC = new DropDownChoice<String>("var2DDC",
			        		 new PropertyModel<String>(selections,"selectedVar2"),var2Choices) {
								private static final long serialVersionUID = 1L;

						@Override
				        protected String getNullKeyDisplayValue() {
				            return "Choose one";
				        }
				    };
			        
			        var2DDC.setOutputMarkupId(true);
				    
				    //set visibility of dropdowns by number of variables, initially false
			        var1DDC.setVisible(false);
			        var2DDC.setVisible(false);

				    if (var1listlen > 1) {
			        	var1DDC.setVisible(true);
			       	}
				    if (var2listlen > 1) {
			        	var2DDC.setVisible(true);
			       	}

			        catitem.add(var1DDC);
			        catitem.add(var2DDC);		        

				    var1DDC.add(new AjaxFormComponentUpdatingBehavior("onchange")
			        {
						private static final long serialVersionUID = 1L;

						@Override
			            protected void onUpdate(AjaxRequestTarget target)
			            {
			                target.add(var2DDC);					
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

			        catitem.add(new Link<Object>("exerlink") {
						private static final long serialVersionUID = 1L;

						@Override
						public void onClick() {
							System.out.println("got to onClick");
							System.out.println("categoryModel.getUrlbase() = " + categoryModel.getUrlbase());
							System.out.println("selections.getExerciseurl() = " + selections.getExerciseurl());
							System.out.println("selections.getSelectedVar1() = " + selections.getSelectedVar1());
							System.out.println("selections.getSelectedVar2() = " + selections.getSelectedVar2());

							String var1str = var1DDC.getModelObject();
							String var2str = var2DDC.getModelObject();
							
							int variablesIn = selections.getVariables();
							
							ExerciseModel exerModel = 
									exerciseService.findExerciseByVariableStrings(categoryModel.getId(), var1str, var2str);
							
							String exerciseurl = categoryModel.getUrlbase() + "?exerciseid="+exerModel.getId(); 
							if (variablesIn == 2) {
								exerciseurl = exerciseurl+"&variable1="+var1str+"&variable2="+var2str;
							}
							else if (variablesIn == 1) {
								exerciseurl = exerciseurl +"&variable1="+var1str+"&variable2=null";
							}
							System.out.println("PreparedExercises, line 325:  exerciseurl = " + 
									exerciseurl);										
							selections.setExerciseurl(exerciseurl);
							
							
							//create assignment for practice exercise
							AssignmentModel assignmentModel = modelFactory.getNewAssignmentModel();
							assignmentModel.setAssignmentName("Practice " + categoryModel.getExerciseName());
							assignmentModel.setType("PRACTICE");

							int exerciseId = exerModel.getId();
							assignmentModel.setExerciseId(exerciseId);
							Calendar cal = Calendar.getInstance();
							Timestamp now = 
									new Timestamp(cal.getTime().getTime());						
							assignmentModel.setStartDate(now);
							cal.add(Calendar.MONTH, 1);
							//set due date temporarily one month in future
							//when exercise is submitted, this should be changed to now
							Timestamp duedate = new Timestamp(cal.getTime().getTime());
							assignmentModel.setDueDate(duedate);
							TimeZone tz = Calendar.getInstance().getTimeZone();
							String timezone = tz.getDisplayName(false, TimeZone.SHORT);
							System.out.println("timezone = " + timezone);
							assignmentModel.setTimeZone(timezone);
							assignmentService.createAssignment(assignmentModel);
							
							
							if (selections.getExerciseurl() != null) {
								String redirectUrl= "/"+ selections.getExerciseurl()+
										"&assignmenttype=PRACTICE&assignmentid=0&classid=0";
								System.out.println("PracticeExercises, line 338:  redirectUrl = " + redirectUrl);
				                getRequestCycle().scheduleRequestHandlerAfterCurrent(new RedirectRequestHandler(redirectUrl));
							}
							else {
								getSession().error("Necessary variable have not been selected.");
							}
						}		        	
			        });
				}
			}); 
			
			form.add(new ListView<EntityExerCategoryModel>("privateList",privateList) {
				private static final long serialVersionUID = 1L;			
				
				@Override
				protected void populateItem(final ListItem<EntityExerCategoryModel> catitem) {
					
					final EntityExerCategoryModel categoryModel = catitem.getModelObject();
					catitem.add(new Label("exercatlab",categoryModel.getExerciseName()));
					
					//create a new Selections object to populate the item
					final Selections selections = new Selections();			
					
					//create a list of exercises for this exercisecategory
					final int catid = categoryModel.getId();
					List<EntityExerciseModel> subexerModelList = new ArrayList<EntityExerciseModel>();
					subexerModelList = exerciseService.getListOfExercisesByCategory(catid);	
					
					List<String> exervar1list = new ArrayList<String>();
					String var1prim = subexerModelList.get(0).getVar1string();
					exervar1list.add(var1prim);
					var1listlen = exervar1list.size();
					if(var1listlen > 1){
						selections.setVariables(1);
					}

					int listlen = subexerModelList.size();

					//'LinkedHashMap' is needed to preserve order in which var2s were inserted into map
					final Map<String, List<String>> varsMap = new LinkedHashMap<String, List<String>>();
						
						//create a list of var1strs for all exercises in the exercisecategory
						List<String> var1list = new ArrayList<String>();
						for (int m = 0; m < listlen; m++) {
							String var1str = subexerModelList.get(m).getVar1string();
							
							if (var1str != null) {
								var1list.add(var1str);
							}
							else {
								var1list.add("None");
							}
							System.out.println("var1list.get("+m+") = " + var1list.get(m));
						}
						int var1listlen = var1list.size();
		
						//create a list of var2strs for each var1str and put both in the varsMap
						for (int n = 0; n < var1listlen; n++) {
							String var1 = var1list.get(n);
							List<String> var2list = new ArrayList<String>();
							for (int j = 0; j < listlen; j++) {
								String var2 = "None";
								if (!var1.equals("None") && var1.equals(subexerModelList.get(j).getVar1string())) {
									var2 = subexerModelList.get(j).getVar2string();
									
									if (var2 != null) {
										var2list.add(var2);
									}
									else {
										var2list.add("None");
									}
								}
								varsMap.put(var1, var2list);
							}
							var2listlen = var2list.size();
							selections.setVariables(2);
						}
						
					//create the IModel list for var1s to be used in var1DDC
				    IModel<List<? extends String>> var1Choices = new AbstractReadOnlyModel<List<? extends String>>()
				    {
						private static final long serialVersionUID = 1L;

						@Override
				        public List<String> getObject()
				        {
							List<String> var1sList = new ArrayList<String>(varsMap.keySet());						
				            return var1sList;
				        }
				    };
					
				    
				    final DropDownChoice<String> var1DDC = new DropDownChoice<String>("var1DDC",
				            new PropertyModel<String>(selections, "selectedVar1"), var1Choices) {
								private static final long serialVersionUID = 1L;

						@Override
				        protected String getNullKeyDisplayValue() {
				            return "Choose one";
				        }
				    };
				    


				    //create a list of var2 lists to be used in var2DDC
				    IModel<List<? extends String>> var2Choices = new AbstractReadOnlyModel<List<? extends String>>()
				    {
						private static final long serialVersionUID = 1L;

						@Override
				        public List<String> getObject()
				        {
				        	selectedVar1 = var1DDC.getModelObject();
				            List<String> var2s = varsMap.get(selectedVar1);
				            if (var2s == null)
				            {
				                var2s = Collections.emptyList();
				            }
				            return var2s;
				        }

				    };

					final DropDownChoice<String> var2DDC = new DropDownChoice<String>("var2DDC",
			        		 new PropertyModel<String>(selections,"selectedVar2"),var2Choices) {
								private static final long serialVersionUID = 1L;

						@Override
				        protected String getNullKeyDisplayValue() {
				            return "Choose one";
				        }
				    };
			        
			        var2DDC.setOutputMarkupId(true);
				    

			        
			        var2DDC.setOutputMarkupId(true);
				    
				    //set visibility of dropdowns by number of variables, initially false
			        var1DDC.setVisible(false);
			        var2DDC.setVisible(false);
			        
			        if(var1listlen > 1) {
			        	var1DDC.setVisible(true);
			        }
			        if(var2listlen > 1) {
			        	var2DDC.setVisible(true);
			        }

			        catitem.add(var1DDC);
			        catitem.add(var2DDC);		        

				    var1DDC.add(new AjaxFormComponentUpdatingBehavior("onchange")
			        {
						private static final long serialVersionUID = 1L;

						@Override
			            protected void onUpdate(AjaxRequestTarget target)
			            {
			                target.add(var2DDC);					
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

			        catitem.add(new Link<Object>("exerlink") {
						private static final long serialVersionUID = 1L;

						@Override
						public void onClick() {
							System.out.println("got to onClick");
							System.out.println("categoryModel.getUrlbase() = " + categoryModel.getUrlbase());
							System.out.println("selections.getExerciseurl() = " + selections.getExerciseurl());
							System.out.println("selections.getSelectedVar1() = " + selections.getSelectedVar1());
							System.out.println("selections.getSelectedVar2() = " + selections.getSelectedVar2());

							String var1str = var1DDC.getModelObject();
							String var2str = var2DDC.getModelObject();
							
							int variablesIn = selections.getVariables();
							
							ExerciseModel exerModel = 
									exerciseService.findExerciseByVariableStrings(categoryModel.getId(), var1str, var2str);
							
							String exerciseurl = categoryModel.getUrlbase() + "?exerciseid="+exerModel.getId(); 
							if (variablesIn == 2) {
								exerciseurl = exerciseurl+"&variable1="+var1str+"&variable2="+var2str;
							}
							else if (variablesIn == 1) {
								exerciseurl = exerciseurl +"&variable1="+var1str+"&variable2=null";
							}
							System.out.println("PracticeExercises, line 257:  exerciseurl = " + 
									exerciseurl);										
							selections.setExerciseurl(exerciseurl);
							
							
							//create assignment for private exercise
							AssignmentModel assignmentModel = modelFactory.getNewAssignmentModel();
							assignmentModel.setAssignmentName("Practice " + categoryModel.getExerciseName());
							assignmentModel.setType("PRACTICE");

							int exerciseId = exerModel.getId();
							assignmentModel.setExerciseId(exerciseId);
							Calendar cal = Calendar.getInstance();
							Timestamp now = 
									new Timestamp(cal.getTime().getTime());						
							assignmentModel.setStartDate(now);
							cal.add(Calendar.MONTH, 1);
							//set due date temporarily one month in future
							//when exercise is submitted, this should be changed to now
							Timestamp duedate = new Timestamp(cal.getTime().getTime());
							assignmentModel.setDueDate(duedate);
							TimeZone tz = Calendar.getInstance().getTimeZone();
							String timezone = tz.getDisplayName(false, TimeZone.SHORT);
							System.out.println("timezone = " + timezone);
							assignmentModel.setTimeZone(timezone);
							assignmentService.createAssignment(assignmentModel);
							
							
							if (selections.getExerciseurl() != null) {
								String redirectUrl= "/"+ selections.getExerciseurl()+"&assignmenttype=PRIVATE&assignmentid=0";
								System.out.println("PracticeExercises, line 338:  redirectUrl = " + redirectUrl);
				                getRequestCycle().scheduleRequestHandlerAfterCurrent(new RedirectRequestHandler(redirectUrl));
							}
							else {
								getSession().error("Necessary variable have not been selected.");
							}
						}		        	
			        });
				}
			});
			
			form.add(new ListView<EntityExerCategoryModel>("sharedList",sharedList) {
				private static final long serialVersionUID = 1L;			
				
				@Override
				protected void populateItem(final ListItem<EntityExerCategoryModel> catitem) {
					
					final EntityExerCategoryModel categoryModel = catitem.getModelObject();
					catitem.add(new Label("exercatlab",categoryModel.getExerciseName()));
					
					String catexerurl = categoryModel.getUrlbase();
					System.out.println("catexerurl = " + catexerurl);
					
					//create a new Selections object to populate the item
					final Selections selections = new Selections();			
					
					//create a list of exercises for this exercisecategory
					final int catid = categoryModel.getId();
					List<EntityExerciseModel> subexerModelList = new ArrayList<EntityExerciseModel>();
					subexerModelList = exerciseService.getListOfExercisesByCategory(catid);	
					
					List<String> exervar1list = new ArrayList<String>();
					String var1prim = subexerModelList.get(0).getVar1string();
					exervar1list.add(var1prim);
					var1listlen = exervar1list.size();
					if(var1listlen > 1){
						selections.setVariables(1);
					}

					int listlen = subexerModelList.size();

					//'LinkedHashMap' is needed to preserve order in which var2s were inserted into map
					final Map<String, List<String>> varsMap = new LinkedHashMap<String, List<String>>();
						
						//create a list of var1strs for all exercises in the exercisecategory
						List<String> var1list = new ArrayList<String>();
						for (int m = 0; m < listlen; m++) {
							String var1str = subexerModelList.get(m).getVar1string();
							
							if (var1str != null) {
								var1list.add(var1str);
							}
							else {
								var1list.add("None");
							}
							System.out.println("var1list.get("+m+") = " + var1list.get(m));
						}
						int var1listlen = var1list.size();
		
						//create a list of var2strs for each var1str and put both in the varsMap
						for (int n = 0; n < var1listlen; n++) {
							String var1 = var1list.get(n);
							List<String> var2list = new ArrayList<String>();
							for (int j = 0; j < listlen; j++) {
								String var2 = "None";
								if (!var1.equals("None") && var1.equals(subexerModelList.get(j).getVar1string())) {
									var2 = subexerModelList.get(j).getVar2string();
									
									if (var2 != null) {
										var2list.add(var2);									
									}
									else {
										var2list.add("None");
									}
								}
								varsMap.put(var1, var2list);
							}
							var2listlen = var2list.size();
							if(var2listlen > 1){
								selections.setVariables(2);
							}
						}
						
					//create the IModel list for var1s to be used in var1DDC
				    IModel<List<? extends String>> var1Choices = new AbstractReadOnlyModel<List<? extends String>>()
				    {
						private static final long serialVersionUID = 1L;

						@Override
				        public List<String> getObject()
				        {
							List<String> var1sList = new ArrayList<String>(varsMap.keySet());						
				            return var1sList;
				        }
				    };
					
				    
				    final DropDownChoice<String> var1DDC = new DropDownChoice<String>("var1DDC",
				            new PropertyModel<String>(selections, "selectedVar1"), var1Choices) {
								private static final long serialVersionUID = 1L;

						@Override
				        protected String getNullKeyDisplayValue() {
				            return "Choose one";
				        }
				    };
				    


				    //create a list of var2 lists to be used in var2DDC
				    IModel<List<? extends String>> var2Choices = new AbstractReadOnlyModel<List<? extends String>>()
				    {
						private static final long serialVersionUID = 1L;

						@Override
				        public List<String> getObject()
				        {
				        	selectedVar1 = var1DDC.getModelObject();
				            List<String> var2s = varsMap.get(selectedVar1);
				            if (var2s == null)
				            {
				                var2s = Collections.emptyList();
				            }
				            return var2s;
				        }

				    };

					final DropDownChoice<String> var2DDC = new DropDownChoice<String>("var2DDC",
			        		 new PropertyModel<String>(selections,"selectedVar2"),var2Choices) {
								private static final long serialVersionUID = 1L;

						@Override
				        protected String getNullKeyDisplayValue() {
				            return "Choose one";
				        }
				    };
			        
			        var2DDC.setOutputMarkupId(true);
				    
				    //set visibility of dropdowns by number of variables, initially false
			        var1DDC.setVisible(false);
			        var2DDC.setVisible(false);
			        
			        if(var1listlen > 1) {
			        	var1DDC.setVisible(true);
			        }
			        if(var2listlen > 1) {
			        	var2DDC.setVisible(true);
			        }
			        
			        catitem.add(var1DDC);
			        catitem.add(var2DDC);		        

				    var1DDC.add(new AjaxFormComponentUpdatingBehavior("onchange")
			        {
						private static final long serialVersionUID = 1L;

						@Override
			            protected void onUpdate(AjaxRequestTarget target)
			            {
			                target.add(var2DDC);					
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

			        catitem.add(new Link<Object>("exerlink") {
						private static final long serialVersionUID = 1L;

						@Override
						public void onClick() {
							System.out.println("got to onClick");
							System.out.println("categoryModel.getUrlbase() = " + categoryModel.getUrlbase());
							System.out.println("selections.getExerciseurl() = " + selections.getExerciseurl());
							System.out.println("selections.getSelectedVar1() = " + selections.getSelectedVar1());
							System.out.println("selections.getSelectedVar2() = " + selections.getSelectedVar2());

							String var1str = var1DDC.getModelObject();
							String var2str = var2DDC.getModelObject();
							
							int variablesIn = selections.getVariables();
							
							ExerciseModel exerModel = 
									exerciseService.findExerciseByVariableStrings(categoryModel.getId(), var1str, var2str);
							
							String exerciseurl = categoryModel.getUrlbase() + "?exerciseid="+exerModel.getId(); 
							if (variablesIn == 2) {
								exerciseurl = exerciseurl+"&variable1="+var1str+"&variable2="+var2str;
							}
							else if (variablesIn == 1) {
								exerciseurl = exerciseurl +"&variable1="+var1str+"&variable2=null";
							}
							System.out.println("PracticeExercises, line 257:  exerciseurl = " + 
									exerciseurl);										
							selections.setExerciseurl(exerciseurl);
							
							
							//create assignment for shared exercise
							AssignmentModel assignmentModel = modelFactory.getNewAssignmentModel();
							assignmentModel.setAssignmentName("Practice " + categoryModel.getExerciseName());
							assignmentModel.setType("PRACTICE");

							int exerciseId = exerModel.getId();
							assignmentModel.setExerciseId(exerciseId);
							Calendar cal = Calendar.getInstance();
							Timestamp now = 
									new Timestamp(cal.getTime().getTime());						
							assignmentModel.setStartDate(now);
							cal.add(Calendar.MONTH, 1);
							//set due date temporarily one month in future
							//when exercise is submitted, this should be changed to now
							Timestamp duedate = new Timestamp(cal.getTime().getTime());
							assignmentModel.setDueDate(duedate);
							TimeZone tz = Calendar.getInstance().getTimeZone();
							String timezone = tz.getDisplayName(false, TimeZone.SHORT);
							System.out.println("timezone = " + timezone);
							assignmentModel.setTimeZone(timezone);
							assignmentService.createAssignment(assignmentModel);
							
							if (selections.getExerciseurl() != null) {
								String redirectUrl= "/"+ selections.getExerciseurl()+"&assignmenttype=SHARED&assignmentid=0";
								System.out.println("PracticeExercises, line 338:  redirectUrl = " + redirectUrl);
				                getRequestCycle().scheduleRequestHandlerAfterCurrent(new RedirectRequestHandler(redirectUrl));
							}
							else {
								getSession().error("Necessary variable have not been selected.");
							}
						}		        	
			        });
			        ExerCategoryModel exerCatModel = exerciseService.findExerCategoryById(categoryModel.getId());
					
			        catitem.add(new Link<Object> ("copybutton") {
						private static final long serialVersionUID = 1L;

						@Override
						public void onClick() {
							setResponsePage(new CreateExercisePage(exerCatModel, exerCatModel.getId()));//Model with Id = copy
						}
					});
				}
			});
			

			add(form);
		}  ///buildForm
		
}

