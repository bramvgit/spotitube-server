package nl.han.oose.dea.resource;

import nl.han.oose.dea.service.TrackService;
import nl.han.oose.dea.service.dto.TrackDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class TrackResource {
    private TrackService trackService;

    @Path("/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks(@QueryParam("token") String token, @QueryParam("forPlaylist") int id) {
        return Response.ok(trackService.getTracksNotInPlaylist(id)).build();
    }

    @Path("/playlists/{id}/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksInPlaylist(@QueryParam("token") String token, @PathParam("id") int id) {
        return Response.ok(trackService.getTracksInPlaylist(id)).build();
    }

    @Path("/playlists/{id}/tracks")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrack(@QueryParam("token") String token, @PathParam("id") int id, TrackDTO trackDTO) {
        return Response.status(Response.Status.CREATED).entity(trackService.addTrack(token, id, trackDTO)).build();
    }

    @Path("/playlists/{playlist_id}/tracks/{track_id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrack(@QueryParam("token") String token, @PathParam("playlist_id") int playlist, @PathParam("track_id") int track) {
        return Response.ok(trackService.deleteTrack(playlist, track)).build();
    }

    @Inject
    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }
}
