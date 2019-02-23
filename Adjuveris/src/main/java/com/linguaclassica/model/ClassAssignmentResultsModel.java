package com.linguaclassica.model;

import java.io.Serializable;

/**
 * Business layer interface for class assignment results entity
 * corresponding to the class_assignment_results look-up table.
 * @author Kwirtanen
 */

public interface ClassAssignmentResultsModel extends Serializable{

	/**
	 * Returns the unique ID field for this ClassAssignmentResults model. 
	 * @return id
	 */
	public Integer getId();
	
	
	Integer getAssignmentId();
	
	void setAssignmentId(Integer assignmentId);
	
	
	Integer getStudentCount();
	
	void setStudentCount(Integer studentCount);
	
	
	Float getTotal();
	
	void setTotal(Float total);
	
	
	Float getAverage();
	
	void setAverage(Float average);


	int getInflecttotal();


	void setInflecttotal(int inflecttotal);


	int getInflectright();


	void setInflectright(int inflectright);


	int getSyntaxtotal();


	void setSyntaxtotal(int syntaxtotal);


	int getSyntaxright();


	void setSyntaxright(int syntaxright);


	int getVocabtotal();


	void setVocabtotal(int vocabtotal);


	int getVocabright();


	void setVocabright(int vocabright);


	int getComprehentotal();


	void setComprehentotal(int comprehentotal);


	int getComprehenright();


	void setComprehenright(int comprehenright);

}
