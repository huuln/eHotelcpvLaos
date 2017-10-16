package com.elcom.eodapp.media.common;

import java.util.Date;
import java.io.Serializable;

/**
 * 
 * @author Application Media
 *
 */
public class VodCtnVO
{
	private int contentid;
	private int servicecontentid;
	private int subjectid;
	private String servicename;
	private String productor;
	private String director;
	private String actors;
	private String plot;
    private String writer;
    private String milisecond;
    private String poster;
    private String starnumber;
    private String urlfilm;
    private String urlfilmtrailer;
    private String militrailer;
    private String price;
    private String iunit;
    private String pg;
    
	public String getPg() {
		return pg;
	}
	public void setPg(String pg) {
		this.pg = pg;
	}
	public String getIunit() {
		return iunit;
	}
	public void setIunit(String iunit) {
		this.iunit = iunit;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getContentid() {
		return contentid;
	}
	public void setContentid(int contentid) {
		this.contentid = contentid;
	}
	public int getServicecontentid() {
		return servicecontentid;
	}
	public void setServicecontentid(int servicecontentid) {
		this.servicecontentid = servicecontentid;
	}
	public int getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(int subjectid) {
		this.subjectid = subjectid;
	}
	public String getServicename() {
		return servicename;
	}
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}
	public String getProductor() {
		return productor;
	}
	public void setProductor(String productor) {
		this.productor = productor;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public String getPlot() {
		return plot;
	}
	public void setPlot(String plot) {
		this.plot = plot;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getMilisecond() {
		return milisecond;
	}
	public void setMilisecond(String milisecond) {
		this.milisecond = milisecond;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getStarnumber() {
		return starnumber;
	}
	public void setStarnumber(String starnumber) {
		this.starnumber = starnumber;
	}
	public String getUrlfilm() {
		return urlfilm;
	}
	public void setUrlfilm(String urlfilm) {
		this.urlfilm = urlfilm;
	}
	public String getUrlfilmtrailer() {
		return urlfilmtrailer;
	}
	public void setUrlfilmtrailer(String urlfilmtrailer) {
		this.urlfilmtrailer = urlfilmtrailer;
	}
	public String getMilitrailer() {
		return militrailer;
	}
	public void setMilitrailer(String militrailer) {
		this.militrailer = militrailer;
	}   
	
}

