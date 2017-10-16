package com.elcom.eodapp.media.pms;

public interface SQL {
	public static final String sqlGetMessageInfo = "{call PMSAPP.getMessageInfo(?,?,?)}";
	public static final String sqlgetFolderMenuPMS = "{call VOD.getFolderMenuPMS(?,?,?)}";
	public static final String sqlgetWelcomeMessage = "{call PMSAPP.getWelcomeMessage(?,?)}";
	public static final String sqlgetAdverImages = "{call PMSAPP.getAdverImages(?,?,?)}";
	public static final String sqlgetHomeService = "{call PMSAPP.getHomeService(?,?)}";
	public static final String sqlgetMainMenu = "{call PMSAPP.getMainMenu(?,?,?)}";
	public static final String sqlgetOultetImage = "{call PMSAPP.getOultetImage(?,?,?,?)}";
	public static final String sqlgetSubMenu = "{call PMSAPP.getSubMenu(?,?,?)}";
	public static final String sqlgetItemOfService = "{call PMSAPP.getItemOfService(?,?,?,?,?)}";
	public static final String sqlgetBills = "{call PMSAPP.getBills(?,?)}";
	public static final String sqlgetItemOfAttractions = "{call PMSAPP.getItemOfAttractions(?,?,?)}";
	public static final String sqlgetItemOfScheduleActivity = "{call PMSAPP.getItemOfScheduleActivity(?,?,?)}";
	public static final String sqlgetpostedItemToBill = "{call PMSAPP.postedItemToBill(?,?,?,?,?,?,?,?,?,?,?)}";
	public static final String sqlgetgetPromotions = "{call PMSAPP.getPromotions(?,?)}";
	public static final String sqlgetMessages = "{call PMSAPP.getMessages(?,?)}";
	public static final String sqlGetExchangeRates = "{call PMSAPP.getExchangeRate(?,?,?)}";
	public static final String sqlGetCountries = "{call PMSAPP.getCountries(?,?)}";
	public static final String sqlGetWeatherToday = "{call PMSAPP.getWeatherToday(?,?)}";
	public static final String sqlGetWeatherInWeek = "{call PMSAPP.getWeathersInWeek(?,?,?)}";
	
}
