package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.StudentAnswerModel;

/**
 * Entity implementation of the QuestionModel interface
 * @author Gene
 * Updated: Changed proficiency from string to integer
 */
@Entity
@Table(name="student_answers")
public class EntityStudentAnswerModel implements StudentAnswerModel {
	private static final long serialVersionUID = 5529447539783591321L;

	@Id
	@GeneratedValue()
	private Integer id;
	private Integer assignmentfactid;
	private Integer questionid;
	private String studentanswer;
	private Integer calculatedweight;
	private Integer score;
	
	public EntityStudentAnswerModel() {
	}

	public EntityStudentAnswerModel(
			Integer id, 
			Integer assignmentfactid,
			Integer questionid, 
			String studentanswer, 
			Integer calculatedweight, 
			Integer score) {
		this.id = id;
		this.assignmentfactid = assignmentfactid;
		this.questionid = questionid;
		this.studentanswer = studentanswer;
		this.calculatedweight = calculatedweight; 
		this.score = score;
	}

	@Override
	public Integer getId() {
		return id;
	}
	@Override
	public Integer getAssignmentFactId() {
		return assignmentfactid;
	}

	public void setAssignmentFactId(Integer assignmentfactid) {
		this.assignmentfactid = assignmentfactid;
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
	public void setStudentAnswer(String studentanswer) {
		this.studentanswer = studentanswer;
	}
	@Override
	public String getStudentAnswer() {
		return studentanswer;
	}
	
	@Override
	public Integer getCalculatedWeight() {
		return calculatedweight;
	}
	
	@Override
	public void setCalculatedWeight(Integer calculatedweight) {
		this.calculatedweight = calculatedweight;
	}
	
	@Override
	public Integer getScore() {
		return score;
	}
	
	@Override
	public void setScore(Integer score) {
		this.score = score;
	}

}




