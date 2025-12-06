package day04

import java.io.File

fun main() {
    val result = File("src/main/kotlin/day04/input.txt").useLines { getAccessibleRolls(it.toList()) }
    println("Accessible rolls: $result")
}

data class Position(
    val row: Int,
    val col: Int
)

fun getAccessibleRolls(rows: List<String>): Int {
    val accessibleRolls = mutableListOf<Position>()
    for ((rowIndex, rowValue) in rows.withIndex()) {
        for ((colIndex, colValue) in rowValue.withIndex()) {
            if (colValue.toString() != "@") continue

            val isTopRow = rowIndex == 0
            val isBottomRow = rowIndex == rows.size - 1
            if (isTopRow && isBottomRow) {
                accessibleRolls.add(Position(rowIndex, colIndex))
                continue
            }
            val isLeftEdge = colIndex == 0
            val isRightEdge = colIndex == rowValue.length - 1
            val isCorner = (isTopRow || isBottomRow) && (isLeftEdge || isRightEdge)
            if (isCorner) {
                accessibleRolls.add(Position(rowIndex, colIndex))
                continue
            }

            val coordsToCheck = mutableListOf<Position>()
            if (!isTopRow) {
                if (!isLeftEdge) {
                    coordsToCheck.add(Position(rowIndex - 1, colIndex - 1))
                }
                coordsToCheck.add(Position(rowIndex - 1, colIndex))
                if (!isRightEdge) {
                    coordsToCheck.add(Position(rowIndex - 1, colIndex + 1))
                }
            }
            if (!isLeftEdge) {
                coordsToCheck.add(Position(rowIndex, colIndex - 1))
            }
            if (!isRightEdge) {
                coordsToCheck.add(Position(rowIndex, colIndex + 1))
            }
            if (!isBottomRow) {
                if (!isLeftEdge) {
                    coordsToCheck.add(Position(rowIndex + 1, colIndex - 1))
                }
                coordsToCheck.add(Position(rowIndex + 1, colIndex))
                if (!isRightEdge) {
                    coordsToCheck.add(Position(rowIndex + 1, colIndex + 1))
                }
            }

            val countAdjacent = coordsToCheck.filter { rows[it.row][it.col].toString() == "@" }.size
            if (countAdjacent < 4) {
                accessibleRolls.add(Position(rowIndex, colIndex))
            }
        }
    }

    if (accessibleRolls.isNotEmpty()) {
        val rowsWithRollsRemoved = markRollsRemoved(rows, accessibleRolls)
        return accessibleRolls.size + getAccessibleRolls(rowsWithRollsRemoved)
    }

    return 0
}

fun markRollsRemoved(rows: List<String>, rollsToRemove: List<Position>): List<String> {
    val newRows = rows.toMutableList()
    for (roll in rollsToRemove) {
        newRows[roll.row] = newRows[roll.row].replaceRange(roll.col..roll.col, "x")
    }
    return newRows
}