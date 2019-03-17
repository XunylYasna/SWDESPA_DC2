package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginHandler{

    Connection myConn = DatabaseConnection.getDatabaseConn();
    Statement statement = null;
    ResultSet resultSet;

    {
        try {
            statement = myConn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String verifyCredentials(String username, String password) {

        int verified = 0;

        //insert query here for boolean values
        String sql = "SELECT COUNT(username)\n" +
                "  FROM gulaplay.user\n" +
                " WHERE UserName = '"+ username +"' AND\n" +
                "       Password = '"+ password +"'\n" +
                " LIMIT 0, 1"; // verify query


        try {
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                verified = Integer.parseInt(resultSet.getString("COUNT(username)"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(verified == 0){
            return "Username or Password incorrect";
        }

        else{
            return "Log In";
        }
    }

    public void test(){
        System.out.println("test");
        String sql = "SELECT * FROM gulaplay.user";
        try {
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                System.out.println("user: " + resultSet.getString("username") + " || password: " + resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
