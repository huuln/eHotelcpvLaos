package com.elcom.eodapp.media.util;

import com.elcom.eodapp.media.vod.*;
import com.elcom.eodapp.media.util.*;
import com.elcom.eodapp.media.common.*;
import com.elcom.eodapp.media.exception.*;


public  class VodCatche
{

  public static java.util.ArrayList Vlang = new java.util.ArrayList();

  // The Catche contains values as params of the system. When the server starts,
  // it will read these params' value from the DB and store them into Hashtable
  public static java.util.Hashtable eventTable = new java.util.Hashtable();

  //The Catche to contains list of Normal Root Subjects
  //public static java.util.ArrayList ModNorRootSubBuf = new java.util.ArrayList();
  public static java.util.ArrayList ModNorRootSubBuf[];// = new java.util.ArrayList[2];

  //The Catche to contains list of Special Root Subjects
  //public static java.util.ArrayList ModSpecRootSubBuf = new java.util.ArrayList();
  public static java.util.ArrayList ModSpecRootSubBuf[];

  //The Catche to contains list of New Root Subjects
  //public static java.util.ArrayList ModNewRootSubBuf = new java.util.ArrayList();
  public static java.util.ArrayList ModNewRootSubBuf[];

  //The Catche to contains All of Normal Subjects
  //public static java.util.ArrayList ModAllNorSubBuf = new java.util.ArrayList();
  public static java.util.ArrayList ModAllNorSubBuf[];

  //The Catche to contains All of Special Subjects
  //public static java.util.ArrayList ModAllSpecSubBuf = new java.util.ArrayList();
  public static java.util.ArrayList ModAllSpecSubBuf[];

  //The Catche to contains All of New Subjects
  //public static java.util.ArrayList ModAllNewSubBuf = new java.util.ArrayList();
  public static java.util.ArrayList ModAllNewSubBuf[];

  public VodCatche()
  {
    ModNorRootSubBuf = new java.util.ArrayList[this.Vlang.size() + 1];
    ModSpecRootSubBuf = new java.util.ArrayList[this.Vlang.size() + 1];
    ModNewRootSubBuf = new java.util.ArrayList[this.Vlang.size() + 1];
    ModAllNorSubBuf = new java.util.ArrayList[this.Vlang.size() + 1];
    ModAllSpecSubBuf = new java.util.ArrayList[this.Vlang.size() + 1];
    ModAllNewSubBuf  = new java.util.ArrayList[this.Vlang.size() + 1];

    for (int i = 0 ; i <= this.Vlang.size() ; i ++)
    {
      ModNorRootSubBuf[i] = new java.util.ArrayList();
      ModSpecRootSubBuf[i] = new java.util.ArrayList();
      ModNewRootSubBuf[i] = new java.util.ArrayList();
      ModAllNorSubBuf[i] = new java.util.ArrayList();
      ModAllSpecSubBuf[i] = new java.util.ArrayList();
      ModAllNewSubBuf[i] = new java.util.ArrayList();
    }

  }

}
