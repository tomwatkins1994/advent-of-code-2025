package day01

import io.kotest.matchers.shouldBe
import org.example.Day01.getNumZeroes
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class OOPRefactorTest {
    @Nested
    inner class GetCombinationTest {
        @Test
        fun `acceptance test against known input and output`() {
            val safe = Safe(50)
            val rotations = listOf("L68", "L30", "R48", "L5", "R60", "L55", "L1", "L99", "R14", "L82")
            rotations.forEach { safe.turnDial(it) }
            val combination = safe.getCombination()
            combination shouldBe 6
        }
    }

    @Nested
    inner class TurnDialTest {
        @Test
        fun `handles left movement going below 0`() {
            val safe = Safe(0)
            safe.turnDial("L10")
            safe.currentPosition shouldBe 90
            //Pair(0, 90)
        }

        @Test
        fun `gets right movement going over 99`() {
            val safe = Safe(99)
            safe.turnDial("R10")
            safe.currentPosition shouldBe 9
            val result = getNumZeroes(99, "R10")
//            result shouldBe Pair(1, 9)
        }

        @Test
        fun `handles left movement not going above or below 0`() {
            val safe = Safe(50)
            safe.turnDial("L1")
            safe.currentPosition shouldBe 49
        }

        @Test
        fun `handles right movement not going above or below 0`() {
            val safe = Safe(50)
            safe.turnDial("R1")
            safe.currentPosition shouldBe 51
        }

        @Test
        fun `handles 3 digit left movement`() {
            val safe = Safe(50)
            safe.turnDial("L201")
            safe.currentPosition shouldBe 49
//            result shouldBe Pair(2, 49)
        }

        @Test
        fun `handles 3 digit right movement`() {
            val safe = Safe(50)
            safe.turnDial("R201")
            safe.currentPosition shouldBe 51
//            result shouldBe Pair(2, 51)
        }

        @Test
        fun `handles a complete turn from 0`() {
            val safe = Safe(0)
            safe.turnDial("R100")
            safe.currentPosition shouldBe 0
//            result shouldBe Pair(1, 0)
        }

        @Test
        fun `handles landing on 0 with less that 100 clicks left`() {
            val safe = Safe(1)
            safe.turnDial("L1")
            safe.currentPosition shouldBe 0
//            result shouldBe Pair(1, 0)
        }

        @Test
        fun `handles landing on 0 with less that 100 clicks right`() {
            val safe = Safe(99)
            safe.turnDial("R1")
            safe.currentPosition shouldBe 0
//            result shouldBe Pair(1, 0)
        }
    }
}