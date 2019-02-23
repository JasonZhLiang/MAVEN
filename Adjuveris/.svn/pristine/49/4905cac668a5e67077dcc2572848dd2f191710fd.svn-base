package com.linguaclassica.exercises;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.linguaclassica.model.QuestionModel;

public class TrueFalseContainer extends ExercisesContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4616680708379051745L;
	
	static RadioGroup<Object> truefalseradio = null;

	public TrueFalseContainer(String id, int questionId) {
		super(id, questionId);
	}

	public TrueFalseContainer(String id, IModel<?> model, int questionId) {
		super(id, model, questionId);
	}
	
	public Boolean updateAnswersfromDatabase(int questionId) {
		
		if(questionId < 1)
			return false;
		answerModel  = questionService.findCorrectAnswerByQuestionId(questionId);	
		if(answerModel == null )
			return false;	
		return true;
	}
	public void CreateExcercise(int questionId){
	
		add(new TextField<String>("tftext",
				new PropertyModel<String>(this,"question")));		
		truefalseradio = new RadioGroup<Object>("truefalse", 
				new PropertyModel<Object>(answerModel, "answerint"));				
		truefalseradio.add(new Radio<Integer>("1", new Model<Integer>(1)));
		truefalseradio.add(new Radio<Integer>("0", new Model<Integer>(0)));
		truefalseradio.setModelObject(answerModel.getAnswerint());
		truefalseradio.setOutputMarkupId(true);
		add(truefalseradio);
		setOutputMarkupId(true);

	}
	
	public Boolean SaveExcercise(QuestionModel questionModel){
		
		questionService.addQuestionData(questionModel);
		questionService.removeAnswersByQuestionId(questionModel.getId());
		
		answerModel.setAnswer((answerModel.getAnswerint() == 1) ? "true" : "false");
		
		answerModel.setQuestionid(questionModel.getId());
		answerModel.setCorrect(true);
		System.out.println("answerModel.getAnswerint() = " + answerModel.getAnswerint());
		questionService.addOrUpdateAnswer(answerModel);
	
		return true;
	}
	
	public Boolean Validate(){
		if(!super.Validate())
			return false;
		
		if(truefalseradio.getModelObject() == null)	{
		//if(answerModel.getAnswerint() == null)	{
			Session.get().error("You must select a radio button ");
			return false;
		}
		return true;
		
	}
}
