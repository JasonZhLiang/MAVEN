package com.linguaclassica.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.linguaclassica.entity.EntityExamModel;
import com.linguaclassica.model.ExamModel;

@Repository
public class ExamRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	public void createExam(EntityExamModel examModel) {
		entityManager.persist(examModel);
		
	}
	
	public void updateExam(EntityExamModel examModel) {
		entityManager.merge(examModel);
	}
	
	public EntityExamModel getExam(Integer contentid) {
		ExamModel exam = entityManager.find(EntityExamModel.class, contentid);
		
		if(exam == null) {
			throw new NoResultException("Exam does not exist");
		}
		
		return (EntityExamModel) exam;
	}
	
	public void deleteExam(EntityExamModel exam) {
		entityManager.remove(exam);
	}
	
	@SuppressWarnings("unchecked")
	public List<EntityExamModel> getAllExams() {
		Query query = entityManager.createQuery("SELECT * FROM Exams");
		List<EntityExamModel> examList = (List<EntityExamModel>) query.getResultList();
		return examList;
	}
	
}
