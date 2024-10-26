Feature: Login functionality

  Background:
    Given a web connection is established

  Scenario: Pre-existing user can log in
    Given I have an account
    And I navigate to the login page
    When I login
    Then I am logged in
#
#  Scenario: Non-existent user cannot log in

#  Scenario: Non-existent user can sign up and then login
#    Given I create account
#    Then I am logged in