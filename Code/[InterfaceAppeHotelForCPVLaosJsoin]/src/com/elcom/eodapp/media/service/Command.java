package com.elcom.eodapp.media.service;

public interface Command {
	public static String command = "command";
	//com cho vod 
	public static int com_getlistsubjectvod = 1;
	public static int com_getlistfileofsubject = 2;
	public static int com_getdetailfilm = 3;
	public static int com_getountilm = 4;
	public static int com_getlisturlsub = 5;
	public static int com_getFilmCode = 6;
	//com cho mod
	public static int com_getmodsubject = 11;
	public static int com_getModListSongSubject = 12;
	public static int com_getountmusic = 13;
	public static int com_getUrlImageBack = 14;
	public static int com_chargeVod = 15;
	public static int com_checkpaid = 16;
	//com cho livetv
	public static int com_getlivesubject = 21;
	public static int com_getlivechannel = 22;
	public static int com_getlivecount = 23;
	//com danh cho phan cas
	public static int com_getlang = 31;
	public static int com_getlogin = 32;
	public static int com_getreg = 33;
	public static int com_static = 35;
	public static int com_getLangs = 46;
	public static int com_stbftpapk = 47;
	public static int com_updatestatusstbftpapk = 48;
	public static int com_setKeyStbIntoRoom = 49;
	//them cho phan welcome khach
	public static int com_getwelcome = 34;
	//them cho phan dong bo gio cho cac stb khong the nho thoi gian
	public static int com_gettiem = 36;
	//Cac chuc nang danh cho Record
	public static int com_setScheduleStb = 37;
	public static int com_getListRecordStb = 38;
	public static int com_delScheduleStb = 39;
	public static int com_updateStatusStbRecord = 40;
	public static int com_getListRecordCore = 41;
	public static int com_updateLinkStbRecord = 42;
	public static int com_updateSizeStbRecord = 43;
	public static int com_updateStatusCore = 44;
	public static int com_delScheduleCore = 45;
	//Cac chuc nang danh cho pms
	public static int com_getMessageInfo = 100;
	public static int com_getFolderMenuPMS = 101;
	public static int com_getWelcomeMessage = 102;
	public static int com_getAdverImages = 103;
	public static int com_getHomeService = 104;
	public static int com_getMainMenu = 105;
	public static int com_getOultetImage = 106;
	public static int com_getSubMenu = 107;     //danh sach của chu đề cấp con serivece dining
	public static int com_getItemOfService = 108; //danh sach cac non an thuoc chu de nao do
	public static int com_getBills = 109; 
	public static int com_getItemOfAttractions = 110;  //danh sach item thuoc Attractons
	public static int com_getItemOfScheduleActivity = 111;  //danh sach item thuoc Activities
	public static int com_getWelcomeMessageFull = 112;  //danh sach item thuoc Activities
	public static int com_postedItemToBill = 114;  //danh sach item thuoc post
	public static int com_getMessage = 115;
	public static int com_getPromotions = 116;
	public static int com_getExchangeRate = 117;
	public static int com_getCountries = 118;    
	public static int com_getWeatherToday = 119;
	public static int com_getWeatherInWeek = 120;
	
	
	
	
}
