@api @hrapis
Feature: Validating locations api calls

  Scenario Outline: Validating get location call
    Given User sends get location api call with <locationId> locationId
    Then User validates 200 status code
    And User validates response body with data
    |locationCountry | <locationCountry>|
    |locationCity | <locationCity> |
    Examples:
    | locationId | locationCountry | locationCity |
    | 1000       |IT               |Roma          |
    |1200        |JP                |Tokyo         |
    |1400        |US               |Southlake     |


#    Scenario: Validating location api calls
#      Given User post location api call with data
#      |locationCity|Chicago|
#      |locationCountry|US  |
#      |locationState  |Illinois|
##      |locationId     | generate |
#      Then User validates 201 status code
#      When User sends get location api call with created locationId
#      Then User validates 200 status code
#      When User sends delete location api call with created locationId
#      Then User validates 204 status code
#      When User sends get location api call with created locationId
#      Then User validates 404 status code
