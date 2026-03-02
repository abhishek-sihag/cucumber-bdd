Feature: Fill form and complete all actions on page

  Scenario Outline: User should be able to fill form and submit

    Given User should navigate to Automation Practice Page
    When Enter name "<name>" , email "<email>" and phone :"<phone>"
    And Select dateFirst "<date1>" , dateSecond "<date2>", dateThird start "<date3s>" and dateThird end "<date3e>"
    And Select gender "<gender>", day "<day>" and country "<country>"
    Then Verify above entered data is valid

    Examples:
      | name   | email               | phone        | date1      | date2    | date3s      | date3e      | gender | day      | country     |
      | Adam   | adam@gmail.com      | +198874674   | 2/11/2009  | 20092019 | 19-09-2024  | 19-09-2024  | male   | Friday   | India       |
      | Adam   | adam2@gmail.com     | +199874674   | 2/11/2009  | 20092019 | 19-09-2024  | 19-09-2024  | male   | Friday   | India       |
      | John   | john@test.com       | +14155550123 | 5/12/2010  | 15082020 | 01-01-2025  | 05-01-2025  | male   | Monday   | United States |
      | Emma   | emma@mail.com       | +447911123456| 10/03/2012 | 01012021 | 10-10-2024  | 15-10-2024  | female | Tuesday  | United Kingdom |
      | Raj    | raj.kumar@mail.in   | +919876543210| 22/07/2008 | 31122018 | 05-05-2024  | 10-05-2024  | male   | Wednesday| India       |
      | Sophia | sophia@test.org     | +61298765432 | 14/02/2015 | 25092022 | 20-11-2024  | 25-11-2024  | female | Thursday | Australia   |
      | Liam   | liam123@gmail.com   | +353871234567| 30/09/2011 | 04072019 | 01-12-2024  | 03-12-2024  | male   | Friday   | France     |
      | Olivia | olivia@mail.ca      | +16045551234 | 18/06/2013 | 12032021 | 15-08-2024  | 20-08-2024  | female | Saturday | Brazil      |
      | Noah   | noah@test.net       | +4915112345678| 09/01/2014| 23042020 | 05-09-2024  | 07-09-2024  | male   | Sunday   | Germany     |
      | Ava    | ava.lee@mail.com    | +819012345678| 11/11/2016 | 01062023 | 12-12-2024  | 18-12-2024  | female | Monday   | Japan       |