/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import domain.Cartracker;
import domain.TrackingPeriod;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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

    @Inject
    private TrackingPeriodService trackingPeriodService;

    /**
     * Gets all cartracker known in the database
     *
     * @return All known cartrackers
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cartracker> getAllTrackers() {
        List<Cartracker> cartrackers = cartrackerService.getAll();
        for (Cartracker cartracker : cartrackers) {
            cartracker.setTrackingPeriods(this.getMovementsFromCartrackerWithId(cartracker.getId()));
        }
        return cartrackers;
    }

    /**
     * Gets a cartracker with the corresponding id
     *
     * @param trackerId The id of the cartracker
     * @return The cartracker with the corresponding id
     */
    @GET
    @Path("/{trackerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cartracker getCartrackerById(@PathParam("trackerId") Long trackerId) {
        return cartrackerService.findById(trackerId);
    }

    /**
     * Adds a new cartracker to the database
     *
     * @return The newly added cartracker
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cartracker addNewTracker() {
        return cartrackerService.create(new Cartracker());
    }

    /**
     * Gets all movements from a cartracker with specified id
     *
     * @param trackerId The id of the cartracker to get all movements from
     * @return All movements from a specific cartracker
     */
    @GET
    @Path("/{trackerId}/movements")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TrackingPeriod> getMovementsFromCartrackerWithId(@PathParam("trackerId") Long trackerId) {
        Cartracker cartracker = cartrackerService.findById(trackerId);
        if (cartracker == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return trackingPeriodService.getAllTrackingPeriodsFromCartracker(cartracker);
    }

    /**
     * Gets all movements from a cartracker with specified id in a specific period
     *
     * @param trackerId The id of the cartracker to get all movements from
     * @return All movements from a specific cartracker
     */
    @GET
    @Path("/{trackerId}/movements/_byperiod")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TrackingPeriod> getMovementsByPeriod(@PathParam("trackerId") Long trackerId,
            @QueryParam("startDate") String startDate,
            @QueryParam("endDate") String endDate) {
        Cartracker cartracker = cartrackerService.findById(trackerId);
        if (cartracker == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return trackingPeriodService.getAllTrackingPeriodsByPeriod(cartracker, format.parse(startDate), format.parse(endDate));
        } catch (ParseException ex) {
            Logger.getLogger(CartrackerResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Adds a new TrackingPeriod to the database for a specific cartracker
     *
     * @param trackerId The id of the cartracker
     * @param trackingPeriod The new TrackingPeriod
     * @return The newly added TrackingPeriod
     */
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

    /**
     * Gets a TrackingPeriod with the specified serialnumber from a specific
     * cartracker
     *
     * @param trackerId The id of the cartracker
     * @param serialNumber The serialnumber of the TrackingPeriod
     * @return The TrackingPeriod with the corresponding serialnumber from the
     * specified cartracker
     */
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
