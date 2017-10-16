package com.elcom.eodapp.media.common;

import java.util.Date;
import java.util.*;
import java.io.Serializable;

public class VodCtnPaymentPlayVO
{
	  private int ctnID;
	  private int modCtnID;
	  private String ctnName;
	  private String picUrl;
	  private int modSubjectID;
	  private String subjectName;
	  private float averagePoint;
	  private long milisecond;
	  private Vector quality;

	  public VodCtnPaymentPlayVO(
	      int ctnID,
	      int modCtnID,
	      String ctnName,
	      String picUrl,
	      int modSubjectID,
	      String subjectName,
	      float averagePoint,
	      long milisecond,
	      Vector quality
	      )
	  {
	    this.ctnID = ctnID;
	    this.modCtnID = modCtnID;
	    this.ctnName = ctnName;
	    this.picUrl = picUrl;
	    this.modSubjectID = modSubjectID;
	    this.subjectName = subjectName;
	    this.averagePoint = averagePoint;
	    this.milisecond = milisecond;
	    this.quality = quality;
	  }

	  public VodCtnPaymentPlayVO()
	  {}
	//------------------------------------------------------------------------------
	  public int getctnID(){
	    return this.ctnID;
	  }

	  public int getmodCtnID(){
	    return this.modCtnID;
	  }

	  public String getctnName(){
	    return this.ctnName;
	  }

	  public String getpicUrl(){
	    return this.picUrl;
	  }

	  public int getmodSubjectID(){
	    return this.modSubjectID;
	  }

	  public String getsubjectName(){
	    return this.subjectName;
	  }

	  public float getaveragePoint(){
	    return this.averagePoint;
	  }

	  public long getmilisecond(){
	    return this.milisecond;
	  }

	  public Vector getquality(){
	    return this.quality;
	  }

	//------------------------------------------------------------------------------
	  public void setctnID(int ctnID){
	    this.ctnID = ctnID;
	  }

	  public void setmodCtnID(int modCtnID){
	    this.modCtnID = modCtnID;
	  }

	  public void setctnName(String ctnName){
	    this.ctnName = ctnName;
	  }

	  public void setpicUrl(String picUrl){
	    this.picUrl = picUrl;
	  }

	  public void setmodSubjectID(int modSubjectID){
	    this.modSubjectID = modSubjectID;
	  }

	  public void setsubjectName(String subjectName){
	    this.subjectName = subjectName;
	  }

	  public void setaveragePoint(float averagePoint){
	    this.averagePoint = averagePoint;
	  }

	  public void getmilisecond(long milisecond){
	    this.milisecond = milisecond;
	  }

	  public void setquality(Vector quality){
	    this.quality = quality;
	  }

	}

