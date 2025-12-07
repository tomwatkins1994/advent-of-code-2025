package day06

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MainTest {
    @Nested
    inner class GetAnswerTest {
        @Test
        fun `acceptance test based on known input and output`() {
            val input = """
                123 328  51 64 
                 45 64  387 23 
                  6 98  215 314
                *   +   *   +  
            """.trimIndent()
            val answer = getAnswer(input)
            answer shouldBe 4277556
        }
    }

    @Nested
    inner class ParseInputTest {
        @Test
        fun `should get operator from one column`() {
            val input = """
                123
                 45
                  6
                *  
            """.trimIndent()
            val problems = parseInput(input)
            problems.map { it.operator } shouldBe listOf("*")
        }

        @Test
        fun `should get numbers from one column`() {
            val input = """
                123
                 45
                  6
                *  
            """.trimIndent()
            val problems = parseInput(input)
            problems.map { it.numbers } shouldBe listOf(listOf(123, 45, 6))
        }

        @Test
        fun `should get operator from more than one column`() {
            val input = """
                123 328  51 64
                 45 64  387 23
                  6 98  215 314
                *   +   *   +
            """.trimIndent()
            val problems = parseInput(input)
            problems.map { it.operator } shouldBe listOf("*", "+", "*", "+")
        }

        @Test
        fun `should get numbers from more than one column`() {
            val input = """
                123 328  51 64
                 45 64  387 23
                  6 98  215 314
                *   +   *   +
            """.trimIndent()
            val problems = parseInput(input)
            problems.map { it.numbers } shouldBe listOf(
                listOf(123, 45, 6),
                listOf(328, 64, 98),
                listOf(51, 387, 215),
                listOf(64, 23, 314),
            )
        }
    }

    @Nested
    inner class SolveProblemTest {
        @Test
        fun `solve problem with addition`() {
            val problem = Problem(
                numbers = listOf(1, 2, 3),
                operator = "+",
            )
            val result = solveProblem(problem)
            result shouldBe 6
        }

        @Test
        fun `solve problem with multiplication`() {
            val problem = Problem(
                numbers = listOf(1, 2, 3, 4),
                operator = "*",
            )
            val result = solveProblem(problem)
            result shouldBe 24
        }
    }
}