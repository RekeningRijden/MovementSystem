/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import domain.Cartracker;
import domain.TrackingPeriod;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import service.CartrackerService;

/**
 *
 * @author Eric
 */
@Path("/trackers")
public class CartrackerResource {

    CartrackerService trackerService = new CartrackerService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cartracker> getTrackers() {
        return trackerService.getAllCartrackers();
    }

    @GET
    @Path("/{trackerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cartracker getCartrackerById(@PathParam("trackerId") Long trackerId) {
        return trackerService.getCartrackerWithId(trackerId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cartracker> addNewTracker(Cartracker cartracker) {
        System.out.println("id: " + cartracker.getId() + ", authorisationCode: " + cartracker.getAutorisationCode());
        trackerService.addNewCartracker(cartracker);
        return trackerService.getAllCartrackers();
    }

    @GET
    @Path("/{trackerId}/movements")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TrackingPeriod> getMovementsFromCartrackerWithId(@PathParam("trackerId") Long trackerId) {
        return trackerService.getCartrackerWithId(trackerId).getMovements();
    }
}
