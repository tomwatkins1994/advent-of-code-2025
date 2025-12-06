package day05

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MainTest {
    @Nested
    inner class GetNumberFreshIngredientsTest {
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