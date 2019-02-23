package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.TextPassageModel;

/**
 * Entity implementation of the ExerciseModel interface
 * @author Gene
 *
 */
@Entity
@Table(name="passages")
public class EntityTextPassageModel implements TextPassageModel
{
	private static final long serialVersionUID = 5529447539783591321L;

	@Id
	@GeneratedValue()
	private Integer id;	
	private String textcontent;
	private int exerciseid;
	private String textinfo;
	
	
	public EntityTextPassageModel() {
	}

	public EntityTextPassageModel(Integer id, String textcontent, Integer exerciseid, Integer questionid,String  textinfo) {
		this.id = id;
		this.textcontent = textcontent;
		this.exerciseid = exerciseid;
		this.textinfo = textinfo;
	}

	@Override
	public Integer getId() {
		return id;
	}


	@Override
	public String getTextcontent() {
		return textcontent;
	}

	@Override
	public void setTextcontent(String textcontent) {
		this.textcontent = textcontent;
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
	public String getTextinfo() {
		return textinfo;
	}

	@Override
	public void setTextinfo(String textinfo) {
		this.textinfo = textinfo;
	}

}



