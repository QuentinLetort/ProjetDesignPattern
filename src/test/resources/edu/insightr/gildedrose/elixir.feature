Feature: elixir Item
  # a vest item quality decreases by one

  Scenario: elixir item update
    Given I have a new inventory
    Then the quality of the elixir item is 7
    When I update the inventory
    Then the quality of the elixir item is 6