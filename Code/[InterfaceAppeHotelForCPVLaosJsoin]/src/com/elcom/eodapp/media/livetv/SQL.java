package com.elcom.eodapp.media.livetv;

public interface SQL {
	//To get all root subjects
	
	public static final String sqlGetSubjects = "select bc_subject.bcsubjectid,bc_subject.subjectname,(select ehotel_config.value from ehotel_config where key = 'path_image_livetv') || bc_subject.urlimage"
			   + " FROM bc_subject ,pms_smartcard "
			   + " where bc_subject.lang_id =  pms_smartcard.lang_id and bc_subject.style_id = 1 "
			   + " and pms_smartcard.serinumber =? order by ORDERBY";  //bc left join pms_imageurl url  on bc.urlimage = url.image_id(+) order by bc.ORDERBY
	
	  /*public static final String sqlGetSubjects =
	      "select bc_subject.bcsubjectid,bc_subject.subjectname,bc_subject.urlimage FROM bc_subject ,pms_smartcard where " +
          " bc_subject.lang_id =  pms_smartcard.lang_id and bc_subject.style_id = 1 " +
          " and pms_smartcard.serinumber = ? order by bc_subject.style_id ";*/
	//To get detail of bc channels
	  public static final String sqlGetChannels =
	      "select bc_channels.channelid, bc_channels.serverid, bc_channels.channelname," +
          "bc_channels.channelcode,bc_channels.price,bc_channels.physical_address,(select ehotel_config.value from ehotel_config where key = 'path_image_livetv') || bc_channels.url_image " +
          " FROM bc_channels , bc_service_channel " + 
          " where bc_service_channel.subjectid = ? and bc_channels.channelid = bc_service_channel.channelid and bc_service_channel.status = 1  ORDER BY bc_channels.channelcode ";
	  public static final String sqlGetChannels1200 =
		      "select bc_channels.channelid, bc_channels.serverid, bc_channels.channelname," +
	          "bc_channels.channelcode,bc_channels.price,bc_channels.url_udp,(select ehotel_config.value from ehotel_config where key = 'path_image_livetv') || bc_channels.url_image " +
	          " FROM bc_channels , bc_service_channel " + 
	          " where bc_service_channel.subjectid = ? and bc_channels.channelid = bc_service_channel.channelid and bc_service_channel.status = 1  ORDER BY bc_channels.channelcode ";
		  
	  public static final String countLiveTv = "begin ? := vod.countLiveTv(?); end;";

}
