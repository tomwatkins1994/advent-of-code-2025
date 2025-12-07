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
        if (index == input.lines().size - 1) {
            operators.addAll(value.split(Regex("\\s+")).filter { it.isNotBlank() })
        } else {
            val numbersToAdd = buildList {
                var valueToAdd = ""
                for (char in value) {
                    if (char.isDigit()) valueToAdd += char
                    if (char.isWhitespace()) {
                        if (valueToAdd.isNotBlank()) {
                            add(valueToAdd)
                            valueToAdd = ""
                        } else {
                            valueToAdd += char
                        }
                    }
                }
                add("")
            }
            numbers.add(listOf(value.split(" ").joinToString("") { it.ifBlank { " " } }))
        }
    }

    return operators.mapIndexed { index, operator ->
        Problem(
            numbers = readNumbers(numbers.map { it[index] }),
            operator = operator
        )
    }
}

fun getColumnLengths(input: String): List<Int> {
    return buildList {
        for ((lineIndex, line) in input.lines().withIndex()) {
            val values = line.split(Regex("\\s+")).filter { it.isNotBlank() }
            if (lineIndex == 0) addAll(MutableList(values.size) { 0 })
            values.map { it.length }
                .forEachIndexed { index, value ->
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

fun readNumbers(rows: List<String>): List<Int> {
    val numbers = MutableList(rows.maxOf { it.length }) { "" }
    for (row in rows.map { it.reversed() }) {
        for ((charIndex, char) in row.withIndex()) {
            if (char.isDigit()) numbers[charIndex] += char
        }
    }

    return numbers.map { it.toInt() }
}