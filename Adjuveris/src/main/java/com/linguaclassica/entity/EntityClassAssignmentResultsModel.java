package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.ClassAssignmentResultsModel;

/**
 * Entity implementation of the ClassAssignmentResultsModel interface
 * @author Kwirtanen
 */

@Entity
@Table(name="class_assignment_results")
public class EntityClassAssignmentResultsModel implements ClassAssignmentResultsModel
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue()
	private Integer id;
	
	private Integer assignmentId;
	private Integer studentCount;
	private Float total;
	private Float average;

	private int inflecttotal;
	private int inflectright;
	private int syntaxtotal;
	private int syntaxright;
	private int vocabtotal;
	private int vocabright;
	private int comprehentotal;
	private int comprehenright;
	
	public EntityClassAssignmentResultsModel() {
	}
	
	public EntityClassAssignmentResultsModel(Integer assignmentId, 
			Integer studentCount, 
			Float total, 
			Float average,
			int inflecttotal,
			int inflectright,
			int syntaxtotal,
			int syntaxright,
			int vocabtotal,
			int vocabright,
			int comprehentotal,
			int comprehenright) {
		this.assignmentId = assignmentId;
		this.studentCount = studentCount;
		this.total = total;
		this.average = average;
		this.inflecttotal = inflecttotal;
		this.inflectright = inflectright;
		this.syntaxtotal = syntaxtotal;
		this.syntaxright = syntaxright;
		this.vocabtotal = vocabtotal;
		this.vocabright = vocabright;
		this.comprehentotal = comprehentotal;
		this.comprehenright = comprehenright;
	}
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public Integer getAssignmentId() {
		return assignmentId;
	}
	

	@Override
	public void setAssignmentId(Integer assignmentId) {
		this.assignmentId = assignmentId;
	}

	@Override
	public Integer getStudentCount() {
		return studentCount;
	}

	@Override
	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}

	@Override
	public Float getTotal() {
		return total;
	}

	@Override
	public void setTotal(Float total) {
		this.total = total;
	}

	@Override
	public Float getAverage() {
		return average;
	}

	@Override
	public void setAverage(Float average) {
		this.average = average;
	}

	@Override
	public int getInflecttotal() {
		return inflecttotal;
	}

	@Override
	public void setInflecttotal(int inflecttotal) {
		this.inflecttotal = inflecttotal;
	}

	@Override
	public int getInflectright() {
		return inflectright;
	}

	@Override
	public void setInflectright(int inflectright) {
		this.inflectright = inflectright;
	}

	@Override
	public int getSyntaxtotal() {
		return syntaxtotal;
	}
	
	@Override
	public void setSyntaxtotal(int syntaxtotal) {
		this.syntaxtotal = syntaxtotal;
	}
	
	@Override
	public int getSyntaxright() {
		return syntaxright;
	}
	
	@Override
	public void setSyntaxright(int syntaxright) {
		this.syntaxright = syntaxright;
	}

	@Override
	public int getVocabtotal() {
		return vocabtotal;
	}
	
	@Override
	public void setVocabtotal(int vocabtotal) {
		this.vocabtotal = vocabtotal;
	}
	
	@Override
	public int getVocabright() {
		return vocabright;
	}
	
	@Override
	public void setVocabright(int syntaxvocab) {
		this.vocabright = syntaxvocab;
	}

	@Override
	public int getComprehentotal() {
		return comprehentotal;
	}
	
	@Override
	public void setComprehentotal(int comprehentotal) {
		this.comprehentotal = comprehentotal;
	}
	
	@Override
	public int getComprehenright() {
		return comprehenright;
	}
	
	@Override
	public void setComprehenright(int comprehenright) {
		this.comprehenright = comprehenright;
	}

}
