package factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private Connection connection;
    private static ConnectionFactory instance;
    private ConnectionFactory(){


        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/task2","root", "password");

        } catch (SQLException e) {
            e.printStackTrace();

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
