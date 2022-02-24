@api
Feature: Validating Books API calls

  Scenario: Validating end to end Order api calls
    Given User sends post order api call with data
    |bookId|5|
    |customerName|Kim Yan|
    When User sends get order api call
    Then User validates status code 200
    When User sends patch order api call
    |customerName|John Doe|
    Then User validates status code 204
    When User sends get order api call
    Then User validates status code 200
    When User sends delete order api call
    Then User validates status code 204
    And User sends get order api call
    Then User validates status code 404

