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
            val freshIngredients = getFreshIngredients(input)
            freshIngredients.size shouldBe 3
        }
    }
}