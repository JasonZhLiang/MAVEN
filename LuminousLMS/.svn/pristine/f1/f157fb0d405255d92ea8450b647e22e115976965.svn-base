package com.linguaclassica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityVideoModel;
import com.linguaclassica.model.VideoModel;
import com.linguaclassica.repository.OrgsContentItemsRepository;
import com.linguaclassica.repository.VideoRepository;


@Service
@Transactional
public class VideoService {
	
	@Autowired
	private OrgsContentItemsRepository orgsContentItemsRepository;
	
	@Autowired
	private VideoRepository videoRepository;
	
	/**
	 * Create a video.
	 * @param video
	 */
	public void createOne(VideoModel video)
	{		
		videoRepository.createOne(video);
	}
	
	/**
	 * Delete a video.
	 * @param video
	 */
	public void deleteOne(VideoModel video)
	{		
		videoRepository.deleteOne(video);
	}
	
	/**
	 * This method is used to find a video by its content id.
	 * @param id
	 */
	public EntityVideoModel getFromContentId(int contentid)
	{
		return videoRepository.getFromContentId(contentid);
	}
	
	/**
	 * Update a video.
	 * @param video
	 */
	public void update(VideoModel video)
	{		
		videoRepository.update(video);
	}
	
	public List<EntityVideoModel> getAllVideosTEMP() {
		return videoRepository.getAllVideos();
	}
}