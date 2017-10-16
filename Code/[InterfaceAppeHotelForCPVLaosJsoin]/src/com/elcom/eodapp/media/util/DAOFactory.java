package com.elcom.eodapp.media.util;


import com.elcom.eodapp.media.pms.PmsContent;
import com.elcom.eodapp.media.record.RecordContent;
import com.elcom.eodapp.media.ro.RoContent;
import com.elcom.eodapp.media.vod.*;
import com.elcom.eodapp.media.cas.CasContent;
import com.elcom.eodapp.media.livetv.*;
import com.elcom.eodapp.media.mod.*;

public class DAOFactory
{
  private static DAOFactory instance = null;

  private DAOFactory()
  {
  }

  public static synchronized DAOFactory getInstance()
  {
    if (instance == null)
      instance = new DAOFactory();

    return instance;
  }

  public VodContentDAO2 getModContentDAO2()
  {
    return new VodContentDAO2();
  }
  
  public BrowserProgDao getBrowserProgDao()
  {
    return new BrowserProgDao();
  }

 
  public ModContent getModService()
  {
	  return new ModContent();
  }
  
  public RoContent getRoService()
  {
	  return new RoContent();
  }
  
  public CasContent getCasContent()
  {
	  return new CasContent();
  }
  
  public RecordContent getRecordContent()
  {
	  return new RecordContent();
  }
  
  public PmsContent getPmsContent()
  {
	  return new PmsContent();
  }
}

