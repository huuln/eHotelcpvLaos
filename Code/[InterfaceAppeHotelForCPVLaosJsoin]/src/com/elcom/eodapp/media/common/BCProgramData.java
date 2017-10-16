package com.elcom.eodapp.media.common;

public class BCProgramData
{
  public int bc_contentID;
  public int programDetailID;
  public String contentName;
  public String subjectName;
  public String picUrl;
  public short channelID;
  public short channelNumber;
  public String channelName;
  public java.sql.Timestamp startTime;
  public java.sql.Timestamp stopTime;

  public BCProgramData()
  {
  }

  public BCProgramData(int bc_contentID, int programDetailID, String contentName,
                       String subjectName,
                       String picUrl, short channelID, short channelNumber, String channelName,
                       java.sql.Timestamp startTime, java.sql.Timestamp stopTime)
  {
    this.bc_contentID = bc_contentID;
    this.programDetailID = programDetailID;
    this.contentName = contentName;
    this.subjectName = subjectName;
    this.picUrl = picUrl;
    this.channelID = channelID;
    this.channelNumber = channelNumber;
    this.channelName = channelName;
    this.startTime = startTime;
    this.stopTime = stopTime;
  }

  public int getBC_contentID()
  {
    return this.bc_contentID;
  }

  public int getProgramDetailID()
  {
    return this.programDetailID;
  }

  public String getContentName()
  {
    return this.contentName;
  }

  public String getSubjectName()
  {
    return this.subjectName;
  }

  public String getPicUrl()
  {
    return this.picUrl;
  }

  public short getChannelID()
  {
    return this.channelID;
  }

  public short getChannelNumber()
  {
    return this.channelNumber;
  }

  public String getChannelName()
  {
    return this.channelName;
  }

  public java.sql.Timestamp getStartTime()
  {
    return this.startTime;
  }

  public java.sql.Timestamp getStopTime()
  {
    return this.stopTime;
  }

  public String toString()
  {
    String ret = "";

    ret += "bc_contentID = " + this.bc_contentID + "\n";
    ret += "programDetailID = " + this.programDetailID + "\n";
    ret += "contentName = " + this.contentName + "\n";
    ret += "subjectName = " + this.subjectName + "\n";
    ret += "picUrl = " + this.picUrl + "\n";

    ret += "channelID = " + this.channelID + "\n";
    ret += "ChannelNumber = " + this.channelNumber + "\n";
    ret += "ChannelName = " + this.channelName + "\n";
    ret += "StartTime = " + this.startTime + "\n";
    ret += "StopTime = " + this.stopTime + "\n";

    return ret;
  }
}
