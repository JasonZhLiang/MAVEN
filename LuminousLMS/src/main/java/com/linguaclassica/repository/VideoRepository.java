package com.linguaclassica.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.linguaclassica.entity.EntityVideoModel;
import com.linguaclassica.model.VideoModel;

@Repository
public class VideoRepository {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	/**
	 * Create a video.
	 * @param video
	 */
	public void createOne(VideoModel video)
	{		
		entityManager.persist(video);
	}
	
	/**
	 * Delete a video.
	 * @param video
	 */
	public void deleteOne(VideoModel video)
	{		
		entityManager.remove(video);
	}
	
	/**
	 * This method is used to find a video by its content id.
	 * @param id
	 */
	public EntityVideoModel getFromContentId(int contentid)
	{
		return entityManager.find(EntityVideoModel.class, contentid);
	}
	
	/**
	 * Update a video.
	 * @param video
	 */
	public void update(VideoModel video)
	{		
		entityManager.merge(video);
	}
	
	/**
	 * This method retrieves a list of videos by videoid
	 * @param videoidList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EntityVideoModel> getListOfVideosByIdList(List<Integer> videoidList)
	{
		List<EntityVideoModel> videoList = new ArrayList<EntityVideoModel>();
		if (videoidList.size() > 0)
		{
		    String qryString;
		    qryString="SELECT v FROM EntityVideoModel v WHERE v.id IN (:videoidList) ORDER BY v.videotitle";
			Query query = entityManager.createQuery(qryString);
			query.setParameter("videoidList", videoidList);
			videoList = (List<EntityVideoModel>) query.getResultList();
		}
	    return videoList;
	}

	@SuppressWarnings("unchecked")
	public List<EntityVideoModel> getAllVideos() {
		List<EntityVideoModel> videos;
		
		Query query = entityManager.createQuery("SELECT v FROM EntityVideoModel v");
		videos = query.getResultList();
		return videos;
	}
}
	