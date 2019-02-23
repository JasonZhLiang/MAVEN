package com.linguaclassica.exercises;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linguaclassica.model.AnswerModel;
import com.linguaclassica.model.ExerCategoryModel;
import com.linguaclassica.model.ExerciseModel;
import com.linguaclassica.model.MessageModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.QuestionGroupModel;
import com.linguaclassica.model.QuestionModel;
import com.linguaclassica.model.TextPassageModel;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.service.ClassService;
import com.linguaclassica.service.ExerciseService;
import com.linguaclassica.service.MessageService;
import com.linguaclassica.service.QuestionService;
import com.linguaclassica.service.UserService;
import com.linguaclassica.teacher.ExerciseListPage;
import com.linguaclassica.teacher.TeacherBasePage;
import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.entity.EntityExerCategoryModel;
import com.linguaclassica.entity.EntityTextPassageModel;

/**
 * The initial welcome page with the main site navigation elements
 * 
 * @author Eugene Price
 * @Copyright("2012 Lingua Classica")
 */
//@RequireHttps
public class CreateExercisePage extends TeacherBasePage {
	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger("UncookedHomePage");

	@SpringBean
	private UserService userService;

	@SpringBean
	private MessageService messageService;

	@SpringBean
	private ClassService classService;

	@SpringBean
	private ExerciseService exerciseService;
	
	@SpringBean
	private QuestionService questionService;
		
	@SpringBean
	private ModelFactory modelFactory;
	
	UserModel userModel;
	
	ExerciseModel exerciseModel;
	
	ExerCategoryModel exerCategoryModel;
	
	QuestionModel questionModel;
	
	final List<String> exertypelist = Arrays.asList("may be used only by teacher who created it",
			"may be shared with other instructors","may be used as practice exercise");
	
	final List<String> exertypestr =  Arrays.asList("PRIVATE", "SHARED", "PRACTICE");
	final List<String> titles = Arrays.asList("Create a new exercise", "Edit the existing exercise information", "Copy the existing exercise");
	final int mode_Create = 0;
	final int mode_Edit = 1;
	final int mode_Copy = 2;
	
	private  int exerciseId = 0;
	private int mode = 0; 
	static DropDownChoice<String> exertypeselect;
	String selected;
	final String suffix = "(Copy)";

	public CreateExercisePage()
	{
		super();
		Initialize(null,0);
		
    }
	
