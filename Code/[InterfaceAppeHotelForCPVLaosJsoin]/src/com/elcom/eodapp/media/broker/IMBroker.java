package com.elcom.eodapp.media.broker;

import java.rmi.*;
import java.sql.*;
import java.util.*;

import org.apache.log4j.*;
import com.elcom.DBI.*;
import com.elcom.eodapp.media.cfg.*;
import com.elcom.eodapp.media.exception.*;

public class IMBroker
{
  //The Singleton pattern
  private static IMBroker instance = null;
  //Get configuration object
  private static Configuration config = null;
  //To refers to DBIPool, contains logic connections to the DBI
  private DBIPool dbiPool = null;
  
  //To log application
  private static final Logger logger = LogManager.getLogger(IMBroker.class);

  //Read configuration params
  static
  {
    ConfigurationLoader loader = ConfigurationLoader.getInstance();
    config = loader.getConfiguration();
  }

  private IMBroker()
  {
    dbiPool = new DBIPool(config.pool_size_max);
  }

  public static synchronized IMBroker getInstance()
  {
    if (instance == null)
    {
      instance = new IMBroker();
    }

    return instance;
  }

  //============================================================================
  public Vector executeSelect(String stmt, Vector params)
  {
    return executeSelect(stmt, params, -1, -1);
  }

  //============================================================================
  public Vector executeSelect(String stmt, Vector params, int fromRow, int noRows)
  {
    //Get a connection to the DBI
    DBI dbi = null;
    try{
      //Get a connection from pool
      dbi = dbiPool.getDBI();

      //Executes sql query statement
      return dbi.executeSelect(stmt, params, noRows, fromRow);

    } catch (SQLException ex){
      logger.error(ex);
      throw new IMBrokerSysException(ex.getMessage());
    } catch (RemoteException ex){
      logger.warn("Calling remote method failed. Trying again!", ex);
      //Error accurs from the DBI module or network trafic
      //Clear dbiPool, and trying again
      dbi = null;
      dbiPool.clearPool();

      //Sleep in some intervals, then try againd
      try {Thread.sleep(500);}catch (InterruptedException ex1) {}

      //Call reverse procedure
      return executeSelect(stmt, params, noRows, fromRow);

    } finally{
      dbiPool.putDBI(dbi);
    }
  }

  //============================================================================
  public void executeBlockSQLStmt(String sqlBlock)
  {
    DBI dbi = null;
    try{
      //Get a connection from pool
      dbi = dbiPool.getDBI();

      //Executes sql query statement
      dbi.executeBlockSQLStmt(sqlBlock);

    } catch (SQLException ex){
      logger.error(ex);
      throw new IMBrokerSysException(ex.getMessage());
    } catch (RemoteException ex){
      logger.warn("Calling remote method failed. Trying again!", ex);
      //Error accurs from the DBI module or network trafic
      //Clear dbiPool, and trying again
      dbi = null;
      dbiPool.clearPool();

      //Sleep in some intervals, then try againd
      try {Thread.sleep(500);}catch (InterruptedException ex1) {}

      //Call reverse procedure
      executeBlockSQLStmt(sqlBlock);

    } finally{
      dbiPool.putDBI(dbi);
    }
  }

  //============================================================================
  public Vector executeSubPro(String sqlSubPro, Vector params)
  {
    DBI dbi = null;

    try{
      //Get a connection from pool
      dbi = dbiPool.getDBI();
      //Executes sql query statement
      return (dbi.executeSubPro(sqlSubPro, params));

    } catch (SQLException ex){
      ex.printStackTrace();
      logger.error(ex);
      throw new IMBrokerSysException(ex.getMessage());
    } catch (RemoteException ex){
      logger.warn("Calling remote method failed. Trying again!", ex);
      //Error accurs from the DBI module or network trafic
      //Clear dbiPool, and trying again
      dbi = null;
      dbiPool.clearPool();

      //Sleep in some intervals, then try againd
      try {Thread.sleep(500);}catch (InterruptedException ex1) {}

      //Call reverse procedure
      return executeSubPro(sqlSubPro, params);

    } finally{
      dbiPool.putDBI(dbi);
    }
  }

//==============================================================================
  public int executeChange(String stmt, Vector params)
  {
    DBI dbi = null;
    try{
      //Get a connection from pool
      dbi = dbiPool.getDBI();

      //Executes sql query statement
      return (dbi.executeChangeData(stmt, params));

    } catch (SQLException ex){
      logger.error(ex);
      throw new IMBrokerSysException(ex.getMessage());
    } catch (RemoteException ex){
      logger.warn("Calling remote method failed. Trying again!", ex);
      //Error accurs from the DBI module or network trafic
      //Clear dbiPool, and trying again
      dbi = null;
      dbiPool.clearPool();

      //Sleep in some intervals, then try againd
      try {Thread.sleep(500);}catch (InterruptedException ex1) {}

      //Call reverse procedure
      return executeChange(stmt, params);

    } finally{
      dbiPool.putDBI(dbi);
    }
  }
}

