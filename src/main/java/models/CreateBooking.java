package models;


import sun.util.calendar.BaseCalendar;
import sun.util.calendar.LocalGregorianCalendar;

import java.sql.Date;

public class CreateBooking {

    public String firstname;
    public String lastname;
    public int totalprice;
    public boolean depositpaid;
    public java.util.Date checkin;
    public Date checkout;
    public String additionalneeds;

    public CreateBooking(String firstname, String lastname, int totalprice, boolean depositpaid, Date checkin, Date checkout, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.checkin = checkin;
        this.checkout = checkout;
        this.additionalneeds = additionalneeds;
    }


}
