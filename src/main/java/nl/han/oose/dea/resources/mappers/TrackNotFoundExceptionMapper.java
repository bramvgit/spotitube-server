package nl.han.oose.dea.resources.mappers;

import nl.han.oose.dea.services.exceptions.TrackNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TrackNotFoundExceptionMapper implements ExceptionMapper<TrackNotFoundException> {
    @Override
    public Response toResponse(TrackNotFoundException e) {
        return Response.accepted().status(Response.Status.NOT_FOUND).build();
    }
}
