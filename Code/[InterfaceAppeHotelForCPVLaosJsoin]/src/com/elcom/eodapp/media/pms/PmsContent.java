package com.elcom.eodapp.media.pms;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Vector;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.elcom.DBI.SubProParam;
import com.elcom.eod.util.UnicodeConverter;
import com.elcom.eodapp.media.broker.IMBroker;
import com.elcom.eodapp.media.cfg.Configuration;
import com.elcom.eodapp.media.cfg.ConfigurationLoader;
import com.elcom.eodapp.media.common.eActivities;
import com.elcom.eodapp.media.common.eAttractions;
import com.elcom.eodapp.media.common.eBill;
import com.elcom.eodapp.media.common.eCountries;
import com.elcom.eodapp.media.common.eGuest;
import com.elcom.eodapp.media.common.eIconMenu;
import com.elcom.eodapp.media.common.eImage;
import com.elcom.eodapp.media.common.eItemDining;
import com.elcom.eodapp.media.common.eItemOrder;
import com.elcom.eodapp.media.common.eMessage;
import com.elcom.eodapp.media.common.ePromotion;
import com.elcom.eodapp.media.common.eService;
import com.elcom.eodapp.media.common.eServiceSub;
import com.elcom.eodapp.media.common.eCurrency;
import com.elcom.eodapp.media.common.eWether;
import com.elcom.eodapp.media.util.DateHelper;

public class PmsContent {
	private static IMBroker broker = IMBroker.getInstance();
	private static final Logger logger = LogManager.getLogger(PmsContent.class);
	private static final String pattern = "MM/dd/yyyy HH:mm:ss";
	private static Configuration config = null;

	static {
		ConfigurationLoader loader = ConfigurationLoader.getInstance();
		config = loader.getConfiguration();
	}

