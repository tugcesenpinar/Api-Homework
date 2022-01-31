package models;

public class UpdateBooking {

    public String firstname;
    public String lastname;
    public int totalprice;
    public boolean depositpaid;

    public UpdateBooking(String firstname, String lastname, int totalprice, boolean depositpaid) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
    }
}
