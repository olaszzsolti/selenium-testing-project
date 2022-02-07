@add_2ch_password_password_quality
Feature: Testing password generator functional behavior, checking behavior of add simple password page

  Scenario: Easy generated password mode
    Given Goto page add_2ch_password.php
    Then Dropdown mode select easy option
    Then Wait 600
    Then Input password_length has value 16
    Then Input password_numbers has value 0
    Then Input password_special has value 0

  Scenario Outline: Testing generated easy password
    Then Test password specifications <length>, <digits>, <specials>, <regen>
    Then Wait 100
  Examples:
      | length | digits | specials | regen |
      |    16  |   0    |      0   |   10  |
      |    24  |   4    |      0   |   10  |
      |    32  |   8    |      0   |   10  |
      |    16  |   4    |      0   |   10  |
      |    16  |   16   |      0   |   10  |
      |    16  |   0    |      10  |   10  |
      |    16  |   0    |      10  |   10  |
      |    26  |   10   |      10  |   10  |

  Scenario: medium generated password mode
    Then Dropdown mode select medium option
    Then Wait 600

  Scenario Outline: Testing generated medium password
    Then Test password specifications <length>, <digits>, <specials>, <regen>
    Then Wait 200
    Examples:
      | length | digits | specials | regen |
      |    16  |   0    |      2   |   10  |
      |    24  |   4    |      0   |   10  |
      |    32  |   8    |      10  |   10  |
      |    16  |   4    |      4   |   10  |
      |    16  |   16   |      0   |   10  |
      |    16  |   0    |      10  |   10  |
      |    16  |   0    |      10  |   10  |
      |    26  |   10   |      10  |   10  |

  Scenario: hard generated password mode
    Then Dropdown mode select hard option
    Then Wait 600

  Scenario Outline: Testing generated hard password
    Then Test hard password specifications <length>, <regen>
    Then Wait 200
    Examples:
      | length | regen |
      |    16  |  10   |
      |    24  |  10   |
      |    32  |  10   |
      |    16  |  10   |
      |    16  |  10   |
      |    16  |  10   |
      |    16  |  10   |
      |    26  |  10   |