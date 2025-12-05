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
            ids.first shouldBe 111
            ids.second shouldBe 222
        }
    }

    @Nested
    inner class ValidateIdTest {
        @Test
        fun `IDs with an odd number should always be valid`() {
            val result = validateId(111)
            result shouldBe true
        }

        @Test
        fun `2 digit non repeated value should be valid`() {
            val result = validateId(12)
            result shouldBe true
        }

        @Test
        fun `2 digit repeated value should be invalid`() {
            val result = validateId(11)
            result shouldBe false
        }

        @Test
        fun `4 digit repeated value should be invalid`() {
            val result = validateId(1212)
            result shouldBe false
        }

        @Test
        fun `4 digit non-repeated value should be invalid`() {
            val result = validateId(1213)
            result shouldBe true
        }
    }

    @Nested
    inner class SumOfInvalidIdsTest {
        @Test
        fun `acceptance test given known inputs and outputs`() {
            val idRanges =
                "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124"
            val result = sumOfInvalidIdRanges(idRanges)
            result shouldBe 4174379265
        }
    }
}