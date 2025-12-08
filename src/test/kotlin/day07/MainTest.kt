package day07

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MainTest {
    @Nested
    inner class GetTotalNumberOfSplitsTest {
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
            val numberOfSplits = getTotalNumberOfSplits(input)
            numberOfSplits shouldBe 21
        }
    }

    @Nested
    inner class GetTotalNumberOfTimelinesTest {
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
            val numberOfSplits = getTotalNumberOfTimelines(input)
            numberOfSplits shouldBe 40
        }

        @Test
        fun `should result in 2 timelines`() {
            val input = """
                .......S.......
                ...............
                .......^.......
                ...............
            """.trimIndent()
            val numberOfSplits = getTotalNumberOfTimelines(input)
            numberOfSplits shouldBe 2
        }
    }

    @Nested
    inner class GetBeamPositionsTest {
        @Test
        fun `get starting position`() {
            val line = ".......S......."
            val positions = getBeamPositions(line)
            positions shouldBe listOf(7)
        }

        @Test
        fun `get single beam position`() {
            val line = ".......|......."
            val positions = getBeamPositions(line)
            positions shouldBe listOf(7)
        }

        @Test
        fun `get multiple beam positions`() {
            val line = ".......|.|....."
            val positions = getBeamPositions(line)
            positions shouldBe listOf(7, 9)
        }
    }

    @Nested
    inner class GetSplitPositionsTest {
        @Test
        fun `get single split position`() {
            val line = ".......^......."
            val positions = getSplitPositions(line)
            positions shouldBe listOf(7)
        }

        @Test
        fun `get multiple split positions`() {
            val line = ".......^.^....."
            val positions = getSplitPositions(line)
            positions shouldBe listOf(7, 9)
        }
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

        @Test
        fun `draw beam with a splitter`() {
            val line = ".......^......."
            val newBeam = drawBeam(line, listOf(7))
            newBeam shouldBe "......|^|......"
        }

        @Test
        fun `draw 2 beam with 2 splitters next to each other`() {
            val line = ".......^.^....."
            val newBeam = drawBeam(line, listOf(7, 9))
            newBeam shouldBe "......|^|^|...."
        }
    }

    @Nested
    inner class GetPossibleTimelinesForLineTest {
        @Test
        fun `return 1 timeline when no splitters`() {
            val line = "..............."
            val newBeam = getPossibleTimelinesForLine(line, listOf(7))
            newBeam shouldBe listOf(".......|.......")
        }

        @Test
        fun `return 2 timelines when there is a splitter`() {
            val line = ".......^......."
            val newBeam = getPossibleTimelinesForLine(line, listOf(7))
            newBeam shouldBe listOf("......|^.......", ".......^|......")
        }
    }
}