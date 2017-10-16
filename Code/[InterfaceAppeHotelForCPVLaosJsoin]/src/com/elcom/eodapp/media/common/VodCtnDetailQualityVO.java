package com.elcom.eodapp.media.common;

public class VodCtnDetailQualityVO
{
	  public int ctnid ;
	  public int modctnid;
	  public int modsubjectid;
	  public int qualityid;
	  public String modctnname;
	  public String modsubjectname;
	  public String qualityname;
	  public long lengthtime;

	  public VodCtnDetailQualityVO(int ctnid,int modctnid,
	                               int modsubjectid,int qualityid,
	                               String modctnname,String modsubjectname,
	                               String qualityname,long lengthtime)
	  {
	    this.ctnid = ctnid;
	    this.modctnid = modctnid;
	    this.modsubjectid = modsubjectid;
	    this.qualityid = qualityid;
	    this.modctnname = modctnname;
	    this.modsubjectname = modsubjectname;
	    this.qualityname = qualityname;
	    this.lengthtime = lengthtime;
	  }
	}

