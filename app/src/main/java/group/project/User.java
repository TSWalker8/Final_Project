package group.project;

public class User {

    public String password;
    public String role;
    public String email;

    public User(){

    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String password, String role, String email){
        this.password=password;
        this.role=role;
        this.email=email;
    }
}
