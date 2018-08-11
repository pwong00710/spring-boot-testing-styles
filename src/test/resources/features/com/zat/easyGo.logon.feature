@Ignore @logon
Feature: easygo logon functionalities

  Scenario: Success to logon to Club SIM
    Given logon to system with following valid information:
      |username |password  |lang    |platform |
      |multi    |csd9      |eng     |android  |
      |multi    |csd9      |chi     |android  |
      |multi    |csd9      |eng     |ios      |
      |multi    |csd9      |chi     |ios      |
    When logon to system with above details
    Then logon success

  Scenario: Fail to logon to Club SIM
    Given logon to system with following invalid information:
      |username |password  |lang    |platform |
      |multi    |csd8      |eng     |android  |
      |single   |csd9      |chi     |android  |
    When logon to system with above details
    Then logon fail

  Scenario: Validate a valid logon token
    Given logon to system with following valid information:
      |username |password  |lang    |platform |
      |multi    |csd9      |eng     |android  |
    When logon to system with above details
    And logon success
    Then logon token is valid
  
#  Scenario Outline: Find super hero by hero names
#    Given input hero name '<heroName>'
#    When do search
#    Then search results are shown for '<realName>'
#
#    Examples:
#      | heroName         | realName         |
#      | Hulk             | Robert Bruce     |
#      | Doctor Strange   | Stephen Vincent  |
