package models;

import java.util.HashMap;
import java.util.Map;

public class UpdateBooking {

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private Map<String,String> bookingdates;

    public UpdateBooking(String firstname, String lastname, int totalprice, boolean depositpaid, Map<String, String> bookingdates) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public Map<String, String> getBookingdates() {
        return bookingdates;
    }
}
