package Model;

public class EventLogs {
    String username;
    String activity;
    String date;

    public EventLogs(String username, String activity, String date) {
        this.username = username;
        this.activity = activity;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public String getActivity() {
        return activity;
    }

    public String getDate() {
        return date;
    }
}
