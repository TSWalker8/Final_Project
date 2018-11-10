package group.project;

public class User {

    public String password;
    public String role;
    public String email;

    public User(){

    }


    public User(String password, String role, String email){
        this.password=password;
        this.role=role;
        this.email=email;
    }
}
