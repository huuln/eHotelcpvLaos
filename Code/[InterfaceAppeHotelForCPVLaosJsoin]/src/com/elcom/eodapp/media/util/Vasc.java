package com.elcom.eodapp.media.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Vasc {
	private Socket requestSocket;
	private DataOutputStream out;
	private DataInputStream in;
	private String message;
	
	private String read_block(DataInputStream in)
	{
		if (in==null) return null;
		byte result[] = new byte[1024*2];
		int pos = 0;
		byte data;
		while (true)
		{
			try {
				data = in.readByte();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				break;
			}
			result[pos] = data;
			if (pos>4 && result[pos]=='\n')
			{
				if (result[pos-1]=='\r' && result[pos-2]=='\n' && result[pos-3]=='\r')
					break;
			}
			pos++;
		}
		if (pos==0) return null;
		return new String(result,0,pos+1);
	}
	//------------------------------------------------
	private void sendMessage(String msg)
	{
		try{
			out.writeBytes(msg);
			out.flush();
			System.out.println("client>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	//------------------------------------------------
	public String closeUrlVasc(String messagegoc)
	{
		String result = "";
		try{
			requestSocket  = new Socket("172.16.3.228",1124);
			System.out.println("Connected to localhost in port 1124");
			
			out = new DataOutputStream(requestSocket.getOutputStream());			
			in = new DataInputStream(requestSocket.getInputStream());
			
			message = messagegoc;		
			
			sendMessage(message);			
						
			in.close();
			out.close();
			requestSocket.close();
			requestSocket.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	//-------------------------------------------------
	public String getUrlVasc(String messagegoc)
	{
		String result = "";
		try{
			requestSocket  = new Socket("172.16.3.228",1124);
			System.out.println("Connected to localhost in port 1124");
			
			out = new DataOutputStream(requestSocket.getOutputStream());			
			in = new DataInputStream(requestSocket.getInputStream());
			
			message = messagegoc;			
			
			sendMessage(message);
			
			result = read_block(in);
			
			if (result.indexOf("OK") >= 0)
			{	
				System.out.println("Vasc.getUrlVasc("+messagegoc+")" + " | result: " + result);
				result = result.substring(result.indexOf("udp"),result.length() - 4);				
			}			
			in.close();
			out.close();
			requestSocket.close();
			requestSocket.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	
}
