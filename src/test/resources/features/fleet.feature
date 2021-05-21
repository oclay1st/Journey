Feature: Fleet functionalities

    Scenario: Create new fleet
        Given Jhon is an admin user
        When Jhon creates a new fleet
        Then the fleet should be created

    Scenario: Retrieve a fleet
        Given Jhon is an admin user
        And a fleet with exists
        When Jhon wants to see the fleet
        Then Jhon should get the fleet

    Scenario: Retrieve a list of fleets
        Given Jhon is an admin user
        And a list of fleet exists
        When Jhon wants to see the fleet
        Then the fleet should be listed