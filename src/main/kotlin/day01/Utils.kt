package day01

fun getTurns(input: String): Int {
    val direction = input.first().toString()
    if (direction != "L" && direction != "R") {
        throw IllegalArgumentException("Invalid direction")
    }

    val turns = input.drop(1).let {
        if (it.isEmpty()) throw IllegalArgumentException("Invalid turns")
        it.toInt()
    }

    return if (direction == "R") turns else turns * -1
}
