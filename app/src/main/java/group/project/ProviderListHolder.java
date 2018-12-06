package group.project;

public class ProviderListHolder {

    private String Name;
    private String Rate;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ProviderListHolder (String Name, String Rate){
        this.Name=Name;
        this.Rate=Rate;
    }

    public ProviderListHolder(){

    }
}
