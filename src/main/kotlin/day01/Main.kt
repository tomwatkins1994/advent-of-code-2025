package org.example.Day01

import java.io.File
import kotlin.math.abs

fun main() {
    val combination = File("src/main/kotlin/day01/input.txt").useLines {
        getCombination(50, it.toList())
    }
    println("Combination: $combination")
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
    val totalTurns = getTurns(movement)
    var numZeroes = abs(totalTurns.div(100))
    val turns = totalTurns % 100
    (startingPos + turns).let {
        if ((it <= 0 || it > 99) && startingPos != 0) numZeroes++
        if (it < 0) return Pair(numZeroes, 100 - abs(it))
        if (it > 99) return Pair(numZeroes, it - 100)
        return Pair(numZeroes, it)
    }
}

fun getCombination(startingPos: Int, rotations: List<String>): Int {
    var numZeroes = 0
    var position = startingPos
    rotations.forEach {
        val res = getNumZeroes(position, it)
        numZeroes += res.first
        position = res.second
    }
    return numZeroes
}