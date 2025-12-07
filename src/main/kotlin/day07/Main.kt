package day07

fun main() {

}

fun getNumberOfSplits(input: String): Int {
    return 0
}

fun getStartingPosition(input: String): Int {
    return input.lines().first().indexOfFirst { it == 'S' }
}

fun drawBeam(line: String, positions: List<Int>): String {
    var newLine = line
    positions.forEach {
        newLine = newLine.replaceRange(it..it, "|")
    }
    return newLine
}