package org.example.Day01

import kotlin.math.abs

fun main() {
    println("Hello world")
}

typealias Movement = Pair<String, Int>

fun getMovement(input: String): Movement {
    val direction = input.first().toString()
    if (direction != "L" && direction != "R") {
        throw IllegalArgumentException("Invalid direction")
    }

    val turns = input.drop(1).let {
        if (it.isEmpty()) throw IllegalArgumentException("Invalid turns")
        it.toInt()
    }

    return Pair(direction, turns)
}

fun getNewPosition(startingPos: Int, movement: Movement): Int {
    val (direction, turns) = movement
    if (direction == "L") {
        (startingPos - turns).let {
            if (it >= 0) return it
            return 100 - abs(it)
        }
    } else if (direction == "R") {
        (startingPos + turns).let {
            if (it <= 99) return it
            return abs(it) - 100
        }
    }

    throw IllegalArgumentException("Invalid movement")
}