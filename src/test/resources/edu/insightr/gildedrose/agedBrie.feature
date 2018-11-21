Feature: agedBrie Item
  # a agedBrie item quality upgrades by 1

  Scenario: agedBrie item update
    Given I have a new inventory
    Then the quality of the aged brie item is 0
    When I update the inventory
    Then the quality of the aged brie item is 1