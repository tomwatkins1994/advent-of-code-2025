package org.example.Day01

import java.io.File
import kotlin.math.abs

fun main() {
    var position = 50
    var numZeroes = 0
    File("src/main/kotlin/day01/input.txt").forEachLine {
        val res = getNumZeroes(position, it)
        numZeroes += res.first
        position = res.second
    }
    println("numZeroes: $numZeroes")
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

fun getNumZeroes(startingPos: Int, movement: String): Pair<Int, Int> {
    val turns = getTurns(movement) % 100
    (startingPos + turns).let {
        if (it < 0) return Pair(1, 100 - abs(it))
        if (it >= 100) return Pair(1, it - 100)
        return Pair(0, it)
    }
}