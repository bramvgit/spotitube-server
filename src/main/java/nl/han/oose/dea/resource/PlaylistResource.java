package nl.han.oose.dea.resource;

import nl.han.oose.dea.service.PlaylistService;
import nl.han.oose.dea.service.dto.PlaylistDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class PlaylistResource {
    private PlaylistService playlistService;

    @Inject
    public void setPlaylistService(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Path("/playlists")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("token") String token) {
        return Response.ok(playlistService.getAll(token)).build();
    }

    @Path("/playlists/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editName(@QueryParam("token") String token, @PathParam("id") int id, PlaylistDTO playlistDTO) {
        return Response.ok(playlistService.editName(token, id, playlistDTO)).build();
    }

    @Path("/playlists")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(@QueryParam("token") String token, PlaylistDTO playlistDTO) {
        return Response.status(Response.Status.CREATED).entity(playlistService.add(token, playlistDTO)).build();
    }

    @Path("/playlists/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@QueryParam("token") String token, @PathParam("id") int id) {
        return Response.ok(Response.Status.CREATED).entity(playlistService.delete(token, id)).build();
    }
}
