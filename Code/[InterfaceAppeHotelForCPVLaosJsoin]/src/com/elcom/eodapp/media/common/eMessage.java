package com.elcom.eodapp.media.common;

public class eMessage {
	private int id;
	private String subject;
	private String content;// content=content_top+ content_bottom+ content_body
	private String content_top;
	private String content_bottom;
	private String sender;
	private String enterDate;
	private String enterTime;
	private int isRead;
	private int isConfirm;
	private String type;// CONFIRM, NORMAL,ORDER
	private int checkNo;
	private int folioNum;

	public String toString() {
		return "eMessage[id=" + id + ",subject=" + subject + ",Cnt_Top="
				+ content_top + ",sender=" + sender + ",checkNo=" + checkNo
				+ ",folioNum=" + folioNum + "]";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	
	public String getContent_top() {
		return content_top;
	}

	public void setContent_top(String content_top) {
		this.content_top = content_top;
	}


	public String getContent_bottom() {
		return content_bottom;
	}

	public void setContent_bottom(String content_bottom) {
		this.content_bottom = content_bottom;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(String enterDate) {
		this.enterDate = enterDate;
	}

	public String getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(String enterTime) {
		this.enterTime = enterTime;
	}

	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	public int getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(int isConfirm) {
		this.isConfirm = isConfirm;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	public int getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(int checkNo) {
		this.checkNo = checkNo;
	}


	public int getFolioNum() {
		return folioNum;
	}

	public void setFolioNum(int folioNum) {
		this.folioNum = folioNum;
	}

}
