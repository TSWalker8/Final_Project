package group.project;

public class User {

    public String password;
    public String role;
    public String email;
    public String userName;

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

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName=userName;
    }

    public User(String password, String role, String email, String userName){
        this.password=password;
        this.role=role;
        this.email=email;
        this.userName=userName;
    }
}
