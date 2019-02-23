package com.linguaclassica.student;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.http.handler.RedirectRequestHandler;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.model.AssignmentFactModel;
import com.linguaclassica.model.AssignmentModel;
import com.linguaclassica.model.ExerCategoryModel;
import com.linguaclassica.model.ExerciseModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.access.Selections;
import com.linguaclassica.entity.EntityExerCategoryModel;
import com.linguaclassica.entity.EntityExerciseModel;
import com.linguaclassica.entity.EntityStudentAssignmentResultsModel;
import com.linguaclassica.service.AssignmentFactService;
import com.linguaclassica.service.AssignmentService;
import com.linguaclassica.service.ExerciseService;
import com.linguaclassica.service.ResultsService;

public class PracticeExercisesPage extends StudentBasePage {
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
	
	public PracticeExercisesPage()
	{
		super();
		System.out.format("PracticeExercisesPanel()%n");
		buildForm();
	}

	class PracticeExercisesForm extends Form<Object> {
		private static final long serialVersionUID = 1L;


		public PracticeExercisesForm(String id) {
			super(id);
		}
	}

	private void buildForm() {
		System.out.format("PracticeExercisesPanel.buildForm()%n");
		
		Form<?> form = new PracticeExercisesForm("exeravailform");
		
		/*
		 * Section below creates a list of available practice exercises
		 */		
		List<EntityExerCategoryModel> categoriesList = new ArrayList<EntityExerCategoryModel>();
		categoriesList = exerciseService.getListOfExerCategoriesByType("PRACTICE");
		
		List<EntityExerciseModel> exerlist = exerciseService.getListOfExercises();
		int exerlistlen = exerlist.size();
		
		List<String> variable1list = new ArrayList<String>();
		
		for (int k = 0; k < exerlistlen; k++) {
			variable1list.add(exerlist.get(k).getVar1string());
		}
		
		form.add(new ListView<EntityExerCategoryModel>("categoriesList",categoriesList) {
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
				System.out.println("PracticeExercisePanel, line 133:  catid = " + catid);
				List<EntityExerciseModel> subexerModelList = new ArrayList<EntityExerciseModel>();
				subexerModelList = exerciseService.getListOfExercisesByCategory(catid);	
				System.out.println("subexerModelList.size() = " + subexerModelList.size());

				List<String> exervar1list = new ArrayList<String>();
				if (subexerModelList.size() > 0)  {  
					String var1prim = subexerModelList.get(0).getVar1string();
					exervar1list.add(var1prim);
				}
				//clear list length variables
				var1listlen = 0;
				var2listlen = 0;
				
				//set var1 list length
				var1listlen = exervar1list.size();
				selections.setVariables(1);

				List<String> exervar2list = new ArrayList<String>();
				String var2prim = subexerModelList.get(0).getVar2string();
				exervar2list.add(var2prim);
				
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
					}
					var1listlen = var1list.size();					
	
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
									var2listlen = var2list.size();
								}
								else {
									var2list.add("None");
									var2listlen = var2list.size();
								}
							}
							varsMap.put(var1, var2list);
						}
					}
					if(var2listlen > 1){
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
			    
			    //set visibility of dropdowns by number of variables, initially false
	        	var1DDC.setVisible(false);
	        	var2DDC.setVisible(false);
	        	
		        if(var1listlen > 1){
		        	var1DDC.setVisible(true);
		       	}
		        if(var2listlen > 1){
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

		        catitem.add(new Button("exerlink")
		        {
					private static final long serialVersionUID = 1L;

					@Override
					public void onSubmit()
					{
						System.out.println("PracticeExercisesForm.onSubmit exerlink");
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
						
						//create assignment for practice exercise
						AssignmentModel assignmentModel = modelFactory.getNewAssignmentModel();
						assignmentModel.setAssignmentName("Practice " + categoryModel.getExerciseName());
						assignmentModel.setStatus("PRACTICE");
						assignmentModel.setType("NORMAL");

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
							String redirectUrl= "/"+ selections.getExerciseurl()+"&assignmentid="+
									assignmentModel.getId()+"&classid=0&assignmentstatus=PRACTICE";
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
		
		/*
		 * Section below will display practice exercise results
		 */
		List<EntityStudentAssignmentResultsModel> studentAssignResults = 
				resultsService.getListOfAssignmentResultsByStudentId(userModel.getId(),"PRACTICE", null);
		System.out.println("PracticeExercisesPanel, line 374:  studentAssignResults.size() = " 
				+ studentAssignResults.size());
		
		ListView<EntityStudentAssignmentResultsModel> resultsListView = 
				new ListView<EntityStudentAssignmentResultsModel>("resultsLV",studentAssignResults) {
					private static final long serialVersionUID = 1L;

					@Override
					protected void populateItem(final ListItem<EntityStudentAssignmentResultsModel> 
						resultitem) {
						
						final EntityStudentAssignmentResultsModel assignResults = 
								resultitem.getModelObject();
		                String exerciseName = "N/A";
		                String var1str = "N/A";
		                String var2str = "N/A";
		                String ratiostr = "N/A";
		                String percentstr = "N/A";
		                String dateform = "N/A";
	                	AssignmentFactModel assignFact = null;
		                
		                AssignmentModel assignModel = null;		                
		                try{
		                	assignFact = 
		                			assignmentfactService.findAssignmentFactById(assignResults.getAssignmentFactId());
		                	assignModel = assignmentService.findAssignmentById(assignFact.getAssignmentId()); 
		                } catch(Exception e){
		                	System.out.println("PracticeExercisesForm populateItem: Unable to properly populate student assignment results- "
		                			+ e.getCause());
		                	assignModel=null;
		                }
		                
		                ExerciseModel exerciseModel = 
		                		exerciseService.findExerciseById(assignModel.getExerciseId());
		                int exerCatId = exerciseModel.getExercisecategory();
		                ExerCategoryModel exerCatModel = exerciseService.findExerCategoryById(exerCatId);
		                exerciseName = exerCatModel.getExerciseName();
		                var1str = exerciseModel.getVar1string();
		                var2str = exerciseModel.getVar2string();

		                if(assignModel!=null){
		                	DecimalFormat df = new DecimalFormat("##.##");
		    				percentstr = df.format(assignResults.getPercent());
		    				percentstr += "%";
		    				String countstr = Float.toString(assignResults.getCorrectNumber()); // get the number of correct answers
		    				String totalstr = Float.toString(assignResults.getTotal());
		    				ratiostr = countstr + "/" + totalstr;
		    				
							// formats assignment's due date for displaying on panel
							java.util.Date date = assignModel.getCreatedDate();   //.getDueDate();
							dateform = DateFormat.getDateTimeInstance(
						            DateFormat.SHORT, DateFormat.SHORT).format(date);
							}
		                
						resultitem.add(new Label("exername",exerciseName));
						resultitem.add(new Label("var1lab",var1str));
						resultitem.add(new Label("var2lab",var2str));
						resultitem.add(new Label("datelab",dateform)); 
						resultitem.add(new Label("ratiolab", ratiostr));
						resultitem.add(new Label("percentlab",percentstr));
						
						resultitem.add(new Button("chooseAssign")
						{
							private static final long serialVersionUID = 1L;
							
							@Override
							public void onSubmit()
							{
								System.out.println("PracticeExercisesForm.onSubmit chooseAssign");
								String assignfactid = 
										Integer.toString(resultitem.getModelObject().getAssignmentFactId());
								PageParameters params = new PageParameters();
								params.add("assignfactid", assignfactid);
								setResponsePage(DetailedResultsPage.class,params);
							}	
						});
					}			
		};
		
		form.add(resultsListView);
		add(form);
	}  ///buildForm
}   ///class
	
	