
package model;

import java.sql.Date;

/**
 * Event Object
 * A <b>Event<b> object contains the different attributes of 
 * an event ...
 * 
 * @author justine
 * @version 1.001
 * @since 2017-10-27
 */

/* Documentation for developer courtesy of Ivy Lim
 * COMMENTS (please comment any concerns and attach your names thanks!):
 * Please seek Justine first before updating any major parts of this code. -Ivy 10-27-17
 * No methods so far, just constructor and attributes.  -Ivy 10-27-17
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
}
