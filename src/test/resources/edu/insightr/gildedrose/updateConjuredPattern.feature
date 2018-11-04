Feature: Conjured Item with factory & strategy Pattern
  # a conjured item quality decreases twice the normal rate

  Scenario: conjured item update
    Given I have a new inventory
    Then the quality of the conjured item is 6
    When I update the inventory using factory and strategy Pattern
    Then the quality of the conjured item is 4