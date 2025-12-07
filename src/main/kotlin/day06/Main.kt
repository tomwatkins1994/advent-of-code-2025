package day06

import java.io.File

fun main() {
    val answer = getAnswer(File("src/main/kotlin/day06/input.txt").readText())
    println("Answer: $answer")
}

fun getAnswer(input: String): Long {
    val problems = parseInput(input)
    return problems.sumOf { solveProblem(it) }
}

data class Problem(
    val numbers: List<Int>,
    val operator: String
)

fun parseInput(input: String): List<Problem> {
    val numbers: MutableList<List<Int>> = mutableListOf()
    val operators: MutableList<String> = mutableListOf()
    for ((index, value) in input.lines().withIndex()) {
        if (index == input.lines().size - 1) {
            operators.addAll(value.split(Regex("\\s+")).filter { it.isNotBlank() })
        } else {
            numbers.add(value.split(Regex("\\s+")).filter { it.isNotBlank() }.map { it.toInt() })
        }
    }

    return operators.mapIndexed { index, operator ->
        Problem(numbers = numbers.map { it[index] }, operator = operator)
    }
}

fun solveProblem(problem: Problem): Long {
    return when (problem.operator) {
        "+" -> problem.numbers.map { it.toLong() }.reduce { acc, i -> acc + i }
        "*" -> problem.numbers.map { it.toLong() }.reduce { acc, i -> acc * i }
        else -> throw IllegalArgumentException("Invalid operator")
    }
}