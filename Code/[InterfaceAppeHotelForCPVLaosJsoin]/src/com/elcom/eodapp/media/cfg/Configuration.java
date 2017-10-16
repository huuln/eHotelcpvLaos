package com.elcom.eodapp.media.cfg;


public class Configuration
{
  //A interval period for the system do synchronizing with the DB
  public int syncInterval = 5; // in minutes
  public int pool_size_max = 5;
  public String smservicename = "SMManager";
  public int certificate_vas = 1;
  public String dbihostname = "192.168.51.1";
  public int dbiport = 10001;
  public String dbiservicename = "DBIInterface";
  public int conn_tries_numbers = 5;
  public int remoteviewport = -1;

  public Configuration()
  {
	  
  }
}

