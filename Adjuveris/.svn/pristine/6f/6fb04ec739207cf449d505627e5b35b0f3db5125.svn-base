package com.linguaclassica.exercises;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.entity.EntityAnswerModel;
import com.linguaclassica.model.AnswerModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.QuestionModel;
import com.linguaclassica.service.QuestionService;

//@Service research if it needs to be declared for every child class
public class ExercisesContainer extends WebMarkupContainer implements Exercise{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9045588200950044515L;
	
	@SpringBean
	protected QuestionService questionService;
	
	@SpringBean
	protected ModelFactory modelFactory;
	


	public void setMulticount(Integer count) {
		this.multicount = count;
	}
	
	protected Integer multicount = 0;// = defaultAnswerRows;
	protected AnswerModel answerModel = null;


	protected String question;
	
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getQuestion() {
		 return question;
	}
	
	protected	List<AnswerModel> multiAnswerModel = null;
	
	
	public ExercisesContainer(String id, int questionId) {
		
		super(id);
		if(id.equals("greekparsing")){
			this.setMulticount(numberofGreekParserModels);
		}
		else if(id.equals("latinparsing")){
			this.setMulticount(numberofLatinParserModels);		
		}
		else if(id.contains("multi")){
			this.setMulticount(defaultAnswerRows);
		}	
		else{
			this.setMulticount(1);
		}
		
		if(questionId < 1 || !updateAnswersfromDatabase(questionId)) {
			answerModel = modelFactory.getNewAnswerModel();		
		}
		updateAnswers();
		CreateExcercise(questionId);
		
		this.setOutputMarkupId(true);
	}

	public ExercisesContainer(String id, IModel<?> model, int questionId) {
		super(id, model);
		if(id.equals("greekparsing")){
			this.setMulticount(numberofGreekParserModels);
			
		}
		else if(id.equals("latinparsing")){
			this.setMulticount(numberofLatinParserModels);
			
		}
		else if(id.contains("multi")){
			this.setMulticount(defaultAnswerRows);
		}	
		else{
			this.setMulticount(1);
		}
		if(questionId < 1 && !updateAnswersfromDatabase(questionId)) {
			answerModel = modelFactory.getNewAnswerModel();
		}
		updateAnswers();
		CreateExcercise(questionId);
		
		this.setOutputMarkupId(true);
	}
	
	
	protected AnswerModel CreateAnswerModel(String answer,Integer answerint, Boolean correct, Integer questionid ){
		AnswerModel am = modelFactory.getNewAnswerModel();
		am.setAnswer(answer);
		am.setAnswerint(answerint);
		am.setCorrect(correct);
		am.setQuestionid(questionid);
		
		return am;
	}
	
	public void ResetToDefaults(){
		if(question != null ){
			question = "";
		}
		if(multiAnswerModel != null && !multiAnswerModel.isEmpty()){
			for(int i = 0; i < multicount; i++){// fill the void
				AnswerModel am = modelFactory.getNewAnswerModel();
				am.setAnswer("");
				am.setAnswerint(null);
				am.setCorrect(false);
				am.setQuestionid(0);
			}	
		
		}
		
		if(answerModel != null){
			answerModel.setAnswer("");
			answerModel.setAnswerint(null);
			answerModel.setCorrect(false);
			answerModel.setQuestionid(0);
		}
	}
	
	public void updateAnswers() {
		
		//Create or add missing models
		int index = 0;
		if(multiAnswerModel == null){
			multiAnswerModel = new ArrayList<AnswerModel>();
		}
		else if(!multiAnswerModel.isEmpty()){
			index = multiAnswerModel.size();
		}
		if (index < multicount){
			for(int i = index; i < multicount; i++){// fill the void
				multiAnswerModel.add(CreateAnswerModel("", 0, false, 0));
			}	
		}
		else if (index > multicount){
			for ( int i = index; i > multicount; i--){
					multiAnswerModel.remove(i-1);//index is not size - must be -1
			}
		}		
	}
	
	public void CreateExcercise(int questionId){
	}
	
	public Boolean SaveExcercise(QuestionModel questionModel){		
		return true;
	}
	

	@Override
	public Boolean updateAnswersfromDatabase(int questionId) {
		
		List<EntityAnswerModel>  listSavedAnswers = null;
		if(questionId > 0){		
			listSavedAnswers = questionService.findAnswersByQuestionId(questionId);
		}
		if(listSavedAnswers == null || listSavedAnswers.isEmpty()){		
			updateAnswers();
			return false;
		}
		int size = listSavedAnswers.size();
		if( size > 0){//if database has answers - create an array for them
			multiAnswerModel = new ArrayList<AnswerModel>(multicount);
		}
		answerModel  = questionService.findCorrectAnswerByQuestionId(questionId);		
		return true;
		
	}

	@Override
	public List<AnswerModel> getmultiAnswerModel(){
		return multiAnswerModel;
	}
	
	@Override
	public void setmultiAnswerModel(List<AnswerModel> multiAnswerModel) {
		this.multiAnswerModel = multiAnswerModel;
		
	}
	protected void ShowChoices(AjaxRequestTarget target){
		
	}

	protected ListView<AnswerModel> CreateSelection() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Boolean Validate(){
		if (question == null || question.isEmpty()) {//check for null strings as well as empty input - instead of equals("")
				Session.get().error("You must enter question");
				return false;
		}
		return true;
	}
	
}

