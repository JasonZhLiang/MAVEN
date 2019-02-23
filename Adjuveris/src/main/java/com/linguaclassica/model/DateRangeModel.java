package com.linguaclassica.model;
import java.io.Serializable;

/**
 * Business layer interface for EntityClassModel entity.
 * 
 */

public interface DateRangeModel extends Serializable {

/* ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	instid INTEGER(16) NOT NULL,
	studentsTerms INTEGER(1) NOT NULL DEFAULT 1,
	instadminTerms INTEGER(2) NOT NULL DEFAULT -1,
	
	UNIQUE KEY unq_instid_idx (instid),
	CONSTRAINT fk_daterange_idx
	FOREIGN KEY (instid) REFERENCES institutions (id)*/ 

	// for JUnit tests
	
	Integer getId();

	void setId(Integer ID);
	
	Integer getInstId();

	void setInstId(Integer instid);

	Integer getStudentsTerms();

	void setStudentsTerms(Integer studentsTerms);

	Integer getInstAdminTerms();

	void setInstAdminTerms(Integer instadminTerms);
	
}