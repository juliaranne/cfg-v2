Feature: Weekly Journal

  Background:
    Given I create account
    And I am logged into the track exercise page

    # a currently fulfilled scenario
  Scenario: Users can see their completed workout duration and type in their weekly journal
    Given I have entered a completed workout
    When I go to the "Weekly Journal" page
    Then I can see the workout type and duration in the journal

#    # our unfullfilled scenario
#  Scenario: Users can see a daily breakdown of their completed workouts in their weekly journal
#    Given I have entered a completed workout
#    When I go to the "Weekly Journal" page
#    Then I can see a daily breakdown of my workout week in the journal
#    And I can see the full details of each workout
