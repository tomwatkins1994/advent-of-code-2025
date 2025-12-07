package day07

fun main() {

}

fun getTotalNumberOfSplits(input: String): Int {
    var positions = listOf<Int>()
    var numberOfSplits = 0
    for (line in input.lines()) {
        val newLine = drawBeam(line, positions)
        positions = getBeamPositions(newLine)
        numberOfSplits += getNumberOfSplits(newLine)
    }

    return numberOfSplits
}

fun getBeamPositions(line: String): List<Int> {
    return line.mapIndexedNotNull { index, elem -> index.takeIf { elem == 'S' || elem == '|' } }
}

fun getNumberOfSplits(line: String): Int {
    return Regex(Regex.escape("|^|")).findAll(line).count()
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