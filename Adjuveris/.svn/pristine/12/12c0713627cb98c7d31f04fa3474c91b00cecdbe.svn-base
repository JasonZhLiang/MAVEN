package com.linguaclassica.exercises;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import com.linguaclassica.model.QuestionModel;

public class FillInBlankContainer extends ExercisesContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1152010076368041398L;

	public FillInBlankContainer(String id, int questionId) {
		super(id, questionId);
		// TODO Auto-generated constructor stub
	}

	public FillInBlankContainer(String id, IModel<?> model, int questionId) {
		super(id, model, questionId);
		// TODO Auto-generated constructor stub
	}
	
	public void CreateExcercise(int questionId){
		
		add(new TextField<String>("fillquestiontf", 
				new PropertyModel<String>(this,"question")));
	
		setOutputMarkupId(true);

	}
	
	public Boolean SaveExcercise(QuestionModel questionModel){
		
		questionService.addQuestionData(questionModel);// must be first here
		questionService.removeAnswersByQuestionId(questionModel.getId());
	
		answerModel.setAnswer((questionModel.getWord()== null)? "" : questionModel.getWord());//wordshowtf.getModelObject());
		answerModel.setQuestionid(questionModel.getId());
		answerModel.setCorrect(true);
		questionService.addOrUpdateAnswer(answerModel);
	
		return true;
	}

}
