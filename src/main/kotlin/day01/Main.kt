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
        val res = startingPos - turns
        if (res >= 0) return res
        return 100 - abs(res)
    } else if (direction == "R") {
        val res = startingPos + turns
        if (res <= 99) return res
        return abs(res) - 100
    }

    throw IllegalArgumentException("Invalid movement")
}