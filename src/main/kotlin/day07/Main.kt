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

fun getTotalNumberOfTimelines(input: String): Int {
    var totalPossibleTimelines = 0
    var positions = listOf<Int>()
    val lines = input.lines()
    for ((lineIndex, line) in input.lines().withIndex()) {
        if (lineIndex == 0) {
            positions = getBeamPositions(line)
        } else {
            totalPossibleTimelines += getPossibleTimelinesFromLine(lines.subList(lineIndex, lines.size - 1), positions)
        }
    }

    return totalPossibleTimelines
}

fun getPossibleTimelinesFromLine(lines: List<String>, positions: List<Int>): Int {
    var possibleTimelines = 0
    for ((lineIndex, line) in lines.withIndex()) {
        val timelines = getPossibleTimelinesForLine(line, positions)
        possibleTimelines += timelines.size
        timelines.forEach {
            val newPositions = getBeamPositions(it)
            possibleTimelines += getPossibleTimelinesFromLine(lines.subList(lineIndex, lines.size - 1), newPositions)
        }
    }

    return possibleTimelines
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

fun getPossibleTimelinesForLine(line: String, positions: List<Int>): List<String> {
    return buildList {
        positions.forEach {
            if (line[it] == '^') {
                add(line.replaceRange(it - 1..it - 1, "|"))
                add(line.replaceRange(it + 1..it + 1, "|"))
            } else {
                add(line.replaceRange(it..it, "|"))
            }
        }
    }
}