package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.QuestionQuestionGroupModel;

/**
 * Entity implementation of the ExerciseModel interface
 * @author Gene
 *
 */
@Entity
@Table(name="question_questiongroups")
public class EntityQuestionQuestionGroupModel implements QuestionQuestionGroupModel
{
	private static final long serialVersionUID = 5529447539783591321L;

	@Id
	@GeneratedValue()
	private Integer id;
	private Integer questiongroupId;
	private Integer questionid;
	
	public EntityQuestionQuestionGroupModel() {
	}

	public EntityQuestionQuestionGroupModel(Integer id, Integer questiongroupId, Integer questionid) {
		this.id = id;
		this.questiongroupId = questiongroupId;
		this.questionid = questionid;
	}

	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public Integer getQuestionGroupId() {
		return questiongroupId;
	}
	
	@Override
	public void setQuestionGroupId(Integer questiongroupId) {
		this.questiongroupId = questiongroupId;
	}
	
	@Override
	public Integer getQuestionid() {
		return questionid;
	}
	
	@Override
	public void setQuestionid(Integer questionid) {
		this.questionid = questionid;
	}

}



