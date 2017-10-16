package com.elcom.eodapp.media.exception;

public class InvalidParameterException extends VodAppException
{

  public InvalidParameterException()
  {
  }

  public InvalidParameterException(String message)
  {
	super(message);
  }

  public InvalidParameterException(String message, Throwable cause)
  {
	super(message, cause);
  }

  public InvalidParameterException(Throwable cause)
  {
	super(cause);
  }
}