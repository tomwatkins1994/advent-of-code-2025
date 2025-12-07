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
    return listOf(Problem(numbers = listOf(123, 45, 6), operator = "*"))
}