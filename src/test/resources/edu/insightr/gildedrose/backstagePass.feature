Feature: backstagePass Item
  # a backstage pass item quality decreases by one

  Scenario: backstagePass item update
    Given I have a new inventory
    Then the quality of the backstage pass item is 20
    When I update the inventory
    Then the quality of the backstage pass item is 21