package Kwetter.filter;

import Kwetter.dao.user.UserDAO;
import Kwetter.jwt.Constant;
import Kwetter.utility.Secured;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final String REALM = "Kwetter";
    private static final String AUTHENTICATION_SCHEME = "Bearer";

    @Inject
    private UserDAO userDAO;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        //Get header from request
        String authorizationHeader = containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        //Validate header
        if(!isTokenBasedAuthentication(authorizationHeader)) {
            abortWithUnauthorized(containerRequestContext);
            return;
        }
        //Extract token from header
        String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();
        //Try and validate token
        try {
            validateToken(token);
        }
        catch(Exception e) {
            abortWithUnauthorized(containerRequestContext);
        }

        String subjectUserId = getSubjectUserId(token);
        int subjectRoleId = getSubjectRoleId(Integer.parseInt(subjectUserId));
        final SecurityContext securityContext = containerRequestContext.getSecurityContext();
        containerRequestContext.setSecurityContext(new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
                return() -> subjectUserId;
            }

            @Override
            public boolean isUserInRole(String role) {
                int roleId = Integer.parseInt(role);
                return subjectRoleId == roleId;
            }

            @Override
            public boolean isSecure() {
                return securityContext.isSecure();
            }

            @Override
            public String getAuthenticationScheme() {
                return AUTHENTICATION_SCHEME;
            }
        });
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {

        //Abort the filter chain with a 401 status code response
        //The WWW-Authenticate header is sent along with the response
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .header(HttpHeaders.WWW_AUTHENTICATE,
                                AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"")
                        .build());
    }

    private void validateToken(String token) throws Exception {
        //Check if the token was issued by the server and if it's not expired
        //Throw an Exception if the token is invalid
        if(userDAO.checkToken(token) == false) {
            throw new Exception();
        }
    }

    private String getSubjectUserId(String token) {
        Claims claims = Jwts.parser()
                            .setSigningKey(Constant.key)
                            .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    private int getSubjectRoleId(int id) {
        return userDAO.getRoleId(id);
    }

    private boolean isTokenBasedAuthentication(String authorizationHeader) {
        //Check if the Authorization header is valid
        //It must not be null and must be prefixed with "Bearer" plus a whitespace
        //The authentication scheme comparison must be case-insensitive
        return authorizationHeader != null && authorizationHeader.toLowerCase()
                                                                 .startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
    }
}
