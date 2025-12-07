package day07

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MainTest {
    @Nested
    inner class GetNumberOfSplitsTest {
        @Test
        fun `acceptance test based on known input and output`() {
            val input = """
                .......S.......
                ...............
                .......^.......
                ...............
                ......^.^......
                ...............
                .....^.^.^.....
                ...............
                ....^.^...^....
                ...............
                ...^.^...^.^...
                ...............
                ..^...^.....^..
                ...............
                .^.^.^.^.^...^.
                ...............
            """.trimIndent()
            val numberOfSplits = getNumberOfSplits(input)
            numberOfSplits shouldBe 21
        }
    }

    @Test
    fun `get starting position`() {
        val input = """
            .......S.......
        """.trimIndent()
        val startingPosition = getStartingPosition(input)
        startingPosition shouldBe 7
    }

    @Nested
    inner class DrawBeamTest {
        @Test
        fun `draw beam with no splitters`() {
            val line = "..............."
            val newBeam = drawBeam(line, listOf(7))
            newBeam shouldBe ".......|......."
        }

        @Test
        fun `draw 2 beams with no splitters`() {
            val line = "..............."
            val newBeam = drawBeam(line, listOf(3, 9))
            newBeam shouldBe "...|.....|....."
        }
    }
}