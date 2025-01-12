Feature: View All page

  Background:
    Given a web connection is established
    And I create account
    And I am logged in
    And I am on the track exercise page
    And I have entered a completed workout

  Scenario: View All page displays all previous workouts
    When I click on "View All"
    Then I can see the details of my workout

  Scenario: Deleting exercise from View All page removes it everywhere
    Given I click on "View All"
    When I select the delete button
    Then the exercise is no longer visible on the View All page
    And I click on "Statistics"
    Then there is no data available