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
                } else {
                    accessibleRolls++
                }
            }
        }
    }

    return accessibleRolls
}