package com.elcom.eodapp.media.record;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;
import com.elcom.eod.util.UnicodeConverter;
import com.elcom.eodapp.media.broker.IMBroker;
import com.elcom.eodapp.media.common.ModSubject;
import com.elcom.eodapp.media.common.ModVO;
import com.elcom.eodapp.media.common.VodCtnVO;
import com.elcom.eodapp.media.util.DateHelper;
import com.elcom.eodapp.media.vod.VodContentSQL;


public class RecordContent {
	//Refers the DB broker object
	  private static IMBroker broker = IMBroker.getInstance();
	  //To log application
	  private static final Logger logger = LogManager.getLogger(RecordContent.class);
	  private static final String pattern = "MM/dd/yyyy HH:mm:ss";

	  public RecordContent(){}
	  
	  public void setScheduleStb(String idkenh,String tenkenh,String keystb,String starttime,String stoptime)
	  {
		  Vector params = new Vector(5);
		  SubProParam param = null;
		  
		  param = new SubProParam(new BigDecimal(idkenh), SubProParam.IN);
		  params.add(param);
		  param = new SubProParam(new String(tenkenh), SubProParam.IN);
		  params.add(param);
		  param = new SubProParam(new String(keystb), SubProParam.IN);
		  params.add(param);
		  param = new SubProParam(new String(starttime), SubProParam.IN);
		  params.add(param);
		  param = new SubProParam(new String(stoptime), SubProParam.IN);
		  params.add(param);

		  params = broker.executeSubPro(SQL.sqlsetScheduleStb, params);
	  }
	  //-------------------------------------------------------------------------------
	  public String getListRecordStb(String keystb)
	  {
		  String result_vRS = new String();
		  Vector vRs = new Vector();
		  Vector params = new Vector(2);

		  SubProParam ketstb_ = new SubProParam(new String(keystb), SubProParam.IN);
		  params.add(ketstb_);	  
			
		  SubProParam out_data = new SubProParam(new Vector(),"STRING_ARR",SubProParam.OUT);
		  params.add(out_data);
		  
		  params = broker.executeSubPro(SQL.sqlgetListRecordStb,params);
		  out_data = (SubProParam) params.get(1);
		  vRs = out_data.getVector();
		  result_vRS = loadDataListRecordStb(vRs,keystb);
		  return result_vRS;		
	  }
	  //-------------------------------------------------------------------------------
	  public String getListRecordCore()
	  {
		  String result_vRS = new String();
		  Vector vRs = new Vector();
		  Vector params = new Vector(1);
			
		  SubProParam out_data = new SubProParam(new Vector(),"STRING_ARR",SubProParam.OUT);
		  params.add(out_data);
		  
		  params = broker.executeSubPro(SQL.sqlgetListRecordCore,params);
		  out_data = (SubProParam) params.get(0);
		  vRs = out_data.getVector();
		  result_vRS = loadDataListRecordCore(vRs);
		  return result_vRS;		
	  }
	  //--------------------------------------------------------------------------------
	  public String getListRecordsCore(String status)
	  {
		  String list = "";
		  return list;
	  }
	  //--------------------------------------------------------------------------------
	  public void delScheduleStb(String idrecord)
	  {
		  Vector params = new Vector(1);
		  SubProParam param = null;

		  param = new SubProParam(new BigDecimal(idrecord), SubProParam.IN);
		  params.add(param);
		  

		  params = broker.executeSubPro(SQL.sqldelScheduleStb, params);
	  }
	//--------------------------------------------------------------------------------
	  public void delScheduleCore(String idrecord)
	  {
		  Vector params = new Vector(1);
		  SubProParam param = null;

		  param = new SubProParam(new BigDecimal(idrecord), SubProParam.IN);
		  params.add(param);
		  

		  params = broker.executeSubPro(SQL.sqldelScheduleCore, params);
	  }
	  //--------------------------------------------------------------------------------
	  public String checkScheduleCore()
	  {
		  String list = "";
		  return list;
	  }
	  //--------------------------------------------------------------------------------
	  public void updateLinkStbRecord(String id,String url_play)
	  {
		  Vector params = new Vector(2);
		  SubProParam param = null;

		  param = new SubProParam(new BigDecimal(id), SubProParam.IN);
		  params.add(param);
		  param = new SubProParam(new String(url_play), SubProParam.IN);
		  params.add(param);

		  params = broker.executeSubPro(SQL.sqlupdateLinkStbRecord, params);
	  }
	  //--------------------------------------------------------------------------------
	  public void updateStatusStbRecord(String idrecord,int status)
	  {
		  Vector params = new Vector(2);
		  SubProParam param = null;
		  param = new SubProParam(new BigDecimal(idrecord), SubProParam.IN);
		  params.add(param);
		  param = new SubProParam(new BigDecimal(status), SubProParam.IN);
		  params.add(param);

		  params = broker.executeSubPro(SQL.sqlupdateStatusStbRecord, params);
	  }
	//--------------------------------------------------------------------------------
	  public void updateSizeStbRecord(String idrecord,String size)
	  {
		  Vector params = new Vector(2);
		  SubProParam param = null;
		  param = new SubProParam(new BigDecimal(idrecord), SubProParam.IN);
		  params.add(param);
		  param = new SubProParam(new BigDecimal(size), SubProParam.IN);
		  params.add(param);

		  params = broker.executeSubPro(SQL.sqlupdateSizeStbRecord, params);
	  }
	//--------------------------------------------------------------------------------
	  public void updateStatusCore(String idrecord,String status)
	  {
		  Vector params = new Vector(2);
		  SubProParam param = null;
		  param = new SubProParam(new BigDecimal(idrecord), SubProParam.IN);
		  params.add(param);
		  param = new SubProParam(new BigDecimal(status), SubProParam.IN);
		  params.add(param);

		  params = broker.executeSubPro(SQL.sqlupdateStatusCore, params);
	  }
	  
