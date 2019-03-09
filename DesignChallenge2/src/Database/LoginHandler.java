package Database;

import java.sql.SQLException;

public class LoginHandler extends DatabaseConnector{


    public String verifyCredentials(String username, String password) {
        boolean userExist = false;
        boolean passwordMatch = false;


        //insert query here for boolean values
        String sql = "SELECT * FROM gulaplay.user"; // verify query
        ResultSet rs = stmt.executeQuery(SQL);
        try {
            statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(!userExist){
            return "User does not exit";
        }

        else if(userExist == true & passwordMatch == false){
            return "Password incorrect";
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
