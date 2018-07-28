Feature: Super Hero functionalities

  Scenario: Create new super hero
    Given super hero with the following details are created:
      |firstName |lastName  |heroName       |
      |Robert    |Bruce     |Hulk           |
      |Stephen   |Vincent   |Doctor Strange |
    When super hero are created with above details
    Then super hero with above details can be found

  Scenario Outline: Find super hero by hero names
    Given input hero name '<heroName>'
    When do search
    Then search results are shown for '<realName>'

    Examples:
      | heroName         | realName         |
      | Hulk             | Robert Bruce     |
      | Doctor Strange   | Stephen Vincent  |
