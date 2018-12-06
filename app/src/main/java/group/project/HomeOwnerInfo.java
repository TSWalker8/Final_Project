package group.project;

public class HomeOwnerInfo {
    private String name;
    private String address;
    private String number;
    private String company;
    private String license;
    private String description;

    public HomeOwnerInfo(String name, String address, String number, String description) {
        this.name = name;
        this.address = address;
        this.number = number;
        this.description = description;
    }

    public HomeOwnerInfo(){

    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
