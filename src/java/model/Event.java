
package model;

import java.sql.Date;

/**
 * Event Object
 * A <b>Event</b> object contains the different attributes of 
 * an event ...
 * 
 * @author justine
 * @version 1.001
 * @since 2017-10-27
 */

/* Documentation for developer courtesy of Ivy Lim
 * COMMENTS (please comment any concerns and attach your names thanks!):
 * Please seek Justine first before updating any major parts of this code.
 * 
 *
 * 
 *
 * original code: 10-27-17
 * last update:
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

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHeadcount() {
        return headcount;
    }

    public void setHeadcount(int headcount) {
        this.headcount = headcount;
    }

    public Date getDateHeld() {
        return dateHeld;
    }

    public void setDateHeld(Date dateHeld) {
        this.dateHeld = dateHeld;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public TransactionReference getFee() {
        return fee;
    }

    public void setFee(TransactionReference fee) {
        this.fee = fee;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
    
    
}
