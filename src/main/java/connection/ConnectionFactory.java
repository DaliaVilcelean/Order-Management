package connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {

    private static  Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());

    private static  String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static  String DBURL = "jdbc:mysql://localhost:3306/new_schooldb";

    private static  String USER = "root";

    private static  String PASS = "Dalia-7100";

    private static ConnectionFactory singleInstance = new ConnectionFactory();


    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private Connection createConnection(){
        Connection connection = null;
        try {
         connection= DriverManager.getConnection(DBURL,USER,PASS);
        } catch (SQLException throwables) {
            LOGGER.log(Level.WARNING,"An error occured while trying to connect to the dtabase");
            throwables.printStackTrace();
        }

        return connection;
    }

    public static Connection getConnection(){
        return singleInstance.createConnection();
    }

    public static void close(Connection connection){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                LOGGER.log(Level.WARNING,"An error occured while trying to close to the dtabase");
                throwables.printStackTrace();
            }
        }
    }

    public static void close(Statement statement){
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException throwables) {
                LOGGER.log(Level.WARNING,"An error occured while trying to close to the dtabase");
                throwables.printStackTrace();
            }
        }
    }

    public static void close(ResultSet resultSet){
        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                LOGGER.log(Level.WARNING,"An error occured while trying to connect to the dtabase");
                throwables.printStackTrace();
            }
        }
    }


}
