package group.project;

public class HomeCalendarHolder {

    private String DateandTime;
    private String Service;
    private String Provider;

    public String getDateandTime() {
        return DateandTime;
    }

    public void setDateandTime(String dateandTime) {
        DateandTime = dateandTime;
    }

    public String getService() {
        return Service;
    }

    public void setService(String service) {
        Service = service;
    }

    public String getProvider() {
        return Provider;
    }

    public void setProvider(String provider) {
        Provider = provider;
    }

    public HomeCalendarHolder(String dateandtime, String service, String provider){
        DateandTime=dateandtime;
        Service=service;
        Provider=provider;

    }

    public HomeCalendarHolder(){

    }
}