	public PmsContent() {
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public String getMessageInfo(int messId, String smartcard) {
		String xml = "";
		eMessage aMessage = null;
		Vector<eMessage> eMessages = new Vector<eMessage>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subIn = new SubProParam(new java.math.BigDecimal(messId), SubProParam.IN);
		params.add(subIn);

		subIn = new SubProParam(smartcard, SubProParam.IN);
		params.add(subIn);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR", SubProParam.OUT);
		params.add(out_data);

		String outScreen = ("getMessageInfo with messId=" + messId + "] ");

		try {
			params = broker.executeSubPro(SQL.sqlGetMessageInfo, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(2);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		eMessages = LoadMessages(outParam, 0);
		if (eMessages != null && eMessages.size() > 0) {
			aMessage = eMessages.get(0);
		}

		xml = xml + "<item id='" + aMessage.getId() + "'>\r\n";
		xml = xml + "<numNo>" + aMessage.getCheckNo() + "</numNo>\r\n";
		xml = xml + "<content><![CDATA[" + UnicodeConverter.encodeUnicode(aMessage.getContent()) + "]]></content>\r\n";
		xml = xml + "<content_bottom><![CDATA[" + UnicodeConverter.encodeUnicode(aMessage.getContent_bottom()) + "]]></content_bottom>\r\n";
		xml = xml + "<content_top><![CDATA[" + UnicodeConverter.encodeUnicode(aMessage.getContent_top()) + "]]></content_top>\r\n";
		xml = xml + "<enterDate>" + aMessage.getEnterDate() + "</enterDate>\r\n";
		xml = xml + "<enterTime>" + aMessage.getEnterTime() + "</enterTime>\r\n";
		xml = xml + "<room>" + aMessage.getFolioNum() + "</room>\r\n";
		xml = xml + "<isConfirm>" + aMessage.getIsConfirm() + "</isConfirm>\r\n";
		xml = xml + "<isRead>" + aMessage.getIsRead() + "</isRead>\r\n";
		xml = xml + "<sender>" + aMessage.getSender() + "</sender>\r\n";
		xml = xml + "<subject>" + aMessage.getSubject() + "</subject>\r\n";
		xml = xml + "<type>" + aMessage.getType() + "</type>\r\n";
		xml = xml + "</item>\r\n";
		return xml;
	}
    
	@SuppressWarnings({ "unchecked", "unused" })
	public String getFolioMessages(String smartcard) {
		String xml = "";
		eMessage aMessage = null;
		Vector<eMessage> eMessages = new Vector<eMessage>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		
		SubProParam subIn = new SubProParam(smartcard, SubProParam.IN);
		params.add(subIn);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR", SubProParam.OUT);
		params.add(out_data);

		try {
			params = broker.executeSubPro(SQL.sqlgetMessages, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(1);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		eMessages = LoadMessages(outParam, 0);
		if (eMessages != null && eMessages.size() > 0) {
			aMessage = eMessages.get(0);
		}
		xml = xml + "<ehotel version='1.0' code='A0002' cache='1'>\r\n";
		xml = xml + "<name>ELCOM-HCM</name>\r\n";
		for (int i = 0; i < eMessages.size(); i++) {
			eMessage item = eMessages.get(i);
			xml = xml + "<item id='" + item.getId() + "'>\r\n";
			xml = xml + "<numNo>" + item.getCheckNo() + "</numNo>\r\n";
			xml = xml + "<content><![CDATA[" + UnicodeConverter.encodeUnicode(item.getContent()) + "]]></content>\r\n";
			xml = xml + "<content_bottom><![CDATA[" + UnicodeConverter.encodeUnicode(item.getContent_bottom()) + "]]></content_bottom>\r\n";
			xml = xml + "<content_top><![CDATA[" + UnicodeConverter.encodeUnicode(item.getContent_top()) + "]]></content_top>\r\n";
			xml = xml + "<enterDate>" + item.getEnterDate() + "</enterDate>\r\n";
			xml = xml + "<enterTime>" + item.getEnterTime() + "</enterTime>\r\n";
			xml = xml + "<room>" + item.getFolioNum() + "</room>\r\n";
			xml = xml + "<isConfirm>" + item.getIsConfirm() + "</isConfirm>\r\n";
			xml = xml + "<isRead>" + item.getIsRead() + "</isRead>\r\n";
			xml = xml + "<sender>" + item.getSender() + "</sender>\r\n";
			xml = xml + "<subject>" + item.getSubject() + "</subject>\r\n";
			xml = xml + "<type>" + item.getType() + "</type>\r\n";
			xml = xml + "</item>\r\n";
		}
		xml = xml + "</ehotel>";
		return xml;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public String getFolderMenuPMS(String stbkey, int folderid) {
		String xml = "";
		Vector params = new Vector(3);
		SubProParam param = null;
		Vector<String> outParam = new Vector<String>();

		param = new SubProParam(stbkey, SubProParam.IN);
		params.add(param); // 0 IN
		param = new SubProParam(new BigDecimal(folderid), SubProParam.IN);
		params.add(param); // 1 IN
		param = new SubProParam(new String(), SubProParam.OUT);
		params.add(param); // 2 OUT
		params = broker.executeSubPro(SQL.sqlgetFolderMenuPMS, params);
		// Get data returned by the stored procedure
		SubProParam paramOUT = (SubProParam) params.get(2);
		if (paramOUT.getString() == null)
			xml = "0";
		else
			xml = paramOUT.getString();

		return xml;
	}

	private static String decodeHtml(String original) {
		return UnicodeConverter.decodeUnicode(original);
	}

	public static String trim(String data) {
		if (data != null) {
			return data.trim();
		}
		return null;
	}

	public static int parseInt(String number) {
		number = trim(number);
		if (number != null && !number.equals("null") && !number.equals("")) {
			return Integer.parseInt(number);
		}
		return 0;
	}

	// -----------------get welcome guest----------------------
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getWelcomeMessage(String stbkey,String typetvbox) throws IOException {
		String jsonText = "";
		JSONObject obj = new JSONObject();
		Vector params = new Vector(2);
		SubProParam param = null;
		Vector<String> outParam = new Vector<String>();

		param = new SubProParam(stbkey, SubProParam.IN);
		params.add(param); // 0 IN
		SubProParam out_data = new SubProParam(outParam, "STRING_ARR", SubProParam.OUT);
		params.add(out_data);

		try {
			params = broker.executeSubPro(SQL.sqlgetWelcomeMessage, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(1);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		eGuest guest = LoadGuest(outParam);
		if (typetvbox.equals("TV")){ 
			obj = new JSONObject();
		    obj.put("room_code","ROOM" + guest.getRoom_code());
		    obj.put("name",guest.getName());
		    obj.put("welcome",guest.getWelcome());
		    obj.put("arrival",guest.getArrival());
		    obj.put("depature",guest.getDepature());
		    obj.put("langCode",guest.getLangCode());
	    }else 
		{
	    	obj = new JSONObject();
	    	obj.put("room_code","ROOM" + guest.getRoom_code());
		    obj.put("name",UnicodeConverter.encodeUnicode(guest.getName()));
		    obj.put("welcome",UnicodeConverter.encodeUnicode(guest.getWelcome()));
		    obj.put("arrival",guest.getArrival());
		    obj.put("depature",guest.getDepature());
		    obj.put("langCode",guest.getLangCode());
		}
		StringWriter out = new StringWriter();
	    obj.writeJSONString(out);  
	    jsonText = out.toString();
		return jsonText;
	}

	// -----------------get Image ADV ----------------------
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getAdverImages(String stbkey, String types) throws IOException {
		String jsonText = "";
		JSONObject obj = new JSONObject();
		JSONArray ja = new JSONArray();
		Vector params = new Vector(2);
		SubProParam param = null;
		Vector<String> outParam = new Vector<String>();

		param = new SubProParam(types, SubProParam.IN);
		params.add(param); // 0 IN
		param = new SubProParam(stbkey, SubProParam.IN);
		params.add(param); // 1 IN
		SubProParam out_data = new SubProParam(outParam, "STRING_ARR", SubProParam.OUT);
		params.add(out_data);

		try {
			params = broker.executeSubPro(SQL.sqlgetAdverImages, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(2);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Vector<eImage> images = LoadImages(outParam);
		
		
		for (int i = 0; i < images.size(); i++) {
			obj = new JSONObject();
			eImage image = new eImage();
			image = images.get(i);
			obj.put("id",image.getImageId());
			obj.put("name",UnicodeConverter.encodeUnicode(image.getImageName()));
			obj.put("url",UnicodeConverter.encodeUnicode(image.getImageUrl()));
			
			ja.add(obj);
		}
		 StringWriter out = new StringWriter();
	     ja.writeJSONString(out);
	     jsonText = out.toString();
		return jsonText;
	}
	
	// -----------------get Promotion ----------------------
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public String getPromotions(String stbkey) {
			String xml = "";
			Vector params = new Vector(2);
			SubProParam param = null;
			Vector<String> outParam = new Vector<String>();

			param = new SubProParam(stbkey, SubProParam.IN);
			params.add(param); // 0 IN
			SubProParam out_data = new SubProParam(outParam, "STRING_ARR", SubProParam.OUT);
			params.add(out_data);

			try {
				params = broker.executeSubPro(SQL.sqlgetgetPromotions, params);
				if (params != null & params.size() > 0) {
					out_data = (SubProParam) params.get(1);
					outParam = out_data.getVector();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			Vector<ePromotion> promotion = LoadPromotions(outParam);

			//xml = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?>\r\n";
			xml = "";
			xml = xml + "<ehotel version='1.0' code='A0002' cache='1'>\r\n";
			xml = xml + "<name>ELCOM-HCM</name>\r\n";
			for (int i = 0; i < promotion.size(); i++) {
				ePromotion promo = new ePromotion();
				promo = promotion.get(i);
				xml = xml + "<item id='" + promo.getPromotionId() + "'>\r\n";
				xml = xml + "<proName><![CDATA[" + UnicodeConverter.encodeUnicode(promo.getProName()) + "]]></proName>\r\n";
				xml = xml + "<name><![CDATA[" + UnicodeConverter.encodeUnicode(promo.getName()) + "]]></name>\r\n";
				xml = xml + "<proDef><![CDATA[" + UnicodeConverter.encodeUnicode(promo.getProDef()) + "]]></proDef>\r\n";
				xml = xml + "<url>" + promo.getUrl() + "</url>\r\n";
				xml = xml + "<urlBg>" + promo.getUrlBg() + "</urlBg>\r\n";
				xml = xml + "<urlIcon>" + promo.getUrlIcon() + "</urlIcon>\r\n";
				xml = xml + "<urlLink>" + promo.getUrlLink() + "</urlLink>\r\n";
				xml = xml + "</item>\r\n";
			}
			xml = xml + "</ehotel>";
			return xml;
		}

	// ----------------- get RVC SERVICE ----------------------
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getHomeService(String stbkey) throws IOException {
		String jsonText = "";
		JSONObject obj = new JSONObject();
		JSONArray ja = new JSONArray();
		Vector params = new Vector(2);
		SubProParam param = null;
		Vector<String> outParam = new Vector<String>();

		param = new SubProParam(stbkey, SubProParam.IN);
		params.add(param); // 0 IN

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR", SubProParam.OUT);
		params.add(out_data);

		try {
			params = broker.executeSubPro(SQL.sqlgetHomeService, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(1);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Vector<eService> services = LoadService(outParam);
		for (int i = 0; i < services.size(); i++) {
			obj = new JSONObject();
			eService service = new eService();
			service = services.get(i);
			obj.put("id",service.getService_id());
			obj.put("name",UnicodeConverter.encodeUnicode(service.getService_name()));
			obj.put("service_code",service.getService_code());
			obj.put("apk_code",service.getApk_code());
			obj.put("apk_ver",service.getVerapk());
			obj.put("urlIcon",service.getUrl_icon());
			obj.put("urlImage",service.getUrl_image());
			obj.put("urlPicBg",service.getUrl_picbg());
			obj.put("level",service.getIlevel());
			obj.put("urllink",service.getService_url());
			
			ja.add(obj);
		}
		StringWriter out = new StringWriter();
	    ja.writeJSONString(out);
	    jsonText = out.toString();
		return jsonText;
	}

	// ---------------------Get chu de thuoc info
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getMainMenu(String serviceid, String stbkey) throws IOException {
		String jsonText = "";
		JSONObject obj = new JSONObject();
		JSONArray ja = new JSONArray();
		Vector params = new Vector(3);
		SubProParam param = null;
		Vector<String> outParam = new Vector<String>();

		param = new SubProParam(new java.math.BigDecimal(serviceid), SubProParam.IN);
		params.add(param); // 0 IN
		param = new SubProParam(stbkey, SubProParam.IN);
		params.add(param); // 1 IN

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR", SubProParam.OUT);
		params.add(out_data);
//		System.out.println(params.get(0).toString());
		try {
			params = broker.executeSubPro(SQL.sqlgetMainMenu, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(2);
				outParam = out_data.getVector();
			}
//			System.out.println("out_data: " + outParam.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Vector<eIconMenu> iconmenu = LoadMainMenu(outParam);

		for (int i = 0; i < iconmenu.size(); i++) {
			eIconMenu icon = new eIconMenu();
			obj = new JSONObject();
			icon = iconmenu.get(i);
			obj.put("id",icon.getMenuid());
			obj.put("name",UnicodeConverter.encodeUnicode(icon.getMenuName()));
			obj.put("urlPicBg",icon.getUrlBg());
			obj.put("urlIcon",icon.getUrlIcon());
			obj.put("urlImage",icon.getUrlImage());
			obj.put("level",icon.getIlevel());
			ja.add(obj);
		}
		StringWriter out = new StringWriter();
	      ja.writeJSONString(out);
	      jsonText = out.toString();
		return jsonText;
	}

	// ------------get image def getOultetImage
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getOultetImage(String outletid, String outletType, String stbkey) throws IOException {
		System.out.println("id: " + outletid + " - type: " + outletType + " - key: " + stbkey);
		String jsonText = "";
		JSONObject obj = new JSONObject();
		JSONArray ja = new JSONArray();
		Vector params = new Vector(4);
		SubProParam param = null;
		Vector<String> outParam = new Vector<String>();
		param = new SubProParam(new java.math.BigDecimal(outletid), SubProParam.IN);
		params.add(param); // 0 IN
		param = new SubProParam(stbkey, SubProParam.IN);
		params.add(param); // 1 IN
		param = new SubProParam(outletType, SubProParam.IN);
		params.add(param); // 2 IN

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR", SubProParam.OUT);
		params.add(out_data);

		try {
			params = broker.executeSubPro(SQL.sqlgetOultetImage, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(3);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Vector<eIconMenu> iconmenu = LoadOutlet(outParam);

		for (int i = 0; i < iconmenu.size(); i++) {
			obj = new JSONObject();
			eIconMenu icon = new eIconMenu();
			icon = iconmenu.get(i);
			obj.put("id",icon.getMenuid());
			obj.put("name",UnicodeConverter.encodeUnicode(icon.getMenuName()));
			obj.put("def",UnicodeConverter.encodeUnicode(icon.getImageDef()));
			obj.put("urlImage",icon.getUrlImage());
			obj.put("urlBg",icon.getUrlBg());
			obj.put("urlIcon",icon.getUrlIcon());
			ja.add(obj);
		}
		StringWriter out = new StringWriter();
	    ja.writeJSONString(out);
	    jsonText = out.toString();
		return jsonText;
	}
    //-------------------lay danh sach chu de con cua chu de dining
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getSubMenu(String mainMenuId,String stbkey) throws IOException {
		String jsonText = "";
		JSONObject obj = new JSONObject();
		JSONArray ja = new JSONArray();
		Vector params = new Vector(3);
		SubProParam param = null;
		Vector<String> outParam = new Vector<String>();
		param = new SubProParam(new java.math.BigDecimal(mainMenuId), SubProParam.IN);
		params.add(param); // 0 IN
		param = new SubProParam(stbkey, SubProParam.IN);
		params.add(param); // 1 IN

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR", SubProParam.OUT);
		params.add(out_data);

		try {
			params = broker.executeSubPro(SQL.sqlgetSubMenu, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(2);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Vector<eServiceSub> iconmenu = LoadSubMenu(outParam);

		for (int i = 0; i < iconmenu.size(); i++) {
			obj = new JSONObject();
			eServiceSub icon = new eServiceSub();
			icon = iconmenu.get(i);
			obj.put("id", icon.getMenuId());
			obj.put("name",  UnicodeConverter.encodeUnicode(icon.getMenuName()));
			obj.put("urlImage", icon.getUrlImage());
			obj.put("urlBg", icon.getUrlBg());
			obj.put("urlIcon", icon.getUrlIcon());
			obj.put("level", icon.getIlevel());
			
			ja.add(obj);
		}
		StringWriter out = new StringWriter();
	      ja.writeJSONString(out);
	      jsonText = out.toString();
		  return jsonText;
	}
	//---------------------Lay danh sach cac mon an thuoc dining
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getItemOfService(String subMenuId,String stbkey,String ffrom,String tto) throws IOException {
		String jsonText = "";
		JSONObject obj = new JSONObject();
		JSONArray ja = new JSONArray();
		
		Vector params = new Vector(5);
		SubProParam param = null;
		Vector<String> outParam = new Vector<String>();
		param = new SubProParam(new java.math.BigDecimal(subMenuId), SubProParam.IN);
		params.add(param); // 0 IN
		param = new SubProParam(stbkey, SubProParam.IN);
		params.add(param); // 1 IN
		param = new SubProParam(new java.math.BigDecimal(ffrom), SubProParam.IN);
		params.add(param); // 2 IN
		param = new SubProParam(new java.math.BigDecimal(tto), SubProParam.IN);
		params.add(param); // 3 IN

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR", SubProParam.OUT);
		params.add(out_data);

		try {
			params = broker.executeSubPro(SQL.sqlgetItemOfService, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(4);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Vector<eItemDining> iconmenu = LoadItemDining(outParam);
		
		for (int i = 0; i < iconmenu.size(); i++) {
			obj = new JSONObject();
			eItemDining icon = new eItemDining();
			icon = iconmenu.get(i);
			
			obj.put("id", icon.getItemCode());
			obj.put("name", UnicodeConverter.encodeUnicode(icon.getItemNname()));
			obj.put("printname", UnicodeConverter.encodeUnicode(icon.getPrintName()));
			obj.put("def", UnicodeConverter.encodeUnicode(icon.getPrintName()));
			obj.put("menuno", icon.getPrintName());
			obj.put("currency", icon.getItemCurrency());
			obj.put("currencysmall", icon.getCurrencySmall());
			obj.put("currencylagre", icon.getCurrencyLagre());
			obj.put("urlImage", icon.getUrlImage());
			obj.put("urlBg", icon.getUrlBg());
			obj.put("urlIcon", icon.getUrlIcon());
			ja.add(obj);
		}
		StringWriter out = new StringWriter();
	      ja.writeJSONString(out);
	      jsonText = out.toString();
		  return jsonText;
	}
	
	//--------------------------lay cac item thuoc Attraction-----------------------
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public String getItemOfAttractions(String subMenuId,String stbkey) {
			String xml = "";
			Vector params = new Vector(3);
			SubProParam param = null;
			Vector<String> outParam = new Vector<String>();
			param = new SubProParam(new java.math.BigDecimal(subMenuId), SubProParam.IN);
			params.add(param); // 0 IN
			param = new SubProParam(stbkey, SubProParam.IN);
			params.add(param); // 1 IN

			SubProParam out_data = new SubProParam(outParam, "STRING_ARR", SubProParam.OUT);
			params.add(out_data);

			try {
				params = broker.executeSubPro(SQL.sqlgetItemOfAttractions, params);
				if (params != null & params.size() > 0) {
					out_data = (SubProParam) params.get(2);
					outParam = out_data.getVector();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			Vector<eAttractions> bill = LoadItemAttraction(outParam);  
			//xml = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?>\r\n";
			xml = "";
			xml = xml + "<ehotel version='1.0' code='A0002' cache='1'>\r\n";
			xml = xml + "<name>ELCOM-HCM</name>\r\n";
			for (int i = 0; i < bill.size(); i++) {
				eAttractions icon = new eAttractions();
				icon = bill.get(i);
				xml = xml + "<item id='" + icon.getIcode() + "'>\r\n";
				xml = xml + "<menuid>" + icon.getMenuId() + "</menuid>\r\n";
				xml = xml + "<name>" + UnicodeConverter.encodeUnicode(icon.getName()) + "</name>\r\n";
				xml = xml + "<address>" + UnicodeConverter.encodeUnicode(icon.getAddress()) + "</address>\r\n";
				xml = xml + "<description>" + UnicodeConverter.encodeUnicode(icon.getDescription()) + "</description>\r\n";
				xml = xml + "<urlImage>" + icon.getUrlImage() + "</urlImage>\r\n";
				xml = xml + "<urlBg>" + icon.getUrlBg() + "</urlBg>\r\n";
				xml = xml + "<urlIcon>" + icon.getUrlIcon() + "</urlIcon>\r\n";
				
				
				xml = xml + "</item>\r\n";
			}
			xml = xml + "</ehotel>";
			return xml;
		}
		//--------------------------lay cac item thuoc Activities-----------------------
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public String getItemOfActivities(String subMenuId,String stbkey) {
			String xml = "";
			Vector params = new Vector(3);
			SubProParam param = null;
			Vector<String> outParam = new Vector<String>();
			param = new SubProParam(new java.math.BigDecimal(subMenuId), SubProParam.IN);
			params.add(param); // 0 IN
			param = new SubProParam(stbkey, SubProParam.IN);
			params.add(param); // 1 IN

			SubProParam out_data = new SubProParam(outParam, "STRING_ARR", SubProParam.OUT);
			params.add(out_data);
			try {
				params = broker.executeSubPro(SQL.sqlgetItemOfScheduleActivity, params);
				if (params != null & params.size() > 0) {
					out_data = (SubProParam) params.get(2);
					outParam = out_data.getVector();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			Vector<eActivities> activitis = LoadItemActivitie(outParam);  
			//xml = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?>\r\n";
			xml = "";
			xml = xml + "<ehotel version='1.0' code='A0002' cache='1'>\r\n";
			xml = xml + "<name>ELCOM-HCM</name>\r\n";
			for (int i = 0; i < activitis.size(); i++) {
				eActivities icon = new eActivities();
				icon = activitis.get(i);
				xml = xml + "<item id='" + icon.getId() + "'>\r\n";
				xml = xml + "<name>" + UnicodeConverter.encodeUnicode(icon.getName()) + "</name>\r\n";
				xml = xml + "<description>" + icon.getDescription() + "</description>\r\n";
				
				xml = xml + "</item>\r\n";
			}
			xml = xml + "</ehotel>";
			return xml;
		}
				
	//--------------------------lay bill-----------------------
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getBills(String stbkey) throws IOException {
		String jsonText = "";
		  JSONObject obj = new JSONObject();
		  JSONArray ja = new JSONArray();
		Vector params = new Vector(2);
		SubProParam param = null;
		Vector<String> outParam = new Vector<String>();
		param = new SubProParam(stbkey, SubProParam.IN);
		params.add(param); // 0 IN

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR", SubProParam.OUT);
		params.add(out_data);

		try {
			params = broker.executeSubPro(SQL.sqlgetBills, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(1);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Vector<eBill> bill = LoadItemBill(outParam);
		
		for (int i = 0; i < bill.size(); i++) {
			obj = new JSONObject();
			eBill icon = new eBill();
			icon = bill.get(i);
			obj.put("id", icon.getId());
			obj.put("folio", icon.getFolio());
			obj.put("itemcode", icon.getItemCode());
			obj.put("ipirce", icon.getIPirce());
			obj.put("iqty", icon.getIQty());
			obj.put("iamount", icon.getIAmount());
			obj.put("istartdate", icon.getIStartDate());
			obj.put("itype", icon.getIType());
			obj.put("ismartcard", icon.getISmartCard());
			obj.put("iunit", icon.getIUnit());
	        
			ja.add(obj);
		}
		StringWriter out = new StringWriter();
	      ja.writeJSONString(out);
	      jsonText = out.toString();
		  return jsonText;
	}
	//--------------------------------------------------------------------------------
	public String postedItemToBill(String items)
	{
		String error = "0";
		Vector<eItemOrder> items_ = DateHelper.getItemOrder(items);
		eItemOrder item = new eItemOrder();
		for (int i = 0 ; i < items_.size(); i++)
		{
			item = items_.get(i);
			System.out.println(item.toString());
		}
		return error;
	}
	
	public  String getExchangeRates(int fromRow, int noRows) {
		String xml = "";
		Vector<String> outParam = new Vector<String>();
		Vector<eCurrency> veCurrency = new Vector<eCurrency>();
		Vector<SubProParam> params = new Vector<SubProParam>();

		SubProParam subIFrom = new SubProParam(
				new java.math.BigDecimal(fromRow), SubProParam.IN);
		params.add(subIFrom);

		SubProParam subITo = new SubProParam(new java.math.BigDecimal(noRows),
				SubProParam.IN);
		params.add(subITo);

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(out_data);

		String outScreen = "getExchangeRates with params:[fromRow=" + fromRow
				+ "noRows=" + noRows + "] ";
		try {
			params = broker.executeSubPro(SQL.sqlGetExchangeRates, params);

			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(2);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			
		}
		veCurrency = LoadCurrencies(outParam);
		xml = "";
		xml = xml + "<ehotel version='1.0' code='A0002' cache='1'>\r\n";
		xml = xml + "<name>ELCOM-HCM</name>\r\n";
		for (int i = 0; i < veCurrency.size(); i++) {
			eCurrency icon = new eCurrency();
			icon = veCurrency.get(i);
			xml = xml + "<item id='" + icon.getId() + "'>\r\n";
			xml = xml + "<buyRate>" + icon.getBuyRate() + "</buyRate>\r\n";
			xml = xml + "<code>" + icon.getCode() + "</code>\r\n";
			xml = xml + "<exRateVN>" + icon.getExRateVN() + "</exRateVN>\r\n";
			xml = xml + "<name>" + icon.getName() + "</name>\r\n";
			xml = xml + "<sellRate>" + icon.getSellRate() + "</sellRate>\r\n";
			xml = xml + "<symbol>" + icon.getSymbol() + "</symbol>\r\n";
			xml = xml + "<transferRate>" + icon.getTransferRate() + "</transferRate>\r\n";
			xml = xml + "<url>" + icon.getUrl() + "</url>\r\n";
			xml = xml + "<urlBg>" + icon.getUrlBg() + "</urlBg>\r\n";
			xml = xml + "<urlICon>" + icon.getUrlICon() + "</urlICon>\r\n";
			
			xml = xml + "</item>\r\n";
		}
		xml = xml + "</ehotel>";
		
	
		
		return xml;
	}
	
	public String getCountries(String level) {
		String xml = "";
		Vector params = new Vector(2);
		SubProParam param = null;
		Vector<String> outParam = new Vector<String>();

		param = new SubProParam(level, SubProParam.IN);
		params.add(param); // 0 IN

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR", SubProParam.OUT);
		params.add(out_data);

		try {
			params = broker.executeSubPro(SQL.sqlGetCountries, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(1);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Vector<eCountries> services = LoadCountries(outParam);

		
		xml = "";
		xml = xml + "<ehotel version='1.0' code='A0002' cache='1'>\r\n";
		xml = xml + "<name>ELCOM-HCM</name>\r\n";
		for (int i = 0; i < services.size(); i++) {
			eCountries service = new eCountries();
			service = services.get(i);
			xml = xml + "<item id='" + service.getId() + "'>\r\n";
			xml = xml + "<name><![CDATA[" + UnicodeConverter.encodeUnicode(service.getName()) + "]]></name>\r\n";
			xml = xml + "<code>" + service.getCode() + "</code>\r\n";
			xml = xml + "<urlIcon>" + service.getIcon() + "</urlIcon>\r\n";
			xml = xml + "<urlImage>" + service.getImage() + "</urlImage>\r\n";
			xml = xml + "<urlPicBg>" + service.getBg() + "</urlPicBg>\r\n";
			xml = xml + "</item>\r\n";
		}
		xml = xml + "</ehotel>";
		return xml;
	}
	//----------------------------------------------------------------------
	public String getWeatherToday(String day) {
		String xml = "";
		Vector params = new Vector(2);
		SubProParam param = null;
		Vector<String> outParam = new Vector<String>();

		param = new SubProParam(day, SubProParam.IN);
		params.add(param); // 0 IN

		SubProParam out_data = new SubProParam(outParam, "STRING_ARR", SubProParam.OUT);
		params.add(out_data);

		try {
			params = broker.executeSubPro(SQL.sqlGetWeatherToday, params);
			if (params != null & params.size() > 0) {
				out_data = (SubProParam) params.get(1);
				outParam = out_data.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Vector<eWether> services = LoadWether(outParam);

		
		xml = "";
		xml = xml + "<ehotel version='1.0' code='A0002' cache='1'>\r\n";
		xml = xml + "<name>ELCOM-HCM</name>\r\n";
		for (int i = 0; i < services.size(); i++) {
			eWether service = new eWether();
			service = services.get(i);
			xml = xml + "<item id='" + service.getId() + "'>\r\n";
			xml = xml + "<datetime>" + service.getDatetime() + "</datetime>\r\n";
			xml = xml + "<temp>" + service.getTemp() + "</temp>\r\n";
			xml = xml + "<tempmin>" + service.getTempmin() + "</tempmin>\r\n";
			xml = xml + "<tempmax>" + service.getTempmax() + "</tempmax>\r\n";
			xml = xml + "<day>" + service.getDay() + "</day>\r\n";
			xml = xml + "<urlImage>" + service.getImage() + "</urlImage>\r\n";
			xml = xml + "<description>" + service.getDescription() + "</description>\r\n";
			xml = xml + "</item>\r\n";
		}
		xml = xml + "</ehotel>";
		return xml;
	}
	//----------------------------------------------------------------------
		public String getWeatherInWeek(String countruid,String sn) {
			String xml = "";
			Vector params = new Vector(3);
			SubProParam param = null;
			Vector<String> outParam = new Vector<String>();

			param = new SubProParam(countruid, SubProParam.IN);
			params.add(param); // 0 IN
			param = new SubProParam(sn, SubProParam.IN);
			params.add(param); // 1 IN

			SubProParam out_data = new SubProParam(outParam, "STRING_ARR", SubProParam.OUT);
			params.add(out_data);

			try {
				params = broker.executeSubPro(SQL.sqlGetWeatherInWeek, params);
				if (params != null & params.size() > 0) {
					out_data = (SubProParam) params.get(2);
					outParam = out_data.getVector();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			Vector<eWether> services = LoadWether(outParam);

			
			xml = "";
			xml = xml + "<ehotel version='1.0' code='A0002' cache='1'>\r\n";
			xml = xml + "<name>ELCOM-HCM</name>\r\n";
			for (int i = 0; i < services.size(); i++) {
				eWether service = new eWether();
				service = services.get(i);
				xml = xml + "<item id='" + service.getId() + "'>\r\n";
				xml = xml + "<datetime>" + service.getDatetime() + "</datetime>\r\n";
				xml = xml + "<temp>" + service.getTemp() + "</temp>\r\n";
				xml = xml + "<tempmin>" + service.getTempmin() + "</tempmin>\r\n";
				xml = xml + "<tempmax>" + service.getTempmax() + "</tempmax>\r\n";
				xml = xml + "<day>" + service.getDay() + "</day>\r\n";
				xml = xml + "<urlImage>" + service.getImage() + "</urlImage>\r\n";
				xml = xml + "<description>" + service.getDescription() + "</description>\r\n";
				xml = xml + "</item>\r\n";
			}
			xml = xml + "</ehotel>";
			return xml;
		}
	// ---------------------LOAD DATA
	public static Vector<eCurrency> LoadCurrencies(Vector<String> vTmp) {
		Vector<eCurrency> ret = new Vector<eCurrency>();
		eCurrency currency = null;
		for (int i = 0; i < vTmp.size(); i = i + 11) {
			currency = new eCurrency();
			currency.setId((String) vTmp.get(i));
			currency.setSymbol((String) vTmp.get(i + 1));
			currency.setName((String) vTmp.get(i + 2));
			currency.setCode((String) vTmp.get(i + 3));

			String buyRate = (String) vTmp.get(i + 4);
			if (buyRate == null || buyRate.equals("")) {
				buyRate = "0";
			}
			currency.setBuyRate(buyRate);
			String sellRate = (String) vTmp.get(i + 5);
			if (sellRate == null || sellRate.equals("")) {
				sellRate = "0";
			}
			currency.setSellRate(sellRate);
			String TransferRate = (String) vTmp.get(i + 6);
			if (TransferRate == null || TransferRate.equals("")) {
				TransferRate = "0";
			}
			currency.setTransferRate(TransferRate);

			String exRateVN = (String) vTmp.get(i + 7);
			if (exRateVN == null || exRateVN.equals("")) {
				exRateVN = "0";
			}
			currency.setExRateVN(exRateVN);

			currency.setUrl((String) vTmp.get(i + 8));
			currency.setUrlBg((String) vTmp.get(i + 9));
			currency.setUrlICon((String) vTmp.get(i + 10));

			ret.add(currency);
		}

		return ret;

	}
	
	public static Vector<eAttractions> LoadItemAttraction(Vector<String> vTmp) {
		Vector<eAttractions> ret = new Vector<eAttractions>();
		eAttractions icon = null;
		for (int i = 0; i < vTmp.size(); i = i + 8) {
			icon = new eAttractions();
			icon.setIcode(Integer.parseInt((String) vTmp.get(i)));
			icon.setName((String) vTmp.get(i + 1));
			icon.setAddress((String) vTmp.get(i + 2));
			icon.setDescription((String) vTmp.get(i + 3));
			icon.setUrlImage((String) vTmp.get(i + 4));
			icon.setUrlBg((String) vTmp.get(i + 5));
			icon.setUrlIcon((String) vTmp.get(i + 6));
			icon.setMenuId(Integer.parseInt((String) vTmp.get(i + 6)));
			ret.add(icon);
		}
		return ret;
	}
	
	public static Vector<eActivities> LoadItemActivitie(Vector<String> vTmp) {
		Vector<eActivities> ret = new Vector<eActivities>();
		eActivities icon = null;
		for (int i = 0; i < vTmp.size(); i = i + 3) {
			icon = new eActivities();
			icon.setId(Integer.parseInt((String) vTmp.get(i)));
			icon.setName((String) vTmp.get(i + 1));
			icon.setDescription((String) vTmp.get(i + 2));
			ret.add(icon);
		}
		return ret;
	}
	
	public static Vector<eBill> LoadItemBill(Vector<String> vTmp) {
		Vector<eBill> ret = new Vector<eBill>();
		eBill icon = null;
		for (int i = 0; i < vTmp.size(); i = i + 11) {
			icon = new eBill();
			icon.setId(Integer.parseInt((String) vTmp.get(i)));
			icon.setFolio((String) vTmp.get(i + 1));
			icon.setItemCode((String) vTmp.get(i + 2));
			icon.setIPirce((String) vTmp.get(i + 3));
			icon.setIQty((String) vTmp.get(i + 4));
			icon.setIAmount((String) vTmp.get(i + 5));
			icon.setIStartDate((String) vTmp.get(i + 6));
			icon.setIStartTime((String) vTmp.get(i + 7));
			icon.setIType((String) vTmp.get(i + 8));
			icon.setISmartCard((String) vTmp.get(i + 9));
			icon.setIUnit((String) vTmp.get(i + 10));
			ret.add(icon);
		}
		return ret;
	}
	
	
	
	public static Vector<ePromotion> LoadPromotions(Vector<String> vTmp) {
		Vector<ePromotion> ret = new Vector<ePromotion>();
		ePromotion icon = null;
		for (int i = 0; i < vTmp.size(); i = i + 8) {
			icon = new ePromotion();
			icon.setPromotionId(Integer.parseInt((String) vTmp.get(i)));
			String proname = (String) vTmp.get(i + 1);
			icon.setProName((decodeHtml(proname)));
			proname = (String) vTmp.get(i + 2);
			icon.setName((decodeHtml(proname)));
			proname = (String) vTmp.get(i + 3);
			icon.setProDef((decodeHtml(proname)));
			icon.setUrl((String) vTmp.get(i + 4));
			icon.setUrlBg((String) vTmp.get(i + 5));
			icon.setUrlIcon((String) vTmp.get(i + 6));
			icon.setUrlLink((String) vTmp.get(i + 7));
			ret.add(icon);
		}
		return ret;
	}
	
	public static Vector<eItemDining> LoadItemDining(Vector<String> vTmp) {
		Vector<eItemDining> ret = new Vector<eItemDining>();
		eItemDining icon = null;
		for (int i = 0; i < vTmp.size(); i = i + 12) {
			icon = new eItemDining();
			icon.setItemCode((String) vTmp.get(i));
			String name = (String) vTmp.get(i + 1);
			icon.setItemNname((decodeHtml(name)));
			name = (String) vTmp.get(i + 2);
			icon.setPrintName((decodeHtml(name)));
			name = (String) vTmp.get(i + 3);
			icon.setItemDef((decodeHtml(name)));
			icon.setMenuno((String) vTmp.get(i + 4));
			icon.setItemCurrency((String) vTmp.get(i + 5));
			icon.setCurrencySmall((String) vTmp.get(i + 6));
			icon.setCurrencyLagre((String) vTmp.get(i + 7));
			icon.setItemUnit((String) vTmp.get(i + 8));
			icon.setUrlImage((String) vTmp.get(i + 9));
			icon.setUrlBg((String) vTmp.get(i + 10));
			icon.setUrlIcon((String) vTmp.get(i + 11));
			ret.add(icon);
		}
		return ret;
	}
	
	
	
	public static Vector<eServiceSub> LoadSubMenu(Vector<String> vTmp) {
		Vector<eServiceSub> ret = new Vector<eServiceSub>();
		eServiceSub icon = null;
		for (int i = 0; i < vTmp.size(); i = i + 6) {
			icon = new eServiceSub();
			icon.setMenuId(Integer.parseInt((String) vTmp.get(i)));
			String name = (String) vTmp.get(i + 1);
			icon.setMenuName(decodeHtml(name));
			icon.setUrlImage((String) vTmp.get(i + 2));
			icon.setUrlBg((String) vTmp.get(i + 3));
			icon.setUrlIcon((String) vTmp.get(i + 4));
			icon.setIlevel((String) vTmp.get(i + 5));
			System.out.print((String) vTmp.get(i + 5));
			ret.add(icon);
		}
		return ret;
	}
	
	public static Vector<eIconMenu> LoadOutlet(Vector<String> vTmp) {
		Vector<eIconMenu> ret = new Vector<eIconMenu>();
		eIconMenu icon = null;
		for (int i = 0; i < vTmp.size(); i = i + 6) {
			icon = new eIconMenu();
			icon.setMenuid(Integer.parseInt((String) vTmp.get(i)));
			String name = (String) vTmp.get(i + 1);
			icon.setMenuName(decodeHtml(name));
			icon.setImageDef((String) vTmp.get(i + 2));
			icon.setUrlImage((String) vTmp.get(i + 3));
			icon.setUrlBg((String) vTmp.get(i + 4));
			icon.setUrlIcon((String) vTmp.get(i + 5));
			ret.add(icon);
		}
		return ret;
	}

	public static Vector<eIconMenu> LoadMainMenu(Vector<String> vTmp) {
		Vector<eIconMenu> ret = new Vector<eIconMenu>();
		eIconMenu icon = null;
		for (int i = 0; i < vTmp.size(); i = i + 6) {
			icon = new eIconMenu();
			icon.setMenuid(Integer.parseInt((String) vTmp.get(i)));
			String name = (String) vTmp.get(i + 1);
			icon.setMenuName(decodeHtml(name));
			icon.setUrlImage((String) vTmp.get(i + 2));
			icon.setUrlBg((String) vTmp.get(i + 3));
			icon.setUrlIcon((String) vTmp.get(i + 4));
			icon.setIlevel((String) vTmp.get(i + 5));
			ret.add(icon);
		}
		return ret;
	}

	// -----------------------------------------
	public static eGuest LoadGuest(Vector<String> vTmp) {

		eGuest guest = new eGuest();
		for (int i = 0; i < vTmp.size(); i = i + 10) {
			guest.setId(Integer.parseInt((String) vTmp.get(i)));
			guest.setName((String) vTmp.get(i + 1));
			guest.setArrival((String) vTmp.get(i + 3));
			guest.setDepature((String) vTmp.get(i + 4));
			guest.setRoom_code(Integer.parseInt((String) vTmp.get(i + 5)));
			guest.setLangCode((String) vTmp.get(i + 7));
			guest.setWelcome((String) vTmp.get(i + 9));

		}
		return guest;
	}

	public static Vector<eMessage> LoadMessages(Vector<String> vTmp, int numStr) {
		Vector<eMessage> ret = new Vector<eMessage>();
		eMessage mess = null;
		for (int i = 0; i < vTmp.size(); i = i + 12) {
			mess = new eMessage();
			mess.setId(Integer.parseInt((String) vTmp.get(i)));
			String subject = (String) vTmp.get(i + 1);
			mess.setSubject(decodeHtml(subject));

			String content = (String) vTmp.get(i + 2);
			mess.setContent(decodeHtml(content));

			String content_top = (String) vTmp.get(i + 3);
			mess.setContent_top(decodeHtml(content_top));

			String content_bottom = (String) vTmp.get(i + 4);
			mess.setContent_bottom(decodeHtml(content_bottom));

			String isRead = (String) vTmp.get(i + 5);
			if (isRead == null || isRead.equals("")) {
				isRead = "0";
			}
			mess.setIsRead(Integer.parseInt(isRead));

			mess.setCheckNo(parseInt((String) vTmp.get(i + 6)));

			String sender = (String) vTmp.get(i + 7);
			mess.setSender(decodeHtml(sender));

			String type = (String) vTmp.get(i + 8);
			if (type == null) {
				type = "NORMAL";
			}
			mess.setType(type);

			mess.setEnterDate((String) vTmp.get(i + 9));
			mess.setEnterTime((String) vTmp.get(i + 10));
			mess.setFolioNum(parseInt((String) vTmp.get(i + 11)));
			ret.add(mess);
		}
		return ret;
	}

	public static Vector<eImage> LoadImages(Vector<String> vTmp) {
		Vector<eImage> ret = new Vector<eImage>();
		eImage image = null;
		for (int i = 0; i < vTmp.size(); i = i + 6) {
			image = new eImage();
			image.setImageId(Integer.parseInt((String) vTmp.get(i)));
			image.setImageName((String) vTmp.get(i + 1));
			image.setImageDef((String) vTmp.get(i + 2));
			image.setImageUrl((String) vTmp.get(i + 3));
			image.setPicBg((String) vTmp.get(i + 4));
			image.setPicIcon((String) vTmp.get(i + 5));

			ret.add(image);
		}
		return ret;
	}
    //----------------------------------------------------------------
	public static Vector<eWether> LoadWether(Vector<String> vTmp) {
		Vector<eWether> ret = new Vector<eWether>();
		eWether service = null;
		for (int i = 0; i < vTmp.size(); i = i + 8) {
			service = new eWether();
			service.setId(Integer.parseInt((String) vTmp.get(i)));
			service.setDatetime((String) vTmp.get(i + 1));
			service.setTemp((String) vTmp.get(i + 2));
			service.setTempmin((String) vTmp.get(i + 3));
			service.setTempmax((String) vTmp.get(i + 4));
			service.setDay((String) vTmp.get(i + 5));
			service.setImage((String) vTmp.get(i + 6));
			service.setDescription((String) vTmp.get(i + 7));
			ret.add(service);
		}
		return ret;
	}
	//----------------------------------------------------------------
	public static Vector<eCountries> LoadCountries(Vector<String> vTmp) {
		Vector<eCountries> ret = new Vector<eCountries>();
		eCountries service = null;
		for (int i = 0; i < vTmp.size(); i = i + 6) {
			service = new eCountries();
			service.setId(Integer.parseInt((String) vTmp.get(i)));
			service.setName((String) vTmp.get(i + 1));
			service.setCode((String) vTmp.get(i + 2));
			service.setImage((String) vTmp.get(i + 3));
			service.setBg((String) vTmp.get(i + 4));
			service.setIcon((String) vTmp.get(i + 5));
			ret.add(service);
		}
		return ret;
	}
	
	public static Vector<eService> LoadService(Vector<String> vTmp) {
		Vector<eService> ret = new Vector<eService>();
		eService service = null;
		for (int i = 0; i < vTmp.size(); i = i + 11) {
			service = new eService();
			service.setService_id(Integer.parseInt((String) vTmp.get(i)));
			service.setService_name((String) vTmp.get(i + 1));
			service.setUrl_icon((String) vTmp.get(i + 3));
			service.setApk_code((String) vTmp.get(i + 4));
			service.setService_url((String) vTmp.get(i + 5));
			service.setService_code((String) vTmp.get(i + 6));
			service.setUrl_image((String) vTmp.get(i + 7));
			service.setUrl_picbg((String) vTmp.get(i + 8));
			service.setIlevel((String) vTmp.get(i + 9));
			service.setVerapk((String) vTmp.get(i + 10));
			ret.add(service);
		}
		return ret;
	}

	public static void main(String[] args) throws IOException {
		PmsContent p = new PmsContent();
//		System.out.println(p.getMainMenu("1", "2001"));
		System.out.println(p.getOultetImage("1", "M", "2001"));
	}
}
