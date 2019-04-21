package Database;

import Model.EventLogs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ActivitiesBuildTemp {
    PreparedStatement prepStatement;
    Connection myConn = DatabaseConnection.getDatabaseConn();
    Statement statement = null;
    ResultSet resultSet;

    public ArrayList<EventLogs> getEvents(int UserID)
    {
        ArrayList<EventLogs> eventLogsuser = new ArrayList<>();

        int

    }
}
