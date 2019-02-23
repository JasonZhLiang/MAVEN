package com.linguaclassica.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityTermModel;
import com.linguaclassica.entity.EntityTermStudentsModel;
import com.linguaclassica.entity.EntityTermTaModel;
import com.linguaclassica.entity.EntityTermTeachersModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.TermModel;
import com.linguaclassica.model.TermStudentsModel;
import com.linguaclassica.model.TermTaModel;
import com.linguaclassica.model.TermTeachersModel;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.repository.TermRepository;
import com.linguaclassica.repository.TermStudentsRepository;
import com.linguaclassica.repository.TermTaRepository;
import com.linguaclassica.repository.TermTeachersRepository;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.ServiceException;

@Service
@Transactional
public class TermService {
	
	@Autowired
	private TermRepository termRepository = new TermRepository();
	
	@Autowired
	private TermStudentsRepository termStudentsRepository = new TermStudentsRepository();
	
	@Autowired
	private TermTeachersRepository termTeachersRepository = new TermTeachersRepository();
	
	@Autowired
	private TermTaRepository termTaRepository = new TermTaRepository();
	
	@Autowired
	ModelFactory modelFactory;
	
	public int createTerm(TermModel termModel) throws EntityAlreadyExistsException, ServiceException
	{
		int termId;
		
		try
		{
			System.out.println("TermService.createTerm");
			termId = termRepository.createTerm((EntityTermModel) termModel);
		} catch (EntityExistsException e) {
			throw new EntityAlreadyExistsException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
		return termId;
	}
	
	public void updateTerm(TermModel termModel)
	{
		termRepository.updateTerm(termModel);
	}
	
	public EntityTermModel findTermById(int termId){
		return termRepository.findTermById(termId);
	}
	
	public EntityTermModel findTermByName(String termName){
		return termRepository.findTermByName(termName);
	}
	
	public List<EntityTermModel> getListOfTermsByInstId(Integer instId) {
		System.out.println("TermService.getListOfTermsByInstId: instId = " + instId);
		return termRepository.getListOfTermsByInstId(instId);
	}
	
	public List<EntityTermModel> getTermsByInstTeacher(Integer instId, Integer userId)
	{
		return termRepository.getTermsByInstTeacher(instId, userId);
	}
	
	/**
	 * get terms
	 * @param instId
	 * @param now
	 * @param nPriorTerms is the number of past terms to retrieve
	 * @return
	 */
	public List<EntityTermModel> getListOfTermsByInstId(Integer instId, Date now, int nPriorTerms)
	{
		System.out.format("TermService.getListOfTermsByInstId(%d, %s, %d prior terms)%n", instId, now.toString(), nPriorTerms);
		List<EntityTermModel> termslist = termRepository.getListOfNewAndExistingTerms(instId, now);
		termslist.addAll(termRepository.getListOfPastTerms(instId, now, nPriorTerms));
		
		return termslist;
	}
	
	/**
	 * Create a term-student association when one does not already exist
	 * @param termTeachersModel
	 * @return
	 */
	public Integer createTermStudents(EntityTermStudentsModel termStudentsModel) {
		System.out.println("TermService.createTermStudents, termStudentsModel.getId() = " + 
				termStudentsModel.getId());
		
		//check for existing user with the number of termStudents with the studentid (userId) of the termStudentsModel
		int termStudentId = 0;
		int existingTSM = termStudentsRepository.getListOfTermStudentById(termStudentsModel);
		//if existing record with this userid does not already exist, create one
		if (existingTSM < 1) {	
			
			termStudentId = termStudentsRepository.createTermStudent(termStudentsModel);
		}
		return termStudentId;
	}
	
	public List<Integer> getListOfStudentIdsByTermId(int termid) {
		List<Integer> studentIdsList = termStudentsRepository.getListOfStudentIdsByTermId(termid);
		return studentIdsList;
	}
	
	/**
	 * Create a term-teacher association when one does not already exist
	 * @param termTeachersModel
	 * @return
	 */
	public Integer createTermTeacher(EntityTermTeachersModel termTeachersModel) {
		System.out.println("TermService.createTermTeacher, termTeachersModel.getId() = " + 
				termTeachersModel.getId());
		//check for existing user with the number of termStudents with the studentid (userId) of the termStudentsModel
		int termTeacherId = 0;
		int existingTSM = termTeachersRepository.getListOfTermTeachersById(termTeachersModel);
		//if existing record with this userid does not already exist, create one
		if (existingTSM < 1) {		
			termTeacherId = termTeachersRepository.createTermTeacher(termTeachersModel);
		}
		return termTeacherId;
	}
	
	/**
	 * Create a term-TA association when one does not already exist
	 * @param termTeachersModel
	 * @return
	 */
	public Integer createTermTa(EntityTermTaModel termTaModel) {
		System.out.println("TermService.createTermTa, termTaModel.getId() = " + 
				termTaModel.getId());
		//check for existing user with the number of termStudents with the taid (userId) of the termTaModel
		int termTaId = 0;
		int existingTSM = termTaRepository.getListOfTermTasById(termTaModel);
		//if existing record with this userid does not already exist, create one
		if (existingTSM < 1) {		
			termTaId = termTaRepository.createTermTa(termTaModel);
		}
		return termTaId;
	}
	
	public List<Integer> getListOfTeacherIdsByTermId(int termid) {
		List<Integer> teacherIdsList = termTeachersRepository.getListOfTeacherIdsByTermId(termid);
		return teacherIdsList;
	}
	
	public List<Integer> getListOfTeacherIdsByTermIds(List<EntityTermModel> termList) {
		int termListlen = termList.size();
		List<Integer> teacheridlist =  new ArrayList<Integer>();
		for (int i = 0; i < termListlen; i++) {
			List<Integer> tmpTeacheridList = getListOfTeacherIdsByTermId(termList.get(i).getId());
			int tmpTeacheridListlen = tmpTeacheridList.size();
			for (int j = 0; j < tmpTeacheridListlen; j++) {
				if(!teacheridlist.contains(tmpTeacheridList.get(j))) {
					teacheridlist.add(tmpTeacheridList.get(j));
				}
			}
		}
		return teacheridlist;
	}
	
	public List<Integer> getListOfTaIdsByTermId(int termid) {
		List<Integer> taIdsList = termTaRepository.getListOfTaIdsByTermId(termid);
		return taIdsList;
	}
	
	public List<EntityTermModel> getListOfExistingTerms(Integer instId, Date now)
	{
		System.out.format("TermService.getListOfExistingTerms(%d, %s)%n", instId, now.toString());
		return termRepository.getListOfExistingTerms(instId, now);
	}
	
	public List<Integer> getCurrentTermIDs(Integer instId, Date now)
	{
		return termRepository.getCurrentTermIDs(instId, now);
	}
	
	public List<Integer> getPriorTermIDs(Integer instId, Date now, Integer nPriorTerms)
	{
		return termRepository.getPriorTermIDs(instId, now, nPriorTerms);
	}
	
	public List<EntityTermModel> getListOfNewTerms(Integer instId, Date now)
	{
		System.out.format("TermService.getListOfNewTerms(%d, %s)%n", instId, now.toString());
		return termRepository.getListOfNewTerms(instId, now);
	}
	
	public List<EntityTermModel> getListOfNewAndExistingTerms(Integer instId, Date now)
	{
		System.out.format("TermService.getListOfNewAndExistingTerms(%d, %s)%n", instId, now.toString());
		return termRepository.getListOfNewAndExistingTerms(instId, now);
	}
	
	public List<EntityTermModel> getListOfPastTerms(Integer instId, Date now, int nPriorTerms)
	{
		System.out.format("TermService.getListOfPastTerms(%d, %s, %d prior terms)%n", instId, now.toString(), nPriorTerms);
		return termRepository.getListOfPastTerms(instId, now, nPriorTerms);
	}
	
	/**
	 * Get all matching terms prior to the specified date and overlapping the specified date
	 * @param instId
	 * @param today
	 * @return
	 */
	public List<EntityTermModel> getPastAndCurrentTerms(Integer instId, Date today)
	{
		return termRepository.getPastAndCurrentTerms(instId, today);
	}
	
	/**
	 * This method retrieves a list of term IDs for the current terms and the nPriorTerms most
	 * recent past terms of an institution, sorted in descending order of start date. If 
	 * nPriorTerms = BEGINNING_OF_TIME then all past term IDs from the start are returned.
	 * If no exceptions are thrown the method is successful.
	 */
	public List<Integer> getListOfRecentTermIDs(int instId, int nPriorTerms) {
		/* This version is exempt from JUnit testing */
		Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		return getListOfRecentTermIDs(instId, now, nPriorTerms);
	}

	/* Use this entry point for JUnit testing */
	public List<Integer> getListOfRecentTermIDs(int instId, Date now, int nPriorTerms) {
		int i, size;
		List<Integer> termIDList = new ArrayList<Integer>();

		/* Get the current term IDs */
		List<EntityTermModel> termList = termRepository.getListOfExistingTerms(instId, now);
		size = termList.size();
		for (i=0; i<size; i++)
			termIDList.add(termList.get(i).getId());

		/* Get the past term IDs */
		termList = termRepository.getListOfPastTerms(instId, now, nPriorTerms);
		size = termList.size();
		for (i=0; i<size; i++)
			termIDList.add(termList.get(i).getId());
		
		return termIDList;
	}
	
	public Integer addStudentToList(UserModel userModel, Integer termid) {
		int termUserId = 0;
		
		TermStudentsModel termStudentsModel = modelFactory.getNewTermStudentsModel();
		termStudentsModel.setStudentid(userModel.getId());
		termStudentsModel.setTermid(termid);
		termUserId = createTermStudents((EntityTermStudentsModel) termStudentsModel);
		
		return termUserId;
	}
	
	public Integer addTeacherToList(UserModel userModel, Integer termid) {
		int termUserId = 0;

		TermTeachersModel termTeachersModel = modelFactory.getNewTermTeachersModel();
		termTeachersModel.setTeacherid(userModel.getId());
		termTeachersModel.setTermid(termid);
		termUserId = createTermTeacher((EntityTermTeachersModel) termTeachersModel);
		
		return termUserId;
	}
	
	public Integer addTaToList(UserModel userModel, Integer termid) {
		int termUserId = 0;
		
		TermTaModel termTaModel = (TermTaModel) modelFactory.getNewTermTaModel();
		termTaModel.setTaid(userModel.getId());
		termTaModel.setTermid(termid);
		termUserId = createTermTa((EntityTermTaModel) termTaModel);
		
		return termUserId;
	}
	
	public void deleteTermStudent(int studentid, int termid) {
		termStudentsRepository.deleteTermStudent(studentid, termid);
	}
	
	public boolean checkTermStudent(int studentid, int termid) {
		return termStudentsRepository.checkTermStudent(studentid, termid);
	}
	
	public void deleteTermTeacher(int teacherid, int termid) {
		termTeachersRepository.deleteTermTeacher(teacherid, termid);
	}
	
	public boolean checkTermTeacher(int teacherid, int termid) {
		return termTeachersRepository.checkTermTeacher(teacherid, termid);
	}
	
	public void deleteTermTa(int taid, int termid) {
		termTaRepository.deleteTermTa(taid, termid);
	}
	
	public boolean checkTermTa(int taid, int termid) {
		return termTaRepository.checkTermTa(taid, termid);
	}
	
	public void deleteTermById(int id) {
		termRepository.deleteTermById(id);
	}
}

