package xbdd.webapp.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import xbdd.model.AuthenticationTokenDetails;
import xbdd.model.AuthenticationTokenSettings;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Date;

@Dependent
class AuthenticationTokenIssuer {

    @Inject
    private AuthenticationTokenSettings settings;

    /**
     * Issue a JWT token
     *
     * @param authenticationTokenDetails
     * @return
     */
    public String issueToken(AuthenticationTokenDetails authenticationTokenDetails) {

        return Jwts.builder()
                .setId(authenticationTokenDetails.getId())
                .setIssuer(settings.getIssuer())
                .setAudience(settings.getAudience())
                .setSubject(authenticationTokenDetails.getUsername())
                .setIssuedAt(Date.from(authenticationTokenDetails.getIssuedDate().toInstant()))
                .setExpiration(Date.from(authenticationTokenDetails.getExpirationDate().toInstant()))
                .claim(settings.getAuthoritiesClaimName(), authenticationTokenDetails.getAuthorities())
                .compact();
    }
}