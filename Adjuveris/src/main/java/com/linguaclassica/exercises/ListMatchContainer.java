package com.linguaclassica.exercises;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import com.linguaclassica.entity.EntityAnswerModel;
import com.linguaclassica.entity.EntityQuestionModel;
import com.linguaclassica.model.AnswerModel;
import com.linguaclassica.model.QuestionGroupModel;
import com.linguaclassica.model.QuestionModel;
public class ListMatchContainer extends ExercisesContainer {

	/**
	 * 
	 */

	private static final long serialVersionUID = -9178060544156525697L;
	
	//protected static StringList questionList = null;
	
	static DropDownChoice<Integer> multicountlist;
	
	private static int questionGroupId = 0;
	
	private	QuestionGroupModel questionGroupModel;
	
	protected static List<QuestionModel> multiQuestionModel = null;
	//QuestionModel questionModel;
		
	public ListMatchContainer(String id, int questionId) {
		super(id, questionId);
		// TODO Auto-generated constructor stub
			 
	}

	public ListMatchContainer(String id, IModel<?> model, int questionId) {
		super(id, model, questionId);
		// TODO Auto-generated constructor stub
		
	}
	
	public void CreateExcercise(int questionId){
		
		
			
		multicountlist = new DropDownChoice<Integer>("multicountlist", 
						new PropertyModel<Integer>(this,"multicount"),	multiChoiceNumber);
		multicountlist.add(new AjaxFormComponentUpdatingBehavior("onchange") {
			private static final long serialVersionUID = 1L;
		
			@Override
		    protected void onUpdate(AjaxRequestTarget target){ 
				
				ShowChoices(target);
		    }			
		});
		
		multicountlist.setOutputMarkupId(true);	
		add(multicountlist);
		
		add(CreateSelection());//add check boxes
		
		add(new Label("multiCountLabel", "Number of matching pairs :"));
			
			
	}
	
	public Boolean SaveExcercise(QuestionModel questionModel){
		
	
		//14.b The following defines actions to be taken when saving each question type, 
		//on submit.  For now not all the question types are handled.
		int index = 0;
		String Question = null;	
		String Match = null;
		
		if(questionGroupId != 0){	
			questionService.removeQuestionsByQuestionGroupId(questionGroupId);	
		}
		 questionGroupModel = 
					modelFactory.getNewQuestionGroupModel();
		 questionGroupModel.setExerciseid(questionModel.getExerciseid());
		questionGroupId = questionService.createQuestionGroup(questionGroupModel);
	//validate input - compare
		for (AnswerModel md : multiAnswerModel){
			
			
			QuestionModel gm = multiQuestionModel.get(index);
			Question = gm.getQuestion();
			Match = md.getAnswer();//make sure there is a pair	
			if(Question != null && !Question.isEmpty() &&
					Match != null && !Match.isEmpty()){
			//Set exercise property for individual question models
				gm.setExerciseid(questionModel.getExerciseid());
				
				gm.setWord(questionModel.getWord());
				//set strword only in the first model to allow for a single result search in repository
				if((index == 0 && questionModel.getPassageid() != null))
						gm.setPassageid( questionModel.getPassageid());
				gm.setStrword((index == 0) ? questionModel.getStrword() : 0);
				
				gm.setEndword(questionModel.getEndword());
				gm.setQuestiontype(questionModel.getQuestiontype());
				gm.setQnumber(questionModel.getQnumber());//(index == 0) ? (questionModel.getQnumber()) : 0);
				gm.setProficiencyindex(questionModel.getProficiencyindex());
				gm.setQuestionGroupid(questionGroupId);
		
			
				questionService.addQuestionData(gm);// register question model and acquire its ID
				int questionID = gm.getId();
				
				questionService.removeAnswersByQuestionId(questionID);						
				md.setQuestionid(questionID);
				md.setCorrect(true);
				questionService.addOrUpdateAnswer(md);													
			}			
			index++;
		}
		ResetToDefaults();
		return true;
	}
	
	@Override
	public Boolean updateAnswersfromDatabase(int questionId) {
		
		if(questionId < 1)
			return false;
	
		questionGroupId = questionId; //questionService.findQuestionGroupId(questionId);
		
		List<QuestionModel>  listSavedQuestionId = questionService.findQuestionsByQuestionGroupId(questionGroupId);
	
		if(listSavedQuestionId == null || listSavedQuestionId.isEmpty())
			return false;		
				
		if(multiAnswerModel == null){				 
			multicount = listSavedQuestionId.size();// the initial setup to the size of answer list
			if(multicount > 0){//if database has answers - create an array for them
				multiAnswerModel = new ArrayList<AnswerModel>();
			}
		
			for(int i = 0; i < multicount; i++){// since the arrays of different types - must copy comparable fields
				EntityAnswerModel emd = questionService.findCorrectAnswerByQuestionId(listSavedQuestionId.get(i).getId());
				multiAnswerModel.add(CreateAnswerModel(emd.getAnswer(),	emd.getAnswerint(),	emd.getCorrect(),	emd.getQuestionid()));
			}
		}
		
		if(multicount > 0){//if database has answers - create an array for them
			if(multiQuestionModel == null){		//multicount = listSavedAnswers.size();// the initial setup to the size of answer list
				multiQuestionModel = new ArrayList<QuestionModel>();
			}
			else{
				multiQuestionModel.clear();
			}
			
			for(int i = 0; i < multicount; i++){// since the arrays of different types - must copy comparable fields
				
				EntityQuestionModel eqm =  questionService.findQuestionById(multiAnswerModel.get(i).getQuestionid());
				multiQuestionModel.add(CreateQuestionModel(eqm));
			}
		}
		return true;
		
	}
	
