package day03

fun main() {

}

fun getTotalJoltage(banks: List<String>): Int {
    return banks.sumOf { getMaximumJoltage(it) }
}

fun getMaximumJoltage(bank: String): Int {
    var firstJoltage = 0
    var firstIndex = 0
    bank.forEachIndexed { index, joltage ->
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

    return "$firstJoltage$secondJoltage".toInt()
}