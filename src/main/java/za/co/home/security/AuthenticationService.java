package za.co.home.security;

import za.co.home.common.Base64Util;
import za.co.home.model.AuthenticateRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("/authenticate")
@ApplicationScoped
public class AuthenticationService {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(AuthenticateRequest request) {
        try {
            String username = request.getUsername();
            String password = request.getPassword();
            authenticate(username, password);
            return Response.ok(generateToken(request.getUsername())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private void authenticate(String username, String password) throws Exception {
        if (!"jonathan".equals(username) || !"Password@1".equals(password)) {
            throw new Exception();
        }
    }

    private String generateToken(String username) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user=" + username + "\n");
        stringBuilder.append("time=" + new Date());
        return Base64Util.base64Encode(stringBuilder.toString());
    }

}
