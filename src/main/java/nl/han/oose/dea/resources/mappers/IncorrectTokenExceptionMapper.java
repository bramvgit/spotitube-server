package nl.han.oose.dea.resources.mappers;

import nl.han.oose.dea.services.exceptions.IncorrectTokenException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IncorrectTokenExceptionMapper implements ExceptionMapper<IncorrectTokenException> {
    @Override
    public Response toResponse(IncorrectTokenException e) {
        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
