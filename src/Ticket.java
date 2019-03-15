import java.util.Date;
import java.util.UUID;

public class Ticket {
    private String ticketID;
    private Date timeIn = new Date();

    public Ticket()
    {
        this.ticketID = UUID.randomUUID().toString();
        this.timeIn = getTimeStamp();
    }

    public Ticket(String sTimeIn)
    {
        this.ticketID = UUID.randomUUID().toString();
        this.timeIn = new Date(sTimeIn);
    }

    public Ticket(String ticketID, String sTimeIn)
    {
        /* use this as you're reading in the current "garage residents" */

        this.timeIn = new Date(sTimeIn);
        this.ticketID = ticketID;
    }

    public Date getTimeStamp()
    {
        return new Date();
    }

    /**
     * Generate an appropriate charge for parking based on the difference between
     * garage entry (defined at ticket creation) and garage exit
     * @param timeOut Date / time (yyyy-MM-dd HH:mm) by which to calculate charge
     * @return Float representing the total charge for parking
     */
    public Float getCharge(Date timeOut)
    {
        Float totalCharge;

        Integer hourDif = this.timeIn.compareTo(timeOut);

        if(hourDif <= 3)
        {
            hourDif = 0;
        }
        else
        {
            hourDif -=3;
        }

        totalCharge = 5f + hourDif.floatValue();

        if (totalCharge > 15f)
        {
            return 15f;
        }
        else
        {
            return totalCharge;
        }
    }

    public Float getCharge(String sTimeOut)
    {
        return this.getCharge(sTimeOut);
    }

    @Override
    public String toString()
    {
        return ticketID + ", " + timeIn;
    }

    /* getters and setters */

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public Date getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(Date timeIn) {
        this.timeIn = timeIn;
    }
}
