Feature: Weekly Journal
  Background:
    Given I have an account for "user1" with password "password"
#    Given I have a new random account
    And I login to the app using the username "user1" and password "password"

    # a currently fullfilled scenario
  Scenario: Users can see their completed workouts in their weekly journal
    Given I have entered a completed workout
    When I go to the "Weekly Journal" page
#    Then I can see my workout in the journal
  Then I can see the workout type and duration in the journal

#    # our unfullfilled scenario
#  Scenario: Users can see a daily breakdown of their completed workouts in their weekly journal
#    Given I have entered a completed workout
#    When I go to the "Weekly Journal" page
#    Then I can see a daily breakdown of my workout week in the journal
#    And I can see the full details of each workout
