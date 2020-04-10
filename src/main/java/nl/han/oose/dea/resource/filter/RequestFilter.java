package nl.han.oose.dea.resource.filter;

import nl.han.oose.dea.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

@Provider
public class RequestFilter implements ContainerRequestFilter {
    private UserService userService;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        MultivaluedMap<String, String> queryParameters = containerRequestContext.getUriInfo().getQueryParameters();

        if (queryParameters.containsKey("token")) {
            userService.verifyToken(queryParameters.getFirst("token"));
        }
    }

    @Inject
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
