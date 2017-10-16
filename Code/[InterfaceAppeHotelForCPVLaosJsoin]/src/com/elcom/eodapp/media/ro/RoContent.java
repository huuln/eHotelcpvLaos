package com.elcom.eodapp.media.ro;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;
import com.elcom.eodapp.media.broker.IMBroker;
import com.elcom.eodapp.media.mod.ModContent;
import com.elcom.eodapp.media.common.*;

public class RoContent {
	//Refers the DB broker object
	  private static IMBroker broker = IMBroker.getInstance();
	  //To log application
	  private static final Logger logger = LogManager.getLogger(ModContent.class);
	  private static final String pattern = "MM/dd/yyyy HH:mm:ss";
    
	public RoContent(){}
	public ArrayList getRoListchannel(String keystb){
		ArrayList result_vRS = new ArrayList();
	    Vector params = new Vector(2);

	    SubProParam inlang = new SubProParam(new String(keystb), SubProParam.IN);
	    params.add(inlang);
	    SubProParam out_data = new SubProParam(new Vector(),"STRING_ARR",SubProParam.OUT);
	    params.add(out_data);
	    params = broker.executeSubPro(SQL.sqlListChannle,params);

	    out_data = (SubProParam)params.get(1);
	    Vector vRs = out_data.getVector();
	    result_vRS = loadDataVinpChannel(vRs);

	    return result_vRS;

	  }
//===============================================================
	
	private ArrayList loadDataVinpChannel(Vector vRs){
	     int idChannel;
	     String channelName;
	     String outPut;

	     RoChannel chanell;
	     ArrayList tmp_vRs = new ArrayList();
	     if (vRs.size() != 0)
	     {
	       for (int i = 0 ; i < vRs.size() ; i = i + 3)
	       {
	         idChannel    = new Integer(((String)vRs.get(i)).trim()).intValue();
	         channelName  = (String)vRs.get( i + 1 );
	         outPut       = (String)vRs.get( i + 2 );

	         chanell = new RoChannel();
	         chanell.setChannelid(idChannel);
	         chanell.setChannelname(channelName);
	         chanell.setOutput(outPut);

	         tmp_vRs.add(chanell);
	         System.out.print(chanell.toString());
	       }
	     }
	     return tmp_vRs;
	   }
}
