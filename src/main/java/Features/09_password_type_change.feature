@CheckPasswordType
Feature: Checking if password type changed, the right elements shown in page
  Scenario: Manual password mode
    Given Logged as admin
    When Dropdown mode select manual option
    Then Wait 600
    Then Has input password password1
    Then Has input password password2

  Scenario: Easy generated password mode
    Then Dropdown mode select easy option
    Then Wait 600
    Then Input number named password_length is visible
    Then Input number named password_numbers is visible
    Then Input number named password_special is visible
    Then Element with id generated_password_password is visible

  Scenario: Medium generated password mode
    Then Dropdown mode select medium option
    Then Wait 600
    Then Input number named password_length is visible
    Then Input number named password_numbers is visible
    Then Input number named password_special is visible
    Then Element with id generated_password_password is visible

  Scenario: Hard generated password mode
    Then Dropdown mode select hard option
    Then Wait 600
    Then Input number named password_length is visible
    Then Input number named password_numbers is visible
    Then Input number named password_special is visible
    Then Element with id generated_password_password is visible

