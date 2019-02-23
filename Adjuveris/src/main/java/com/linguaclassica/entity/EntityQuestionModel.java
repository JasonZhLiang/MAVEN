package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.QuestionModel;

/**
 * Entity implementation of the QuestionModel interface
 * @author Gene
 * Updated: Changed proficiency from string to integer
 * Updated: Changed weight from string to integer 16-01-2016
 */
@Entity
@Table(name="questions")
public class EntityQuestionModel implements QuestionModel {
	private static final long serialVersionUID = 5529447539783591321L;

	@Id
	@GeneratedValue()
	private Integer id;
	private Integer exerciseid;
	private Integer qnumber;
	private String questiontype; 
	private String question;
	private Integer passageid;
	private Integer proficiencyindex;  
	private Integer weight;
	private Boolean swassessed;

	private String word;
	private Integer linenumber;
	private Integer wordnumber;
	private Integer strword;
	private Integer endword;
	private Integer questiongroupid;
	
	
	public EntityQuestionModel() {
	}

	public EntityQuestionModel(
			Integer id, 
			Integer exerciseid, 
			Integer qnumber, 
			String questiontype, 
			String question,
			Integer passageid,
			Integer proficiencyindex, 
			Integer weight, 
			Boolean swassessed,
			String  word,
			Integer linenumber,
			Integer strword,
			Integer endword,
			Integer questiongroupid) {
		this.id = id;
		this.exerciseid = exerciseid;
		this.qnumber = qnumber;
		this.questiontype = questiontype; 
		this.question = question;
		this.passageid = passageid;
		this.proficiencyindex = proficiencyindex; 
		this.weight = weight;
		this.swassessed = swassessed;
	
		this.word = word;
		this.linenumber = linenumber;
		this.strword = strword;
		this.endword = endword;
		this.questiongroupid = questiongroupid;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public Integer getExerciseid() {
		return exerciseid;
	}

	@Override
	public void setExerciseid(Integer exerciseid) {
		this.exerciseid = exerciseid;
	}

	@Override
	public Integer getQnumber() {
		return qnumber;
	}

	@Override
	public void setQnumber(Integer qnumber) {
		this.qnumber = qnumber;
	}
	
	@Override
	public String getQuestiontype() { 
		return questiontype;
	}
	
	@Override
	public void setQuestiontype(String questiontype) { 
		this.questiontype = questiontype;
	}
	
	@Override
	public String getQuestion() {
		return question;
	}
	
	@Override
	public void setQuestion(String question) {
		this.question = question;
	}	
	
	@Override
	public Integer getPassageid() {
		return passageid;
	}
	
	@Override
	public void setPassageid(Integer passageid) {
		this.passageid = passageid;
	}	
	
	@Override
	public Integer getProficiencyindex() {
		return proficiencyindex;
	}
	
	@Override
	public void setProficiencyindex(Integer proficiencyindex) {
		this.proficiencyindex = proficiencyindex;
	}
	
	@Override
	public Integer getWeight() {
		return weight;
	}
	
	@Override
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
	@Override
	public Boolean getSwassessed() {
		return swassessed;
	}
	
	@Override
	public void setSwassessed(Boolean swassessed) {
		this.swassessed = swassessed;
	}

	
	@Override
	public String getWord() {
		return word;
	}

	@Override
	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public Integer getLinenumber() {
		return linenumber;
	}

	@Override
	public void setWordnumber(Integer wordnumber) {
		this.wordnumber = wordnumber;
	}

	@Override
	public Integer getWordnumber() {
		return wordnumber;
	}

	@Override
	public void setLinenumber(Integer linenumber) {
		this.linenumber = linenumber;
	}

	@Override
	public Integer getStrword() {
		return strword;
	}

	@Override
	public void setStrword(Integer strword) {
		this.strword = strword;
	}

	@Override
	public Integer getEndword() {
		return endword;
	}

	@Override
	public void setEndword(Integer endword) {
		this.endword = endword;
	}

	@Override
	public Integer getQuestionGroupid()
	{
		return questiongroupid;
	}

	@Override
	public void setQuestionGroupid(Integer questiongroupid) {
		this.questiongroupid = questiongroupid;
	}
}




