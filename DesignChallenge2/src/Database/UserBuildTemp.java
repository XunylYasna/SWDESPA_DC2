package Database;

import Model.Playlist;
import Model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserBuildTemp {

    Connection myConn = DatabaseConnection.getDatabaseConn();
    Statement statement = null;
    ResultSet resultSet;

    public User getUser(String username){
        int userID;
        String firstName;
        String lastName;
        String password;
        String email;
        ArrayList<Playlist> playlistList;

        User user = null;

        String sql = "SELECT * FROM gulaplay.user WHERE username LIKE " + username + ";";

        try {
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
//                eventMonth.add(new Event(resultSet.getDate("date"),resultSet.getString("event_name"),resultSet.getString("color")));
                userID = resultSet.getInt("iduser");
                firstName = resultSet.getString("firstname");
                lastName = resultSet.getString("lastname");
                password = resultSet.getString("password");
                email = resultSet.getString("email");

                user = new User(userID, username,firstName, lastName, password, email, null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;


    }
}
