package com.elcom.eodapp.media.common;

import java.util.Vector;
public final class Helper
{
  public Helper()
  {}

  public static int getFromRow(int fromRow, int Max)
  {
    if (fromRow <= -1) fromRow = 0;
    if (fromRow > Max) fromRow = Max;

    return fromRow;
  }

  public static int getToRow(int fromRow, int noRows, int Max)
  {
    if (fromRow <= -1) fromRow = 0;
    if (noRows <= -1) noRows = Max;
    int toRow = fromRow + noRows;
    if (toRow > Max) toRow = Max;

    return toRow;
  }

  public static boolean isDigit(String str)
  {
    try{
      Long.parseLong(str.trim());

      return true;
    } catch (NumberFormatException ex){
      return false;
    }
  }

  //Check for a string is null/empty or not
  public static boolean isNoE(String str)
  {
    return (str == null || str.trim().length() == 0);
  }
}
