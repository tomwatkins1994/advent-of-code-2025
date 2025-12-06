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
            accessibleRolls shouldBe 43
        }

        @Test
        fun `1 row should contain only accessible rolls`() {
            val rows = listOf(
                "..@@.@@@@."
            )
            val accessibleRolls = getAccessibleRolls(rows)
            accessibleRolls shouldBe 6
        }

        @Test
        fun `corners should always be accessible`() {
            val rows = listOf(
                "@........@",
                "..........",
                "@........@",
            )
            val accessibleRolls = getAccessibleRolls(rows)
            accessibleRolls shouldBe 4
        }

        @Test
        fun `2 rows should handle getting accessible rolls with only rows above or below`() {
            val rows = listOf(
                "..@@.@@@@.",
                "@@@.@@@.@@"
            )
            val accessibleRolls = getAccessibleRolls(rows)
            accessibleRolls shouldBe 14
        }

        @Test
        fun `3 rows should handle getting accessible rolls with only rows above or below`() {
            val rows = listOf(
                "..@@.@@@@.",
                "@@@.@@@.@@",
                "@.@.@.@.@@"
            )
            val accessibleRolls = getAccessibleRolls(rows)
            accessibleRolls shouldBe 20
        }
    }

    @Nested
    inner class MarkRollsRemovedTest {
        @Test
        fun `remove 1 roll`() {
            val rows = listOf(
                "..@@.@@@@."
            )
            val rollsToRemove = listOf(Position(0, 2))
            val newRows = markRollsRemoved(rows, rollsToRemove)
            newRows shouldBe listOf(
                "..x@.@@@@."
            )
        }

        @Test
        fun `remove multiple rolls from the same row`() {
            val rows = listOf(
                "..@@.@@@@."
            )
            val rollsToRemove = listOf(Position(0, 2), Position(0, 3))
            val newRows = markRollsRemoved(rows, rollsToRemove)
            newRows shouldBe listOf(
                "..xx.@@@@."
            )
        }

        @Test
        fun `remove 2 rolls`() {
            val rows = listOf(
                "..@@.@@@@.",
                "@@@.@@@.@@",
            )
            val rollsToRemove = listOf(Position(0, 2), Position(1, 5))
            val newRows = markRollsRemoved(rows, rollsToRemove)
            newRows shouldBe listOf(
                "..x@.@@@@.",
                "@@@.@x@.@@",
            )
        }
    }
}