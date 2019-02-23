package com.linguaclassica.exercises;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linguaclassica.model.QuestionModel;
import com.linguaclassica.model.TextPassageModel;
import com.linguaclassica.service.ExerciseService;
import com.linguaclassica.service.QuestionService;

@Component
public class OrderPassageQuestions {
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	ExerciseService exerciseService;	
	
	void RenumberPassage(int passageid, String passagecontent) {
		
		TextPassageModel textPassageModel = questionService.findPassageById(passageid);
		List<QuestionModel> questionModelList = questionService.findQuestionByPassageId(passageid);
		int listlen = questionModelList.size();
		int passagelen = passagecontent.length();
		int start = 0;
		int parenopen = 0;
		int parenclos = 0;
		for (int i = 0; i < listlen; i++) {
			parenopen = passagecontent.indexOf("{",start);
			parenclos = passagecontent.indexOf("}",start);
			String startstr = passagecontent.substring(0,parenopen + 1);
			String endstr = passagecontent.substring(parenclos,passagelen);
			passagecontent = startstr + questionModelList.get(i).getQnumber() + endstr;
			passagelen = passagecontent.length();
			start = parenclos + 1;
		}
		textPassageModel.setTextcontent(passagecontent);
		exerciseService.updateTextPassage(textPassageModel);
	}
}
