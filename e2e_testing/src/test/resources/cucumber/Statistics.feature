Feature: Statistics Page

  Background:
    Given a web connection is established
    And I create account
    And I am logged in
    And I am on the track exercise page

  Scenario: No exercises entered shows an empty statistics page
    Given I go to the "Statistics" page
    Then I see "Well done, <username>! This is your overall effort:"
    And there is no data available

  Scenario: Entering a new exercise shows in the statistics page
    Given I have entered a completed workout
    When I go to the "Statistics" page
    Then I see "Well done, <username>! This is your overall effort:"
    And the workout data is visible in the format:
      | header         | contents                |
      | <ExerciseType> | Total Duration: <X> min |
