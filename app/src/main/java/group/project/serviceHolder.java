package group.project;

public class serviceHolder {
    private String service;
    private String rate;

    public serviceHolder(String service, String rate) {
        this.service = service;
        this.rate = rate;
    }

    public serviceHolder(){

    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

}
