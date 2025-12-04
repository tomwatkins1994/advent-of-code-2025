package day01

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.example.Day01.getMovement
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
        fun `should error if invalid movement`() {
            val exception = shouldThrow<IllegalArgumentException> {
                getMovement("L")
            }
            exception.message shouldBe "Invalid movement"
        }
    }

}