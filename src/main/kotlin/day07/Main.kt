package day07

import java.io.File

fun main() {
    val totalSplits = getTotalNumberOfSplits(File("src/main/kotlin/day07/input.txt").readText())
    println("Total splits: $totalSplits")

    val totalTimelines = getTotalNumberOfTimelines(File("src/main/kotlin/day07/input.txt").readText())
    println("Total timelines: $totalTimelines")
}

fun getBeamPositions(line: String): List<Int> {
    return line.mapIndexedNotNull { index, elem -> index.takeIf { elem == 'S' || elem == '|' } }
}

// Part 1

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

// Part 2

fun getTotalNumberOfTimelines(input: String): Long {
    val lines = input.lines()
    val timelinesCache = mutableMapOf<Pair<Int, List<Int>>, Long>()

    fun getPossibleTimelinesFromLine(lineIndex: Int, positions: List<Int>): Long {
        val newLines = lines.subList(lineIndex, lines.size)
        if (newLines.isEmpty()) {
            return 0
        }

        val cacheKey = Pair(lineIndex, positions)
        if (timelinesCache.containsKey(cacheKey)) {
            return timelinesCache[cacheKey]!!
        }

        val timelines = getPossibleTimelinesForLine(newLines.first(), positions)
        val result = (timelines.size - 1) + timelines.sumOf {
            getPossibleTimelinesFromLine(
                lineIndex + 1,
                getBeamPositions(it)
            )
        }
        timelinesCache[cacheKey] = result

        return result
    }

    return 1 + getPossibleTimelinesFromLine(
        0,
        getBeamPositions(lines.first())
    )
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