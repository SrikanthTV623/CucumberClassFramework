Feature: Validate login functionality


  Scenario: Verify user can login with valid credentials Scenario1
    Given user opens the website
    Then verify user is on login page
    When user enters username "valid.username" and password "valid.password"
    Then verify user is on home page


     @smoke
  Scenario: Verify locked out user cannot get access to the application Scenario2
    Given user opens the website
    Then verify user is on login page
    When user enters username "locked.username" and password "valid.password"
    Then verify locked out user error message is displayed as below
      | Sorry, this user has been locked out. |

