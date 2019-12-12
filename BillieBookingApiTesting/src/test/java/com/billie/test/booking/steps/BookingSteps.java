package com.billie.test.booking.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import com.billie.test.booking.model.Booking;
import com.billie.test.booking.model.BookingResponse;
import com.billie.test.booking.utilities.BookingParser;
import com.billie.test.booking.utilities.ServiceCalls;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BookingSteps {
    public String token;
    public Booking referenceBooking;
    public Booking currentBooking;
    public BookingResponse currentBookingResponse;
    public ResponseOptions<Response> currentResponse;

    @Given("^I have an booking with the following attributes$")
    public void iHaveAnUserWithTheFollowingAttributes(DataTable dataTable) {
        referenceBooking = BookingParser.parseFromDataTableExtended(dataTable);
    }

    @Given("I have an already created booking with the following attributes")
    public void iHaveAnAlreadyCreatedBookingWithTheFollowingAttributes(DataTable dataTable) {
        currentBookingResponse = ServiceCalls.executePostWithBody(BookingParser.parseFromDataTableExtended(dataTable)).body().as(BookingResponse.class);
    }

    @When("I perform post operation")
    public void iPerformPostOperation() {
        currentResponse = ServiceCalls.executePostWithBody(referenceBooking);
        currentBookingResponse = currentResponse.body().as(BookingResponse.class);
        currentBooking = currentBookingResponse.getBooking();
    }

    @When("I perform an update operation with this attributes")
    public void iPerformAnUpdateOperationWithThisAttributes(DataTable dataTable) {
        referenceBooking = BookingParser.parseFromDataTableExtended(dataTable);
        currentResponse = ServiceCalls.executePutWithBody(referenceBooking, currentBookingResponse.getBookingid(), token);
        currentBooking = currentResponse.body().as(Booking.class);
    }

    @Then("Returned status is {int}")
    public void returnedStatusIsOK(int status) {
        assertThat(currentResponse.statusCode(), equalTo(status));
    }

    @And("Response booking data is the same as in posted one")
    public void responseDataIsTheSameAsInCreationBookingRequest() {
        assertThat(currentBooking, equalToObject(referenceBooking));
    }

    @And("Response body has bookingid field with not-null value")
    public void responseBodyHasFieldWithNotNullValue() {
        assertThat(currentBookingResponse.getBookingid(), notNullValue());
    }

    @And("I am authenticated as")
    public void iAmAuthenticatedAs(DataTable dataTable) {
        token = ServiceCalls.getAuthenticationToken(BookingParser.createAuthenticationFromDataTable(dataTable));
    }

    @When("I perform get operation using the bookingid for the created booking")
    public void iPerformGetOperationUsingTheBookingidForTheCreatedBooking() {
        referenceBooking = currentBookingResponse.getBooking();
        currentResponse = ServiceCalls.executeGetWithPath(currentBookingResponse.getBookingid(), token);
        currentBooking = currentResponse.body().as(Booking.class);
    }

    @When("I perform delete operation using the bookingid for the created booking")
    public void iPerformDeleteOperationUsingTheBookingidForTheCreatedBooking() {
        referenceBooking = currentBookingResponse.getBooking();
        currentResponse = ServiceCalls.executeDeleteWithPath(currentBookingResponse.getBookingid(), token);
    }

    @And("get request for deleted booking returns {int} status")
    public void getRequestForDeletedBookingReturnsStatus(int status) {
        currentResponse = ServiceCalls.executeGetWithPath(currentBookingResponse.getBookingid(), token);
        assertThat(currentResponse.statusCode(), equalTo(status));
    }
}
