package Kwetter.filter;

import Kwetter.exception.UnauthorizedException;
import Kwetter.model.Role;
import Kwetter.utility.Secured;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Secured
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        //Get resource class from url
        //Extract roles
        Class<?> resourceClass = resourceInfo.getResourceClass();
        List<Role> classRoles = extractRoles(resourceClass);
        //Get resource methods from url
        //Extract roles
        Method resourceMethod = resourceInfo.getResourceMethod();
        List<Role> methodRoles = extractRoles(resourceMethod);
        //Check if user is allowed
        try {
            if(methodRoles.isEmpty()) {
                checkPermissions(containerRequestContext, classRoles);
            }
            else {
                checkPermissions(containerRequestContext, methodRoles);
            }
        }
        catch (Exception e) {
            containerRequestContext.abortWith(
                    Response.status(Response.Status.FORBIDDEN).build());
        }
    }

    // Extract roles from the annotated element
    private List<Role> extractRoles(AnnotatedElement annotatedElement) {
        if(annotatedElement == null) {
            return new ArrayList<>();
        }
        else {
            Secured secured = annotatedElement.getAnnotation(Secured.class);
            if(secured == null) {
                return new ArrayList<>();
            }
            else {
                Role[] allowedRoles = secured.value();
                return Arrays.asList(allowedRoles);
            }
        }
    }

    private void checkPermissions(ContainerRequestContext requestContext, List<Role> allowedRoles) throws Exception {
        // Check if user contains allowed roles
        // Throw an exception if user doesn't have any of these
        final SecurityContext currentSecurityContext = requestContext.getSecurityContext();
        boolean permitted = allowedRoles.isEmpty();

        for(Role role : allowedRoles) {
            int roleInt = role.ordinal();
            if(currentSecurityContext.isUserInRole(Integer.toString(roleInt)))
                permitted = true;
        }

        if(!permitted) {
            int userId = Integer.parseInt(currentSecurityContext.getUserPrincipal().getName());
            throw new UnauthorizedException("User with ID '" + userId + "' attempted to make an unauthorized request.");
        }
    }
}