	  //---------------------------------------------------------------------------------
	  public String loadDataListRecordStb(Vector vRS,String keystb)
	  {
		String aRet = "<?xml version='1.0' encoding='UTF-8'?>\r\n<ListRecord serinumber=' "+ keystb +" '>\r\n";
		int ID_;
        String CHANNEL_NAME_,URL_RECORD_,SERNUMBER_,START_TIME_,STOP_TIME_,STATUS_,
        PRIVATE_CHANNELNAME_,SIZEINKB_,URL_HINH_;
	    
	    RecordContent re;
	    
	    for (int i = 0; i < vRS.size(); i = i + 10)
	    {	      
	      re = new RecordContent(); //new an instance of RecordContent
	      ID_ = Integer.parseInt((String)vRS.get(i));	      
	      CHANNEL_NAME_ = (String)vRS.get(i + 1);
	      URL_RECORD_ = (String)vRS.get(i + 2);
	      SERNUMBER_ = (String)vRS.get(i + 3);
	      START_TIME_ = (String)vRS.get(i + 4);
	      STOP_TIME_ = (String)vRS.get(i + 5);
	      STATUS_ = (String)vRS.get(i + 6);
	      PRIVATE_CHANNELNAME_ = (String)vRS.get(i + 7);
	      SIZEINKB_ = (String)vRS.get(i + 8);
	      URL_HINH_ = (String)vRS.get(i + 9);
	      
	      aRet = aRet + "<item id = '" + ID_ + "'>\r\n";
	      aRet = aRet + "<CHANNEL_NAME>" + CHANNEL_NAME_ + "</CHANNEL_NAME>\r\n";
	      aRet = aRet + "<URL_RECORD>" + URL_RECORD_ + "</URL_RECORD>\r\n";
	      aRet = aRet + "<START_TIME>" + START_TIME_ + "</START_TIME>\r\n";
	      aRet = aRet + "<STOP_TIME>" + STOP_TIME_ + "</STOP_TIME>\r\n";
	      aRet = aRet + "<STATUS>" + STATUS_ +  "</STATUS>\r\n";
	      aRet = aRet + "<PRIVATE_CHANNELNAME>" + PRIVATE_CHANNELNAME_ +  "</PRIVATE_CHANNELNAME>\r\n";
	      aRet = aRet + "<SIZEINKB>" + SIZEINKB_ +  "</SIZEINKB>\r\n";
	      aRet = aRet + "<URL_HINH>" + URL_HINH_ +  "</URL_HINH>\r\n";
	      aRet = aRet + "</item>\r\n";
	      
	    }
	    aRet = aRet + "</ListRecord>";
	    return aRet;
	  }
	  //---------------------------------------------------------------------------------
	  public String loadDataListRecordCore(Vector vRS)
	  {
		String aRet = "<?xml version='1.0' encoding='UTF-8'?>\r\n<ListRecord>\r\n";
		int ID_;
        String CHANNEL_NAME_,URL_RECORD_,SERNUMBER_,START_TIME_,DURATION_,STATUS_,
        PRIVATE_CHANNELNAME_,SIZEINKB_,URL_HINH_;
	    
	    RecordContent re;
	    
	    for (int i = 0; i < vRS.size(); i = i + 10)
	    {	      
	      re = new RecordContent(); //new an instance of RecordContent
	      ID_ = Integer.parseInt((String)vRS.get(i));	      
	      CHANNEL_NAME_ = (String)vRS.get(i + 1);
	      URL_RECORD_ = (String)vRS.get(i + 2);
	      SERNUMBER_ = (String)vRS.get(i + 3);
	      START_TIME_ = (String)vRS.get(i + 4);
	      DURATION_ = (String)vRS.get(i + 5);
	      STATUS_ = (String)vRS.get(i + 6);
	      PRIVATE_CHANNELNAME_ = (String)vRS.get(i + 7);
	      SIZEINKB_ = (String)vRS.get(i + 8);
	      URL_HINH_ = (String)vRS.get(i + 9);
	      
	      aRet = aRet + "<item id = '" + ID_ + "'>\r\n";
	      aRet = aRet + "<CHANNEL_NAME>" + CHANNEL_NAME_ + "</CHANNEL_NAME>\r\n";
	      aRet = aRet + "<SERNUMBER>" + SERNUMBER_ + "</SERNUMBER>\r\n";
	      aRet = aRet + "<URL_RECORD>" + URL_RECORD_ + "</URL_RECORD>\r\n";
	      aRet = aRet + "<START_TIME>" + START_TIME_ + "</START_TIME>\r\n";
	      aRet = aRet + "<DURATION>" + DURATION_ + "</DURATION>\r\n";
	      aRet = aRet + "<STATUS>" + STATUS_ +  "</STATUS>\r\n";
	      aRet = aRet + "<PRIVATE_CHANNELNAME>" + PRIVATE_CHANNELNAME_ +  "</PRIVATE_CHANNELNAME>\r\n";
	      aRet = aRet + "<SIZEINKB>" + SIZEINKB_ +  "</SIZEINKB>\r\n";
	      aRet = aRet + "<URL_HINH>" + URL_HINH_ +  "</URL_HINH>\r\n";
	      aRet = aRet + "</item>\r\n";
	      
	    }
	    aRet = aRet + "</ListRecord>";
	    return aRet;
	  }
}