Feature: Sulfuras Item
  # a sulfuras item quality does not change

  Scenario: sulfuras item update
    Given I have a new inventory
    Then the quality of the sulfuras item is 80
    When I update the inventory
    Then the quality of the sulfuras item is 80