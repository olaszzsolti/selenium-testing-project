@add_2ch_password
Feature: 2ch_password form testing

  Scenario: Login as Admin
    Given Logged as admin
    Then Goto page add_2ch_password.php

  Scenario Outline: test all input element
    Then Has input <type> <name>
    Examples:
      | type       | name       |
      | checkbox   | external   |
      | text       | email      |
      | text       | name       |
      | text       | username   |
      | text       | system_    |
      | text       | encryptKey |
      | select     | expire     |
      | select     | mode       |
      | password   | password1  |
      | password   | password2  |

  Scenario Outline: test all labels element
    Then Has element <type> with i18n <name>
    Examples:
      | type  | name                          |
      | label | password_external_accessible  |
      | label | password_email                |
      | label | password_greet                |
      | label | password_username             |
      | label | password_system               |
      | label | password_expire               |
      | label | password_type                 |
      | label | password_password             |
      | label | password_comment              |
      | label | password_key                  |
      | h2    | password_section_password     |

  Scenario: Test other elements
    Then Has textarea description
    Then Has button getNewKey
    Then Has submit submit

    Then Dropdown expire has 31d selected
    Then Dropdown mode has manual selected
    Then Checkbox external set to 0
    When Checkbox external clicked
    Then Checkbox external set to 1

    Then Dropdown mode select easy option
    Then Has input number password_length
    Then Has input number password_numbers
    Then Has input number password_special
    Then Has element with id generated_password_password
    Then Has button getNewPassword

    Then Check language selectors
    Then Has element with id site-name
    Then Has element with id footer-layout-area-1
    Then Has element with id footer-layout-area-2

    Then Logout