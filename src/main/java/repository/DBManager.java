package repository;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private static Connection conn;
    private PropertiesConfiguration databaseProperties = new PropertiesConfiguration();

    public DBManager(){
        this.setUpDB();

    }

    private void setUpDB(){

        try{
            databaseProperties.load("DB.properties");
            String username = databaseProperties.getString("database.username");
            String password = databaseProperties.getString("database.password");
            String host = databaseProperties.getString("database.host");
            String port = databaseProperties.getString("database.port");
            String DB = databaseProperties.getString("database.DB");

            String connectionUrl = host + ":" + port + "/" + DB;
            conn = DriverManager.getConnection(connectionUrl, username, password);


        }catch(SQLException | ConfigurationException e){
            System.out.println("Error occurred while connecting to database!");
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return conn;
    }


}
