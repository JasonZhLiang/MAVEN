package com.linguaclassica.exercises;

import java.util.Arrays;
import java.util.List;

import com.linguaclassica.model.AnswerModel;
import com.linguaclassica.model.QuestionModel;

public interface Exercise {
	
	void CreateExcercise(int questionId);
	
	Boolean SaveExcercise(QuestionModel questionModel);
	
	void ResetToDefaults();

	void updateAnswers();
	
	Boolean updateAnswersfromDatabase(int questionId );
	
	List<AnswerModel> getmultiAnswerModel();
		
	void  setmultiAnswerModel(List<AnswerModel> multiAnswerModel);
	
	static final public Integer  numberofLatinParserModels = 7;
	static final public Integer  numberofGreekParserModels = 8;
	static final public Integer  indexLatinLabel = 5;
	static final public Integer  indexLatinText = 6;
	static final public Integer  indexGreekLabel = 6;
	static final public Integer  indexGreekText = 7;
	static final public	Integer defaultAnswerRows = 4;
	
	static final List<Integer> multiChoiceNumber = Arrays.asList(new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20});

	
}

