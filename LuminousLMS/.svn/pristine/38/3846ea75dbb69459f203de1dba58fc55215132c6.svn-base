package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.VideoModel;

@Entity
@Table(name="videos")
public class EntityVideoModel implements VideoModel {

	private static final long serialVersionUID = 6331128951228340173L;

	@Id
	@GeneratedValue()
	private Integer contentid;
		
	private String videoid;
		
	private String videoproviderurl;
	
	private String transcript;
	
	public EntityVideoModel() {
	}

	/**
	 * Normal constructor used to create a org
	 */
	public EntityVideoModel(String videoid, String videoproviderurl) {
			this.videoid = videoid;
			this.videoproviderurl = videoproviderurl;
		}

		@Override
		public Integer getContentId() {
		return contentid;
		}

		@Override
		public void setContentId(Integer contentid) {
			this.contentid = contentid;
		}
		
		@Override
		public String getVideoid() {
			return videoid;
		}

		@Override
		public void setVideoid(String videoid) {
			this.videoid = videoid;
		}
		
		@Override
		public String getVideoProviderUrl() {
			return videoproviderurl;
		}

		@Override
		public void setVideoProviderUrl(String videoproviderurl) {
			this.videoproviderurl = videoproviderurl;
		}
		
		@Override
		public String getTranscript() {
			return transcript;
		}
	}
