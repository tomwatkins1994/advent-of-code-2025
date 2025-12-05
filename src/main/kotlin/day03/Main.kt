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
    var firstJoltage = 0
    var firstIndex = 0
    bank.substring(0, bank.length - 1).forEachIndexed { index, joltage ->
        val joltageValue = joltage.toString().toInt()
        if (joltageValue > firstJoltage) {
            firstJoltage = joltageValue
            firstIndex = index
        }
    }

    var secondJoltage = 0
    bank.substring(firstIndex + 1).forEachIndexed { index, joltage ->
        val joltageValue = joltage.toString().toInt()
        if (joltageValue > secondJoltage) {
            secondJoltage = joltageValue
        }
    }

    return "$firstJoltage$secondJoltage".toLong()
}