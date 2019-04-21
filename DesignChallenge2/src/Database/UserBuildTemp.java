package Database;

import Model.Playlist;
import Model.User;

import java.sql.*;
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
        String usertype;
        ArrayList<Playlist> playlistList;

        User user = null;


        try {
            PreparedStatement prepStatement = myConn.prepareStatement("SELECT * FROM gulaplay.user WHERE username LIKE  ?");
            prepStatement.setString(1,username);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next()){
                userID = resultSet.getInt("iduser");
                firstName = resultSet.getString("firstname");
                lastName = resultSet.getString("lastname");
                password = resultSet.getString("password");
                email = resultSet.getString("email");
                usertype = resultSet.getString("user_type");
                user = new User(userID, username,firstName, lastName, password, email,usertype);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
