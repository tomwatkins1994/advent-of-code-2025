package day07

import java.io.File

fun main() {
    val answer = getTotalNumberOfSplits(File("src/main/kotlin/day07/input.txt").readText())
    println("Answer: $answer")
}

fun getTotalNumberOfSplits(input: String): Int {
    var positions = listOf<Int>()
    var numberOfSplits = 0
    for (line in input.lines()) {
        val splitPositions = getSplitPositions(line)
        numberOfSplits += splitPositions.intersect(positions).count()
        val newLine = drawBeam(line, positions)
        positions = getBeamPositions(newLine)
    }

    return numberOfSplits
}

fun getBeamPositions(line: String): List<Int> {
    return line.mapIndexedNotNull { index, elem -> index.takeIf { elem == 'S' || elem == '|' } }
}

fun getSplitPositions(line: String): List<Int> {
    return line.mapIndexedNotNull { index, elem -> index.takeIf { elem == '^' } }
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