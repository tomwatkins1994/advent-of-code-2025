package org.example.Day01

import java.io.File
import kotlin.math.abs

fun main() {
    var position = 50
    var numZeroes = 0
    var finishedZeroes = 0
    File("src/main/kotlin/day01/input.txt").forEachLine {
        val start = position
        val res = getNumZeroes(position, it)
//        if (numZeroes + res.first > numZeroes) {
//            println("$it: ${res.second} (Start: $start) (${numZeroes + res.first})")
//        }
        numZeroes += res.first
        position = res.second
        if (res.second == 0) finishedZeroes++
        //println("$it: $position ($numZeroes)")
    }
    println("numZeroes: $numZeroes ($finishedZeroes)")
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
    val turns = totalTurns % 100
    var numZeroes = abs(totalTurns.div(100))
    if (turns == 0) {
        return Pair(numZeroes, startingPos)
    }
    (startingPos + turns).let {
        if ((it <= 0 || it > 99) && startingPos != 0) numZeroes++
        if (it < 0) return Pair(numZeroes, 100 - abs(it))
        if (it > 99) return Pair(numZeroes, it - 100)
        return Pair(numZeroes, it)
    }
}