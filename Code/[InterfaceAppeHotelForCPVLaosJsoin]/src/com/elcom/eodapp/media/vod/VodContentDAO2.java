package com.elcom.eodapp.media.vod;

import java.util.*;
import java.io.IOException;
import java.math.BigDecimal;

import com.elcom.DBI.SubProParam;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import com.elcom.eodapp.media.broker.*;
import com.elcom.eodapp.media.common.*;
import com.elcom.eodapp.media.util.*;
import com.elcom.eodapp.media.exception.*;

public class VodContentDAO2 extends VodContent{
	//To log application
	  //private static final Logger logger = LogManager.getLogger(VodContentDAO2.class);
	  //Get Object that manage & broker communicating
	  private static IMBroker broker = IMBroker.getInstance();
	  //
	  public String language = "";

	  public VodContentDAO2()
	  {
	  } //constructor


//--------------------------------------------------------------------------------------
	  public String checkPlayMedia(String keystb,int idfilm)
	  {
		  return checkPlay(keystb, idfilm);
	  }
//--------------------------------------------------------------------------------------
	  public String getByPrimaryKey(String keystb,int idfilm)
		      throws ModCtnNotFoundAppException, IOException
		  {		
		    
		  	String item = ""; 
		  	String result_vRS = "";
		  	Vector vRs = new Vector();
		  	Vector params = new Vector(3);

		  	SubProParam ketstb_ = new SubProParam(new String(keystb), SubProParam.IN);
		  	params.add(ketstb_);
		    
		  	SubProParam idfilm_ = new SubProParam(new BigDecimal(idfilm), SubProParam.IN);
		  	params.add(idfilm_);		  
			
		  	SubProParam out_data = new SubProParam(new Vector(),"STRING_ARR",SubProParam.OUT);
		  	params.add(out_data);
		  
		  	params = broker.executeSubPro(SQL.getByPrimaryKey,params);
		  	out_data = (SubProParam) params.get(2);
		  	vRs = out_data.getVector();		  
		  	result_vRS = loadDataFilmXML(vRs);
		  	item = result_vRS;
		  	return item;
		  }
//--------------------------------------------------------------------------------------
	  public void setCurTime(int Subid, int modid, String curtime)
	   {
	     Vector params = new Vector(3);
	     SubProParam param = null;

	     param = new SubProParam(new BigDecimal(Subid), SubProParam.IN);
	     params.add(param);
	     param = new SubProParam(new BigDecimal(modid), SubProParam.IN);
	     params.add(param);
	     param = new SubProParam(new String(curtime), SubProParam.IN);
	     params.add(param);

	     params = broker.executeSubPro(VodContentSQL.sqlSetCurTime, params);
	   }
//--------------------------------------------------------------------------------------
	  /*public ArrayList getMODCtnIDsOfSubjectNew(String keystb,short subjectid,int fromRow, int noRows)
	  {  
		  ArrayList result_vRS = new ArrayList();
		  Vector vRs = new Vector();
		  Vector params = new Vector(3);

		  SubProParam ketstb_ = new SubProParam(new String(keystb), SubProParam.IN);
		  params.add(ketstb_);
		    
		  SubProParam subjectid_ = new SubProParam(new BigDecimal(subjectid), SubProParam.IN);
		  params.add(subjectid_);		  
			
		  SubProParam out_data = new SubProParam(new Vector(),"STRING_ARR",SubProParam.OUT);
		  params.add(out_data);
		  
		  params = broker.executeSubPro(SQL.sqlgetListFilm,params);
		  out_data = (SubProParam) params.get(2);
		  vRs = out_data.getVector();
		  result_vRS = loadDataFilm(vRs);
		  return result_vRS;		  
	  }*/
//--------------------------------------------------------------------------------------
	  public String getMODCtnIDsOfSubjectNew(String keystb, short subjectid,
			   int fromRow, int noRows) throws IOException {
			  String result_vRS = "";
			  Vector vRs = new Vector();
			  Vector params = new Vector(3);

			  SubProParam ketstb_ = new SubProParam(new String(keystb),
			    SubProParam.IN);
			  params.add(ketstb_);

			  SubProParam subjectid_ = new SubProParam(new BigDecimal(subjectid),
			    SubProParam.IN);
			  params.add(subjectid_);

			  /*SubProParam frorow_ = new SubProParam(new BigDecimal(fromRow),
			    SubProParam.IN);
			  params.add(frorow_);

			  SubProParam noRows_ = new SubProParam(new BigDecimal(noRows),
			    SubProParam.IN);
			  params.add(noRows_);*/

			  SubProParam out_data = new SubProParam(new Vector(), "STRING_ARR",
			    SubProParam.OUT);
			  params.add(out_data);

			  params = broker.executeSubPro(SQL.sqlgetListFilm, params);
			  System.out.println(SQL.sqlgetListFilm + ": subjectid=" + subjectid);
			  out_data = (SubProParam) params.get(2);
			  vRs = out_data.getVector();
			  result_vRS = loadDataFilmXML(vRs);
			  return result_vRS;
			 }
//--------------------------------------------------------------------------------------
	  public int getSizeListFilmNotSub()
	  {
		  int size = 0;
		  Vector vRs = broker.executeSelect(VodContentSQL.sqlGetSizeListNotSub,null);
		  for (int i = 2; i < vRs.size(); i++)
		  {
			  Vector vRow = (Vector)vRs.get(i);
			  size = Integer.parseInt((String)vRow.get(0));
		  }
		  return size;
	  }
//--------------------------------------------------------------------------------------
	  public int getSizeListFilm(int subjectid)
	  {
	    int size = 0;	    
	    Vector vRs = broker.executeSelect(VodContentSQL.sqlGetSizeList + subjectid,null);
	    for (int i = 2; i < vRs.size(); i++)
	    {
	       Vector vRow = (Vector)vRs.get(i);
	       size = Integer.parseInt((String)vRow.get(0));
	    }

	    return size;
	  }
//--------------------------------------------------------------------------------------
	  @SuppressWarnings("unchecked")
	public String  getAllNorSubjects(String keystb,String typevodmod) throws IOException
	  {		
		  String result_vRS = "";
		  SubProParam inSub;
		  Vector<SubProParam> params = new Vector<SubProParam>(3);
		  
		  inSub = new SubProParam(new String(keystb), SubProParam.IN);
	      params.add(inSub);
	      inSub = new SubProParam(new String(typevodmod), SubProParam.IN);
	      params.add(inSub);
	      
		  SubProParam out_data = new SubProParam(new Vector<Object>(),"STRING_ARR",SubProParam.OUT);
		  params.add(out_data);
		  
		  params = broker.executeSubPro(SQL.sql_VodSubject, params);
		  out_data = (SubProParam) params.get(2);
		  Vector vRs = out_data.getVector();
		  
		  result_vRS = loadVodSubjectXML(vRs);
	      return result_vRS;
	  }
//-----------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------
	  @SuppressWarnings("unchecked")
	public String  getFilmCode(String keystb)
	  {		
		    String mili = "";
		    Vector params = new Vector(3);
		    SubProParam param = null;
		    param = new SubProParam(new String(), SubProParam.OUT);
		    params.add(param); //0 OUT
		    param = new SubProParam(new BigDecimal(keystb), SubProParam.IN);
		    params.add(param); //1 IN

		    params = broker.executeSubPro(SQL.sqlgetFilmCode, params);
		    //Get data returned by the stored procedure
		    SubProParam paramOUT = (SubProParam)params.get(0);
		    if (paramOUT.getString() == null)
		      mili = "";
		    else
		      mili = (paramOUT.getString()).trim();

		    return mili;
		  }	  
//------------------------------------------------------------------------------	  
	  public String getMiliUrlTrailer(int modcontentid)
	  {
	    String mili = "0";
	    Vector params = new Vector(3);
	    SubProParam param = null;
	    param = new SubProParam(new String(), SubProParam.OUT);
	    params.add(param); //0 OUT
	    param = new SubProParam(new BigDecimal(modcontentid), SubProParam.IN);
	    params.add(param); //1 IN

	    params = broker.executeSubPro(SQL.sqlurl_getmilitrailer, params);
	    //Get data returned by the stored procedure
	    SubProParam paramOUT = (SubProParam)params.get(0);
	    if (paramOUT.getString() == null)
	      mili = "0";
	    else
	      mili = (paramOUT.getString()).trim();

	    return mili;
	  }	  
//------------------------------------------------------------------------------
	  @SuppressWarnings("unchecked")
	public String getUrlSub(int ctnid) throws IOException
	  {
		  String result_vRS = "";		  
		  Vector vRs = new Vector();
		  
		  Vector<SubProParam> params = new Vector<SubProParam>(2);
		  SubProParam param = null;		  
		  param = new SubProParam(new BigDecimal(ctnid), SubProParam.IN);
		  params.add(param); 
		  
		  SubProParam out_data = new SubProParam(new Vector(),"STRING_ARR",SubProParam.OUT);
		  params.add(out_data);
		  
		  params = broker.executeSubPro(SQL.sqlGetListUrl,params);
		  out_data = (SubProParam) params.get(1);
		  vRs = out_data.getVector();
		  result_vRS = loadDataUrlXML(vRs);
		  return result_vRS;
		  
	  }	  
	//------------------------------------------------------------------------------
	  public String getURLNew(int modcontentid,int istrailer)
		      throws ModCtnNotFoundAppException,
		      VodURLUnableException,
		      VodClientTypeNotFoundAppException
		  {
		    String clientUrl;
		    //Bind IN/OUT Parameters
		    Vector params = new Vector(2);
		    SubProParam param = null;
		    param = new SubProParam(new String(), SubProParam.OUT);
		    params.add(param); //0 OUT		    
		    param = new SubProParam(new BigDecimal(modcontentid), SubProParam.IN);
		    params.add(param); //1 IN		   
		    param = new SubProParam(new BigDecimal(istrailer), SubProParam.IN);  //0 link chinh, 1 la trailer
		    params.add(param); //2 IN

		    //Executes the DB stored procedure
		    params = broker.executeSubPro(SQL.sqlGetURL, params);
		    //Get data returned by the stored procedure
		    SubProParam paramOUT = (SubProParam)params.get(0);
		    if (paramOUT.getString() == null)
		    	clientUrl = "";
		    else clientUrl = (paramOUT.getString());

		    if (clientUrl.equals("-1")){
		      throw new VodClientTypeNotFoundAppException("Client type not found.");
		    } else if (clientUrl.equals("-2")){
		      throw new ModCtnNotFoundAppException("ModContent not found.");
		    } else if (clientUrl.equals("-3")){
		      throw new VodURLUnableException("Content Type not found");
		    } else if (clientUrl.equals("-4")){
		      throw new VodURLUnableException("Not URL available.");
		    }

		    return (clientUrl);

		  }//End of getURL	  
	  //-----------------------------------------------------------------------------
	  private String checkPlay(String keystb,int modctnid)
	  {	    
	    Vector<SubProParam> params = new Vector<SubProParam>(3);
	    SubProParam param = null;
	    param = new SubProParam(new String(), SubProParam.OUT);
	    params.add(param); //0 OUT
	    param = new SubProParam(new String(keystb), SubProParam.IN);
	    params.add(param); //1 IN
	    param = new SubProParam(new BigDecimal(modctnid), SubProParam.IN);
	    params.add(param); //2 IN
	    //Executes the DB stored procedure
	    params = broker.executeSubPro(SQL.sqlcheckpay, params);
	    //Get data returned by the stored procedure
	    SubProParam paramOUT = (SubProParam)params.get(0);
	    String checkPlays = (paramOUT.getString()).trim();	    
	    return checkPlays;
	  }
	  //-------------------------------------------------------------------------------
	  public int chargeVod(String keystb ,int idcontent,String price,String namecontent,
			  			   String servicename, String typeprice, String nameguest,String pincode)
	  {
		  int id = -1;
		  Vector<SubProParam> params = new Vector<SubProParam>(9);
		    
		  SubProParam param = null;
		  param = new SubProParam(new String(), SubProParam.OUT);
		  params.add(param); //0 OUT
		  
		  param = new SubProParam(new String(keystb), SubProParam.IN);
		  params.add(param); //1 IN
		  param = new SubProParam(new BigDecimal(idcontent), SubProParam.IN);
		  params.add(param); //2 IN
		  param = new SubProParam(new String(price), SubProParam.IN);
		  params.add(param); //3 IN
		  param = new SubProParam(new String(namecontent), SubProParam.IN);
		  params.add(param); //4 IN
		  param = new SubProParam(new String(servicename), SubProParam.IN);
		  params.add(param); //5 IN
		  param = new SubProParam(new String(typeprice), SubProParam.IN);
		  params.add(param); //6 IN
		  param = new SubProParam(new String(nameguest), SubProParam.IN);
		  params.add(param); //7 IN
		  param = new SubProParam(new String(pincode), SubProParam.IN);
		  params.add(param); //8 IN	
		  		  
		  //Executes the DB stored procedure
		  params = broker.executeSubPro(SQL.chargeMedia, params);
		 //Get data returned by the stored procedure
		 SubProParam paramOUT = (SubProParam)params.get(0);
		 id = new Integer(paramOUT.getString()).intValue();	 
		 return id;
	  } 	  
	 //--------------------------------------------------------------------------------------
	  public int checkpaid(String keystb,String contentid)
	  {
		  int ket_qua = 0;
		  Vector<SubProParam> params = new Vector<SubProParam>(3);
		    
		  SubProParam param = null;
		  param = new SubProParam(new String(), SubProParam.OUT);
		  params.add(param); //0 OUT
		  
		  param = new SubProParam(new String(keystb), SubProParam.IN);
		  params.add(param); //1 IN
		  param = new SubProParam(new BigDecimal(contentid), SubProParam.IN);
		  params.add(param); //2 IN
		  		  
		  //Executes the DB stored procedure
		  params = broker.executeSubPro(SQL.sqlcheckpaid, params);
		  //Get data returned by the stored procedure
		  SubProParam paramOUT = (SubProParam)params.get(0);
		  ket_qua = new Integer(paramOUT.getString()).intValue();
		  return ket_qua;
	  }
	 //--------------------------------------------------------------------------------------
	  public int countFilm(int subjectid)
	  {
		  int count = 0;
		  Vector<SubProParam> params = new Vector<SubProParam>(2);
		    
		  SubProParam param = null;
		  param = new SubProParam(new String(), SubProParam.OUT);
		  params.add(param); //0 OUT
		  
		  param = new SubProParam(new BigDecimal(subjectid), SubProParam.IN);
		  params.add(param); //1 IN
		  
		    //Executes the DB stored procedure
		 params = broker.executeSubPro(SQL.sqlcountfilm, params);
		 //Get data returned by the stored procedure
		 SubProParam paramOUT = (SubProParam)params.get(0);
		 count = new Integer(paramOUT.getString()).intValue();	 
		 return count;
	  }  	  
}
