package group.project;

public class serviceProviderInfo {
    private String name;
    private String address;
    private String number;
    private String company;
    private String license;
    private String description;

    public serviceProviderInfo(String name, String address, String number, String company, String license, String description) {
        this.name = name;
        this.address = address;
        this.number = number;
        this.company = company;
        this.license= license;
        this.description = description;
    }

    public serviceProviderInfo(){

    }

    public String getName() {
        return name;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public void setName(String name) {

        this.name = name;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
