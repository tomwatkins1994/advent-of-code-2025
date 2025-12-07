package day07

fun main() {

}

fun getNumberOfSplits(input: String): Int {
    return 0
}

fun getStartingPosition(input: String): Int {
    return input.lines().first().indexOfFirst { it == 'S' }
}

fun drawBeam(line: String, positions: List<Int>): String {
    val newLine = line.map { it.toString() }.toMutableList()
    positions.forEach {
        if (newLine[it] == "^") {
            newLine[it - 1] = "|"
            newLine[it + 1] = "|"
        } else {
            newLine[it] = "|"
        }
    }
    return newLine.joinToString("")
}