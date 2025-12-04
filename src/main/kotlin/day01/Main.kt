package org.example.Day01

fun main() {
    println("Hello world")
}

typealias Movement = Pair<String, Int>

fun getMovement(input: String): Movement {
    val direction = input.first().toString()
    if (direction != "L" && direction != "R") {
        throw IllegalArgumentException("Invalid direction")
    }

    val movement = input.drop(1).let {
        if (it.isEmpty()) throw IllegalArgumentException("Invalid movement")
        it.toInt()
    }

    return Pair(direction, movement)
}
