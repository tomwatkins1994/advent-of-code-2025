package day07

fun main() {

}

fun getNumberOfSplits(input: String): Int {
    return 0
}

fun getStartingPosition(input: String): Int {
    return input.lines().first().indexOfFirst { it == 'S' }
}