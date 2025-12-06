package day05

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MainTest {
    @Nested
    inner class GetFreshAndAvailableIngredientsTest {
        @Test
        fun `acceptance test based on known input and output`() {
            val input = """
                3-5
                10-14
                16-20
                12-18

                1
                5
                8
                11
                17
                32
            """.trimIndent()
            val freshIngredients = getFreshAndAvailableIngredients(input)
            freshIngredients.size shouldBe 3
        }
    }

    @Nested
    inner class GetTotalFreshIngredients {
        @Test
        fun `acceptance test based on known input and output`() {
            val input = """
                3-5
                10-14
                16-20
                12-18

                1
                5
                8
                11
                17
                32
            """.trimIndent()
            val freshIngredients = getTotalFreshIngredients(input)
            freshIngredients shouldBe 14
        }
    }

    @Nested
    inner class RemoveIngredientRangeOverlaps {
        @Test
        fun `remove overlap on 2 ranges`() {
            val ranges = listOf(
                IngredientRange(3, 5),
                IngredientRange(4, 7),
            )
            val newRanges = removeIngredientRangeOverlaps(ranges)
            newRanges shouldBe listOf(
                IngredientRange(3, 3),
                IngredientRange(4, 7),
            )
        }

        @Test
        fun `remove overlap on 3 ranges`() {
            val ranges = listOf(
                IngredientRange(3, 6),
                IngredientRange(4, 7),
                IngredientRange(5, 8),
            )
            val newRanges = removeIngredientRangeOverlaps(ranges)
            newRanges shouldBe listOf(
                IngredientRange(3, 3),
                IngredientRange(4, 4),
                IngredientRange(5, 8),
            )
        }
    }

    @Nested
    inner class ParseInputTest {
        @Test
        fun `gets 1 range and 1 ingredient`() {
            val input = """
                3-5
                
                1
            """.trimIndent()
            val parsedInput = parseInput(input)
            parsedInput shouldBe Ingredients(fresh = listOf(IngredientRange(3, 5)), available = setOf(1))
        }

        @Test
        fun `gets multiple ranges and ingredients`() {
            val input = """
                3-5
                7-8
                
                1
                2
                4
            """.trimIndent()
            val parsedInput = parseInput(input)
            parsedInput shouldBe Ingredients(
                fresh = listOf(IngredientRange(3, 5), IngredientRange(7, 8)),
                available = setOf(1, 2, 4)
            )
        }
    }
}