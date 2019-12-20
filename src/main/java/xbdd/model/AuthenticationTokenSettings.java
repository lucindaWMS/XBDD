package xbdd.model;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class AuthenticationTokenSettings {

    /**
     * Identifies the recipients that the JWT token is intended for.
     */
    @Inject
    private String audience;

    /**
     * Identifies the JWT token issuer.
     */
    @Inject
    private String issuer;

    /**
     * JWT claim for the authorities.
     */
    @Inject
    private String authoritiesClaimName = "authorities";

    public String getAudience() {
        return audience;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getAuthoritiesClaimName() {
        return authoritiesClaimName;
    }
}
