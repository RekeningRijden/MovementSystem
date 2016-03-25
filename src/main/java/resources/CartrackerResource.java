/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import domain.Cartracker;
import domain.TrackingPeriod;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import service.CartrackerService;
import service.TrackingPeriodService;

/**
 * @author Eric
 */
@Path("/trackers")
@Named
public class CartrackerResource {

    @Inject
    private CartrackerService cartrackerService;

    private TrackingPeriodService trackingPeriodService = new TrackingPeriodService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cartracker> getAllTrackers() {
        return cartrackerService.getAll();
    }

    @GET
    @Path("/{trackerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cartracker getCartrackerById(@PathParam("trackerId") Long trackerId) {
        return cartrackerService.findById(trackerId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cartracker addNewTracker(Cartracker cartracker) {
        return cartrackerService.create(cartracker);
    }

    @GET
    @Path("/{trackerId}/movements")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TrackingPeriod> getMovementsFromCartrackerWithId(@PathParam("trackerId") Long trackerId) {
        if (cartrackerService.findById(trackerId) == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return trackingPeriodService.getAllTrackingPeriodsFromCartracker(cartrackerService.findById(trackerId));
    }

    //TODO - Implementatie van de trackingPeriodService/Dao voor de MongoDB
    @POST
    @Path("/{trackerId}/movements")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TrackingPeriod addTrackingPeriodForCartracker(@PathParam("trackerId") Long trackerId, TrackingPeriod trackingPeriod) {
        System.out.println("Added TrackingPeriod - sn: " + trackingPeriod.getSerialNumber() + ", nr of pos: " + trackingPeriod.getPositions().size());
        Cartracker cartracker = cartrackerService.findById(trackerId);
        if (cartracker == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return trackingPeriodService.addTrackingPeriodForCartracker(trackingPeriod, cartracker);
    }

    @GET
    @Path("/{trackerId}/movements/{serialNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public TrackingPeriod getTrackingPeriodWithSerialNumber(@PathParam("trackerId") Long trackerId, @PathParam("serialNumber") Long serialNumber) {
        Cartracker cartracker = cartrackerService.findById(trackerId);
        if (cartracker == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return trackingPeriodService.getTrackingPeriodBySerialNumber(serialNumber, cartracker);
    }
}
