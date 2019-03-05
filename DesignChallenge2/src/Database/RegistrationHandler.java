package Database;

import java.sql.SQLException;

public class RegistrationHandler extends DatabaseConnector{


    public void registration(String username, String email, String password) {
        System.out.println("registration test");
        String sql = "SELECT * FROM gulaplay.user";// insert insert user query here
        try {
            statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void test() throws SQLException {
        System.out.println("registration test");
        String sql = "SELECT * FROM gulaplay.user";

        statement.executeQuery(sql);
    }

}
