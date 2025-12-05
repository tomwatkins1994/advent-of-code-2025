package day02

import java.io.File

fun main() {
    val result = sumOfInvalidIdRanges(File("src/main/kotlin/day02/input.txt").readText())
    println("Result: $result")
}

fun parseIdRange(idRange: String): Pair<Long, Long> {
    return idRange.split("-").let {
        Pair(it[0].toLong(), it[1].toLong())
    }
}

fun validateId(id: Long): Boolean {
    val idString = id.toString()
    val numbersInHalf = idString.length / 2
    val firstHalf = idString.substring(0, numbersInHalf)
    val secondHalf = idString.substring(numbersInHalf)
    return firstHalf != secondHalf
}

fun sumOfInvalidIdRanges(idRanges: String): Long {
    val validIds = buildList {
        idRanges.split(",")
            .map { parseIdRange(it) }
            .map { idRange ->
                for (id in idRange.first..idRange.second) {
                    id.takeIf { !validateId(it) }?.let { add(it) }
                }
            }
    }

    return validIds.sum()
}