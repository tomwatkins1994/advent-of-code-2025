package org.example.Day01

import java.io.File
import kotlin.math.abs

fun main() {
    var position = 50
    File("src/main/kotlin/day01/input.txt").forEachLine {
        position = getNewPosition(position, it)
        println(position)
    }
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
        if (it < 0) return 100 - abs(it)
        if (it >= 100) return it - 100
        return it
    }
}