package nl.han.oose.dea.resource.mapper;

import nl.han.oose.dea.exception.IncorrectTokenException;

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
