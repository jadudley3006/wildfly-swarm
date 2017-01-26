package za.co.home.greeting;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;

/**
 * Created by Jonathan Dudley on 26/01/2017.
 */
@Stateless
@ApplicationScoped
public class PingService {

    public String getPing() {
        return "cdi pong!!";
    }

}
