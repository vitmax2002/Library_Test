package library.authentication;

public class RegisterRequest {

    private String username;
    private String password;
    private String authority;

    public RegisterRequest() {
    }

    public RegisterRequest(String username, String password,String authority) {

        this.username = username;
        this.password = password;
        this.authority=authority;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
