# Advent of Code 2025

## Day 01

* **Part 1** - Simple enough
* **Part 2** - The change to the requirements in lead to me contemplating the functional approach I had in part 1.
    * The edge case where the dial starting on 0 before rotating caught me out more than I care to admit
    * The `getNumZeroes()` function returning both the number of zeroes but also the new position felt a bit dirty but
      worked ok
    * I decided to do an OOP Refactor once I had the correct answer to see if the code felt nicer. I do think it did as
      I could keep track of the current position and number of zeroes in the state for the `Safe` class
    * I also think that because I no longer had to test the number of zeroes being returned (I am just telling my class
      to turn the dial now) it felt less like I was testing implementation detail for the combination.

## Day 02

* **Part 1** - Simple enough - started with a functional approach, hoping this wouldn't bite me like it did day 1. Not
  going for `Long` over `Int` from the get go was a surprising oversight