@Sanity @Amadeus_vs_BCOM_Sanity
Feature: Amadeus vs BCOM Single Night Multi Room Multi Guest Booking

  Background:
    Given Open Browser and Navigate to HotelBooker
    When user enters username and password
    And user clicks login button
    And selects client "Test QA Client(Sabre)"
    Then Validate selected client should display on header
    And disable Travel Policy In Agency Admin

  Scenario Outline: Amadeus vs BCOM Single Night Multi Room Multi Guest Standard Booking
    # SHOP Phase
    When user selects country "<country>"
    And enters location "<location>" from suggestion
    And selects distance "<distance>"
    And enters number of nights as "<nights>"
    And selects number of rooms as "<rooms>"
    And selects number of guests as "<guests>"
    And selects arrival date <days> days from today
    And clicks on search button
    Then hotel search results should be displayed or a message if no hotels found
    And Select the Rate Plan from "<provider>" with refundable "<refundable>"
    # BOOK Phase
    And Click on Full Rate Information
    And Add Booking Contact details
    And Add Traveller details
    And Click on Fax Communication Preference and Add Custom Data Fields
    And Select Payment Method as "<payment_method>"
    And Click on Finish button
    Then Validate Booking Reference Number to be display
    And Click on Home button
    # RETRIEVE Phase
    And user clicks on View Bookings tab
    And user enters booking reference
    And user clicks search button for booking retrieval
    Then Validate booking retrieval page should be displayed
    # CANCEL Phase
    When clicks Cancel Booking and confirms cancellation
    Then booking status should be displayed as Cancelled

    Examples:
      | country | location | distance | days | nights | rooms | guests | provider    | refundable | payment_method |
      | USA     | Dallas   | 20 Miles | 30   | 1      | 2     | 2      | Booking.com | No         | Credit Card    |
