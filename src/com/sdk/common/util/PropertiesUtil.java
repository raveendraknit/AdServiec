package com.sdk.common.util;

import java.util.*;
import java.io.*;

/**  
 * The purpose of the class is to read from propertise file and return the value.
 * @author  Administrator              
 */
 
public class PropertiesUtil {

public static final String TP_APPLICATION =   "Ad.properties";

  private static HashMap cachedPropertyFiles = new HashMap();

  /**
	 * This method is used to get the String value of the key from the propertise file passed as parameter.
	 * @param String
	 * @param String
	 * @return String.    
	 */ 
  public static String getProperty(String propfile, String key)
  {
    Properties p = getProperties(propfile);
    if (p == null) return null;
    return p.getProperty(key);
  }

  /**
	 * This method is used to get the double value of the key from the propertise file passed as parameter.
	 * @param String
	 * @param String
	 * @return double.    
	 */ 
  public static double getNumericProperty(String propfile, String key)
  throws NumberFormatException
  {
    String value = getProperty(propfile, key);

    if(value == null)
      throw new NumberFormatException("Could not find property " + key);

    return Double.parseDouble(value);
  }

  /**
	 * This method is used to get the String value of the key from the propertise file.
	 * @param String
	 * @return String.    
	 */ 
  public static String getProperty(String key)
  {
    return getProperty(TP_APPLICATION, key);
  }

  /**
	 * This method is used to get the double value of the key from the propertise file.
	 * @param String
	 * @return double.    
	 */ 
  public static double getNumericProperty(String key)
  throws NumberFormatException
  {
    return getNumericProperty(TP_APPLICATION, key);
  }

  /**
	 * This method is used to get String[] of the propertise file name that are cached.
	 * @return String[].    
	 */ 
  public static String[] getCachedPropertiesFilenames()
  {
    synchronized(cachedPropertyFiles)
    {
      int i = 0;
      String[] filenames = new String[cachedPropertyFiles.size()];

      for(Iterator e = cachedPropertyFiles.keySet().iterator(); e.hasNext(); i++)
      {
        filenames[i++] = (String)e.next();
      }
      return filenames;
    }
  }

  /**
	 * This method is used to get the String that contain key value pair from the propertise.
	 * @param String
	 * @return String.    
	 */ 
  public String list(String propfile) {
    Properties properties = (Properties)cachedPropertyFiles.get(propfile);
    if (properties== null) return "";


       synchronized(properties){
         StringBuffer buf = new StringBuffer(properties.size() * 50);

         for(Enumeration e = properties.keys(); e.hasMoreElements();){
           String key = (String)e.nextElement();
           String val = (String)properties.get(key);
           if (val.length() > 40){
             val = val.substring(0, 37) + "...";
           }
           buf.append(key);
           buf.append('=');
           buf.append(val);
           buf.append('\n');
         }
         return buf.toString();
       }
     }



  /**
	 * This method is used to reload the propertise in cache.
	 * @param String
	 * @return void.    
	 */ 
  public static void reloadProperties(String propfile) throws IOException{
    cachedPropertyFiles.put(propfile, loadPropertiesFromClassPath(propfile));
  }

  /**
	 * This method is used to get the propertise from cache according to propfile name.
	 * @param String
	 * @return Properties.    
	 */ 
  public synchronized static Properties getProperties(String propfile) {
    Properties properties = (Properties) cachedPropertyFiles.get(propfile);
    if(properties==null) {
      try {
        properties = loadPropertiesFromClassPath(propfile);
        cachedPropertyFiles.put(propfile, properties);
      }catch(FileNotFoundException e){
    	  System.out.println("FileNotFoundException in PropertiesUtil -> getProperties(String propfile) : " + e.getMessage());
			e.printStackTrace();
      }catch(IOException e){
    	  System.out.println("IOException in PropertiesUtil -> getProperties(String propfile) : " + e.getMessage());
			e.printStackTrace();
      }
    }
    return properties;

  }
  /**
	 * This method is used to load the propertise from the class path.
	 * @param String
	 * @return Properties.    
	 */ 

  private static Properties loadPropertiesFromClassPath(String filename) throws IOException {
      // use the classloader to find the file in the classpath
      ClassLoader cl = PropertiesUtil.class.getClassLoader();
      InputStream in = cl.getResourceAsStream(filename);

      if(in == null) {
        throw new FileNotFoundException("Could not find " + filename + " in classpath");
      }

      Properties properties = new Properties();
      properties.load(in);
      in.close();
      return properties;
    }

}