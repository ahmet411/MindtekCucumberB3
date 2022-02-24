@regression @smoke @MB3-101 @ui
Feature: Validating calculate functionality in TestSheep

  Background: Repeated steps
    Given user navigates to TestSheep application

  Scenario Outline: Validating calculate functionality with valid inputs
    When user enters "<Number1>" as first number
    And user enters "<Number2>" as second number
    And user selects "<Operation>" operator
    And user clicks on Calculate button
    Then user validates output is <Answer>
    Examples:
      | Number1 | Number2 | Operation   | Answer |
      | 6       | 2       | Add         | 8      |
      | 10      | 5       | Subtract    | 5      |
      | 2       | 3       | Multiply    | 6      |
      | 4       | 1       | Divide      | 4      |
      | 1       | 2       | Concatenate | 12     |

  Scenario Outline: Validating calculate functionality with invalid inputs
    When user enters "<Number1>" as first number
    And user enters "<Number2>" as second number
    And user selects "<Operation>" operator
    And user clicks on Calculate button
    Then user validates "<ErrorMessage>" error message
    Examples:
      | Number1 | Number2 | Operation | ErrorMessage             |
      | abc     | abc     | Add       | Number 1 is not a number |
      | 5       | abc     | Add       | Number 2 is not a number |
      | 5       | 0       | Divide    | Divide by zero error!    |


