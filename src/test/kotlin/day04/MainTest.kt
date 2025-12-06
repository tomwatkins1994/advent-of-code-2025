package day04

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MainTest {
    @Nested
    inner class GetAccessibleRollsTest {
        @Test
        fun `acceptance test based on known input and output`() {
            val rows = listOf(
                "..@@.@@@@.",
                "@@@.@.@.@@",
                "@@@@@.@.@@",
                "@.@@@@..@.",
                "@@.@@@@.@@",
                ".@@@@@@@.@",
                ".@.@.@.@@@",
                "@.@@@.@@@@",
                ".@@@@@@@@.",
                "@.@.@@@.@."
            )
            val accessibleRolls = getAccessibleRolls(rows)
            accessibleRolls shouldBe 13
        }
    }
}