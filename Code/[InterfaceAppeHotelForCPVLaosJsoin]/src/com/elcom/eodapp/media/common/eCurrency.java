package com.elcom.eodapp.media.common;


public class eCurrency {
	private String id;
	private String code;
	private String name;
	private String symbol;
	private String buyRate;
	private String transferRate;
	private String sellRate;
	private String exRateVN;
	private String date;

	private String url;
	private String urlBg;
	private String urlICon;

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

	public String getUrlICon() {
		return urlICon;
	}

	public void setUrlICon(String urlICon) {
		this.urlICon = urlICon;
	}

	public eCurrency() {
	}

	public String toString() {
		return "eCurrency[id=" + id + ",code=" + code + ",symbol=" + symbol
				+ ",sellRate=" + sellRate + ",url=" + url + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getBuyRate() {
		return buyRate;
	}

	public void setBuyRate(String buyRate) {
		this.buyRate = buyRate;
	}

	public String getTransferRate() {
		return transferRate;
	}

	public void setTransferRate(String transferRate) {
		this.transferRate = transferRate;
	}

	public String getSellRate() {
		return sellRate;
	}

	public void setSellRate(String sellRate) {
		this.sellRate = sellRate;
	}

	public String getExRateVN() {
		return exRateVN;
	}

	public void setExRateVN(String exRateVN) {
		this.exRateVN = exRateVN;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
