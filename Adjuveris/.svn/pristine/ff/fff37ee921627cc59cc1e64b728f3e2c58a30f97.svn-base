package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.QuestionGroupModel;

/**
 * Entity implementation of the ExerciseModel interface
 * @author Gene
 *
 */
@Entity
@Table(name="question_groups")
public class EntityQuestionGroupModel implements QuestionGroupModel
{
	private static final long serialVersionUID = 5529447539783591321L;

	@Id
	@GeneratedValue()
	private Integer id;
	@SuppressWarnings("unused")
	private Integer exerciseid;
	
	public EntityQuestionGroupModel() {
	}

	public EntityQuestionGroupModel(Integer id) {
		this.id = id;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setExerciseid(Integer exerciseid) {
		this.exerciseid = exerciseid;
		
	}

}



