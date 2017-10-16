package com.elcom.eodapp.media.common;

public class RoChannel {
	/**
	 * 
	 */
	private int channelid;
	private String channelname;
	private String output;

	public RoChannel() {
	}

	

	public int getChannelid() {
		return channelid;
	}



	public void setChannelid(int channelid) {
		this.channelid = channelid;
	}



	public String getChannelname() {
		return channelname;
	}



	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}



	public String getOutput() {
		return output;
	}



	public void setOutput(String output) {
		this.output = output;
	}


		public String toString() {
		return channelid + " | " + channelname + " | " + output;
	}
}

