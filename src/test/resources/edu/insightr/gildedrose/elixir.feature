Feature: vest Item
  # a vest item quality decreases by one

  Scenario: vest item update
    Given I have a new inventory
    Then the quality of the vest item is 7
    When I update the inventory
    Then the quality of the vest item is 6