package day01

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class UtilsTest {
    @Nested
    inner class GetTurnsTest {
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
}