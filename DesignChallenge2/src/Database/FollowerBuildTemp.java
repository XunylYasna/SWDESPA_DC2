package Database;

import Model.User;

import java.sql.*;
import java.util.ArrayList;

public class FollowerBuildTemp {
    PreparedStatement prepStatement;
    Connection myConn = DatabaseConnection.getDatabaseConn();
    Statement statement = null;
    ResultSet resultSet;

    /*public ArrayList<User>getFollowers(String username)
    {
        int id_follower;
        int id_followee;

        User user = null;
        try {
        PreparedStatement prepStatement = myConn.prepareStatement("SELECT * FROM gulaplay.user WHERE username LIKE ?");
        prepStatement.setString(1, username);
        resultSet = prepStatement.executeQuery();
        while(resultSet.next()){

        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}*/
}
