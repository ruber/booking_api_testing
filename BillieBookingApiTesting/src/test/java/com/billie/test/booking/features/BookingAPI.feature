Feature: Verify Booking API operations
  Scenario: Verify booking creation
    Given I have an booking with the following attributes
      | firstName | lastName | totalPrice | depositpaid | additionalneeds | checkin    | checkout   |
      | Ross      | Geller   | 111        | true        | BreakFast       | 2018-01-01 | 2019-01-01 |
    When I perform post operation
    Then Returned status is 200
    And Response booking data is the same as in posted one
    And Response body has bookingid field with not-null value

    Scenario: Verify booking update
      Given I have an already created booking with the following attributes
        | firstName | lastName | totalPrice | depositpaid | additionalneeds | checkin    | checkout   |
        | Ross      | Geller   | 111        | true        | BreakFast       | 2018-01-01 | 2019-01-01 |
      And I am authenticated as
        | user  | password    |
        | admin | password123 |
      When I perform an update operation with this attributes
        | firstName | lastName | totalPrice | depositpaid | additionalneeds | checkin    | checkout   |
        | Ross2     | Geller2  | 112        | false       | BreakFast2      | 2018-01-02 | 2019-01-02 |
      Then Returned status is 200
      And Response booking data is the same as in posted one

  Scenario: Verify getting booking
    Given I have an already created booking with the following attributes
      | firstName | lastName | totalPrice | depositpaid | additionalneeds | checkin    | checkout   |
      | Ross      | Geller   | 111        | true        | BreakFast       | 2018-01-01 | 2019-01-01 |
    And I am authenticated as
      | user  | password    |
      | admin | password123 |
    When I perform get operation using the bookingid for the created booking
    Then Returned status is 200
    And Response booking data is the same as in posted one

  Scenario: Verify deleting booking
    Given I have an already created booking with the following attributes
      | firstName | lastName | totalPrice | depositpaid | additionalneeds | checkin    | checkout   |
      | Ross      | Geller   | 111        | true        | BreakFast       | 2018-01-01 | 2019-01-01 |
    And I am authenticated as
      | user  | password    |
      | admin | password123 |
    When I perform delete operation using the bookingid for the created booking
    Then Returned status is 201
    And get request for deleted booking returns 404 status