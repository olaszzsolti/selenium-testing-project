@login_page
Feature: Check login page's fields and functions

  Scenario: Has all required input field
    Given Goto login page login.php
    Then Has input text username
    Then Has input password password
    Then Has submit submit
    Then Has element a with i18n login_with_token
    Then Has element label with i18n password_username
    Then Has element label with i18n password_section_password
    Then Check language selectors
    Then Has element with id site-name
    Then Has element with id footer-layout-area-1
    Then Has element with id footer-layout-area-2
    Then Logout

  Scenario Outline:
    Given Goto login page login.php
    Then Insert <username> to username field
    Then Insert <password> to password field
    Then Click on Submit
    Then Has element with class form-inline ui-state-error
    Then Logout
    Examples:
      | username | password |
      |          |          |
      |          | 34234    |
      | admin    |          |
      | user     |  qaws    |

  Scenario: Insert good username and password
    Given Goto login page login.php
    Then Insert admin to username field
    Then Insert 123123 to password field
    Then Click on Submit
    Then Check current url is equals to http://172.22.1.3/add_password.php url
    Then Logout

  Scenario: Click on link and redirect to login_request
    Given Goto login page login.php
    Then Check link with i18nData login_with_token href is equal to http://172.22.1.3/login_request.php
    Then Logout

