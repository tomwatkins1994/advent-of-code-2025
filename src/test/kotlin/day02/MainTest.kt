package day02

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MainTest {
    @Nested
    inner class ParseIdRangeTest {
        @Test
        fun `get 2 numbers from input`() {
            val ids = parseIdRange("111-222")
            ids.first shouldBe "111"
            ids.second shouldBe "222"
        }
    }

    @Nested
    inner class ValidateIdTest {
        @Test
        fun `IDs with an odd number should always be valid`() {
            val result = validateId("111")
            result shouldBe true
        }

        @Test
        fun `2 digit non repeated value should be valid`() {
            val result = validateId("12")
            result shouldBe true
        }

        @Test
        fun `2 digit repeated value should be invalid`() {
            val result = validateId("11")
            result shouldBe false
        }

        @Test
        fun `4 digit repeated value should be invalid`() {
            val result = validateId("1212")
            result shouldBe false
        }

        @Test
        fun `4 digit non-repeated value should be invalid`() {
            val result = validateId("1213")
            result shouldBe true
        }
    }
}