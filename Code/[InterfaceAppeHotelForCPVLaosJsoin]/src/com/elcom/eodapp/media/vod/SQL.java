package com.elcom.eodapp.media.vod;

public interface SQL {
	public static final String sql_VodSubject = "begin vod.getVodSubject(?,?,?); end;";
	public static final String sqlGetListUrl = "begin vod.getUrl(?,?); end;";
	public static final String sqlGetURL =  "begin ? :=  vod.getVodUrl( ?, ?); end;";	
	public static final String sqlurl_getmilitrailer = "begin ? := vod.getMiliTrailer( ?); end;";
	public static final String sqlcheckpay = "begin ? := vod.checkpay(?,?); end;";
	public static final String sqlcountfilm = "begin ? := vod.countFilm(?); end;";
	//public static final String sqlgetListFilm = "begin vod.getMODCtnIDsOfSubjectNew(?,?,?); end;";
	public static final String getByPrimaryKey = "begin vod.getByPrimaryKey(?,?,?); end;";
	public static final String chargeMedia = "begin ? := vod.insertVodChar(?,?,?,?,?,?,?,?); end;";
	public static final String sqlcheckpaid = "begin ? := vod.checkpaid(?,?); end;";
	public static final String sqlgetListFilm = "begin vod.getMODCtnIDsOfSubjectNew(?,?,?); end;";
	public static final String sqlgetFilmCode =  "begin ? :=  vod.getFilmCode(?); end;";
	
}
