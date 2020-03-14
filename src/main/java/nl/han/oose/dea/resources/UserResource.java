package nl.han.oose.dea.resources;

import nl.han.oose.dea.services.UserService;
import nl.han.oose.dea.services.dto.UserDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class UserResource {

    private UserService userService;

    @Inject
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postLogin(UserDTO userDTO) {
        return Response.accepted(userService.login(userDTO)).status(Response.Status.CREATED).build();
    }
}
