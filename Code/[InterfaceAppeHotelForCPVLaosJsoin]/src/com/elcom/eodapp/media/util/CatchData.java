package com.elcom.eodapp.media.util;


import java.util.*;

public class CatchData
{
  private java.util.Hashtable hashtable = new java.util.Hashtable();
  private Object mutex = new Object();

  public CatchData()
  {
  }

  public Object get(Object key)
  {
    synchronized(mutex){
      return hashtable.get(key);
    }
  }

  public Object put(Object key, Object value)
  {
    synchronized(mutex){
     // System.out.println("Put: key("+ key + "): " + "Value("+ value + ")");
     return hashtable.put(key, value);
    }
  }

  public void clear()
  {
    synchronized(mutex){
      hashtable.clear();
    }
  }

  public void reload(Map t)
  {
    synchronized(mutex){
      hashtable.clear();
      hashtable.putAll(t);
    }
  }

  public void reload(ArrayList arrKey, ArrayList arrValue)
  {
    if (arrKey.size() == arrValue.size()){
      synchronized (mutex){
        hashtable.clear();

        for (int i = 0; i < arrKey.size(); i++){
          hashtable.put(arrKey.get(i), arrValue.get(i));
          //System.out.println("Put: key("+ arrKey.get(i) + "): " + "Value("+ arrValue.get(i) + ")");
        }
      }
    }
  }

  public boolean contains(Object value)
  {
    return hashtable.contains(value);
  }

  public Enumeration elements()
  {
    return hashtable.elements();
  }

  public Collection values()
  {
    return hashtable.values();
  }

  public Object remove(Object key)
  {
    return hashtable.remove(key);
  }
}
