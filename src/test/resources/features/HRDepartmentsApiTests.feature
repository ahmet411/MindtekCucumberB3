@api @hrdepartmentsapis
Feature: Validating Departments api calls

  Scenario: Validating department created with api post call and shown in UI
    Given User creates department with departments post call having random data
    Then User validates status code is 201
    When User gets created department
    Then User validates status code is 200
    When user navigates to login page
    When user logs in to HRapp
    Then User validates created department in Departments dropdown
    When user deletes created department
    Then User validates status code is 204
    When User gets created department
    Then User validates status code is 404
