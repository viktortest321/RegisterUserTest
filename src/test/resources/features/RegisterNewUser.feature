Feature: User Registration

  As a new supporter
  I want to register an account
  So that I can access the system

  Background:
    Given I open the registration page

  Scenario: Successful user registration
    When I enter a valid date of birth
    When I enter a valid first name
    And I enter a valid last name
    And I enter a unique email
    And I enter matching passwords
    And I accept the terms
    And I submit the form
    Then I should see a success message

  Scenario: Missing last name
    When I enter a valid date of birth
    When I enter a valid first name
    And I leave the last name empty
    And I enter a unique email
    And I enter matching passwords
    And I accept the terms
    Then I should see the error message "Last Name is required"

  Scenario: Passwords do not match
    When I enter a valid date of birth
    When I enter a valid first name
    And I enter a valid last name
    And I enter a unique email
    And I enter non-matching passwords
    And I accept the terms
    Then I should see the error message "Password did not match"

  Scenario: Terms not accepted
    When I enter a valid date of birth
    When I enter a valid first name
    And I enter a valid last name
    And I enter a unique email
    And I enter matching passwords
    And I do not accept the terms
    And I submit the form
    Then I should see the error message "You must confirm that you have read and accepted our Terms and Conditions"
