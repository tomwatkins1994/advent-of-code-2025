package org.example.Day01

import day01.getTurns
import java.io.File
import kotlin.math.abs

fun main() {
    val combination = File("src/main/kotlin/day01/input.txt").useLines {
        getCombination(50, it.toList())
    }
    println("Combination: $combination")
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