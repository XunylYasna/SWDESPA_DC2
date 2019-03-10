package Database;

import java.sql.*;

public class DatabaseConnector {
    Connection myConn;
    protected Statement statement;
    protected PreparedStatement prepStatement;
    protected ResultSet resultSet;

    private final String url = "jdbc:mysql://localhost:3306/gulaplay?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String user = "root";
    private final String password = "toor";//"Mionixsargas101!";

    public DatabaseConnector(){
        try{
            // Connect to database
            myConn = DriverManager.getConnection(url,user,password);
            statement = myConn.createStatement();
            System.out.println("Connected to DB!");

        }

        catch (Exception exc){
            System.out.println("Connection failed!");
            exc.printStackTrace();
        }
    }
}
