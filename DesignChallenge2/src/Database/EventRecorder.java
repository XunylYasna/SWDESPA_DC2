package Database;

import Model.Playlist;

import java.sql.*;

public class EventRecorder {
    PreparedStatement prepStatement;
    Connection myConn = DatabaseConnection.getDatabaseConn();
    Statement statement = null;
    ResultSet resultSet;

    public EventRecorder(String description, String username){

      String sql = "INSERT INTO activity (dateofactivity, activitydescription, username)\n"
                    + "values(?,?,?)";
        try {
            prepStatement = myConn.prepareStatement(sql);
            prepStatement.setDate(1, new Date(new java.util.Date().getTime()));
            prepStatement.setString(2, description);
            prepStatement.setString(3, username);
            prepStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
