package xbdd.webapp.resource;

import xbdd.model.AuthenticationToken;
import xbdd.model.Authority;
import xbdd.webapp.service.AuthenticationTokenService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

@Path("/authentication")
public class AuthenticationEndpoint {

    public static ConcurrentHashMap<String, String> tokenMap = new ConcurrentHashMap<>();
    private AuthenticationTokenService authenticationTokenService = new AuthenticationTokenService();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("username") String username,
                                     @FormParam("password") String password) {

        try {

            // Authenticate the user using the credentials provided
            authenticate(username, password);

            // Issue a token for the user
            AuthenticationToken token = issueToken(username);

            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    private void authenticate(String username, String password) throws Exception {
        // Authenticate against a database, LDAP, file or whatever
        // Throw an Exception if the credentials are invalid
    }

    private AuthenticationToken issueToken(String username) {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
        Set<Authority> authoritySet = new TreeSet<>();
        authoritySet.add(Authority.USER);
        String token = authenticationTokenService.issueToken(username, authoritySet);
        AuthenticationToken authenticationToken = new AuthenticationToken();
        authenticationToken.setToken(token);
        return authenticationToken;
    }
}