@AI
Feature: Agency Admin Travel Policy Configuration

  Background:
    Given Open Browser and Navigate to HotelBooker
    When user enters username and password
    And user clicks login button
    And selects client "Test QA Client(Sabre)"
    Then Validate selected client should display on header
    And disable Travel Policy In Agency Admin

  Scenario: Disable Travel Policy Active Setting - Detailed Steps
    # All individual steps shown for clarity
    When user clicks on Tools button
    And user clicks on Agency Admin link
    And user switches to Agency Admin window
    And user clicks on Travel Policy link
    And user unchecks Policy Active checkbox
    And user clicks on Update Policy button
    Then user should see Update Successful message
    When user closes Agency Admin window

