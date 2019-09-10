package factories;

import org.apache.log4j.Logger;
import utils.PropertyReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

   private final  static Logger logger = Logger.getLogger(ConnectionFactory.class);
    private Connection connection;
    private static ConnectionFactory instance;
    private ConnectionFactory(){
        Properties properties = PropertyReader.readProperty();

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager
                    .getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"),properties.getProperty("db.password"));

        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }

    }

    public static ConnectionFactory getInstance(){
        if (instance == null){
            instance = new ConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
