package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseHandler {
    private Connection myConn;
    private Statement statement;
    private ResultSet resultSet;

    private String url = "jdbc:mysql://localhost:3306/events?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String user = "root";
    private String password = "toor";

    public DatabaseHandler() {

        try{
            // Connect to database
            myConn = DriverManager.getConnection(url,user,password);
            statement = myConn.createStatement();
        }

        catch (Exception exc){
            exc.printStackTrace();
        }
    }
}
