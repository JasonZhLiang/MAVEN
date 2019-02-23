package com.linguaclassica.exercises;

import java.io.Serializable;

public class QuestionData implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String qtype;
	int exerCategory;
	int exerciseId;
	
	public QuestionData() {
		
	}
	
	public QuestionData(String qtype,Integer exerCategory, Integer exerciseId) {
		this.qtype = qtype;
		this.exerCategory = exerCategory;
		this.exerciseId = exerciseId;
	}

	/**
     * @return Currently selected qtype
     */
    public String getQtype()
    {
        return qtype;
    }

    /**
     * @param qtype
     *            The question type that is currently selected
     */
    public void setQtype(String qtype)
    {
        this.qtype = qtype;
    }

	/**
     * @return current exercise category
     */
    public Integer getExerCategory()
    {
        return exerCategory;
    }

    /**
     * @param exerCategory
     *            The question type that is currently selected
     */
    public void setExerCategory(Integer exerCategory)
    {
        this.exerCategory = exerCategory;
    }

	/**
     * @return current exercise id
     */
    public Integer getExerciseId()
    {
        return exerciseId;
    }

    /**
     * @param exerciseId
     *            The current exerciseId
     */
    public void setExerciseId(Integer exerciseId)
    {
        this.exerciseId = exerciseId;
    }
}