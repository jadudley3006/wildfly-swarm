package za.co.home.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "users")
@NamedQuery(name = "Users.findByUsername", query = "SELECT c FROM UserEntity c WHERE c.username = :username")
@XmlRootElement
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 4875513908785399148L;

    public static final String FIND_BY_USERNAME = "Users.findByUsername";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String username;
    private String password;
    private String salt;

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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
