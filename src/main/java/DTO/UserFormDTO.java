package DTO;

public class UserFormDTO {

    private long user_id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public UserFormDTO(long user_id, String firstName, String lastName, String email, String password) {
        this.user_id = user_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
    public UserFormDTO(){}

    public UserFormDTO(String firstname, String lastname, String email, String password) {
    }

    public long getUser_id() {
        return user_id;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
