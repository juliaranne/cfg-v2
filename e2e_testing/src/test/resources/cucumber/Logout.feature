Feature: Logout functionality

  Background:
    Given a web connection is established
    And I create account
    And I am logged in

  Scenario: Logging out brings me to the sign in page
    When I click on "Logout"
#    Then I am on the login page


  Scenario: Exercise data persists after logout and shows upon next login
