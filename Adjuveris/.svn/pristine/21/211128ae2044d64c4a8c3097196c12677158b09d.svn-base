package com.linguaclassica.exercises;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import com.linguaclassica.entity.EntityAnswerModel;
import com.linguaclassica.model.AnswerModel;
import com.linguaclassica.model.QuestionModel;

public class MultiSelectContainer extends ExercisesContainer {
	private static final long serialVersionUID = 968718221691224053L;
	/**
	 * 
	 */
	static DropDownChoice<Integer> multicountlist;
	static TextField<String> multiquestiontf;
	//static TextField<String> multianswertf;

	
	public MultiSelectContainer(String id, int questionId) {
		super(id, questionId);
		// TODO Auto-generated constructor stub
	}

	public MultiSelectContainer(String id, IModel<?> model, int questionId) {
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
	
	public Boolean SaveExcercise(QuestionModel questionModel){
		
	
		/////////////////////////////////
		questionService.addQuestionData(questionModel);
		questionService.removeAnswersByQuestionId(questionModel.getId());
		String Answer = null;
		for (AnswerModel md : multiAnswerModel){
			Answer = md.getAnswer();
			if((Answer != null && !Answer.isEmpty())){// add only answers from the filled fields
				md.setQuestionid(questionModel.getId());
				md.setAnswerint((md.getCorrect()) ? 1 : 0);
				questionService.createAnswer(md);
				questionService.addOrUpdateAnswer(md);
			}
		}
		return true;
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
	
		
		protected void populateItem(ListItem<AnswerModel> item){
			
			EntityAnswerModel answer = (EntityAnswerModel) item.getModelObject();
	
			item.add(new Label("answertext", "Possible answer " + (item.getIndex() + 1)+ ":"));
			
			CheckBox check = new CheckBox("checkAnswer", new PropertyModel<Boolean>(answer,"correct"));//check box need boolean value
				
				check.setMarkupId("checkAnswer" + item.getIndex());
				// the override of onUpdate method needed for proper binding to PropertyModel
				// DO NOT remove or comment out
				check.add( new AjaxFormComponentUpdatingBehavior("onclick") {
					private static final long serialVersionUID = 1L;
					@Override
					protected void onUpdate(AjaxRequestTarget t){
					}					
				});
			check.setOutputMarkupId(true);
			item.add(check);		
		
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
	public Boolean Validate(){
		
		if(!super.Validate())
			return false;
		
		boolean check = false;	
		boolean oneAnswer = false;	
		String Answer = null;	
	//validate input
		for (AnswerModel md : multiAnswerModel){
			Answer = md.getAnswer();
			if(Answer != null && !Answer.isEmpty()){
				oneAnswer = true;
				if(md.getCorrect()){
					check = true;//make sure at least one check box is marked
				}
			}
			else if(md.getCorrect()){//if selection has no actual answer
				Session.get().error("Your selection must have a corresponding answer");
				return false;				
			}
		}
		if(!check){
			Session.get().error("You must select at least one check box");
			return false;		
		}
		if(!oneAnswer){
			Session.get().error("You must write in at least one answer");
			return false;		
		}
	return true;
		
	}
	
}
