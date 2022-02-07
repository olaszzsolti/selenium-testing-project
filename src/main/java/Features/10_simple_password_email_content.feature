@add_password_email_content
Feature: Testing sent email content

  Scenario Outline: Sending and validating e-mails for simple existing password sending
    Given Goto page add_password.php
    Then Input email set to <email>
    Then Checkbox external set to 1
    Then Input name set to <name>
    Then Input username set to <username>
    Then Input system_ set to <system_>
    Then Dropdown expire select <expire> option
    Then Dropdown mode select manual option
    Then Input password1 set to <password>
    Then Input password2 set to <password>
    Then Textarea description set to <description>
    Then Submit submit click
    Then Wait 500
    Then Has i18n element email_sent
    Then Validate email content <email>, <name>, <username>, <system_>, <expire>, <password>, <description>, admin

    Examples:
      | email                   | name   | username | system_                | expire | password | description    |
      | ferenc.sandor@p92.hu    | Ferenc | sandorf  | invalid                | 7d     | 123123   | something      |
      | jakab.gipsz@example.com | Jakab  | gipszj   | gitlab                 | 31d    | 123123   | no description |
      | test_user1@p92.hu       | test   | tuser1   | passwordmanager.p92.hu | 0      | 123123   |                |
