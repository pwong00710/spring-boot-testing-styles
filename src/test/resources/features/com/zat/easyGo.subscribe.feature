@Ignore @logon @subscribe
Feature: easygo subscribe functionalities

  Background: Logon to system
    Given logon to system with following valid information:
      |username |password  |lang    |platform |
      |multi    |csd9      |eng     |android  |
    When logon to system with above details
    Then logon success
    
  Scenario Outline: Subscribe data service
    Given logon token is valid
    And query remaining usage of '<mrt>'
    When subscribe <data>G local data plan for '<mrt>'
    Then data plan have <data>G more for '<mrt>'

    Examples:
      |mrt          | data |
      |52900195     | 1    |      
      |52900195     | 2    |
#      |52900195     | 5    |
#      |52900195     | 6    |
#      |52900195     | 10   |
#      |52900195     | 11   |
#      |52900195     | 12   |
#      |52900195     | 20   | 