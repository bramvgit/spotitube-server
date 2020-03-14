package nl.han.oose.dea.resources.mappers;

import nl.han.oose.dea.services.exceptions.IncorrectLoginCredentialsException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IncorrectLoginCredentialsExceptionMapper implements ExceptionMapper<IncorrectLoginCredentialsException> {
    @Override
    public Response toResponse(IncorrectLoginCredentialsException e) {
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
