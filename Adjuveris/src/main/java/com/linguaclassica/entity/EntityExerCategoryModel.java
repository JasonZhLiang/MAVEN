package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.ExerCategoryModel;

/**
 * Entity implementation of the ExerciseModel interface
 * @author Gene
 *
 */
@Entity
@Table(name="exercisecategories")
public class EntityExerCategoryModel implements ExerCategoryModel
{
	private static final long serialVersionUID = 5529447539783591321L;

	@Id
	@GeneratedValue()
	private Integer id;

	private String exercisename;
	private String urlbase;
	private String exercisetype;
	private String comment;
	
	public EntityExerCategoryModel() {
	}

	public EntityExerCategoryModel(Integer id, String exercisename, String urlbase, String exercisetype, 
			String comment) {
		this.id = id;
		this.exercisename = exercisename;
		this.urlbase = urlbase;
		this.exercisetype = exercisetype;
		this.comment = comment;
	}

	@Override
	public Integer getId() {
		return id;
	}	
	
	@Override
	public String getExerciseName() {
		return exercisename;
	}

	@Override
	public void setExerciseName(String exercisename) {
		this.exercisename = exercisename;
	}
	
	@Override
	public String getUrlbase() {
		return urlbase;
	}
		
	@Override
	public void setUrlbase(String urlbase) {
		this.urlbase = urlbase;
	}
	
	@Override
	public String getExercisetype() {
		return exercisetype;
	}
		
	@Override
	public void setExercisetype(String exercisetype) {
		this.exercisetype = exercisetype;
	}
	
	@Override
	public String getComment() {
		return comment;
	}
		
	@Override
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}



