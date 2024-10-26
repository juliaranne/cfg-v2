Feature: Login functionality

  Background:
    Given a web connection is established

  Scenario: Pre-existing user can log in
    Given I have an account
    And I navigate to the login page
    When I login with these details
    Then I am logged in

  Scenario: Non-existent user cannot log in
    Given I make up some new credentials that are not yet registered
    When I login with these details
    Then I see "Failed to login"

  Scenario: Non-existent user can sign up and then login
    Given I create account
    Then I am logged in