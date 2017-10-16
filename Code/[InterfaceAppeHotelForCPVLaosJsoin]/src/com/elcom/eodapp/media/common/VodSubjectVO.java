package com.elcom.eodapp.media.common;

public class VodSubjectVO
{
	  private int subjectid;
	  private String subjectname;
	  private String picUrl;
	  private String picIcon;
	  private String description;
	  private int parentid;

	  private boolean hold_subject;
	  private int modContentCount;

	  public VodSubjectVO(){}
	 

	  public int getSubjectid() {
		return subjectid;
	}


	public void setSubjectid(int subjectid) {
		this.subjectid = subjectid;
	}


	

	public String getSubjectname() {
		return subjectname;
	}


	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}


	public String getPicUrl() {
		return picUrl;
	}


	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getParentid() {
		return parentid;
	}


	public void setParentid(int parentid) {
		this.parentid = parentid;
	}


	public boolean isHold_subject() {
		return hold_subject;
	}


	public void setHold_subject(boolean hold_subject) {
		this.hold_subject = hold_subject;
	}


	public int getModContentCount() {
		return modContentCount;
	}


	public void setModContentCount(int modContentCount) {
		this.modContentCount = modContentCount;
	}


	public String toString()
	  {
	    StringBuffer buf = new StringBuffer();
	    buf.append(new String("subjectid="+ this.subjectid+","));
	    buf.append(new String("subjectname="+ this.subjectname+","));
	    buf.append(new String("PicUrl="+ this.picUrl+","));
	    buf.append(new String("description="+ this.description+","));
	    buf.append(new String("parentid="+ this.parentid+","));
	    buf.append(new String("hold_Subject="+ this.hold_subject+","));
	    buf.append(new String("modcontent_count="+ this.modContentCount+"\n"));

	    return buf.toString();
	  }
}