	public CreateExercisePage ( ExerCategoryModel exerCatModel, int exerciseId) {		
		
		Initialize(exerCatModel,exerciseId);
		
	}
	//
	private void Initialize ( ExerCategoryModel exerCatModel, int exerciseId) {		
			
			mode = ((exerCatModel == null) ? mode_Create : mode_Edit) + ((exerciseId > 0)? 1 :0);
			this.exerciseId = exerciseId;
			System.out.println("HomePage loaded");
			
			//get logged in user model
			Request request = getRequestCycle().getRequest();
			userModel = AlphPlusSession.get().getUser(request); 
			if(mode != mode_Create && exerCatModel != null){
				this.exerCategoryModel = exerCatModel;
				selected = exertypelist.get((exertypestr.indexOf(exerCategoryModel.getExercisetype())));		
			}
			else{
				exerCategoryModel = modelFactory.getNewExerCategoryModel();//new model for new exercise
				exerciseModel = modelFactory.getNewExerciseModel();
				questionModel = modelFactory.getNewQuestionModel();
				
			}
			if(mode == mode_Copy){
				nameCheck();//check the title before display
			}
			Form<Object> form = new Form<Object>("HomeForm");
			MessageModel messageModel = messageService.findFirstMessageModel();
			String stat = messageModel.getStatus().toString();
			String msg = messageModel.getMessage();	
			
			System.out.println("stat = " + stat);
			
			if (stat.equals("SHOW")) {
				System.out.println("show message");
				error(msg);
			}
			
			AlphPlusSession.get().setSelectedClasses(null);  //clears classes selected for TeacherTALandingPage
			
			form.add( new Label("CreateorEdit", titles.get(mode)));
					
			TextField<String> exercisenametf = new TextField<String>("exernametf",
					new PropertyModel<String> (exerCategoryModel,"exercisename"));

			TextField<String> commenttxt = new TextField<String>("commenttxt",
					new PropertyModel<String> (exerCategoryModel,"comment"));
			
			exertypeselect = new DropDownChoice<String>("exertypeselect", 
					new PropertyModel<String>(this, "selected"), exertypelist);
			
			form.add(exercisenametf);
			form.add(exertypeselect);
			form.add(commenttxt);
			form.setEnabled(true);	
			
			/*
			form.add(new Button("gotoListButton").add( new AjaxFormComponentUpdatingBehavior("onclick") {
				private static final long serialVersionUID = 1L;
				@Override
				protected void onUpdate(AjaxRequestTarget t){	
				//	setResponsePage(TeacherTALandingPage.class);
				}					
			})); */
						
			final Label titleupdated = new Label("titleupdated", "The exercise infromation has been updated.");
			titleupdated.setVisible(false);
			titleupdated.setOutputMarkupId(true);
			titleupdated.setOutputMarkupPlaceholderTag(true);
			form.add(titleupdated);
			//////////////
			Button copyButton =	 new Button("copybutton") {
				private static final long serialVersionUID = 1L;

				public void onSubmit() {
					if(!Veryify()){
						return;
					}
					if(mode == mode_Copy && exerCategoryModel != null && getExerciseId() > 0){
						duplicateExercise();
					//	AlphPlusSession.get().setTeacherLandingPageTabIndex(3);// in case we wish to go to the create/edit exercise
						setResponsePage(ExerciseListPage.class);
					}
				}
			};
			form.add(copyButton.setVisible(mode == mode_Copy));
			//The reason for AjaxButton here is the Java Script visual effect - prevents proper message display
			AjaxButton namebutton = new AjaxButton("namesubmit"){

				private static final long serialVersionUID = 1L; 
				
				//@Override
				public void onSubmit(AjaxRequestTarget target, Form<?> form){
					
					String newName = exercisenametf.getModelObject();
					if(!Veryify()){
						target.add(target.getPage());
						return;
					}
					exerCategoryModel.setExerciseName(newName);
					exerCategoryModel.setExercisetype(exertypestr.get(exertypelist.indexOf(selected)));
			
					if(mode == mode_Create || exerCategoryModel == null ){		
						exerCategoryModel.setUrlbase("TeacherGeneratedExerciseServlet");
						exerciseService.createExerCategory(exerCategoryModel);
						logger.info("ExercisesHomePage, line 134: exerCategporyId = " + exerCategoryModel.getId());
						exerciseModel.setCreatedById(userModel.getId());
						exerciseModel.setExercisecategory(exerCategoryModel.getId());
						exerciseModel.setVar1string("None");
						exerciseModel.setVar2string("None");
						setExerciseId(exerciseService.createExercise(exerciseModel));
					}
					else{
						exerciseService.updateExerCategory(exerCategoryModel);
					}
					mode = mode_Edit;//created or updated - change mode to edit to prevent the creating of a new record;
					titleupdated.setVisible(true);
					target.add(titleupdated);
					target.appendJavaScript(
				            "$('#titleupdated').fadeOut(9000);");
					
				}
			};
			namebutton.setVisible(mode != mode_Copy);
			form.add(namebutton);	
			
			Button addExercise = new Button("addExercise"){

				private static final long serialVersionUID = 1L; 
				
				public void onSubmit(){
					if(!Veryify()){
						//target.add(form);// edit mode is processed in a different call
						return;
					}
					
					if(mode == mode_Create || exerCategoryModel == null ){		
						exerCategoryModel.setUrlbase("TeacherGeneratedExerciseServlet");
						exerCategoryModel.setExercisetype(exertypestr.get(exertypelist.indexOf(selected)));
						exerciseService.createExerCategory(exerCategoryModel);
						logger.info("ExercisesHomePage, line 134: exerCategporyId = " + exerCategoryModel.getId());
						exerciseModel.setCreatedById(userModel.getId());
						exerciseModel.setExercisecategory(exerCategoryModel.getId());
						exerciseModel.setVar1string("None");
						exerciseModel.setVar2string("None");
						setExerciseId(exerciseService.createExercise(exerciseModel));
					}
					else{
						exerciseService.updateExerCategory(exerCategoryModel);
					}
					AlphPlusSession.get().setExerciseCategory(exerCategoryModel.getId());
					AlphPlusSession.get().setExerciseId(getExerciseId());
					AlphPlusSession.get().setCount(1);
					AlphPlusSession.get().setQuestionId(0);
					AlphPlusSession.get().setSelectedVal("None");
					AlphPlusSession.get().setPassageId(0);
					AlphPlusSession.get().setPassageTxt("");
					AlphPlusSession.get().setEditOption(false);
					AlphPlusSession.get().setAddOption(false);

					setResponsePage(EditExercisePage.class);
				}
			};
			addExercise.setVisible(mode != mode_Copy);
			form.add(addExercise);	
			add(form);
		
	}
	private void nameCheck(){
		String name = exerCategoryModel.getExerciseName();
		if(mode == mode_Copy){			
			name = checkForSuffix(name);
		}	
		name = checkForDuplicates(name);
		
		exerCategoryModel.setExerciseName(name);
		
	}
	
