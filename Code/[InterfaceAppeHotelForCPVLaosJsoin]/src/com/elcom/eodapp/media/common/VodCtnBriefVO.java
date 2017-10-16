package com.elcom.eodapp.media.common;

import java.util.Date;

public class VodCtnBriefVO
{
	  private int contentId;
	  private int modcontentId;
	  private String modcontentName;
	  private String picUrl;
	  private String actor;
	  private String director;
	  private java.util.Date releasedate;
	  private float averagepoint;
	  private long milisecond;

	  public VodCtnBriefVO()
	  {}

	  public VodCtnBriefVO(int ctnID, int modCtnID, String modCtnName, String picUrl, String actor,
	                       String director, Date releaseDate, float averagePoint, long miliSecond)
	  {
		this.contentId = ctnID;
		this.modcontentId = modCtnID;
		this.modcontentName = modCtnName;
		this.picUrl = picUrl;
		this.actor = actor;
	    this.director = director;
	    this.releasedate = releaseDate;
	    this.averagepoint = averagePoint;
	    this.milisecond = miliSecond;
	  }

	  public int getContentId()
	  {
		return this.contentId;
	  }

	  public int getModcontentId()
	  {
		return this.modcontentId;
	  }

	  public String getModcontentName()
	  {
		return this.modcontentName;
	  }

	  public String getPicUrl()
	  {
		return this.picUrl;
	  }

	  public String getActor()
	  {
		return this.actor;
	  }

	  public String getDirector()
	  {
	    return this.director;
	  }

	  public java.util.Date getReleaseDate()
	  {
	    return this.releasedate;
	  }

	  public float getAveragePoint()
	  {
	    return this.averagepoint;
	  }

	  public long getMilisecond()
	  {
	    return this.milisecond;
	  }

	  public void setContentId(int ctnID)
	  {
		this.contentId = ctnID;
	  }

	  public void setModcontentId(int modCtnID)
	  {
		this.modcontentId = modCtnID;
	  }

	  public void setModcontentName(String name)
	  {
		this.modcontentName = name;
	  }

	  public void setPicUrl(String picUrl)
	  {
		this.picUrl = picUrl;
	  }

	  public void setActor(String actor)
	  {
		this.actor = actor;
	  }

	  public void setDirector(String dir)
	  {
	    this.director = dir;
	  }

	  public void setReleaseDate(java.util.Date rdate)
	  {
	    this.releasedate = rdate;
	  }

	  public void setAveragePoint(float apoint)
	  {
	    this.averagepoint = apoint;
	  }

	  public void setMilisecond(long milis)
	  {
	    this.milisecond = milis;
	  }


	  public String toString()
	  {
	    StringBuffer buf = new StringBuffer();
		buf.append("[ContentID = " + this.contentId + ",");
	    buf.append("ModContentID = " + this.modcontentId + ",");
	    buf.append("ModContentName = " + this.modcontentName + ",");
	    buf.append("PicURL = " + this.picUrl + ",");
	    buf.append("Actor = " + this.actor + ",");
	    buf.append("Director = "+ this.director + ",");
	    buf.append("ReleaseDate = "+ this.releasedate + ",");
	    buf.append("AveragePoint = "+ this.averagepoint + ",");
	    buf.append("Milisecond = "+this.milisecond + "]\n");

	    return buf.toString();
	  }
	}

