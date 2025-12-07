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
    val numbers: MutableList<List<String>> = mutableListOf()
    val operators: MutableList<String> = mutableListOf()
    for ((index, value) in input.lines().withIndex()) {
        val values = value.split(Regex("\\s+")).filter { it.isNotBlank() }
        if (index == input.lines().size - 1) {
            operators.addAll(value.split(Regex("\\s+")).filter { it.isNotBlank() })
        } else {
            numbers.add(listOf(value.split(" ").joinToString("") { it.ifBlank { " " } }))
        }
    }

    return operators.mapIndexed { index, operator ->
        Problem(numbers = numbers.map { it[index].toInt() }, operator = operator)
    }
}

fun solveProblem(problem: Problem): Long {
    return when (problem.operator) {
        "+" -> problem.numbers.map { it.toLong() }.reduce { acc, i -> acc + i }
        "*" -> problem.numbers.map { it.toLong() }.reduce { acc, i -> acc * i }
        else -> throw IllegalArgumentException("Invalid operator")
    }
}

fun readNumbers(rows: List<String>): List<Int> {
    val numbers = MutableList(rows.maxOf { it.length }) { "" }
    for (row in rows.map { it.reversed() }) {
        for ((charIndex, char) in row.withIndex()) {
            if (char.isDigit()) numbers[charIndex] += char
        }
    }

    return numbers.map { it.toInt() }
}