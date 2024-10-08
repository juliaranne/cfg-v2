Feature: Workout details
#  Background:
#    Given I have an account
#    And I have logged into the app

  Scenario: Users can see full details of their completed workouts
    Given I have entered my completed workout
#    When I go to the statistics page
    Then I can see a daily breakdown of my workout week in the journal
#    And I can see the full details of each workout

# not recognising step defs as need to sort dependencies & glue, also need to determine where cucumber tests should live

#  Scenario: Workout data across two weeks is not displayed on the same week - vwording?