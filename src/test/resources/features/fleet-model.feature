Feature: Fleet model functionalities

    Scenario: Create new fleet model
        Given Jhon is an admin user
        When Jhon creates a new fleet model
        Then the fleet model should be created

    Scenario: Retrieve a fleet model
        Given Jhon is an admin user
        And a fleet model with name Mercedes exists
        When Jhon wants to see the fleet model Mercedes
        Then Jhon should get the fleet model Mercedes 

    Scenario: Retrieve a list offleet models
        Given Jhon is an admin user
        And a list of fleet models exists
        When Jhon wants to see the fleet models 
        Then the fleet models should be listed  