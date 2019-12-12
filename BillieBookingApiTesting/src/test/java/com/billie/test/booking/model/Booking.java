package com.billie.test.booking.model;

import java.util.Objects;

public class Booking {

    private String firstname;
    private String lastname;
    private double totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
        this.lastname = lastName;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalPrice(double totalprice) {
        this.totalprice = totalprice;
    }

    public BookingDates getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingDates bookingdates) {
        this.bookingdates = bookingdates;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        boolean result = Double.compare(booking.getTotalprice(), getTotalprice()) == 0 &&
                isDepositpaid() == booking.isDepositpaid() &&
                Objects.equals(getFirstname(), booking.getFirstname()) &&
                Objects.equals(getLastname(), booking.getLastname()) &&
                Objects.equals(getBookingdates(), booking.getBookingdates()) &&
                Objects.equals(getAdditionalneeds(), booking.getAdditionalneeds());
        return result;

    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstname(), getLastname(), getTotalprice(), isDepositpaid(), getBookingdates(), getAdditionalneeds());
    }

    @Override
    public String toString() {
        return "Booking{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid=" + depositpaid +
                ", bookingdates=" + bookingdates +
                ", additionalneeds='" + additionalneeds + '\'' +
                '}';
    }

    public static class BookingDates {
        private String checkin;
        private String checkout;

        public String getCheckin() {
            return checkin;
        }

        public void setCheckin(String checkin) {
            this.checkin = checkin;
        }

        public String getCheckout() {
            return checkout;
        }

        public void setCheckout(String checkout) {
            this.checkout = checkout;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BookingDates that = (BookingDates) o;
            return Objects.equals(getCheckin(), that.getCheckin()) &&
                    Objects.equals(getCheckout(), that.getCheckout());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getCheckin(), getCheckout());
        }
    }
}
