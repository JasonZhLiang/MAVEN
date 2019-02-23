package com.linguaclassica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityContentItemsModel;
import com.linguaclassica.model.ContentItemsModel;
import com.linguaclassica.repository.ContentItemsRepository;

@Service
@Transactional
public class ContentItemsService {
	
	@Autowired
	private ContentItemsRepository contentItemsRepository;

	/**
	 * Create a content item.
	 * @param cItem
	 * @return The id for the new record
	 */
	public int createOne(ContentItemsModel cItem)
	{
		return contentItemsRepository.createOne(cItem);
	}
	
	/**
	 * Delete a content item.
	 * @param cItem
	 */
	public void deleteOne(ContentItemsModel cItem)
	{
		contentItemsRepository.deleteOne(cItem);
	}
	
	/**
	 * This method is used to find a content item by ID.
	 * @param id
	 */
	public EntityContentItemsModel getOne(int id)
	{
		return contentItemsRepository.getOne(id);
	}
	
	/**
	 * Update a content item.
	 * @param cItem
	 */
	public void update(ContentItemsModel cItem)
	{
		contentItemsRepository.update(cItem);
	}

	/**
	 * Return the list of content items for a given assignment
	 * @param asgnId
	 */
	public List<EntityContentItemsModel> getContentItemsForAnAssignment(int asgnId)
	{
		return contentItemsRepository.getContentItemsForAnAssignment(asgnId);
	}
}