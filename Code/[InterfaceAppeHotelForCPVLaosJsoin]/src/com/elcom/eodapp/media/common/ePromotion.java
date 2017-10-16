package com.elcom.eodapp.media.common;

public class ePromotion {
	private int promotionId;
	public int getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	private String proName;
    private String name;
    private String proDef;
    private String url;
    private String urlBg;
    private String urlIcon;
    private String urlLink;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProDef() {
		return proDef;
	}
	public void setProDef(String proDef) {
		this.proDef = proDef;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrlBg() {
		return urlBg;
	}
	public void setUrlBg(String urlBg) {
		this.urlBg = urlBg;
	}
	public String getUrlIcon() {
		return urlIcon;
	}
	public void setUrlIcon(String urlIcon) {
		this.urlIcon = urlIcon;
	}
	public String getUrlLink() {
		return urlLink;
	}
	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}
}
