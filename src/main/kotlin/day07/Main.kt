package day07

import java.io.File

fun main() {
    val totalSplits = getTotalNumberOfSplits(File("src/main/kotlin/day07/input.txt").readText())
    println("Total splits: $totalSplits")

    val totalTimelines = getTotalNumberOfTimelines(File("src/main/kotlin/day07/input.txt").readText())
    println("Total timelines: $totalTimelines")
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
    var cnt = 0
    val timelinesMap = mutableMapOf<Pair<Int, List<Int>>, Int>()

    fun getPossibleTimelinesFromLine(lineIndex: Int, lines: List<String>, positions: List<Int>): Int {
        if (lines.isEmpty()) {
            return 0
        }

        val cacheKey = Pair(lineIndex, positions)
        if (timelinesMap.containsKey(cacheKey)) {
            println("Memo hit")
            return timelinesMap[cacheKey]!!
        }

        cnt++
        println(cnt)

        val timelines = getPossibleTimelinesForLine(lines.first(), positions)
        val result = (timelines.size - 1) + timelines.sumOf {
            getPossibleTimelinesFromLine(
                lineIndex + 1,
                lines.subList(1, lines.size),
                getBeamPositions(it)
            )
        }
        timelinesMap[cacheKey] = result

        return result
    }

    val lines = input.lines()
    val positions = getBeamPositions(input.lines().first())

    return 1 + getPossibleTimelinesFromLine(0, lines.subList(1, lines.size), positions)
}

fun getTotalNumberOfTimelines2(input: String): Int {
    var positions = listOf<Int>()
    var timelines = 1
    var splits = 0
    for (line in input.lines()) {
        val newLine = drawBeam(line, positions)
        val newPositions = getBeamPositions(newLine)
//
//            val splitPositions = getSplitPositions(line)
//            val numberOfSplits = splitPositions.intersect(positions).count()
//
//            splits++
//            timelines += (splits * numberOfSplits) - (numberOfSplits - 1)
//
//            //getBeamPositions(newLine).count { !positions.contains(it) } - 1
//        }
        positions = newPositions
    }

    return timelines
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

data class BeamPosition(val index: Int, val value: Int)

//fun getLineWithValues(line: String, positions: List<BeamPosition>): String {
//    val newLine = line.map { it.toString() }.toMutableList()
//    positions.forEach { (index, value) ->
//        if (newLine[index] == "^") {
//            newLine[index - 1][0].takeIf { it.isDigit() }?.let {
//                it
//            }
//            newLine[index - 1] = (newLine[index - 1][0] + value).toString()
//            newLine[index + 1] = (newLine[index + 1].toInt() + value).toString()
//        } else {
//            newLine[index] = value.toString()
//        }
//    }
//    return newLine.joinToString("")
//}