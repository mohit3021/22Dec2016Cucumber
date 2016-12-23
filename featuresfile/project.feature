Feature: Test Project Composite API

@smoke
Scenario: Test createProject with /api/projects Request.
Given I have POST method with request createProject and JWT token with input body parameters mentioned in Yml file
Then Validate Status code for createProject
And Validate Response Body for createProject


    
