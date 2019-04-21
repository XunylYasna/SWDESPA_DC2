package Model;

public class EventLogs {
    String username;
    String activity;
    String date;
    int id;

    public EventLogs(String username, String activity, String date, int id) {
        this.username = username;
        this.activity = activity;
        this.date = date;
        this.id = id;
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
