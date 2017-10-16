package com.elcom.eodapp.media.exception;

public class BcpccSysException extends RuntimeException
{

  public BcpccSysException()
  {
  }

  public BcpccSysException(String message)
  {
	super(message);
  }

  public BcpccSysException(String message, Throwable cause)
  {
	super(message, cause);
  }

  public BcpccSysException(Throwable cause)
  {
	super(cause);
  }
}
