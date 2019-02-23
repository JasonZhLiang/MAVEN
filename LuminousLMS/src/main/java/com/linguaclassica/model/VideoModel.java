package com.linguaclassica.model;

import java.io.Serializable;

public interface VideoModel extends Serializable {
	
	/**
	 * Returns the contentid field for this message model
	 * @return content Id
	 */
	public Integer getContentId();

	/**
	 * Sets the contentid field for this message model
	 */
	public void setContentId(Integer contentid);
	/**
	 * Sets the videoid
	 * @param videoid
	 */
	public void setVideoid(String videoid);
	
	/**
	 * Returns videoid
	 * @return
	 */
	public String getVideoid();

	/**
	 * Sets the videoproviderurl
	 */
	public void setVideoProviderUrl(String videoproviderurl);
	
	/**
	 * Returns the videoproviderurl
	 * @return
	 */
	public String getVideoProviderUrl();
	
	/**
	 * Returns the transcript
	 * @return
	 */
	public String getTranscript();
}

