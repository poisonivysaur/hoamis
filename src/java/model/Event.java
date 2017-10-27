
package model;

import java.sql.Date;

/**
 *
 * @author justine
 */
public class Event {
    
    protected int eventID;
    protected String eventName;
    protected String description;
    protected int headcount;
    protected Date dateHeld;
    protected String location;
    protected User organizer;
    protected String startTime;
    protected String endTime;
    protected TransactionReference fee;
    protected Date requestDate;

    public Event(String eventName, String description, int headcount, Date dateHeld, String location, User organizer, String startTime, String endTime, TransactionReference fee, Date requestDate) {
        this.eventName = eventName;
        this.description = description;
        this.headcount = headcount;
        this.dateHeld = dateHeld;
        this.location = location;
        this.organizer = organizer;
        this.startTime = startTime;
        this.endTime = endTime;
        this.fee = fee;
        this.requestDate = requestDate;
    }
}