	private boolean checkForNameDuplicates(String name){
		List<EntityExerCategoryModel> listofCategories = exerciseService.getListOfExerCategories();
		
		for(EntityExerCategoryModel eecm :listofCategories){				
			if(name != null && name.equals(eecm.getExerciseName())){	
				//if exercise is being edited - make sure that the name belongs to the same exercise by checking its id
				if(mode != mode_Copy && eecm.getId() == exerCategoryModel.getId()){
					return false;
				}
				return true;				
			}
		}	
		return false;
	}
	
	private String checkForDuplicates(String name){
		List<EntityExerCategoryModel> listofCategories = exerciseService.getListOfExerCategories();
		
		for(EntityExerCategoryModel eecm :listofCategories){				
			if(name != null && name.equals(eecm.getExerciseName())){			
				name = checkForNumber(name); 				
			}
		}	
		return name;
	}
	private String checkForSuffix(String name){
		
		if(name != null && !name.isEmpty() && name.contains(suffix)){
			if(name.endsWith(suffix)){
				name += "1";
			}else{
				name = checkForNumber(name);
			}
		}
		else{
			name += suffix;
		}
		return name;
	}
	
	private String checkForNumber(String name){
		try{
			int index = name.lastIndexOf(suffix)+ suffix.length();
			int num =  Integer.parseInt(name.substring(index));
			name = name.substring(0, index) + Integer.toString(num + 1);
			
		}catch (NumberFormatException nfx){
			if(name.endsWith(suffix)){
				name += "1";
			}
			//do nothing it's ok
		}	
		return name;
	}
	
