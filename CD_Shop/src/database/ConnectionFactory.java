package database;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
/**
 * Class that encapsulates connection for database and generates
 * connection objects.
 * @author timokhin
 *
 */
public class ConnectionFactory {

	private static ConnectionFactory instance;
	private static Connection connection;
	/*
	 * Connection parameters. 
	 */
	private String url = "jdbc:mysql://localhost:3306/cdshop?characterEncoding=utf8";
	private String user = "root";
	private String password = "root";
	private String driverClass = "com.mysql.jdbc.Driver"; 
	
    private ConnectionFactory() throws SQLException {
        try {
            Class.forName(driverClass);
            connection = (Connection) DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * 
     * @return an insance of ConnectionFactory to generate connections.
     * @throws SQLException if a database access error occurs or the url is null
     */
    public static ConnectionFactory getInstance() {
        if (instance == null)
			try {
				instance = new ConnectionFactory();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        return instance;
    }
    /**
     * 
     * @return a connection to a database
     */
    public Connection getConnection() { 
        return connection;
    } 
}
