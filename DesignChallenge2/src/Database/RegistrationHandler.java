package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationHandler extends DatabaseConnector{

    public void registration(String username, String email, String password) {
        System.out.println("registration test");

        String sql = "INSERT INTO user (username, password, email)\n" +
                "values (?, ?, ?)";// insert insert user query here

        try {
            prepStatement = myConn.prepareStatement(sql);
            prepStatement.setString(1,username);
            prepStatement.setString(2, password);
            prepStatement.setString( 3, email);
            prepStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void test() throws SQLException {
        System.out.println("registration test");
        String sql = "INSERT INTO user (iduser, username, password, userType)\n" +
                "values (?, ?, ?, ?)";

        statement.executeQuery(sql);
    }

}
