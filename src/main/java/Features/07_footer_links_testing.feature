@footerLinks
Feature: logo buttons and footer links testing

  Scenario: Login as Admin
    Given Logged as admin

  Scenario Outline: Test Links in footer
    Then Check link with title <title> href is equal to <url>
    Then Wait 200

    Examples:
      | title                 | url                                       |
      | P92                   | https://www.p92.hu/site/hu                |
      | "P92 IT Solutions"    | https://www.p92.hu/site/hu                |
      | Kezdőlap              | https://www.p92.hu/site/hu                |
      | "Mobil Alkalmazások"  | https://p92.hu/site/hu/mobileoverview?id=3050747d-2a9c-4eb2-b56b-ff5e4201a63c |
      | "Web Alkalmazások"    | https://p92.hu/site/hu/websiteoverview?id=47f40c4b-97cc-43bf-b0f6-02225800be11 |
      | Sikertörténetek       | https://www.p92.hu/site/hu/successstories |
      | Csapat                | https://www.p92.hu/site/hu/team           |
      | Blog                  | https://www.p92.hu/site/hu/blog           |
      | Rólunk                | https://p92.hu/site/hu/aboutdetails?id=ee11a2e2-9a37-46d1-b914-78634f4b5eeb |
      | Karrier               | https://www.p92.hu/site/hu/careers        |
      | Kapcsolat             | https://www.p92.hu/site/hu/contact        |
