package group.project;

public class ProviderListHolder {

    private String Name;
    private String Rate;
    private String Availability;
    private String Rating;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public String getAvailability() {
        return Availability;
    }

    public void setAvailability(String availability) {
        Availability = availability;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public ProviderListHolder (String Name, String Rate, String Availability, String Rating){
        this.Name=Name;
        this.Rate=Rate;
        this.Availability= Availability;
        this.Rating=Rating;
    }

    public ProviderListHolder(){

    }
}
