package com.linguaclassica.teacher;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.googlecode.wicket.jquery.ui.form.button.ConfirmButton;
import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.entity.EntityExerciseModel;
import com.linguaclassica.exercises.CreateExercisePage;
import com.linguaclassica.exercises.EditExercisePage;
import com.linguaclassica.model.ExerCategoryModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.service.ClassService;
import com.linguaclassica.service.ExerciseService;
import com.linguaclassica.service.QuestionService;
import com.linguaclassica.service.UserService;
import com.linguaclassica.teacher.TeacherExercisesPage.PracticeExercisesForm;

public class ExerciseListPage extends TeacherBasePage {
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	private ExerciseService exerciseService;
	
	@SpringBean
	private QuestionService questionService;

	
	@SpringBean
	private UserService userService;
	
	@SpringBean
	private ClassService classService;
	
	ModelFactory modelFactory;
	
	ArrayList<String> classSelection;
	
	List<String> classnamelist;
	
	List<String> classeslist;
	
	ArrayList<String> classesSelected;
	
	ArrayList<String> selClassesSelected;
	
	List<EntityExerciseModel> exerciselistIn;
	
	List<Integer> classidlist;
	
	// Active user
	private final UserModel userModel = AlphPlusSession.get().getUser(getRequest());
	public ExerciseListPage() {
		super();
		buildForm();
    }
	/*public ExerciseListPage(final String idIn, String category)
	{
		super(idIn);
		
		add(new Link<Object>("createExercisebutton") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new CreateExercisePage(null, 0));//null and 0 - for create
			}
		});*/
		////////////
		class ExerciseListForm extends Form<Object> {
			private static final long serialVersionUID = 1L;


			public ExerciseListForm(String id) {
				super(id);
			}
		}

		private void buildForm() {
			
		
			System.out.println("Avialable Exercises List  Form: beginning of buildform");
			
			Form<?> form = new ExerciseListForm("editlistform");
			
			/*
			 * Section below creates a list of available exercises
			 */	
						
			List<EntityExerciseModel> exerciseList = 
					exerciseService.getListOfExercisesCreatedByUser(userModel.getId());
	
			form.add(new ListView<EntityExerciseModel>("exerciseList", exerciseList) {
				private static final long serialVersionUID = 1L;
			
				protected void populateItem(ListItem<EntityExerciseModel> exeritem) {		
					EntityExerciseModel exercises = exeritem.getModelObject();
					final int exerciseId = exercises.getId();
					exeritem.add(new Label("exeridlab",exerciseId));
					final int exerCatId = exercises.getExercisecategory();
				
					ExerCategoryModel exerCatModel = exerciseService.findExerCategoryById(exerCatId);
					String exerCatName = exerCatModel.getExerciseName();
					exeritem.add(new Label("exernamelab",exerCatName));
					exeritem.add(new Label("commentlab", exerCatModel.getComment()));
					
					exeritem.add(new Link<Object>("renameExercisebutton") {
						private static final long serialVersionUID = 1L;
	
						@Override
						public void onClick() {
							setResponsePage(new CreateExercisePage(exerCatModel, 0 ));//Model + no Id = Rename
						}
					});
																			
					exeritem.add(new Link<Object> ("copybutton") {
						private static final long serialVersionUID = 1L;
	
						@Override
						public void onClick() {
							setResponsePage(new CreateExercisePage(exerCatModel, exerciseId ));//Model with Id = copy
						}
					});
					
					// ConfirmButton set up outside if clauses because it must be added to all 
					// question items but is visible only if there is only one question for a 
					// passage-based question.
					
					ConfirmButton confdeletebutton = new ConfirmButton("confdeletebutton","Delete",
							"Please Confirm Deletion", "The selected exercise will be permanently deleted") {
						private static final long serialVersionUID = 1L;
	
							@Override
							public void onError() {
								this.error("Validation failed!");
							}
	
																																				@Override
						public void onSubmit() {
							System.out.println("delete link clicked");
							exerciseService.deleteExercise(exercises.getId());
							//if not associated exercises are left - delete category
							if(exerciseService.getListOfExercisesByCategory(exercises.getExercisecategory()) == null){
								exerciseService.deleteExerciseCategory(exercises.getExercisecategory());
							}
							setResponsePage(ExerciseListPage.class);	
						}																																																												
					};	
					exeritem.add(confdeletebutton);
					
					exeritem.add(new Link<Object> ("editbutton") {
								private static final long serialVersionUID = 1L;
	
								@Override
								public void onClick() {
									System.out.println("Edit exercise clicked.");
									AlphPlusSession.get().setExerciseCategory(exerCatId);
									AlphPlusSession.get().setExerciseId(exerciseId);
									AlphPlusSession.get().setCount(1);
									AlphPlusSession.get().setQuestionId(0);
									AlphPlusSession.get().setSelectedVal("None");
									AlphPlusSession.get().setPassageId(0);
									AlphPlusSession.get().setPassageTxt("");
									AlphPlusSession.get().setEditOption(false);
									AlphPlusSession.get().setAddOption(false);
									setResponsePage(EditExercisePage.class);
								}
							});
					}
				
			});
			add(form);		
		
	}		
}


