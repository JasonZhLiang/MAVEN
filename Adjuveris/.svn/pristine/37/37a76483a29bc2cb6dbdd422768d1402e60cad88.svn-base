package com.linguaclassica.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.linguaclassica.entity.EntityInstitutionModel;
import com.linguaclassica.model.InstitutionModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class InstitutionRepository {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	/**
	 * This method creates an institution. Constraint may cause an exceptions to be thrown.
	 * @param institutionModel
	 */
	public int createInstitution(EntityInstitutionModel instModel) 
	{
		int instId;

		entityManager.persist(instModel);
		entityManager.refresh(instModel);
		instId = instModel.getId();
		return instId;
	}
	
	/**
	 * This method is used to find an institution by its id.
	 * If successful, the valid InstitutionModel is returned.
	 * @param id
	 * @return
	 * @throws NoResultException if the class cannot be found.
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	public EntityInstitutionModel findInstitutionById(int id) 
			throws NoResultException, PersistenceException {	
		
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT ex from EntityInstitutionModel ex where ex.id = :id");
			query.setParameter("id", id);
			
			EntityInstitutionModel instModel = (EntityInstitutionModel) query.getSingleResult();
			
			System.out.println("InstitutionRepository.findInstitutionById.getInstname() = " + instModel.getInstname());
			
			return instModel;
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * This method is used to find an institution by its name.
	 * If successful, the valid InstitutionModel is returned.
	 * @param instname
	 * @return EntityInstitutionModel
	 * @throws NoResultException if the class cannot be found.
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	public EntityInstitutionModel findInstitutionByName(String instName) 
			throws NoResultException, PersistenceException {		
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT ex from EntityInstitutionModel ex where ex.instname = :instName");
			query.setParameter("instName", instName);
			
			EntityInstitutionModel instModel = (EntityInstitutionModel) query.getSingleResult();
			
			System.out.println("InstitutionRepository.findInstitutionByName.getId() = " + instModel.getId());
			
			return instModel;
		
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * This method updates an institution record.
	 * An exception may be thrown that the form handler must trap.
	 * @param institutionModel
	 */
	public void updateInstitution(InstitutionModel institutionModelIn)
	{
		System.out.format("InstitutionRepository.updateInstitution(%d %s)%n", 
				institutionModelIn.getId(), institutionModelIn.getInstname());
		InstitutionModel institutionModel = entityManager.find(EntityInstitutionModel.class, institutionModelIn.getId());
		institutionModel.setInstname(institutionModelIn.getInstname());
		institutionModel.setPostaddress(institutionModelIn.getPostaddress());
		institutionModel.setPrimecontact(institutionModelIn.getPrimecontact());
		institutionModel.setPrimephone(institutionModelIn.getPrimephone());
		institutionModel.setPrimeemail(institutionModelIn.getPrimeemail());
		institutionModel.setAdminfirstname(institutionModelIn.getAdminfirstname());
		institutionModel.setAdminlastname(institutionModelIn.getAdminlastname());
		institutionModel.setAdminemail(institutionModelIn.getAdminemail());
		entityManager.persist(institutionModel);
		entityManager.flush();
	 }

	/**
	 * This method retrieves the list of institutions
	 * If no exceptions are thrown the method is successful.
	 * @return List<EntityInstitutionModel>
	 */
	@SuppressWarnings("unchecked")
	public List<EntityInstitutionModel> getListOfInstitutions() {
		try {
			Query query = entityManager.createQuery(
				"SELECT inst FROM EntityInstitutionModel inst ORDER BY inst.instname ASC");
			
			List<EntityInstitutionModel> instList = new ArrayList<EntityInstitutionModel>();
	
			instList = (List<EntityInstitutionModel>) query.getResultList();
			
			int instlistlen = instList.size();
			if (instlistlen > 0) {
				System.out.println("InstitutionRepository.getListOfInstitution.get(0).getInstname() = " + 
						instList.get(0).getInstname());
			}			
			
			return instList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}

