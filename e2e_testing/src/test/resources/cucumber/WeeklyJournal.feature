Feature: Weekly Journal

  Background:
    Given a web connection is established
    And I create account
    And I am logged in
    And I am on the track exercise page

  Scenario: Users can see a daily breakdown of their completed workouts in their weekly journal
    Given I have entered a completed workout
    When I click on "Weekly Journal"
    Then I can see the workout type and duration in the journal
    And I can see the full details of each workout
