Feature: Conjured Item
  # a conjured item quality decreases twice the normal rate

  Scenario: conjured item update using visitor pattern
    Given I have a new inventory
    Then the quality of the conjured item is 6
    When I update the inventory with visitor
    Then the quality of the conjured item is 4