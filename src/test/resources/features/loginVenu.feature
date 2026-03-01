Feature: Validate login functionality

  @smoke
  Scenario Outline: Verify user can login with valid credentials "<Scenario>"
    Given user opens the website
    Then verify user is on login page
    When user enters login credentials
    Then verify user is on home page

    Examples:
      | Scenario  |
      | Scenario1 |
      | Scenario2 |