package com.elcom.eodapp.media.util;

import java.util.*;
import java.net.InetAddress;
import java.text.SimpleDateFormat;

import com.elcom.eodapp.media.common.eItemOrder;


/**
 * This class contains helper methods for dealing with
 * Date objects.
 */
public final class DateHelper
{

  public static String convertMoney(String money, String unit) {
        if(unit!=null && unit.equals("USD")){
            return money;
        }else{
            List ah = new ArrayList();
            int lenghtA = money.length();
            //System.out.println("lenghtA=" + lenghtA);
            int soDuA = lenghtA % 3;
            //System.out.println("soDuA=" + soDuA);
            int soNguyen = lenghtA / 3;
            int index = 1;
            if (soDuA > 0) {
                index = soNguyen + 1;
                //System.out.println("index=" + index);
            } else {
                if (soNguyen > 1) {
                    index = soNguyen;
                }
            }
            String newA = money;
            //System.out.println(newA);
            for (int i = 0; i < index; i++) {
               // System.out.println("newA bd =" + newA);
                if (newA.length() > 3) {
                    String tmp = money.substring(newA.length() - 3, newA.length());
                    newA = newA.substring(0, newA.length() - 3);
                   // System.out.println("i=" + i + ",newA=" + newA + ",tmp=" +tmp);
                    ah.add(0, tmp);
                } else {
                    ah.add(0, newA);
                }
            }
            //System.out.println(ah.toString());
            Iterator iterator = ah.iterator();
            return processMoney(iterator);
        }
    }

    private static String processMoney(Iterator vIterator) {
        StringBuffer str = new StringBuffer();
        while (vIterator.hasNext()) {
            str.append(vIterator.next());
            str.append(",");

        }
        String tmp = str.toString();
        tmp = tmp.substring(0, tmp.length() - 1);
        System.out.println(tmp);
        return tmp;

    }


  public static String format(Date date)
  {

	// returns a String representation of the date argument,
	// formatted according to the pattern argument, which
	// has the same syntax as the argument of the SimpleDateFormat
	// class1E
	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	return formatter.format(date);
  }

  public static java.util.Date parseDate(String strDate)
  {
	try{
	  // return a Date
	  // Parse the previous string back into a Date.
	  java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
		  "MM/dd/yyyy HH:mm:ss");
	  java.text.ParsePosition pos = new java.text.ParsePosition(0);
	  java.util.Date d = formatter.parse(strDate, pos);

	  return d;

	} catch (Exception ex){}

