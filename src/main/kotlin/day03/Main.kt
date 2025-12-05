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
    var remainingBatteries = bank

    for (i in 1..12) {
        var currentJoltage = 0
        var nextIndex = 0
        remainingBatteries
            .substring(0, remainingBatteries.length - (12 - i))
            .map { it.toString().toInt() }
            .forEachIndexed { index, joltage ->
                if (joltage > currentJoltage) {
                    currentJoltage = joltage
                    nextIndex = index + 1
                }
            }
        maxJoltage += currentJoltage.toString()
        remainingBatteries = remainingBatteries.substring(nextIndex)
    }

    return maxJoltage.toLong()
}