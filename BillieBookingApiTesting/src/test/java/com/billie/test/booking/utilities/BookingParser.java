package com.billie.test.booking.utilities;

import io.cucumber.datatable.DataTable;
import com.billie.test.booking.model.Authentication;
import com.billie.test.booking.model.Booking;

public class BookingParser {
    private static final int DATA_ROW_NUMBER = 1;
    private static final int FIRST_NAME_COLUMN = 0;
    private static final int LAST_NAME_COLUMN = 1;
    private static final int TOTAL_PRICE_COLUMN = 2;
    private static final int DEPOSIT_PAID_COLUMN = 3;
    private static final int ADDITIONAL_NEEDS_COLUMN = 4;
    private static final int CHECKIN_COLUMN = 5;
    private static final int CHECKOUT_COLUMN = 6;
    private BookingParser(){}

    public static Authentication createAuthenticationFromDataTable(DataTable table){
        return new Authentication(table.row(DATA_ROW_NUMBER).get(0), table.row(DATA_ROW_NUMBER).get(1));
    }

    public static Booking parseFromDataTableExtended(DataTable table){

        Booking booking = new Booking();
        booking.setFirstname(table.row(DATA_ROW_NUMBER).get(FIRST_NAME_COLUMN));
        booking.setLastname(table.row(DATA_ROW_NUMBER).get(LAST_NAME_COLUMN));
        booking.setTotalPrice(Double.valueOf(table.row(DATA_ROW_NUMBER).get(TOTAL_PRICE_COLUMN)));
        booking.setDepositpaid(Boolean.valueOf(table.row(DATA_ROW_NUMBER).get(DEPOSIT_PAID_COLUMN)));
        booking.setAdditionalneeds(table.row(DATA_ROW_NUMBER).get(ADDITIONAL_NEEDS_COLUMN));
        booking.setBookingdates(parseBookingDates(table.row(DATA_ROW_NUMBER).get(CHECKIN_COLUMN), table.row(DATA_ROW_NUMBER).get(CHECKOUT_COLUMN)));

        return booking;
    }

    public static Booking.BookingDates parseBookingDates(String checkin, String checkout){
        Booking.BookingDates bookingDates = new Booking.BookingDates();
        bookingDates.setCheckin(checkin);
        bookingDates.setCheckout(checkout);
        return bookingDates;
    }
}
