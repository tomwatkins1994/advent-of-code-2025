package org.example.Day01

import kotlin.math.abs

fun main() {
    println("Hello world")
}

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

fun getNewPosition(startingPos: Int, movement: String): Int {
    (startingPos + getTurns(movement)).let {
        if (it >= 0 && it <= 99) return it
        return 100 - abs(it)
    }
}