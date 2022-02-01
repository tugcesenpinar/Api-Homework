package models;

import java.util.Date;

public class BookingDates {
    String checkin;
    String checkout;

    public BookingDates(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public String getCheckin() {
        return checkin;
    }

    public String  getCheckout() {
        return checkout;
    }
}
