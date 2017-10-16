package com.elcom.eodapp.media.mod;

public interface SQL {
	public static final String sqlSubjectAlbum =
		      "begin vod.getModSubject(?,?); end;";	
	public static final String sqlgetModListSongSubject =
		      "begin vod.getModListSongSubject(?,?,?,?,?); end;";
	public static final String sqlgetModFavorite =
		      "begin vod.getModFavorite(?,?,?,?,?); end;";
	public static final String sqladdModFavorite =
		      "begin ? := vod.addModFavorite(?,?); end;";
	public static final String sqlremoveFavorite =
		      "begin ? := vod.removeFavorite(?,?); end;";
	public static final String sqlCountMusic =
		      "begin ? := vod.countMusic(?); end;";	
	public static final String sqlGetUrlImageBack =
			"begin ? := vod.getUrlImageBack(?); end;";
	
}
