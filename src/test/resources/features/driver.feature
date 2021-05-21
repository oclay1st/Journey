Feature: Driver functionalities

    Scenario: Create new driver
        Given Jhon is an admin user
        When Jhon creates a new driver
        Then the driver should be created