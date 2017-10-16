package com.elcom.eodapp.media.record;

public interface SQL {
	public static final String sqlsetScheduleStb =
		      "begin vod.setScheduleStb(?,?,?,?,?); end;";
	public static final String sqlgetListRecordStb =
		      "begin vod.getListRecordStb(?,?); end;";
	public static final String sqlgetListRecordCore =
		      "begin vod.getListRecordCore(?); end;";
	public static final String sqldelScheduleStb =
		      "begin vod.delScheduleStb(?); end;";
	public static final String sqldelScheduleCore =
		      "begin vod.delScheduleCore(?); end;";
	public static final String sqlupdateStatusStbRecord =
		      "begin vod.updateStatusStbRecord(?,?); end;";
	public static final String sqlupdateLinkStbRecord =
			"begin vod.updateLinkStbRecord(?,?); end;";
	public static final String sqlupdateSizeStbRecord =
			"begin vod.updateSizeStbRecord(?,?); end;";
	public static final String sqlupdateStatusCore =
			"begin vod.updateStatusCore(?,?); end;";
	
	
}
