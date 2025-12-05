package day01

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.example.Day01.getCombination
import org.example.Day01.getNumZeroes
import org.example.Day01.getTurns
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MainTest {
    @Nested
    inner class GetMovementTest {
        @Test
        fun `gets left turns`() {
            val result = getTurns("L1")
            result shouldBe -1
        }

        @Test
        fun `gets right turns`() {
            val result = getTurns("R1")
            result shouldBe 1
        }

        @Test
        fun `handles 2 digits`() {
            val result = getTurns("R12")
            result shouldBe 12
        }

        @Test
        fun `handles 3 digits`() {
            val result = getTurns("R123")
            result shouldBe 123
        }

        @Test
        fun `should error if invalid direction`() {
            val exception = shouldThrow<IllegalArgumentException> {
                getTurns("U12")
            }
            exception.message shouldBe "Invalid direction"
        }

        @Test
        fun `should error if invalid turns`() {
            val exception = shouldThrow<IllegalArgumentException> {
                getTurns("L")
            }
            exception.message shouldBe "Invalid turns"
        }
    }

    @Nested
    inner class GetNumZeroesTest {
        @Test
        fun `handles left movement going below 0`() {
            val result = getNumZeroes(0, "L10")
            result shouldBe Pair(0, 90)
        }

        @Test
        fun `gets right movement going over 99`() {
            val result = getNumZeroes(99, "R10")
            result shouldBe Pair(1, 9)
        }

        @Test
        fun `handles left movement not going above or below 0`() {
            val result = getNumZeroes(50, "L1")
            result shouldBe Pair(0, 49)
        }

        @Test
        fun `handles right movement not going above or below 0`() {
            val result = getNumZeroes(50, "R1")
            result shouldBe Pair(0, 51)
        }

        @Test
        fun `handles 3 digit left movement`() {
            val result = getNumZeroes(50, "L201")
            result shouldBe Pair(2, 49)
        }

        @Test
        fun `handles 3 digit right movement`() {
            val result = getNumZeroes(50, "R201")
            result shouldBe Pair(2, 51)
        }

        @Test
        fun `handles a complete turn from 0`() {
            val result = getNumZeroes(0, "R100")
            result shouldBe Pair(1, 0)
        }

        @Test
        fun `handles landing on 0 with less that 100 clicks left`() {
            val result = getNumZeroes(1, "L1")
            result shouldBe Pair(1, 0)
        }

        @Test
        fun `handles landing on 0 with less that 100 clicks right`() {
            val result = getNumZeroes(99, "R1")
            result shouldBe Pair(1, 0)
        }
    }

    @Nested
    inner class GetCombinationTest {
        @Test
        fun `acceptance test against known input and output`() {
            val rotations = listOf("L68", "L30", "R48", "L5", "R60", "L55", "L1", "L99", "R14", "L82")
            val combination = getCombination(50, rotations)
            combination shouldBe 6
        }
    }
}