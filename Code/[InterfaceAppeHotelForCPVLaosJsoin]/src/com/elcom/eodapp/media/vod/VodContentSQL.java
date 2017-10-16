package com.elcom.eodapp.media.vod;

public class VodContentSQL {
	//The SQL statement to get information in brief of a content
	  private String keystb = "ABCDEFG";
	  private String sqlGetBrief = null;
	  private String sqlGetBrief2 = null;
	  private String sqlBrowser = null;
	  private String sqlGetDetail = null;
	  private String sqlVote = null;
	  private String sqlGetAllModNextComes = null;
	  private String sqlGetPriceContentFollowTime = null;
	  private String sqlDetailQuality = null;
	  public static final String sqlGetGuestListOfRoom="{call PMS_APP.getGuestListOfRoom(?,?)}";
	  private String sqlGetPaymentPlay = "begin VOD_HUU_GETPAYMENTPLAY(?, ?, ?, ?, ?); end;";
	  public static final String sqlSetCurTime = "begin VOD.setCurTime(?, ?, ?); end;";
	  public static String sqlGetPriceContent = "begin GET_PRICE_OF_CONTENT(?, ?); end;";
	  public static String sqlGetTemalate = "select a.stylename , b.templatename " +
	                         "from EPG_STYLE a,EPG_TEMPLATE b,EPG_SUBCRIBERS c " +
	                         "where c.templateid = b.templateid " +
	                         "and c.styleid = b.styleid " +
	                         "and c.styleid = a.styleid " +
	                         "and a.styleid = b.styleid " +
	                         "and c.subcriberid = ?";
	  public static String sqlGetTemplates = "select a.templateid,a.templatename,a.des " +
	                                         "from EPG_TEMPLATE a " +
	                                         "where STYLEID = ?";

	  public static String sqlUpdateTempFlloUser = "BEGIN TEMPLATE_EPGUPDATESUB(?,?); END;";

	  public static final int DETAIL_FIELD_NUM = 26;
	  public static final String sqlGetModNextCome =
	      "Select modcontentid,modcontentname,content_picture_path,comedate, " +
	      "productor,director,star,mainactor,mainactress,language,subtitle, " +
	      "summary_sort,detaildescription,milisecond, releasedate,provider " +
	      "from mod_content_nextcomming where modcontentid = ?";
	  public static final String sqlPriceInfo =
	      "select duration,viewtime,bill_getprice(?) as price from content_price where contentid = ?";
	  public static final String sqlBillSession =
	      "begin ? := mod_billingsession(?,?); end;";
	  public static final String sqlCheckPay =
	      "begin ? := vod.checkpay(?,?); end;";
	  public static final String sqlisCheckCards =  "begin ? := EODAPP_COMMON.isCheckCards(?,?); end;";
	  public static final String sqlGetSizeList = "select count(*) from mod_content_detail_h where modsubjectid = ";
	  public static final String sqlGetSizeListNotSub = "select count(*) from mod_content_detail_h";
	  public static final String sqlGetMessHotel =
	            "begin HOTEL_INFOR.INFO_GET_MES_HOTEL(?,?,?,?); end;";
	  //============Ket hop cua tu
	  public static final String sqlChecksalto = "begin ? := checksalto(?); end;";

	  public VodContentSQL(String keystb)
	  {
	    this.keystb = keystb;
	  }

	  public VodContentSQL()
	  {

	  }

	//==============================================================================
	    public void setSqlDetailQuality(){
	      //p_vodid NUMBER,p_qualityid NUMBER,p_istrailer NUMBER,languages varchar2,out_data OUT string_arr
	      sqlDetailQuality = "begin VOD_HUU_GETDETAILVOD_QUALITY(?, ?, ?, ?, ?); end;";
	    }

	    public String getSqlDetailQuality(){
	      return sqlDetailQuality;
	    }

	//==============================================================================
	  public void setSqlGetPaymentPlay(){
	    //Subid NUMBER, modctnid NUMBER,language VARCHAR2,out_modcontent OUT String_Arr,out_price OUT String_Arr
	    sqlGetPaymentPlay = "begin VOD_HUU_GETPAYMENTPLAY(?, ?, ?, ?, ?); end;";
	  }

	  public String getSqlGetPaymentPlay(){
	    return sqlGetPaymentPlay;
	  }
	//==============================================================================
	  public void setSqlGetBrief(){
	    sqlGetBrief = "select a.modcontentid,a.contentid,b.contentname,a.content_picture_path, "+
	      "decode(b.mainactress, null, b.mainactor, decode(b.mainactor, null, b.mainactress, "+
	      "concat(concat(b.mainactor, ', '), b.mainactress))) actor, b.director, releasedate, averagepoint, milisecond "+
	      "from mod_content a, contents_" +keystb+ " b, vote_results c where a.contentid = b.contentid "+
	      "and b.contentid = c.contentid and a.modcontentid = ?";
	  }

	  public String getSqlGetBrief()
	  {
	    return sqlGetBrief;
	  }
	//==============================================================================
	  public void setSqlGetBrief2()
	  {
	    //The SQL statement to get information in briefly for many contents
	    sqlGetBrief2 =
	      "select a.modcontentid,a.contentid,b.contentname,a.content_picture_path, " +
	      "b.star,a.privateprice from mod_content a, contents_"+keystb+" b " +
	      "where (a.contentid = b.contentid) and (a.modcontentid in ";
	  }

	  public String getSqlGetBrief2()
	  {
	    return sqlGetBrief2;
	  }
	//==============================================================================
	  public void setSqlBrowser()
	  {
	    sqlBrowser =
	    		"select distinct b.contentid,b.svc_id,b.subjectid,a.contentname,a.productor,a.director,a.actors,a.plot,  a.writer,d.duration,a.poster,a.votes,d.filepath,a.currency,a.iunit " +
	    		" from vod_svc_contents a,vod_service_subject b, pms_smartcard c, vod_contents d " +
	    		" where  a.contentid = b.contentid and c.serinumber = '" + keystb + "' and c.LANG_ID = a.lang_id and b.contentid = d.contentid";

	  }

	  public String getSqlBrowser()
	  {
	    return sqlBrowser;
	  }
	//==============================================================================
	   public void setSqlGetDetail()
	   {
	     //Name of DB store procedure to get information in detail of a content
	     sqlGetDetail =
	      "begin mod_content_getdetail_h(?, ?, ?, ?, ?, ?, ?, ?); end;";
	   }

	   public String getSqlGetDetail()
	   {
	     return sqlGetDetail;
	   }
	//==============================================================================
	   public void setSqlVote()
	   {
	     //Name of DB store procedure to get information in detail of a content
	     sqlVote =
	         "begin ? := vote_dovote(?,?,?,?); end;";
	   }

	   public String getSqlVote()
	     {
	       return sqlVote;
	     }
	//==============================================================================
	   public void setSqlGetAllModNextComes()
	   {
	     sqlGetAllModNextComes =
	      "Select modcontentid,modcontentname,content_picture_path,comedate, " +
	      "productor,director,star,mainactor,mainactress,language, " +
	      "subtitle,summary_sort,detaildescription,milisecond, " +
	      "releasedate,provider from mod_content_nextcomming";
	   }

	   public String getSqlGetAllModNextComes()
	   {
	     return sqlGetAllModNextComes;
	   }
	//==============================================================================
	   public void setSqlGetPriceFollowTime(){
	     sqlGetPriceContentFollowTime = "select BILL_GETPRICE_CURRENCY(?) from dual";
	   }

	   public String getSqlGetPriceFollowTime(){
	     return sqlGetPriceContentFollowTime;
	   }
}
