package day03

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MainTest {
    @Nested
    inner class GetTotalJoltage {
        @Test
        fun `acceptance test total joltage from known input and output`() {
            val banks = listOf(
                "987654321111111",
                "811111111111119",
                "234234234234278",
                "818181911112111"
            )
            val totalJoltage = getTotalJoltage(banks)
            totalJoltage shouldBe 3121910778619
        }
    }

    @Nested
    inner class GetMaximumJoltageTest {
        @Test
        fun `a bank with all values the same works`() {
            val maxJoltage = getMaximumJoltage("111111111111111")
            maxJoltage shouldBe 111111111111
        }

        @Test
        fun `a bank with 2 distinct values works`() {
            val maxJoltage = getMaximumJoltage("811111111111111")
            maxJoltage shouldBe 811111111111
        }

        @Test
        fun `a bank with 3 distinct values works`() {
            val maxJoltage = getMaximumJoltage("891111111111111")
            maxJoltage shouldBe 911111111111
        }

        @Test
        fun `a bank where the highest number is the last one works`() {
            val maxJoltage = getMaximumJoltage("811111111111119")
            maxJoltage shouldBe 811111111119
        }
    }
}