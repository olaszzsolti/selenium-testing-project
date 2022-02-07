@add_password_invalid_errors
Feature: Testing workflow for invalid inputs

  Scenario Outline: Testing invalid input combinations for proper indentification
    Given Goto page add_password.php
    Then Input email set to <email>
    Then Checkbox external set to 1
    Then Input name set to <name>
    Then Input username set to <username>
    Then Input system_ set to <system_>
    Then Dropdown expire select <expire> option
    Then Dropdown mode select manual option
    Then Input password1 set to <password1>
    Then Input password2 set to <password2>
    Then Textarea description set to <description>
    Then Submit submit click
    Then Wait 500
    Then Has i18n element <i18n_error>
    Examples:
      | email                | name   | username | system_ | expire | password1 | password2 | description | i18n_error                       |
      | invalid              | Ferenc | sandorf  | invalid | 7d     | 123123    | 123123    | something   | password_error_invalid_email     |
      | invalid@invalid      | Ferenc | sandorf  | invalid | 7d     | 123123    | 123123    | something   | password_error_invalid_email     |
      | ferenc.sandor@p92.hu | Ferenc | sandorf  | invalid | 7d     |           |           | something   | password_error_no_password       |
      | ferenc.sandor@p92.hu | Ferenc | sandorf  | invalid | 7d     | 123123    | 123124    | something   | password_error_password_mismatch |