	private void duplicateExercise(){
		
		ExerciseModel oldExerciseModel = exerciseService.findExerciseById(exerciseId);
		
		ExerCategoryModel newExerCategoryModel = modelFactory.getNewExerCategoryModel();//new model for new exercise
		newExerCategoryModel.setUrlbase(exerCategoryModel.getUrlbase());
		newExerCategoryModel.setExerciseName(exerCategoryModel.getExerciseName());
		newExerCategoryModel.setExercisetype(exertypestr.get(exertypelist.indexOf(selected)));
		newExerCategoryModel.setComment(exerCategoryModel.getComment());
		exerciseService.createExerCategory(newExerCategoryModel);
		
		exerciseModel = modelFactory.getNewExerciseModel();
		
		exerciseModel.setCreatedById(userModel.getId());
		exerciseModel.setExercisecategory(newExerCategoryModel.getId());
		exerciseModel.setVar1string(oldExerciseModel.getVar1string());
		exerciseModel.setVar2string(oldExerciseModel.getVar2string());
		exerciseModel.setIdentifier(oldExerciseModel.getIdentifier());
		exerciseModel.setVariable1(oldExerciseModel.getVariable1());
		exerciseModel.setVariable2(oldExerciseModel.getVariable2());
		
		int oldExerciseId = exerciseId;
		setExerciseId(exerciseService.createExercise(exerciseModel));
		Integer questionGroupId = null;
		Integer passageId = null;
		
		//////////////////////////////
		List<QuestionModel> listOfQuestions = questionService.getListOfQuestionByExerID(oldExerciseId);
		for( QuestionModel qm: listOfQuestions){
			QuestionModel newQuestionModel = modelFactory.getNewQuestionModel();
			
			if(qm.getPassageid()!= null && qm.getPassageid() != passageId){
				
				EntityTextPassageModel oldTextPassageModel = questionService.findPassageById(qm.getPassageid());
				TextPassageModel textPassageModel =  modelFactory.getNewTextPassageModel();
				textPassageModel.setExerciseid(exerciseModel.getId());// new Exercise ID
				textPassageModel.setTextcontent(oldTextPassageModel.getTextcontent());
				textPassageModel.setTextinfo(oldTextPassageModel.getTextinfo());
				passageId = exerciseService.createTextPassage(textPassageModel);
			}
			else{
				passageId = null;
			}
			
			newQuestionModel.setPassageid(passageId);//passage copy
			newQuestionModel.setEndword(qm.getEndword());
			newQuestionModel.setExerciseid(exerciseModel.getId());// new Exercise ID
			newQuestionModel.setEndword(qm.getEndword());
			newQuestionModel.setLinenumber(qm.getLinenumber());
			newQuestionModel.setProficiencyindex(qm.getProficiencyindex());
			newQuestionModel.setQnumber(qm.getQnumber());
			newQuestionModel.setQuestion(qm.getQuestion());
			
			if(qm.getQuestionGroupid()!= null && qm.getQuestionGroupid() != questionGroupId){
				QuestionGroupModel questionGroupModel = modelFactory.getNewQuestionGroupModel();
				questionGroupModel.setExerciseid(exerciseModel.getId());
				questionGroupId = questionService.createQuestionGroup(questionGroupModel);
			}
			else{
				questionGroupId = null;
			}
			
			newQuestionModel.setQuestionGroupid(questionGroupId);//copy that
			newQuestionModel.setQuestiontype(qm.getQuestiontype());
			newQuestionModel.setStrword(qm.getStrword());
			newQuestionModel.setSwassessed(qm.getSwassessed());
			newQuestionModel.setWeight(qm.getWeight());
			newQuestionModel.setWord(qm.getWord());
			newQuestionModel.setWordnumber(qm.getWordnumber());
			questionService.addQuestionData(newQuestionModel);
			
			if(!qm.getQuestiontype().equals("List matching")){
				List<AnswerModel> answerList = questionService.getListOfAnswersByQuestionId(qm.getId());
				for( AnswerModel am: answerList){
					AnswerModel newAnserModel = modelFactory.getNewAnswerModel();
					newAnserModel.setAnswer(am.getAnswer());
					newAnserModel.setAnswerint(am.getAnswerint());
					newAnserModel.setCorrect(am.getCorrect());
					newAnserModel.setQuestionid(newQuestionModel.getId());
					questionService.createAnswer(newAnserModel);
				//	questionService.addOrUpdateAnswer(newAnserModel);	
					
				}
			}
		}
		
	}
	
	class HomeForm extends Form<Object> {
		private static final long serialVersionUID = 1L;

		public HomeForm(String id) {
			super(id);
			
		}
		
		public void onSubmit() {
		}			
	}
	
	public int getExerciseId() {
		return exerciseId;
	}
	
	public void setExerciseId(int exerciseId) {
		this.exerciseId = exerciseId;
	}
	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}
	
	protected Boolean Veryify(){
		String exerciseName = exerCategoryModel.getExerciseName();
		if(exerciseName == null || exerciseName.isEmpty()){
			Session.get().error("You must name the exerecise in order to use it");
			return false;
		}
		if(selected == null || selected.isEmpty()){
			Session.get().error("You must select an exercise type");
			return false;
		}
		
	//	if(mode != mode_Edit){//prevent duplicate names
			if(checkForNameDuplicates(exerciseName)){//return of a different name means there is a match in the database
				Session.get().error("The exercise " + exerciseName + " already exists. Please choose a different title.");
				exerCategoryModel.setExerciseName(checkForDuplicates(checkForSuffix(exerciseName)));
				return false;
			}
		//}
		return true;
	}
}