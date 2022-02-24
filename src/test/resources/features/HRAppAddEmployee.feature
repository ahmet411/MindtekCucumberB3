@regression @smoke @jdbc
Feature: Validate create employee functionality
  @jdbc_TC01
  Scenario: Validate created employee persisted in database
    Given user navigates to login page
    When user logs in to HRapp
    And creates new employee
    Then user validates new employee is created in database
    #And user cleans created data

