@createIamToken
Feature: Test Token service API

@smoke
Scenario Outline: Test api/token/login createIamToken
      Given I have POST method with request createIamToken and input Body "<Password>" and "<Username>"
      Then Validate Status code
      And Validate Response Body
      And Save JWT token in a variable
      
      Examples:
      |Password      |Username|
      |Collaboration!|coolmohit3021@gmail.com|
      
@Regression
Scenario Outline: Test api/token/login createIamToken
      Given I have POST method with request createIamToken and input Body "<Password>" and "<Username>"
      Then Validate Status code
      And Validate Response Body
      And Save JWT token in a variable
      
      Examples:
      |Password      |Username|
      |Collaboration!1|coolmohit3021@gmail.com|
