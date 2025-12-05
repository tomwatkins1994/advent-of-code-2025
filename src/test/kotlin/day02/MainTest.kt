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
}