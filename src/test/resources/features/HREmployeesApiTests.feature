@api @hremployeesapis
Feature: Validating employees api calls

  Scenario Outline: Validating DB, UI, and API data matching for employees
    Given user gets employee from database with employeeId <employeeId>
    When user navigates to login page
    And user logs in to HRapp
    And user searches for employee with <employeeId> employeeId
    Then user validates UI data matches with DB data
    When user get employee with get employee api call with <employeeId> employeeId
    Then user validates 200 statusCode
    And user validates API data matches with DB data
    Examples:
    |employeeId|
    |120       |
    |123       |
    |206       |

    @MB3-55
    Scenario: Creating employee with post api call
      Given user creates employee with post api call with data
      |firstName|Patel|
      |lastName |Harsh|
      |departmentName|Administration|
      Then user validates 201 statusCode
      When user navigates to login page
      And user logs in to HRapp
      And user searches for created employee
      Then user validates employee is created in UI with data
        |firstName|Patel|
        |lastName |Harsh|
        |departmentName|Administration|

    @MB3-56
    Scenario: Validating number of employees in specific department
      Given user get number of employees in "IT" department from DB
      When user navigates to login page
      And user logs in to HRapp
      And user selects "IT" department from dropdown
      Then user validates UI number of employees matches with DB number
      When user gets employees from "IT" department with api call
      Then user validates number of employees in API matches with DB number
