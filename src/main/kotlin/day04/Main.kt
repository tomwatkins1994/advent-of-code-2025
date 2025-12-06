package day04

import java.io.File

fun main() {
    val result = File("src/main/kotlin/day04/input.txt").useLines { getAccessibleRolls(it.toList()) }
    println("Accessible rolls: $result")
}

fun getAccessibleRolls(rows: List<String>): Int {
    val accessibleRolls: MutableList<Pair<Int, Int>> = mutableListOf()
    rows.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { colIndex, value ->
            if (value.toString() == "@") {
                val isTopRow = rowIndex == 0
                val isBottomRow = rowIndex == rows.size - 1
                val isLeftEdge = colIndex == 0
                val isRightEdge = colIndex == row.length - 1
                val isCorner = (isTopRow || isBottomRow) && (isLeftEdge || isRightEdge)

                if (isCorner) {
                    accessibleRolls.add(Pair(rowIndex, colIndex))
                } else if (isTopRow && isBottomRow) {
                    accessibleRolls.add(Pair(rowIndex, colIndex))
                } else {
                    var countAdjacent = 0
                    if (!isTopRow) {
                        if (!isLeftEdge) {
                            if (rows[rowIndex - 1][colIndex - 1].toString() == "@") countAdjacent++
                        }
                        if (rows[rowIndex - 1][colIndex].toString() == "@") countAdjacent++
                        if (!isRightEdge) {
                            if (rows[rowIndex - 1][colIndex + 1].toString() == "@") countAdjacent++
                        }
                    }
                    if (!isLeftEdge) {
                        if (rows[rowIndex][colIndex - 1].toString() == "@") countAdjacent++
                    }
                    if (!isRightEdge) {
                        if (rows[rowIndex][colIndex + 1].toString() == "@") countAdjacent++
                    }
                    if (!isBottomRow) {
                        if (!isLeftEdge) {
                            if (rows[rowIndex + 1][colIndex - 1].toString() == "@") countAdjacent++
                        }
                        if (rows[rowIndex + 1][colIndex].toString() == "@") countAdjacent++
                        if (!isRightEdge) {
                            if (rows[rowIndex + 1][colIndex + 1].toString() == "@") countAdjacent++
                        }
                    }
                    if (countAdjacent < 4) {
                        accessibleRolls.add(Pair(rowIndex, colIndex))
                    }

                }
            }
        }
    }

    if (accessibleRolls.isNotEmpty()) {
        return accessibleRolls.size + getAccessibleRolls(markRollsRemoved(rows, accessibleRolls))
    }

    return 0
}

fun markRollsRemoved(rows: List<String>, rollsToRemove: List<Pair<Int, Int>>): List<String> {
    val newRows = rows.toMutableList()
    for (roll in rollsToRemove) {
        newRows[roll.first] = rows[roll.first].replaceRange(roll.second..roll.second, "x")
    }
    return newRows
}