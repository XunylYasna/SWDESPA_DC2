package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnector {
    private Connection myConn;
    protected Statement statement;
    protected ResultSet resultSet;

    private final String url = "jdbc:mysql://localhost:3306/gulaplay?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String user = "root";
    private final String password = "toor";

    public DatabaseConnector(){
        try{
            // Connect to database
            System.out.println("Connecting to DB...");
            myConn = DriverManager.getConnection(url,user,password);
            statement = myConn.createStatement();
        }

        catch (Exception exc){
            System.out.println("Connection failed!");
            exc.printStackTrace();
        }
    }
}