	return null;
  }

  public static long toYYYYMMDD(java.util.Date date)
  {
    java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
        "yyyyMMdd");
    return Long.parseLong(formatter.format(date));
  }

  public static long getDateInSeconds(java.util.Date d)
  {
	int day = getDay(d);
	int month = getMonth(d);
	int year = getYear(d);

	long seconds = ( (year - 1900) * 365 * 24 + (year - 1900) * 6 +
					month * 30 * 24 + day * 24) * 60 * 60;

	Long convertLong = new Long(seconds);
	return convertLong.intValue();
  }

  public static long getTimeInSeconds(java.sql.Time t)
  {
	java.util.Date d = new java.util.Date(t.getTime());
	int h = getHour(d);
	int m = getMinute(d);
	int s = getSecond(d);

	long seconds = h * 60 * 60 + m * 60 + s;
	Long convertLong = new Long(seconds);
	return convertLong.intValue();
  }

  //=====================================================================
  public static final Date getDate(int year, int month, int day,
								   int hour, int minute)
  {
	// returns a Date with the specified time elements
	Calendar cal = new GregorianCalendar(year, intToCalendarMonth(month),
										 day, hour, minute);

	return cal.getTime();

  } // getDate

  public static final Date getDate(int year, int month, int day)
  {
	// returns a Date with the specified time elements,
	// with the hour and minutes both set to 0 (midnight)

	Calendar cal = new GregorianCalendar(year, intToCalendarMonth(month),
										 day);

	return cal.getTime();

  } // getDate

  static public int dayDiff(Date first, Date second)
  {

	// returns the difference, in days, between the first
	// and second Date arguments

	long msPerDay = 1000 * 60 * 60 * 24;
	long diff = (first.getTime() / msPerDay) - (second.getTime() / msPerDay);
	Long convertLong = new Long(diff);
	return convertLong.intValue();
  } // dayDiff

  static public int getYear(Date date)
  {

	Calendar cal = new GregorianCalendar();
	cal.setTime(date);
	return cal.get(Calendar.YEAR);
  } // getYear

  static public int getMonth(Date date)
  {

	Calendar cal = new GregorianCalendar();
	cal.setTime(date);
	int calendarMonth = cal.get(Calendar.MONTH);
	return calendarMonthToInt(calendarMonth);
  } // getMonth

  static public int getDay(Date date)
  {
	Calendar cal = new GregorianCalendar();
	cal.setTime(date);
	return cal.get(Calendar.DAY_OF_MONTH);
  } // getDay

  static public int getHour(Date date)
  {

	Calendar cal = new GregorianCalendar();
	cal.setTime(date);
	return cal.get(Calendar.HOUR_OF_DAY);
  } // geHour

  static public int getMinute(Date date)
  {

	Calendar cal = new GregorianCalendar();
	cal.setTime(date);
	return cal.get(Calendar.MINUTE);
  } // geMinute

  static public int getSecond(Date date)
  {
	Calendar cal = new GregorianCalendar();
	cal.setTime(date);
	return cal.get(Calendar.SECOND);
  } // getSecond

  private static int calendarMonthToInt(int calendarMonth)
  {

	if (calendarMonth == Calendar.JANUARY){
	  return 1;
	} else if (calendarMonth == Calendar.FEBRUARY){
	  return 2;
	} else if (calendarMonth == Calendar.MARCH){
	  return 3;
	} else if (calendarMonth == Calendar.APRIL){
	  return 4;
	} else if (calendarMonth == Calendar.MAY){
	  return 5;
	} else if (calendarMonth == Calendar.JUNE){
	  return 6;
	} else if (calendarMonth == Calendar.JULY){
	  return 7;
	} else if (calendarMonth == Calendar.AUGUST){
	  return 8;
	} else if (calendarMonth == Calendar.SEPTEMBER){
	  return 9;
	} else if (calendarMonth == Calendar.OCTOBER){
	  return 10;
	} else if (calendarMonth == Calendar.NOVEMBER){
	  return 11;
	} else if (calendarMonth == Calendar.DECEMBER){
	  return 12;
	} else{
	  return 1;
	}

  } // calendarMonthToInt

  private static int intToCalendarMonth(int month)
  {

	if (month == 1){
	  return Calendar.JANUARY;
	} else if (month == 2){
	  return Calendar.FEBRUARY;
	} else if (month == 3){
	  return Calendar.MARCH;
	} else if (month == 4){
	  return Calendar.APRIL;
	} else if (month == 5){
	  return Calendar.MAY;
	} else if (month == 6){
	  return Calendar.JUNE;
	} else if (month == 7){
	  return Calendar.JULY;
	} else if (month == 8){
	  return Calendar.AUGUST;
	} else if (month == 9){
	  return Calendar.SEPTEMBER;
	} else if (month == 10){
	  return Calendar.OCTOBER;
	} else if (month == 11){
	  return Calendar.NOVEMBER;
	} else if (month == 12){
	  return Calendar.DECEMBER;
	} else{
	  return Calendar.JANUARY;
	}

  } // intToCalendarMonth
 
  public static String format(Date date, String pattern)
  {

	// returns a String representation of the date argument,
	// formatted according to the pattern argument, which
	// has the same syntax as the argument of the SimpleDateFormat
	// class1E

	SimpleDateFormat formatter = new SimpleDateFormat(pattern);
	return formatter.format(date);
  }
  
  public static long toYYYYMMDDHHMISS(java.util.Date d)
  {
    String temp = format(d, "yyyyMMddHHmmss");

    return (Long.parseLong(temp));
  }

  public static long toYYYYMMDDHHMISS(java.sql.Timestamp ts)
  {
    String temp = format(ts, "yyyyMMddHHmmss");

    return (Long.parseLong(temp));
  }
  
  public static String getIpRemote()
  {
	 
	  String remoteip = "Chua cai";
	  return remoteip;
  }  
    
  public static boolean pingStb(String ip)
  {
	  boolean ok = false;
	  try
      {  
		  if (ip == null) return false;
          InetAddress address = InetAddress.getByName(ip);
          ok = address.isReachable(1000);

          System.out.println("Is host reachable? " + ok);
      } catch (Exception e)
      {
          e.printStackTrace();
      }
	  return ok;
  }
  
  public static String convertLenh(int lenh)
  {
	
	if (lenh == 1) return "com_getlistsubjectvod";
	if (lenh == 2) return "com_getlistfileofsubject";
	if (lenh == 3) return "com_getdetailfilm";
	if (lenh == 4) return "com_getountilm";
	if (lenh == 5) return "com_getlisturlsub";
	
	if (lenh == 11) return "com_getmodsubject";
	if (lenh == 12) return "com_getModListSongSubject";
	if (lenh == 13) return "com_getountmusic";
	if (lenh == 14) return "com_getUrlImageBack";
	if (lenh == 15) return "com_chargeVod";
	if (lenh == 16) return "com_checkpaid";
	
	if (lenh == 21) return "com_getlivesubject";
	if (lenh == 22) return "com_getlivechannel";
	if (lenh == 23) return "com_getlivechannel";
	
	if (lenh == 31) return "com_getlang";
	if (lenh == 32) return "com_getlogin";
	if (lenh == 33) return "com_getreg";
	if (lenh == 35) return "com_static";
	
	if (lenh == 37) return "com_setScheduleStb";
	if (lenh == 38) return "com_getListRecordStb";
	if (lenh == 39) return "com_delScheduleStb";
	if (lenh == 40) return "com_updateStatusStbRecord";
	if (lenh == 41) return "com_getListRecordCore";
	if (lenh == 42) return "com_updateLinkStbRecord";
	
	
	return "Khong biet lenh - " + lenh;
  }
  
  public static Vector<eItemOrder> getItemOrder(String items)
  {
	  Vector<eItemOrder> items_ = new Vector<eItemOrder>();
	  String item;
	  String result_[] = items.split("[@]");
	  String result[];
	  for(String r : result_){
		  eItemOrder order = new eItemOrder();
		  item = r;
		  result = item.split("[-]");
		  order.setItemCode(result[0]);
		  order.setiQty(result[1]);
		  order.setOrderDate(result[2]);
		  order.setOrderTime(result[3]);
		  items_.add(order);
	  }
	  return items_;
  }
  
  
  public static void main(String[] args)
  {
	  //UnicodeConverter.decodeUnicode("")
	  
  }
} // class
