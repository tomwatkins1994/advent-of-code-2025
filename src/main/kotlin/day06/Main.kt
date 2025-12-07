package day06

fun main() {

}

fun getAnswer(input: String): Long {
    return 0
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