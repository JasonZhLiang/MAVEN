package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.AnswerModel;

/**
 * Entity implementation of the ExerciseModel interface
 * @author Gene
 *
 */
@Entity
@Table(name="answers")
public class EntityAnswerModel implements AnswerModel
{
	private static final long serialVersionUID = 5529447539783591321L;

	@Id
	@GeneratedValue()
	private Integer id;
	
	private Integer questionid;
	private String answer;
	private Integer answerint;
	private Boolean correct;
	
	public EntityAnswerModel() {
	}

	public EntityAnswerModel(Integer id, Integer questionid, String answer, Integer answerint, Boolean correct) {
		this.id = id;
		this.questionid = questionid;
		this.answer = answer;
		this.answerint = answerint;
		this.correct = correct;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public Integer getQuestionid() {
		return questionid;
	}

	@Override
	public void setQuestionid(Integer questionid) {
		this.questionid = questionid;
	}

	@Override
	public String getAnswer() {
		return answer;
	}

	@Override
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public Integer getAnswerint() {
		return answerint;
	}

	@Override
	public void setAnswerint(Integer answerint) {
		this.answerint = answerint;
	}
	
	@Override
	public Boolean getCorrect() {
		return correct;
	}

	@Override
	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}
}




