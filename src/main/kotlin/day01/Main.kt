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

    val movement = input.drop(1).let {
        if (it.isEmpty()) throw IllegalArgumentException("Invalid movement")
        it.toInt()
    }

    return Pair(direction, movement)
}

fun getNewPosition(startingPos: Int, movement: Movement): Int {
    if (movement.first == "L") {
        val res = startingPos - movement.second
        if (res >= 0) return res
        return 100 - abs(res)
    } else if (movement.first == "R") {
        val res = startingPos + movement.second
        if (res <= 99) return res
        return abs(res) - 100
    }

    throw IllegalArgumentException("Invalid movement")
}