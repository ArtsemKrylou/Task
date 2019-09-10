package utils;

import factories.ConnectionFactory;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

   private final static Logger logger = Logger.getLogger(PropertyReader.class);

    public static Properties readProperty() {

        Properties prop = new Properties();
        try (InputStream input = PropertyReader.class.getResourceAsStream("/config.properties")) {


            prop.load(input);



        } catch (IOException ex) {
            logger.warn(ex.getMessage());
        }

        return prop;

    }

}
