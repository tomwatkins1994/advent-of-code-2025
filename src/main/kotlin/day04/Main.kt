package day04

import java.io.File

fun main() {
    val result = File("src/main/kotlin/day04/input.txt").useLines { getAccessibleRolls(it.toList()) }
    println("Accessible rolls: $result")
}

fun getAccessibleRolls(rows: List<String>): Int {
    val accessibleRolls = mutableListOf<Pair<Int, Int>>()
    for ((rowIndex, row) in rows.withIndex()) {
        for ((colIndex, col) in row.withIndex()) {
            if (col.toString() != "@") continue

            val isTopRow = rowIndex == 0
            val isBottomRow = rowIndex == rows.size - 1
            if (isTopRow && isBottomRow) {
                accessibleRolls.add(Pair(rowIndex, colIndex))
                continue
            }
            val isLeftEdge = colIndex == 0
            val isRightEdge = colIndex == row.length - 1
            val isCorner = (isTopRow || isBottomRow) && (isLeftEdge || isRightEdge)
            if (isCorner) {
                accessibleRolls.add(Pair(rowIndex, colIndex))
                continue
            }

            val coordsToCheck = mutableListOf<Pair<Int, Int>>()
            if (!isTopRow) {
                if (!isLeftEdge) {
                    coordsToCheck.add(Pair(rowIndex - 1, colIndex - 1))
                }
                coordsToCheck.add(Pair(rowIndex - 1, colIndex))
                if (!isRightEdge) {
                    coordsToCheck.add(Pair(rowIndex - 1, colIndex + 1))
                }
            }
            if (!isLeftEdge) {
                coordsToCheck.add(Pair(rowIndex, colIndex - 1))
            }
            if (!isRightEdge) {
                coordsToCheck.add(Pair(rowIndex, colIndex + 1))
            }
            if (!isBottomRow) {
                if (!isLeftEdge) {
                    coordsToCheck.add(Pair(rowIndex + 1, colIndex - 1))
                }
                coordsToCheck.add(Pair(rowIndex + 1, colIndex))
                if (!isRightEdge) {
                    coordsToCheck.add(Pair(rowIndex + 1, colIndex + 1))
                }
            }

            val countAdjacent = coordsToCheck.filter { rows[it.first][it.second].toString() == "@" }.size
            if (countAdjacent < 4) {
                accessibleRolls.add(Pair(rowIndex, colIndex))
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
        newRows[roll.first] = newRows[roll.first].replaceRange(roll.second..roll.second, "x")
    }
    return newRows
}