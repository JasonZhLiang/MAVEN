package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.QuestionOptionModel;

/**
 * Entity implementation of the ExerciseModel interface
 * @author Gene
 *
 */
@Entity
@Table(name="question_options")
public class EntityQuestionOptionModel implements QuestionOptionModel
{
	private static final long serialVersionUID = 5529447539783591321L;

	@Id
	@GeneratedValue()
	private Integer id;
	
	private Integer questionid;
	private String questionoption;
	
	public EntityQuestionOptionModel() {
	}

	public EntityQuestionOptionModel(Integer id, Integer questionid, String questionoption) {

		this.id = id;		
		this.questionid = questionid;
		this.questionoption = questionoption;
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
	public String getQuestionoption() {
		return questionoption;
	}

	@Override
	public void setQuestionoption(String questionoption) {
		this.questionoption = questionoption;
	}
}



