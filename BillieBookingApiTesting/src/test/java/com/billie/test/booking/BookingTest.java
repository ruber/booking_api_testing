package com.billie.test.booking;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/billie/test/booking",
        glue = "com.billie.test.booking.steps"
)
public class BookingTest {
}
