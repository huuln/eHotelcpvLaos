package com.elcom.eodapp.media.common;

public class BCSubject {
	private int subjectid;
	private String subjectname;
	private String picUrl;
	
	

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public int getSubjectid() {
		return subjectid;
	}
   
	public String getSubjectname() {
		return subjectname;
	}
	
	public void setSubjectid(int subjectid) {
		this.subjectid = subjectid;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public BCSubject()
	{}
	
	//public String toString(){
	//	 return "subjectid: " + subjectid + " | subjectname: " + subjectname + " == ";
	//}
}
