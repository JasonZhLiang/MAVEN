package com.linguaclassica.exercises;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import com.linguaclassica.entity.EntityAnswerModel;
import com.linguaclassica.model.AnswerModel;
import com.linguaclassica.model.QuestionModel;

public class MultiChoiceContainer extends ExercisesContainer {

	static DropDownChoice<Integer> multicountlist;
	
	static RadioGroup<Integer> multipleChoiceRadioGroup;
	static TextField<String> multiquestiontf;
	//static TextField<String> multianswertf;

	/**
	 * 
	 */
	private static final long serialVersionUID = 5473738655503405059L;

	public MultiChoiceContainer(String id, int questionId) {
		super(id, questionId);
		// TODO Auto-generated constructor stub
	}

	public MultiChoiceContainer(String id, IModel<?> model, int questionId) {
		super(id, model, questionId);
	}
	
	public void CreateExcercise(int questionId){
	
	//create drop down list of limited choices
		
	
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
	
			multipleChoiceRadioGroup = new RadioGroup<Integer>("radiogroup", new PropertyModel<Integer>(answerModel, "answerint"));
			multipleChoiceRadioGroup.add(CreateSelection());
			multipleChoiceRadioGroup.setOutputMarkupId(true);
			
			multipleChoiceRadioGroup.add( new AjaxFormChoiceComponentUpdatingBehavior() { 
				private static final long	serialVersionUID	= 1L;
	
				@Override
				protected void onUpdate(AjaxRequestTarget target) {		
								
					Integer choice = answerModel.getAnswerint();
					for( int i = 0; i < multiAnswerModel.size(); i++)
						multiAnswerModel.get(i).setCorrect(i == choice);//set correct to the corresponding model while removing the previous one
				}					
			});
			add(multipleChoiceRadioGroup);//add radio buttons
			
			
			//add components to the container
			multiquestiontf = new TextField<String>("multiquestiontf", new PropertyModel<String>(this,"question"));
			multiquestiontf.setOutputMarkupId(true);
			multiquestiontf.add( new AjaxFormComponentUpdatingBehavior("onkeyup") {
				private static final long serialVersionUID = 1L;
				@Override
				protected void onUpdate(AjaxRequestTarget t){
				}					
			});
			
			add(multiquestiontf);
			add(new Label("multiCountLabel", "Number of possible answer choices :"));
		
	}
		
	@Override
	public Boolean updateAnswersfromDatabase(int questionId) {
		
		if(questionId < 1)
			return false;
		
		answerModel = modelFactory.getNewAnswerModel();
		
		List<EntityAnswerModel>  listSavedAnswers = questionService.findAnswersByQuestionId(questionId);
		if(listSavedAnswers == null || listSavedAnswers.isEmpty())
			return false;			
		if(multiAnswerModel == null){				 
			multicount = listSavedAnswers.size();// the initial setup to the size of answer list
			if(multicount > 0){//if database has answers - create an array for them
				multiAnswerModel = new ArrayList<AnswerModel>();
			}
			
			for(int i = 0; i < multicount; i++){// since the arrays of different types - must copy comparable fields
				multiAnswerModel.add(CreateAnswerModel(listSavedAnswers.get(i).getAnswer(),
						listSavedAnswers.get(i).getAnswerint(),
						listSavedAnswers.get(i).getCorrect(),
						listSavedAnswers.get(i).getQuestionid()));
			}
		}
		return true;
		
	}
	
	protected void ShowChoices(AjaxRequestTarget target){
		
		updateAnswers();
		CreateSelection();
		target.add(this);
		
	}
	
	protected ListView<AnswerModel>  CreateSelection(){
			
		ListView<AnswerModel> list = new ListView<AnswerModel>("classlistview",
					multiAnswerModel){
		private static final long serialVersionUID = 1L;
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		protected void populateItem(ListItem<AnswerModel> item){
				
				EntityAnswerModel answer = (EntityAnswerModel) item.getModelObject();
		
				item.add(new Label("answertext", "Possible answer " + (item.getIndex() + 1)+ ":"));
				
			
				Radio radioButton = new Radio("radioselect", new PropertyModel<Integer>(answer,"answerint"));
				answer.setAnswerint(item.getIndex());//Necessary to have buttons indexed for the Radio Group control reference
				if(answer.getCorrect()){
					answerModel.setAnswerint(item.getIndex());
				}
				radioButton.setMarkupId("radioselect" + item.getIndex());
				item.add(radioButton);
		
				TextField<String> tf = new TextField<String>("multianswertf", new PropertyModel<String>(answer,"answer"));
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
	
	public Boolean SaveExcercise(QuestionModel questionModel){
				
		
		questionService.addQuestionData(questionModel);
		questionService.removeAnswersByQuestionId(questionModel.getId());
		int correctMultipleChoiceAnswerID = (Integer) multipleChoiceRadioGroup.getModelObject();
		for (AnswerModel md : multiAnswerModel){
			md.setQuestionid(questionModel.getId());

			if (md.getAnswerint() == correctMultipleChoiceAnswerID) {
				md.setCorrect(true);
			}
			String Answer = md.getAnswer();
			if((Answer != null && !Answer.isEmpty())){// add only answers from the filled fields
				
				questionService.createAnswer(md);
				questionService.addOrUpdateAnswer(md);			
			}	
		}
		return true;
	}
	public Boolean Validate(){
		if(!super.Validate())
			return false;
		
		int correctMultipleChoiceAnswerID = 0;
		try{
			correctMultipleChoiceAnswerID = (Integer) multipleChoiceRadioGroup.getModelObject();
			//make sure the selection has its answer
			AnswerModel md = multiAnswerModel.get(correctMultipleChoiceAnswerID);// retrieve the selected answer
			String Answer = md.getAnswer();
			if((Answer == null || Answer.isEmpty()&& md.getAnswerint() == correctMultipleChoiceAnswerID)){
				Session.get().error("Your selection must have a corresponding answer");
				return false;				
			}		
		}catch (Exception e){
			Session.get().error("You must select a radio button ");
			return false;
		}
		
		return true;
		
	}
}
		

