package com.billie.test.booking.utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import com.billie.test.booking.model.Authentication;

import java.util.HashMap;

public class ServiceCalls {
    private static RequestSpecification request;
    private static final String BASE_URL = "https://restful-booker.herokuapp.com";
    private static final String BOOKING_PATH = "/booking";
    private static final String AUTH_PATH = "/auth";

    private ServiceCalls() {
    }

    static {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(BASE_URL);
        builder.setContentType(ContentType.JSON);
        RequestSpecification requestSpec = builder.build();
        request = RestAssured.given().spec(requestSpec);
    }

    public static String getAuthenticationToken(Authentication authentication) {
        return getAuthenticationToken(authentication.getUser(), authentication.getPassword());
    }

    public static String getAuthenticationToken(String user, String password) {
        HashMap<String, String> body = new HashMap<>();
        body.put("username", user);
        body.put("password", password);
        request.body(body);
        return request.post(AUTH_PATH).body().jsonPath().get("token");
    }

    public static ResponseOptions<Response> executePostWithBody(Object o) {
        return request.body(o).post(BOOKING_PATH);
    }

    public static ResponseOptions<Response> executePutWithBody(Object o, int bookingId, String token) {
        setCookieHeader(token);
        return request.body(o).put(String.format("%s/%s", BOOKING_PATH, bookingId));
    }

    public static ResponseOptions<Response> executeGetWithPath(int bookingId, String token) {
        setCookieHeader(token);
        return request.get(String.format("%s/%s", BOOKING_PATH, bookingId));
    }

    public static ResponseOptions<Response> executeDeleteWithPath(int bookingId, String token) {
        setCookieHeader(token);
        return request.delete(String.format("%s/%s", BOOKING_PATH, bookingId));
    }

    private static void setCookieHeader(String token) {
        request.header(new Header("Cookie", String.format("token=%s", token)));
    }
}
