@login_request_page
Feature: Check login request page's fields and functions

  Scenario: Has all required input field

    Given Goto login page login_request.php
    Then Has input text email
    Then Has submit submit
    Then Has element a with i18n login_with_username
    Then Has element label with i18n password_email
    Then Check language selectors
    Then Has element with id site-name
    Then Has element with id footer-layout-area-1
    Then Has element with id footer-layout-area-2
    Then Logout

  Scenario: inserting wrong email and submit
    Given Goto login page login_request.php
    Then Insert valami@valami.com to email field
    Then Click on Submit
    Then Has element with class form-inline ui-state-error
    Then Logout

  Scenario: inserting good email and submit
    Given Goto login page login_request.php
    Then Insert valami@p92.hu to email field
    Then Click on Submit
    Then Has i18n element email_sent
    Then Logout

  Scenario: Click on link and redirect
    Given Goto login page login_request.php
    Then Check link with i18nData login_with_username href is equal to http://172.22.1.3/login.php
    Then Logout
