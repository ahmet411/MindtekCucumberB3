@regression @UI @ONT-114
Feature: Validating Etsy application search and filter functionalities

  Background: Repeated first steps in each scenario
    Given user navigates to Etsy application
    When user searches for "carpet"
    And user applies price filter over 1000


# @Before method will run
  Scenario: Validating price range filter functionality for searched items
    Then user validates that item prices are over 1000
    # @After method will run
@smoke
  # @Before method will run
  Scenario: Validating search results
    Then user validates search result items contain keyword "carpet" and that item prices are over 1000
    # @After method will run

