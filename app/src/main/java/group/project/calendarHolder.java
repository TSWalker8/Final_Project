package group.project;

public class calendarHolder {

    private String date;
    private String time;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHours() {
        return time;
    }

    public void setHours(String time) {
        this.time = time;
    }

    public calendarHolder(String date, String time){
        this.date=date;
        this.time=time;

    }
}
