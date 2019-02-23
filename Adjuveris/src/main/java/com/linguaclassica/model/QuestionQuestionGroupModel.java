package com.linguaclassica.model;

import java.io.Serializable;



/**
 * Business layer interface for EntityClassModel entity.
 */
public interface QuestionQuestionGroupModel extends Serializable {

	Integer getId();

	Integer getQuestionGroupId();

	void setQuestionGroupId(Integer questiongroupId);

	Integer getQuestionid();

	void setQuestionid(Integer questionid);

}
