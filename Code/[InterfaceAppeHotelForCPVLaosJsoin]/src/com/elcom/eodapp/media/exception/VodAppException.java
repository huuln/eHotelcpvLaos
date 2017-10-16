package com.elcom.eodapp.media.exception;

public class VodAppException extends Exception
{

  public VodAppException()
  {
  }

  public VodAppException(String message)
  {
	super(message);
  }

  public VodAppException(String message, Throwable cause)
  {
	super(message, cause);
  }

  public VodAppException(Throwable cause)
  {
	super(cause);
  }
}