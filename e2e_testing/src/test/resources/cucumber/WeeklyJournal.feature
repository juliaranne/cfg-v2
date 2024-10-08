Feature: Weekly Journal
  Background:
    Given I have an account for "user1" with password "password"
    And I login to the app using the username "user1" and password "password"

    # a currently fullfilled scenario


    # our unfullfilled scenario
  Scenario: Users can see a daily breakdown of their completed workouts in their weekly journal
    Given I have entered a completed workout
    When I go to the "Weekly Journal" page
    Then I can see a daily breakdown of my workout week in the journal
    And I can see the full details of each workout

# not recognising step defs as need to sort dependencies & glue, also need to determine where cucumber tests should live

#  Scenario: Workout data across two weeks is not displayed on the same week - vwording?