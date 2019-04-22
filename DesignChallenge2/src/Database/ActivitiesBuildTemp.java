package Database;

import Model.EventLogs;

import java.sql.*;
import java.util.ArrayList;

public class ActivitiesBuildTemp {
    PreparedStatement prepStatement;
    Connection myConn = DatabaseConnection.getDatabaseConn();
    Statement statement = null;
    ResultSet resultSet;

   public ArrayList<EventLogs>getEvents(String username)
    {
        ArrayList<EventLogs> eventLogsuser = new ArrayList<>();

        int activity_id;
        String date;
        String description;

        EventLogs eventlog;

        try
        {
            PreparedStatement prepStatement = myConn.prepareStatement("SELECT * FROM gulaplay.activity where username = ?");
            prepStatement.setString(1, username);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next()){
                description = resultSet.getString("activitydescription");
                date = resultSet.getString("dateofactivity");
                activity_id = resultSet.getInt("idactivity");

                eventlog = new EventLogs(username, description, date, activity_id);
                eventLogsuser.add(eventlog);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return eventLogsuser;
    }


}
