package com.elcom.eodapp.media.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elcom.RemoteMonitor.RemoteViewServer;
import com.elcom.eodapp.media.cas.CasContent;
import com.elcom.eodapp.media.cfg.Configuration;
import com.elcom.eodapp.media.cfg.ConfigurationLoader;
import com.elcom.eodapp.media.exception.ModCtnNotFoundAppException;
import com.elcom.eodapp.media.livetv.BrowserProgDao;
import com.elcom.eodapp.media.mod.ModContent;
import com.elcom.eodapp.media.pms.PmsContent;
import com.elcom.eodapp.media.record.RecordContent;
import com.elcom.eodapp.media.util.DAOFactory;
import com.elcom.eodapp.media.util.DateHelper;
import com.elcom.eodapp.media.vod.VodContentDAO2;

public class CoreMedia extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// private static final FileLog logger = new FileLog();
	private static VodContentDAO2 voddao = DAOFactory.getInstance().getModContentDAO2();
	private static CasContent casdao = DAOFactory.getInstance().getCasContent();
	private static ModContent moddao = DAOFactory.getInstance().getModService();
	private static BrowserProgDao livedao = DAOFactory.getInstance().getBrowserProgDao();
	private static RecordContent recorddao = DAOFactory.getInstance().getRecordContent();
	private static PmsContent pmsdao = DAOFactory.getInstance().getPmsContent();
	private int vodsubjectid;
	private static RemoteViewServer remoteViewServer = null;

	public CoreMedia() {
		super();
		ConfigurationLoader loader = ConfigurationLoader.getInstance();
		Configuration config = loader.getConfiguration();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>" + config.remoteviewport + "<<<<<<<<<<<<<<<<<<<<<<<");
		if (config.remoteviewport > 0) {
			System.out.println("Start RemoteViewServer with port " + config.remoteviewport);
			remoteViewServer = new RemoteViewServer(config.remoteviewport, "SERVER APP MODULE");
			remoteViewServer.start();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@SuppressWarnings("unused")
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// FileLog log;
		String keystb, subid, current, langid, room, ip, channelid,typetvbox;
		String ipClient = request.getRemoteAddr();
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(5);
		response.setContentType("text/plain");
		response.addHeader("Connection", "Keep-Alive");
		response.addHeader("Keep-Alive", "timeout=60000");
		PrintWriter out = response.getWriter();
		int lenh = new Integer(request.getParameter(Command.command)).intValue();
		keystb = request.getParameter(Param.keystb);
		subid = request.getParameter(Param.subid);
		langid = request.getParameter(Param.langid);
		room = request.getParameter(Param.room);
		ip = request.getParameter(Param.ip);
		channelid = request.getParameter(Param.channelid);
		typetvbox = request.getParameter(Param.typetvbox);

		// logger.write("Ip: " + ip + " - keystb: " + keystb + " - curent time stb: ");
		System.out.println("Ip: " + ip + " - keystb: " + keystb + " - lenh : " + lenh);
		// log = new FileLog(ip + ".log");
		// log.write("=> Yeu cau lenh: " + DateHelper.convertLenh(lenh) + " - " + lenh);
		// log.flush();
		// Cac chuc nang danh cho Record
		if (lenh == Command.com_getListRecordStb) {
			String xml = recorddao.getListRecordStb(keystb);
			System.out.println(xml);
			out.println(xml);
			return;
		} else if (lenh == Command.com_getListRecordCore) {
			String xml = recorddao.getListRecordCore();
			System.out.println(xml);
			out.println(xml);
			return;
		} else if (lenh == Command.com_setScheduleStb) {
			String tenkenh = request.getParameter(Param.tenkenh);
			String starttime = request.getParameter(Param.starttime);
			String stoptime = request.getParameter(Param.stoptime);
			String idkenh = request.getParameter(Param.idkenh);
			System.out.println(lenh + " | " + tenkenh + " | " + starttime + " | " + stoptime + " | " + idkenh);
			recorddao.setScheduleStb(idkenh, tenkenh, keystb, starttime, stoptime);
			out.println("OK");
			return;
		} else if (lenh == Command.com_delScheduleStb) {
			String idrecord = request.getParameter(Param.idrecord);
			recorddao.delScheduleStb(idrecord);
			out.println("OK");
			return;
		} else if (lenh == Command.com_delScheduleCore) {
			String idrecord = request.getParameter(Param.idrecord);
			recorddao.delScheduleCore(idrecord);
			out.println("OK");
			return;
		} else if (lenh == Command.com_updateStatusStbRecord) {
			String idrecord = request.getParameter(Param.idrecord);
			String status_ = request.getParameter(Param.status);
			int status = 0;
			if (status_ != null)
				status = new Integer(status_).intValue();
			recorddao.updateStatusStbRecord(idrecord, status);
			out.println("OK");
			return;
		} else if (lenh == Command.com_updateLinkStbRecord) {
			String idrecord = request.getParameter(Param.idrecord);
			String urlrecord = request.getParameter(Param.urlrecord);
			recorddao.updateLinkStbRecord(idrecord, urlrecord);
			out.println("OK");
			return;
		} else if (lenh == Command.com_updateSizeStbRecord) {
			String idrecord = request.getParameter(Param.idrecord);
			String size = request.getParameter(Param.size);
			recorddao.updateSizeStbRecord(idrecord, size);
			out.println("OK");
			return;
		} else if (lenh == Command.com_updateStatusCore) {
			String idrecord = request.getParameter(Param.idrecord);
			String status = request.getParameter(Param.status);
			recorddao.updateStatusCore(idrecord, status);
			out.println("OK");
			return;
		} else
		/*
		 * if (lenh == Command.com_updateStatusCore){ String idrecord = request.getParameter(Param.idrecord); String status =
		 * request.getParameter(Param.status); recorddao.updateStatusCore(idrecord, status); out.println("OK"); }
		 */
		// End Cac chuc nang danh cho Record

		// Them phan dong bo thoi gian danh cho cac stb khong dung thoi gian
		if (lenh == Command.com_gettiem) {
			String time = DateHelper.format(new Date(), "yyyy-MM-dd HH:mm:ss");
			out.print(time);
			return;
		} else
		// Them phan welcome
		if (lenh == Command.com_getwelcome) {
			Properties prop = new Properties();
			FileInputStream inStream = new FileInputStream("Config/welcome.properties");
			prop.load(inStream);

			String com_getwelcome = prop.getProperty("welcome", " ");
			inStream.close();
			System.out.println(com_getwelcome);
			out.println(com_getwelcome);
			return;
		} else
		// Set lang danh cho phan cas
		if (lenh == Command.com_getlang) {
			System.out.println("Ip: " + ip + " - In setLang(" + keystb + "," + langid + ")");
			int xml = casdao.setLang(keystb, new Integer(langid).intValue());
			System.out.println(xml);
			out.println(xml);
			return;
		} else if (lenh == Command.com_setKeyStbIntoRoom) {
			System.out.println("Ip: " + ip + " - In setKeyStbIntoRoom(" + keystb + "," + room + ")");

			String xml = casdao.setKeyStbIntoRoom(keystb, room, ip);
			System.out.println(xml);
			out.println(xml);
			return;
		} else if (lenh == Command.com_getlogin) {
			System.out.println("Ip: " + ip + " - In login(" + keystb + ")");

			String xml = casdao.login(keystb, ip);
			System.out.println(xml);
			out.println(xml);
			return;
		} else if (lenh == Command.com_getreg) {
			System.out.println("Ip: " + ip + " - In register(" + keystb + "," + room + "," + ip + ")");
			int xml = casdao.register(keystb, room, ip);
			System.out.println(xml);
			out.println(xml);
			return;
		} else if (lenh == Command.com_getLangs) {
			System.out.println("Ip: " + ip + " - SN: " + keystb);
			String json = casdao.getLangs(keystb,ip);
			System.out.println(json);
			out.println(json);
			return;
		} else if (lenh == Command.com_stbftpapk) {
			System.out.println("Ip: " + ip + " - SN: " + keystb);
			String xml = casdao.getStbftpapk(keystb);
			System.out.println(xml);
			out.println(xml);
			return;
		} else if (lenh == Command.com_updatestatusstbftpapk) {
			String idapk = request.getParameter(Param.idapk);
			System.out.println("Ip: " + ip + " - SN: " + keystb + " - idapk: " + idapk);

			String xml = casdao.updatestatusStbftpapk(keystb, idapk);
			System.out.println(xml);
			out.println(xml);
			return;
		} else
		// lenh insert cac noi dung da duoc xem.
		if (lenh == Command.com_static) {
			int id_content = new Integer(request.getParameter(Param.contentid)).intValue();
			String type = request.getParameter(Param.type);
			System.out.println("Ip: " + ip + " - In statics(" + keystb + "," + id_content + "," + type + ")");
			int xml = casdao.statics(keystb, id_content, type);
			System.out.println(xml);
			out.println(xml);
			return;
		} else
		// lenh tich cuoc cac noi dung xem
		if (lenh == Command.com_chargeVod) {
			/*
			 * String keystb ,int idcontent,String price,String namecontent, String servicename, String typeprice, String nameguest,String
			 * pincode
			 */
			int contentid = new Integer(request.getParameter(Param.contentid)).intValue();
			String price = request.getParameter(Param.price);
			String namecontent = request.getParameter(Param.namecontent);
			String servicename = request.getParameter(Param.servicename);
			String typeprice = request.getParameter(Param.typeprice);
			String nameguest = request.getParameter(Param.nameguest);
			String pincode = request.getParameter(Param.pincode);

			System.out.println("Ip: " + ip + " - In chargeVod(" + keystb + "," + contentid + "," + price + ")");
			int xml = voddao.chargeVod(keystb, contentid, price, namecontent, servicename, typeprice, nameguest, pincode);
			System.out.println(xml);
			out.println(xml);
			return;
		} else
		// lenh tich cuoc cac noi dung xem
		if (lenh == Command.com_getFilmCode) {
			System.out.println("Ip: " + ip + " - In com_getFilmCode(" + keystb + ")");
			String xml = voddao.getFilmCode(keystb);
			System.out.println(xml);
			out.println(xml);
			return;
		} else
		// lenh kiem tra da tinh cuoc chua
		if (lenh == Command.com_checkpaid) {
			String contentid = request.getParameter(Param.contentid);

			System.out.println("Ip: " + ip + " - In checkpaid(" + keystb + "," + contentid + ")");
			int xml = voddao.checkpaid(keystb, contentid);
			System.out.println(xml);
			out.println((xml + "").trim());
			return;
		} else
		// lay danh sach chu de phim theo tung stb
		if (lenh == Command.com_getlistsubjectvod) {
			// int folderid = new Integer(request.getParameter(Param.folderid)).intValue();
			System.out.println("Ip: " + ip + " - In getAllNorSubjects(" + keystb + ")");
			String type = request.getParameter(Param.type);
			String json = voddao.getAllNorSubjects(keystb,type);
			System.out.println(json);
			out.println(json);
			return;
		} else
		// Lay danh sach phim thuoc chu
		if (lenh == Command.com_getlistfileofsubject) {
			short subjectid = new Short(request.getParameter(Param.subjectid)).shortValue();
			int fromRow = new Integer(request.getParameter(Param.fromRow)).intValue();
			int noRows = new Integer(request.getParameter(Param.noRows)).intValue();
			System.out.println("Ip: " + ip + " - In getMODCtnIDsOfSubjectNew(" + keystb + "," + subjectid + "," + fromRow + ","
					+ noRows + ")");
			String json = voddao.getMODCtnIDsOfSubjectNew(keystb, subjectid, fromRow, noRows);
			System.out.println(json);
			out.println(json);
			return;
		} else
		// Lay tong so luong cua phim
		if (lenh == Command.com_getountilm) {
			vodsubjectid = new Integer(request.getParameter(Param.subjectid)).intValue();
			int count = voddao.countFilm(vodsubjectid);
			out.println(count);
			return;
		} else
		if (lenh == Command.com_getdetailfilm) {  /**  **/
			int contentid = new Integer(request.getParameter(Param.contentid)).intValue();
			String xml ="";
			try {
				xml = voddao.getByPrimaryKey(keystb,contentid);
			} catch (ModCtnNotFoundAppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(xml);
			out.println(xml);
			return;
		} else	
		// Lay danh sach link url ngon ngu
		if (lenh == Command.com_getlisturlsub) {
			System.out.println("Ip: " + ip + " - In getUrlSub(" + request.getParameter(Param.contentid) + ")");
			int ctnid = new Integer(request.getParameter(Param.contentid)).intValue();
			String json = voddao.getUrlSub(ctnid);
			System.out.println(json);
			out.println(json);
			return;
		} else
		// lay danh sach chu de nhac
		if (lenh == Command.com_getmodsubject) {
			System.out.println("Ip: " + ip + " - In getModSubject(" + keystb + ")");
			String json = moddao.getModSubject(keystb);
			System.out.println(json);
			out.println(json);
			return;
		} else
		// lay danh sach nhac thuoc chu de
		if (lenh == Command.com_getModListSongSubject) {
			short subjectid = new Short(request.getParameter(Param.subjectid)).shortValue();
			int fromRow = new Integer(request.getParameter(Param.fromRow)).intValue();
			int noRows = new Integer(request.getParameter(Param.noRows)).intValue();
			System.out.println("Ip: " + ip + " - In getModListSongSubject(" + keystb + "," + subjectid + "," + fromRow + "," + noRows
					+ ")");
			String xml = moddao.getModListSongSubject(keystb, subjectid, fromRow, noRows);
			System.out.println(xml);
			out.println(xml);
			return;
		} else
		// lay tong so luong nhac thuoc chu de
		if (lenh == Command.com_getountmusic) {
			int subjectid = new Integer(request.getParameter(Param.subjectid)).intValue();
			int count = moddao.countMusic(subjectid);
			out.println(count);
			return;
		} else
		// lay hinh cua nhac
		if (lenh == Command.com_getUrlImageBack) {
			int subjectid = new Integer(request.getParameter(Param.subjectid)).intValue();
			String url = moddao.getUrlImageBack(subjectid);
			System.out.println(url);
			out.println(url);
			return;
		} else
		// Danh sach chu de Livetv
		if (lenh == Command.com_getlivesubject) {
			int fromRow = new Integer(request.getParameter(Param.fromRow)).intValue();
			int noRows = new Integer(request.getParameter(Param.noRows)).intValue();
			System.out.println("Ip: " + ip + " - In getLiveTvSubject(" + keystb + "," + fromRow + "," + noRows + ")");
			String json = livedao.getLiveTvSubject(keystb, fromRow, noRows);
			System.out.println(json);
			out.println(json);
			return;
		} else
		// Danh sach kenh thuoc chu de livetv
		if (lenh == Command.com_getlivechannel) {
			System.out.println("Ip: " + ip + " - In getLiveTvSubject(" + channelid + ")");
			String json = livedao.getLivetvChannelList(new Integer(channelid).intValue());
			System.out.println(json);
			out.println(json);
			return;
		} else
		// Tong so luong cua kenh thuoc chu de nao do
		if (lenh == Command.com_getlivecount) {
			int subjectid = new Integer(request.getParameter(Param.subjectid)).intValue();
			System.out.println("Ip: " + ip + " - In countLiveTv(" + subjectid + ")");
			int xml = livedao.countLiveTv(subjectid);
			System.out.println(xml);
			out.println(xml);
			return;
		} else
		// lenh danh cho pms
		if (lenh == Command.com_getMessageInfo) {
			int messId = new Integer(request.getParameter(Param.messId)).intValue();
			String xml = pmsdao.getMessageInfo(messId, keystb);
			System.out.println(xml);
			out.println(xml);
			return;
		} else
		if (lenh == Command.com_getMessage) {
			String xml = pmsdao.getFolioMessages(keystb);
			System.out.println(xml);
			out.println(xml);
			return;
		} else
		if (lenh == Command.com_getPromotions) {
			String xml = pmsdao.getPromotions(keystb);
			System.out.println(xml);
			out.println(xml);
			return;
		} else if (lenh == Command.com_getFolderMenuPMS) {
			int folderid = new Integer(request.getParameter(Param.folderid)).intValue();

			String xml = pmsdao.getFolderMenuPMS(keystb, folderid);
			System.out.println(xml);
			out.println(xml);
			return;
		} else if (lenh == Command.com_getWelcomeMessage) {
			System.out.println("lenh getWelcomeMessage: " + lenh + " - keystb: " + keystb);
			String json = pmsdao.getWelcomeMessage(keystb,typetvbox);
			System.out.println(json);
			out.println(json);
			return;
		} else if (lenh == Command.com_getAdverImages) {
			String types = request.getParameter(Param.types);
			System.out.println("lenh: " + lenh + " - keystb: " + keystb + " - types: " + types);
			String json = pmsdao.getAdverImages(keystb, types);
			System.out.println(json);
			out.println(json);
			return;
		} else if (lenh == Command.com_getHomeService) {
			System.out.println("lenh: " + lenh + " - keystb: " + keystb);
			String json = pmsdao.getHomeService(keystb);
			System.out.println(json);
			out.println(json);
			return;
		}else if (lenh == Command.com_getExchangeRate) {
			System.out.println("lenh: " + lenh + " - keystb: " + keystb);
			String xml = pmsdao.getExchangeRates(-1,-1);
			System.out.println(xml);
			out.println(xml);
			return;
		} else if (lenh == Command.com_getMainMenu) {
			System.out.println("get main menu");
			String serviceid = request.getParameter(Param.serviceid);
			System.out.println("lenh: " + lenh + " - keystb: " + keystb + " - serviceid: " + serviceid);
			String json = pmsdao.getMainMenu(serviceid, keystb);
			System.out.println(json);
			out.println(json);
			return;
		} else if (lenh == Command.com_getOultetImage) {
			String outletid = request.getParameter(Param.outletid);
			String outletType = request.getParameter(Param.outletType);

			System.out.println("lenh: " + lenh + " - keystb: " + keystb + " - outletid: " + outletid);
			String json = pmsdao.getOultetImage(outletid, outletType, keystb);
			System.out.println(json);
			out.println(json);
			return;
		} else if (lenh == Command.com_getSubMenu) {
			String mainMenuId = request.getParameter(Param.mainnenuid);
			System.out.println("lenh: " + lenh + " - keystb: " + keystb + " - mainMenuId: " + mainMenuId);
			String json = pmsdao.getSubMenu(mainMenuId, keystb);
			System.out.println(json);
			out.println(json);
			return;
		} else if (lenh == Command.com_getItemOfService) {
			String subMenuId = request.getParameter(Param.submenuid);
			String ffrom = request.getParameter(Param.fromRow);
			String tto = request.getParameter(Param.noRows);
			System.out.println("lenh: " + lenh + " - keystb: " + keystb + " - mainMenuId: " + subMenuId);
			
			String json = pmsdao.getItemOfService(subMenuId, keystb, ffrom, tto);
			System.out.println(json);
			out.println(json);
			return;
		}else if (lenh == Command.com_getBills) {
			System.out.println("lenh: " + lenh + " - keystb: " + keystb );
			
			String json = pmsdao.getBills(keystb);
			System.out.println(json);
			out.println(json);
			return;
		}else if (lenh == Command.com_getItemOfAttractions) {
			String mainMenuId = request.getParameter(Param.mainnenuid);
			System.out.println("lenh: " + lenh + " - keystb: " + keystb + " - mainMenuId: " + mainMenuId);
			
			String xml = pmsdao.getItemOfAttractions(mainMenuId,keystb);
			System.out.println(xml);
			out.println(xml);
			return;
		} else if (lenh == Command.com_getItemOfScheduleActivity) {
			String mainMenuId = request.getParameter(Param.mainnenuid);
			System.out.println("lenh: " + lenh + " - keystb: " + keystb + " - mainMenuId: " + mainMenuId);
			
			String xml = pmsdao.getItemOfActivities(mainMenuId,keystb);
			System.out.println(xml);
			out.println(xml);
			return;
		}else if (lenh == Command.com_getCountries) {
			String level = "2";
			System.out.println("pmsdao.getCountries");
			String xml = pmsdao.getCountries(level);
			System.out.println(xml);
			out.println(xml);
			return;
		}else if (lenh == Command.com_getWeatherToday) {
			String day = request.getParameter(Param.day);
			String xml = pmsdao.getWeatherToday(day);
			System.out.println(xml);
			out.println(xml);
			return;
		}else if (lenh == Command.com_getWeatherInWeek) {
			String coutrid = request.getParameter(Param.coutrid);
			String xml = pmsdao.getWeatherInWeek(coutrid,keystb);
			System.out.println(xml);
			out.println(xml);
			return;
		}else if (lenh == Command.com_postedItemToBill) {
			String items = request.getParameter(Param.items); 
			System.out.println("lenh: " + lenh + " - keystb: " + keystb);
			
			String xml = pmsdao.postedItemToBill(items);
			System.out.println(xml);
			out.println(xml);
			return;
		}

	}
}
