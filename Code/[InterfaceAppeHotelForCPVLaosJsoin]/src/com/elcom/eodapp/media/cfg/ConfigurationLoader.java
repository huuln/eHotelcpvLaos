package com.elcom.eodapp.media.cfg;

import java.io.*;
import java.sql.*;
import java.util.*;
import org.apache.log4j.Logger;

public class ConfigurationLoader
{
  static Logger log = Logger.getLogger(ConfigurationLoader.class);

  private static ConfigurationLoader instance = null;
  private static Configuration configuration = null;

  private ConfigurationLoader()
  {
    configuration = new Configuration();

    build();
  }

  public static synchronized ConfigurationLoader getInstance()
  {
    if (instance == null)
    {
      instance = new ConfigurationLoader();
    }

    return instance;
  }

  public Configuration getConfiguration()
  {
    return configuration;
  }

  private void build()
  {
    try{
      String currentDir = System.getProperty("user.dir");
      System.out.println("Current folder "+ currentDir);	
      Properties prop = new Properties();
      FileInputStream inStream = new FileInputStream("Config/eodapp.properties");
      prop.load(inStream);

      configuration.smservicename = prop.getProperty("ehoteldbi.smservicename","SessionManager");
      configuration.dbihostname = prop.getProperty("ehoteldbi.dbihostname");
      configuration.dbiport = Integer.parseInt(prop.getProperty("ehoteldbi.dbiport"));
      configuration.dbiservicename = prop.getProperty("ehoteldbi.dbiservicename", "DBInterface");
      configuration.pool_size_max = Integer.parseInt(prop.getProperty("ehoteldbi.pool_size_max","10"));
      configuration.syncInterval = Integer.parseInt(prop.getProperty("ehoteldbi.syncinterval","5"));
      configuration.conn_tries_numbers = Integer.parseInt(prop.getProperty("ehoteldbi.conn_tries_numbers", "10"));
      configuration.certificate_vas = Integer.parseInt(prop.getProperty("ehoteldbi.certificate_vas", "1"));
      configuration.remoteviewport = Integer.parseInt(prop.getProperty("ehoteldbi.remoteviewport","-1"));
      inStream.close();
    } catch (Throwable t){
      // We have to catch Throwable, otherwise we will miss
      // NoClassDefFoundError and other subclasses of Error
      log.error("Building ConfigurationLoader failed.", t);
      throw new RuntimeException(t);
    }
  }
}
