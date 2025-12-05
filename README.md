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
  going for `Long` over `Int` from the get-go was a surprising oversight
* **Part 2** - Tricky to get the logic adjusted but existing tests made it easy to build upon.
    * I wasn't too happy having a loop within a loop for time complexity reasons but not sure on a great
      alternative. I did manage to conditionally find a way to skip the inner loop though if I knew the length would not
      be divisible evenly by the chunk size
    * I figured there was an optimisation to reduce the outer loop iterations from the IDs length to maybe half the
      length, but I
      resisted this until I had fully passing tests
    * After getting the tests all passing and the correct answer I managed to simplify the inner loop significantly - a
      strong example of why TDD is really useful here - the R/G refactor cycle made this really easy
    * In the end, once the inner loop had been simplified it became clear that a `for` loop actually was a clearer way
      to go than a `while` loop

## Day 03

* **Part 1** - Probably the easiest 1 yet - was tempted to try and refactor some of the repeated code but seemed
  premature before seeing pt 2
* **Part 2** - Definitely the easiest part 2 so far
    * My instinct to avoid the refactor was good, I am not sure I would have ended up at the same place I ended up at by
      the end
    * Wasn't a huge amount of obvious refactoring after getting the answer due to the simplicity of the solution
