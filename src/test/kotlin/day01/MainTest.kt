package day01

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.example.Day01.getMovement
import org.example.Day01.getNewPosition
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MainTest {
    @Nested
    inner class GetMovementTest {
        @Test
        fun `gets left movement`() {
            val result = getMovement("L1")
            result shouldBe Pair("L", 1)
        }

        @Test
        fun `gets right movement`() {
            val result = getMovement("R1")
            result shouldBe Pair("R", 1)
        }

        @Test
        fun `handles 2 digits`() {
            val result = getMovement("R12")
            result shouldBe Pair("R", 12)
        }

        @Test
        fun `should error if invalid direction`() {
            val exception = shouldThrow<IllegalArgumentException> {
                getMovement("U12")
            }
            exception.message shouldBe "Invalid direction"
        }

        @Test
        fun `should error if invalid turns`() {
            val exception = shouldThrow<IllegalArgumentException> {
                getMovement("L")
            }
            exception.message shouldBe "Invalid turns"
        }
    }

    @Nested
    inner class GetNewPositionTest {
        @Test
        fun `handles left movement going below 0`() {
            val result = getNewPosition(0, Pair("L", 1))
            result shouldBe 99
        }

        @Test
        fun `gets right movement going over 100`() {
            val result = getNewPosition(99, Pair("R", 1))
            result shouldBe 0
        }

        @Test
        fun `handles left movement not going above or below 0`() {
            val result = getNewPosition(50, Pair("L", 1))
            result shouldBe 49
        }

        @Test
        fun `handles right movement not going above or below 0`() {
            val result = getNewPosition(50, Pair("R", 1))
            result shouldBe 51
        }
    }
}