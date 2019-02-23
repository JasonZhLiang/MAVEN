package com.linguaclassica.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.linguaclassica.entity.EntityProficiencyModel;
import com.linguaclassica.entity.EntityQuestionModel;
import com.linguaclassica.model.QuestionModel;
import com.linguaclassica.model.ProficiencyModel.Proficiency;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class QuestionRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * 
	 * This method creates a question. If no exceptions are thrown the method is successful.
	 * 
	 * @param questionModel
	 * @throws PersistenceException if an unspecified error occurs.
	 */
	public void createQuestion(EntityQuestionModel questionModel) 
			throws EntityExistsException, PersistenceException {

		try {
				entityManager.persist(questionModel);
				System.out.println("after questionModel" +questionModel.getId());
			} 
		catch (PersistenceException e) {
				
				if (e.getCause() instanceof ConstraintViolationException) {
					throw new EntityExistsException(e);
				}
				
				// Didn't match, throw the original exception
				throw e;
			}
		return;	
	}
		
	
	/**
	 * 
	 * This method creates a question where question number is not included in questionModel. 
	 * If no exceptions are thrown the method is successful.	 * 
	 * @param questionModel
	 * @throws PersistenceException if an unspecified error occurs.
	 */
	public void addQuestionData(EntityQuestionModel questionModel) throws PersistenceException {
		
		EntityQuestionModel existingQuestionModel = null;
		try {
			
			Query query = entityManager.createQuery(
					"SELECT COUNT(q) FROM EntityQuestionModel q WHERE q.id = :qId ");
			query.setParameter("qId", questionModel.getId());
				
			long cnt = (Long) query.getSingleResult();
			
			if (cnt > 0){
				Query queryget = entityManager.createQuery(
						"SELECT q FROM EntityQuestionModel q WHERE q.id = :qId");

				queryget.setParameter("qId",  questionModel.getId());
				existingQuestionModel = (EntityQuestionModel) queryget.getSingleResult();
				existingQuestionModel.setExerciseid(questionModel.getExerciseid());
				existingQuestionModel.setQnumber(questionModel.getQnumber());
				existingQuestionModel.setQuestiontype(questionModel.getQuestiontype()); //added 24-03-2015
				existingQuestionModel.setQuestion(questionModel.getQuestion());
				existingQuestionModel.setPassageid(questionModel.getPassageid());
				existingQuestionModel.setProficiencyindex(questionModel.getProficiencyindex());
				existingQuestionModel.setWeight(questionModel.getWeight());
				existingQuestionModel.setSwassessed(questionModel.getSwassessed());

				entityManager.persist(existingQuestionModel);
				
			}else{
				entityManager.persist(questionModel);
			}
		} catch (PersistenceException e) {			
			throw e;
		}
		}
	
	
	public void addOrUpdateQuestion(EntityQuestionModel questionModelIn) throws PersistenceException {
		
		
			try {
				if (questionModelIn.getId() != null) {
					QuestionModel questionModel = (QuestionModel) entityManager.find(EntityQuestionModel.class,questionModelIn.getId());
					System.out.println("QuestionRepository.addOrUpdateQuestion, questionModelIn.getId() = " +
							questionModelIn.getId());
					
					//System.out.println("QuestionRepository:  questionModel not null");
					questionModel.setExerciseid(questionModelIn.getExerciseid());
					questionModel.setQnumber(questionModelIn.getQnumber());
					questionModel.setQuestiontype(questionModelIn.getQuestiontype());
					questionModel.setQuestion(questionModelIn.getQuestion());
					questionModel.setPassageid(questionModelIn.getPassageid());
					questionModel.setProficiencyindex(questionModelIn.getProficiencyindex());
					questionModel.setWeight(questionModelIn.getWeight());
					questionModel.setSwassessed(questionModelIn.getSwassessed());
	//				questionModel.setTextinfo(questionModelIn.getTextinfo());
					questionModel.setWord(questionModelIn.getWord());
					questionModel.setLinenumber(questionModelIn.getLinenumber());
					questionModel.setWordnumber(questionModelIn.getWordnumber());
					questionModel.setStrword(questionModelIn.getStrword());
					questionModel.setEndword(questionModelIn.getEndword());
					entityManager.persist(questionModel);
				}
				else {
					//System.out.println("questionModel is null"  + questionModelIn.getId());
					entityManager.persist(questionModelIn);
					//System.out.println("questionModel is null"  + questionModelIn.getId());
				}
		}
		catch (NoResultException nor) {
			throw new NoResultException();
		}
		catch (EntityAlreadyExistsException eae) {
			throw new EntityAlreadyExistsException();
		}
		
	}
	
	public Integer findLastQuestionId() {
		int questid = 0;
		try {
			// get the id for the last question created
			Query query = entityManager.createQuery(
				"SELECT q FROM EntityQuestionModel q order by q.id desc" );			
			@SuppressWarnings("unchecked")
			List<EntityQuestionModel> questlist = query.setMaxResults(1).getResultList();
			questid = questlist.get(0).getId();
			return questid;
			
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/*
	 * @param questionid
	 * @return List of questions
	 * permits null check of questions
	 */
	@SuppressWarnings("unchecked")
	public List<EntityQuestionModel> getListOfQuestionsById(int questionid) {
		List<EntityQuestionModel> questionList = new ArrayList<EntityQuestionModel>();
		try {
			Query query = entityManager.createQuery(
				"SELECT q FROM EntityQuestionModel q where q.id = :qid");
			
			query.setParameter("qid",questionid);
			
			questionList = (List<EntityQuestionModel>) query.getResultList();
		
			return questionList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/*
	 * @param questiongroupid
	 * @return List of questions
	 * permits null check of questions
	 */
	@SuppressWarnings("unchecked")
	public List<QuestionModel> getListOfQuestionsByQuestionGroupId(int questGroupId) {
		List<QuestionModel> questionList = new ArrayList<QuestionModel>();
		try {
			Query query = entityManager.createQuery(
				"SELECT q FROM EntityQuestionModel q where q.questiongroupid = :qgid ORDER BY q.id ASC");
			
			query.setParameter("qgid",questGroupId);
			
			questionList = (List<QuestionModel>) query.getResultList();
		
			return questionList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/*
	 * @param questiongroupid
	 * @return List of question ids by question group id
	 * permits null check of questions
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getListOfQuestionIdsByQuestionGroupId(int questGroupId) {
		//List<Integer> questionList = new ArrayList<Integer>();
		try {
			Query query = entityManager.createQuery(
				"SELECT q:id FROM EntityQuestionModel q where q.questiongroupid = :qgid ORDER BY q.id ASC");
			
			query.setParameter("qgid",questGroupId);
			
			List<Integer> questionList = (List<Integer>) query.getResultList();
		
			return questionList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	public Integer getQuestionGroupListSize() {
		int listlen = 0;
		try {
			Query query = entityManager.createQuery(
				"SELECT q.id FROM EntityQuestionModel q where q.questiongroupid = :pid");
	//		query.setParameter("pid",questiongroupid);
			@SuppressWarnings("unchecked")
			List<Integer> pqlist = query.getResultList();
			Integer previousid = 0;
			for(Integer groupid: pqlist){
				if(previousid != groupid){// count only unique group ids
					previousid = groupid;
					listlen++;
				}
			}
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return listlen;
	}
	
	public Integer getPassageQuestionListSize(int passageid) {
		int listlen = 0;
		try {
			Query query = entityManager.createQuery(
				"SELECT q.id FROM EntityQuestionModel q where q.passageid = :pid");
			query.setParameter("pid",passageid);
			@SuppressWarnings("unchecked")
			List<Integer> pqlist = query.getResultList();
			listlen = pqlist.size();
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return listlen;
	}
	
	/*
	 * @param passageid
	 * @return List of questions by passageid where strword > 0; ordered by strword
	 * permits null check of questions
	 */
	@SuppressWarnings("unchecked")
	public List<EntityQuestionModel> getListOfQuestionsByPassageIdStrword(int passageid) {
		List<EntityQuestionModel> questionList = new ArrayList<EntityQuestionModel>();
		try {
			Query query = entityManager.createQuery(
				"SELECT q FROM EntityQuestionModel q where q.passageid = :pid AND " + 
						"q.strword > 0 ORDER BY q.strword ASC");
			
			query.setParameter("pid",passageid);			
			questionList = (List<EntityQuestionModel>) query.getResultList();
		
			return questionList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Get a list of QuestionModels for an exercise by exercise id
	 * @param exerid
	 * @return list of questions and answers
	 */
	@SuppressWarnings("unchecked")
	public List<QuestionModel> getListOfQuestionByExerID(int exerid) {
		try {
			Query query = entityManager.createQuery(
				"SELECT q FROM EntityQuestionModel q where q.exerciseid = :exerid order by qnumber ASC");
			
			query.setParameter("exerid",exerid);
			
			List<QuestionModel> questList = new ArrayList<QuestionModel>();
	
			questList = (List<QuestionModel>) query.getResultList();
			
			return questList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	
	/**
	 * Get a list of questionIds by exerciseId
	 * @param exerid
	 * @return list of questions and answers
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getListOfQuestionIdsByExerID(int exerid) {
		try {
			Query query = entityManager.createQuery(
				"SELECT q.id FROM EntityQuestionModel q where q.exerciseid = :exerid");
			
			query.setParameter("exerid",exerid);
			
			List<Integer> questIdList = (List<Integer>) query.getResultList();
			
			return questIdList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Get the list of proficiencies evaluated by the assignment
	 * @param exerciseid
	 * @return comma delimited string of proficiencies
	 */
	@SuppressWarnings("unchecked")
	public String getProficienciesByExerciseId(Integer exerciseid) {
		try {
			// get the distinct list of proficiency ids from the questions within an exercise
			Query query = entityManager.createQuery(
				"SELECT DISTINCT q.proficiencyid FROM EntityQuestionModel q " +
					"WHERE q.exerciseid = :exerciseid ");
			query.setParameter("exerciseid", exerciseid);
			
			ArrayList<Integer> proficiencyIdList = new ArrayList<Integer>();
			proficiencyIdList = (ArrayList<Integer>)query.getResultList();
			
			//System.out.println("ExerciseRepository.getProficienciesByAssignment.proficiencyIdList = " + proficiencyIdList);

			// get the corresponding comma delimited string of proficiency names in friendly format
			String proficiencyString = buildProficiencyStringFromIdList(proficiencyIdList);
			
			// return the comma delimited string of proficiency names
			return proficiencyString;
			
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	
	
	
	/**
	 * Build a string of proficiencies from a list of proficiency ids
	 * @param proficiencyIdList
	 * @return comma delimited string of proficiency names
	 */
	public String buildProficiencyStringFromIdList(ArrayList<Integer> proficiencyIdList){
		
		//get the proficiency string for the question
		ArrayList<Integer> proficiencyids = proficiencyIdList;
		ArrayList<String> pn = new ArrayList<String>();
		String pName;
		if (proficiencyids.size() > 0) {
			for (Integer id : proficiencyids) {
				pName = getProficiencyString(id);
				System.out.println(id + ": " + pName);
				pn.add(pName);
			}
		}else{
			System.out.println("proficiency list is empty");
		}
		
		// build the comma delimited string of proficiency friendly names
		String proficiencyString = StringUtils.collectionToCommaDelimitedString(pn);
		
		//System.out.println("QuestionRepository.buildProficiencyStringFromIdList = " + proficiencyString);
		
		return proficiencyString;
	}
	
	/**
	 * Returns friendly proficiency name for a question
	 * @param proficiencyId
	 * @return string
	 */
	public String getProficiencyString(Integer proficiencyId)
	{
		Proficiency proficiency = getProficiencyById(proficiencyId);
		return EntityProficiencyModel.getProficiencyString(proficiency);
	}

	/**
	 * Get a proficiency based on id
	 * @param proficiencyId
	 * @return proficiency
	 */
	public Proficiency getProficiencyById(Integer proficiencyId)
	{
		System.out.println("QuestionRepository, line 419:  proficiencyId = " + proficiencyId);
		TypedQuery<Proficiency> query = entityManager.createQuery(
				"SELECT proficiency FROM EntityProficiencyModel " +
				"WHERE id = :proficiencyId ", Proficiency.class);
		query.setParameter("proficiencyId", proficiencyId);
		Proficiency proficiency = Proficiency.VOCABULARY; // use this as default
		try
		{
			 proficiency = query.getSingleResult();
			 System.out.println("QuestionRepository: getProficiencyById = " + proficiency);
		} catch (Exception e) {
			System.out.println("Could not retrieve the Proficiency for " + proficiencyId.toString() + " with error: " + e);
		}
		return proficiency;
	}
	
	/**
	 * Get a question by it's id
	 * @param id
	 * @return EntityQuestionModel
	 */
	public EntityQuestionModel findQuestionById(int id) {		
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT q from EntityQuestionModel q where q.id = :id");
			query.setParameter("id", id);
			
			EntityQuestionModel questionModel = (EntityQuestionModel) query.getSingleResult();
			
			//System.out.println("questionRepository.findQuestionById");
			
			return questionModel;
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Get a question by it's start word index
	 * @param strword
	 * @return QuestionModel
	 */
	public QuestionModel findQuestionByStrWord(int passageid, int strword) {
		System.out.println("QuestionRepository, line 345:  passageid, strword = " + 
				passageid + ", " + strword);		
		try {
			Query query1 = entityManager.createQuery("SELECT q from EntityQuestionModel q where "+
					"q.passageid = :passId and q.strword = :strwrd");
			query1.setParameter("strwrd", strword);
			query1.setParameter("passId", passageid);
			EntityQuestionModel questModelPrim = (EntityQuestionModel) query1.getSingleResult();
						
			return questModelPrim;
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Get proficiency Id based on proficiency
	 * @param proficiency
	 * @return proficiencyId
	 */
	public Integer getProficiencyIndexByProficiency(Proficiency proficiency) {
		// TODO Auto-generated method stub
		TypedQuery<Integer> query = entityManager.createQuery(
				"SELECT id FROM EntityProficiencyModel " +
				"WHERE proficiency = :proficiency ", Integer.class);
		query.setParameter("proficiency", proficiency);
		Integer proficiencyId=1; // use this as default
		try
		{
			 proficiencyId = query.getSingleResult();
			 //System.out.println("QuestionRepository: getProficiencyIdByProficiency = " + proficiencyId);
		} catch (Exception e) {
			System.out.println("Could not retrieve the ProficiencyId for " + proficiency + " with error: " + e);
		}
		return proficiencyId;
	}
	
	public void deleteQuestion(Integer questionIdIn) {
		
		try {
			Query query = entityManager.createQuery(
				"SELECT q FROM EntityQuestionModel q where q.id = :qid");
		
			query.setParameter("qid", questionIdIn);
	
			EntityQuestionModel questionModel = (EntityQuestionModel) query.getSingleResult();
						
			entityManager.remove(questionModel);
			return;
		}
		catch (NoResultException e) {
			// This exception is thrown by getSingleResult if a match cannot be found.
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

/*	public EntityTextPassageModel findPassageByQuestionId(Integer questionId) 
			throws NoResultException, PersistenceException {	
		EntityTextPassageModel passageModel = null;
		try {
			logger.info("QuestionRepository, line 84:  questionId = " + questionId);
			List<EntityQuestionModel> questionList = new ArrayList<EntityTextPassageModel>();
			Query query = null;
			query = entityManager.createQuery("SELECT que from EntityQuestionModel que where que.questionid = :questionid");
			query.setParameter("questionid", questionId);
			
			questionList = (List<EntityQuestionModel>) query.getResultList();
			logger.info("QuestionRepository, line 95:  passageList.size() = " + passageList.size());
			if (passageList.size() > 0) {
				passageModel = passageList.get(0);
			}
			
			return passageModel;
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	} 

	@SuppressWarnings("unchecked")
	public List<QuestionModel>  findQuestionsByQuestionGroupId(int questiongroupid){

		try {	
			Query query = entityManager.createQuery("SELECT q FROM EntityQuestionModel q where q.questiongroupid = :questiongroupid order by q.id ASC");  //questiongroupid, linenumber, questionid");
			query.setParameter("questiongroupid", questiongroupid);		
			return (List<QuestionModel>) query.getResultList();
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	*/
	
	/**
	 * Get a question by passage id
	 * @param pasageid
	 * @return EntityQuestionModel
	 */
	public List<QuestionModel> findQuestionByPassageId(int passageId) {		
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT q from EntityQuestionModel q where q.passageid = :passageId order by q.id ASC");  //passageid, linenumber, id");
			query.setParameter("passageId", passageId);
			
			@SuppressWarnings("unchecked")
			List<QuestionModel> questionModel = (List<QuestionModel>) query.getResultList();
			
			//System.out.println("questionRepository.findQuestionByPassageId");
			
			return questionModel;
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Get a max question by passage id
	 * @param pasageid
	 * @return EntityQuestionModel
	 */
	@SuppressWarnings("unchecked")
	public Integer getMaxQuestionByPassageId(int passageId) {		
		try {
		//	Query query = null;
		//	query = entityManager.createQuery("SELECT max(q.qnumber) from EntityQuestionModel q where q.passageid = :passageId");
			Query query = entityManager.createQuery("SELECT q FROM EntityQuestionModel q where q.passageId = :passageId");				
				
			query.setParameter("passageId", passageId);
			
		//	Integer maxquestion = (Integer) query.getSingleResult();
			List<QuestionModel> questList = new ArrayList<QuestionModel>();
			
			questList = (List<QuestionModel>) query.getResultList();
					
			return questList.size();
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getAllQuestionGroupsId(int exerciseid) {
		
		try {				
			
			Query query = entityManager.createQuery(
				"SELECT DISTINCT q.questiongroupid FROM EntityQuestionModel q " +
					"WHERE q.exerciseid = :exerciseid ");
			query.setParameter("exerciseid", exerciseid);
			
			return (List<Integer>)query.getResultList();
		/*	Integer previousid = 0;
			for(Integer groupid: pqlist){
				if(previousid != groupid){// count only unique group ids
					previousid = groupid;
				}
			}*/
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}