	protected void ShowChoices(AjaxRequestTarget target){		
			updateAnswers();
			CreateSelection();
			target.add(this);		
	}
	
	public void updateAnswers() {
		super.updateAnswers();
		updateQuestions();//override super in order to process questions
	}
	
	public void updateQuestions() {
		
		//Create or add missing models
				int index = 0;
				if(multiQuestionModel == null){
					multiQuestionModel = new ArrayList<QuestionModel>();
				}
				else if(!multiQuestionModel.isEmpty()){
					index = multiQuestionModel.size();
				}
				if (index < multicount){
					for(int i = index; i < multicount; i++){// fill the void
						multiQuestionModel.add(CreateQuestionModel("", 0));
					}	
				}
				else if (index > multicount){
					for ( int i = index; i > multicount; i--){
							multiQuestionModel.remove(i-1);//index is not size - must be -1
					}
				}
	}	
	
	protected QuestionModel CreateQuestionModel(String question,Integer qnumber ){
		QuestionModel gm = modelFactory.getNewQuestionModel();
		gm.setQuestion(question);
		gm.setQnumber(qnumber);
		gm.setQuestionGroupid(questionGroupId);
		return gm;
	}
	
	protected QuestionModel CreateQuestionModel(EntityQuestionModel questionModel ){
		QuestionModel gm = modelFactory.getNewQuestionModel();
		gm.setQuestion(questionModel.getQuestion());
		gm.setExerciseid(questionModel.getExerciseid());
		gm.setPassageid(questionModel.getPassageid());
		gm.setWord(questionModel.getWord());
		gm.setStrword(questionModel.getStrword());
		gm.setEndword(questionModel.getEndword());
		gm.setQuestiontype(questionModel.getQuestiontype());
		gm.setQnumber(questionModel.getQnumber());
		gm.setQuestionGroupid(questionModel.getQuestionGroupid());
		return gm;
	}
	protected ListView<AnswerModel>  CreateSelection(){
		
		
		ListView<AnswerModel> list = new ListView<AnswerModel>("classlistview",
				multiAnswerModel){
			private static final long serialVersionUID = 1L;
			
			protected void populateItem(ListItem<AnswerModel> item){
				
				EntityAnswerModel answer = (EntityAnswerModel) item.getModelObject();
				QuestionModel gm = multiQuestionModel.get(item.getIndex());
				
				item.add(new Label("answertext", "Match " + (item.getIndex() + 1) + " :"));
			
				TextField<String> qtf = new TextField<String>("LMQuestion", new PropertyModel<String>(gm,"question"));
				qtf.setOutputMarkupId(true);
				// the override of onUpdate method needed for proper binding to PropertyMOdel
				// DO NOT remove or comment out
				qtf.add( new AjaxFormComponentUpdatingBehavior("onkeyup") {
					private static final long serialVersionUID = 1L;
					@Override
					protected void onUpdate(AjaxRequestTarget t){
												
					}					
				});
				item.add(qtf);	
				
				TextField<String> tf = new TextField<String>("LMAnswer", new PropertyModel<String>(answer,"answer"));
				tf.setOutputMarkupId(true);
				// the override of onUpdate method needed for proper binding to PropertyMOdel
				// DO NOT remove or comment out
				tf.add( new AjaxFormComponentUpdatingBehavior("onkeyup") {
					private static final long serialVersionUID = 1L;
					@Override
					protected void onUpdate(AjaxRequestTarget t){
					}					
				});
				item.add(tf);									
			}
		};
	
		list.setOutputMarkupId(true);
		
		return list;
	}
	
	
	public Boolean Validate(){
				
		int numberofMatches = 0;	
		int index = 0;
		String Question = null;	
		String Match = null;
	//validate input - compare
		for (AnswerModel md : multiAnswerModel){
			
			QuestionModel gm = multiQuestionModel.get(index);
			Question = gm.getQuestion();
			Match = md.getAnswer();//make sure there is a pair							
			if(Question != null && !Question.isEmpty()){
					
				
				if(Match != null && !Match.isEmpty()){
					numberofMatches++;
				}
				else {//if selection has no actual answer
					Session.get().error("The pair number " + (index + 1)+ " misses its answer");
					return false;				
				}
					
			}
			else {//if selection has no actual answer
				if(Match != null && !Match.isEmpty()){
					Session.get().error("The pair number: " + (index + 1)+ " misses its question");
					return false;	
				}
			}
			//ignore empty pairs
			index++;
		
		}
		
		if(numberofMatches == 0){
			Session.get().error("You must create at least one matching pair");
			return false;		
		}
	return true;
		
	}
	
	public void ResetToDefaults(){
		super.ResetToDefaults();
		if(multiQuestionModel != null){
			multiQuestionModel.clear();
		}
		updateQuestions();	
		questionGroupId = 0;
	}
}
