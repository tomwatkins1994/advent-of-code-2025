package day03

import java.io.File

fun main() {
    val result = File("src/main/kotlin/day03/input.txt").useLines { getTotalJoltage(it.toList()) }
    println("Total Joltage: $result")
}

fun getTotalJoltage(banks: List<String>): Long {
    return banks.sumOf { getMaximumJoltage(it) }
}

fun getMaximumJoltage(bank: String): Long {
    var maxJoltage = ""
    var remaining = bank

    for (i in 1..12) {
        var currentJoltage = 0
        var nextIndex = 0
        remaining.let { it.substring(0, it.length - (12 - i)) }.forEachIndexed { index, joltage ->
            val joltageValue = joltage.toString().toInt()
            if (joltageValue > currentJoltage) {
                currentJoltage = joltageValue
                nextIndex = index + 1
            }
        }
        maxJoltage += currentJoltage.toString()
        remaining = remaining.substring(nextIndex)
    }

    return maxJoltage.toLong()
}