@left_menu
Feature: Left menu content and functions testing

  Scenario: Login as Admin
    Given Logged as admin

    Scenario Outline: testing left menu content and functions
      Then Has menu with i18n <menu-item>
      Then Click on <menu-item> Link with data-i18n
      Then Check current url is equals to <url> url
      Then Wait 500
      
      Examples:
        | menu-item           | url                                     |
        | menu_simple_password| http://172.22.1.3/add_password.php      |
        | menu_2ch_password   | http://172.22.1.3/add_2ch_password.php  |
        | menu_help           | http://172.22.1.3/help.php              |
        | menu_logout         | http://172.22.1.3/logout.php            |

   




