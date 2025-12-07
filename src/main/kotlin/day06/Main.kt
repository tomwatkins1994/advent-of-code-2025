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
    val numberStrings: MutableList<List<String>> = mutableListOf()
    val operators: MutableList<String> = mutableListOf()

    val columnLengths = getColumnLengths(input)

    val lines = input.lines()
    for ((index, line) in lines.withIndex()) {
        if (index == lines.size - 1) {
            operators.addAll(line.split(Regex("\\s+")).filter { it.isNotBlank() })
        } else {
            var currentPosition = 0
            numberStrings.add(columnLengths.map { colLength ->
                val overflow = (currentPosition + colLength) - line.length
                val endPosition = if (overflow > 0) line.length else currentPosition + colLength
                val number =
                    line.substring(currentPosition, endPosition) + if (overflow > 0) " ".repeat(overflow) else ""
                currentPosition += colLength + 1
                number
            })
        }
    }

    return operators.mapIndexed { index, operator ->
        Problem(
            numbers = readNumbers(numberStrings.map { it[index] }),
            operator = operator
        )
    }
}

fun getColumnLengths(input: String): List<Int> {
    return buildList {
        for ((lineIndex, line) in input.lines().withIndex()) {
            val values = line.split(Regex("\\s+")).filter { it.isNotBlank() }
            if (lineIndex == 0) addAll(MutableList(values.size) { 0 })
            values.map { it.length }.forEachIndexed { index, value ->
                if (value > get(index)) {
                    set(index, value)
                }
            }
        }
    }
}

fun solveProblem(problem: Problem): Long {
    return when (problem.operator) {
        "+" -> problem.numbers.map { it.toLong() }.reduce { acc, i -> acc + i }
        "*" -> problem.numbers.map { it.toLong() }.reduce { acc, i -> acc * i }
        else -> throw IllegalArgumentException("Invalid operator")
    }
}

fun readNumbers(numberStrings: List<String>): List<Int> {
    val numbers = MutableList(numberStrings.maxOf { it.length }) { "" }
    for (numberString in numberStrings.map { it.reversed() }) {
        for ((charIndex, char) in numberString.withIndex()) {
            if (char.isDigit()) numbers[charIndex] += char
        }
    }

    return numbers.map { it.toInt() }
}