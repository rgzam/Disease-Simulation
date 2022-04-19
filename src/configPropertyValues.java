import java.io.File;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

public class configPropertyValues {

      String result = " ";
         InputStream inputStream;
          public String getPropValues() throws IOException {

             try {
                  Properties prop = new Properties();
                  String propFileName = "config.properties";

                  inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

                  if (inputStream != null) {
                      prop.load(inputStream);
                  } else {
                      throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
                  }

                  Date time = new Date(System.currentTimeMillis());

                  // get the property value and print it out
                 String dimensions = prop.getProperty("dimensions","200,200");
                  String exposuredistance = prop.getProperty("exposuredistance","20");
                  String incubation = prop.getProperty("incubation","5");
                  String sickness = prop.getProperty("sickness","10");
                  String recover = prop.getProperty("recover","0.95");
                  String grid = prop.getProperty("grid");
                  String random = prop.getProperty("random");
                  String randomgrid = prop.getProperty("randomgrid");
                  String initialsick = prop.getProperty("initialsick","1");


             } catch (Exception e) {
                  System.out.println("Exception: " + e);
             } finally {
                  inputStream.close();
              }
             return result;
          }
      }


