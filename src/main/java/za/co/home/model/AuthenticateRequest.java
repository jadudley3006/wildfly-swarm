package za.co.home.model;

import java.io.Serializable;

public class AuthenticateRequest implements Serializable {

    private static final long serialVersionUID = 1220026677110784986L;

    private String username;
    private String password;

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
