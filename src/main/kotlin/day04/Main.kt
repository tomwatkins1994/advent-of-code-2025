package day04

fun main() {

}

fun getAccessibleRolls(rows: List<String>): Int {
    var accessibleRolls = 0
    rows.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { colIndex, value ->
            if (value.toString() == "@") {
                val isTopRow = rowIndex == 0
                val isBottomRow = rowIndex == rows.size - 1
                val isLeftEdge = colIndex == 0
                val isRightEdge = colIndex == row.length - 1
                val isCorner = (isTopRow || isBottomRow) && (isLeftEdge || isRightEdge)

                if (isCorner) {
                    accessibleRolls++
                } else if (isTopRow && isBottomRow) {
                    accessibleRolls++
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
                    if (countAdjacent < 4) accessibleRolls++
                }

            }
        }
    }

    return accessibleRolls
}