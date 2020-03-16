package nl.han.oose.dea.resources;

import nl.han.oose.dea.services.interfaces.TrackService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/")
public class TrackResource {
    private TrackService trackService;

    @Inject
    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }

    @Path("/playlists/{id}/tracks")
    @GET
    public Response getTracksInPlaylist(@QueryParam("token") String token, @PathParam("id") int id) {
        return Response.ok(trackService.getTracksInPlaylist(token, id)).build();
    }
}
