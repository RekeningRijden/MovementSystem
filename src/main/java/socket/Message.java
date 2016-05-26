package socket;

import domain.TrackingPeriod;

import java.io.Serializable;

/**
 * Created by Eric on 13-05-16.
 */
public class Message implements Serializable {

    private Long trackerId;

    private TrackingPeriod trackingPeriod;

    private Long initTrackerId;

    public Message() {
        // create empty message
    }

    public Message(Long trackerId, TrackingPeriod trackingPeriod) {
        this.trackerId = trackerId;
        this.trackingPeriod = trackingPeriod;
    }

    public Long getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(Long trackerId) {
        this.trackerId = trackerId;
    }

    public TrackingPeriod getTrackingPeriod() {
        return trackingPeriod;
    }

    public void setTrackingPeriod(TrackingPeriod trackingPeriod) {
        this.trackingPeriod = trackingPeriod;
    }

    public Long getInitTrackerId() {
        return initTrackerId;
    }

    public void setInitTrackerId(Long initTrackerId) {
        this.initTrackerId = initTrackerId;
    }
}
