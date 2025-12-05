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
    for (i in 1..idString.length) {
        var scannedDigits = 0
        var compareToChunk = ""
        var matchingPattern = false
        while (scannedDigits <= idString.length && scannedDigits + i <= idString.length) {
            val currentChunk = idString.substring(scannedDigits, scannedDigits + i)
            matchingPattern = compareToChunk == currentChunk
            if (!matchingPattern && compareToChunk != "") {
                break
            }
            compareToChunk = currentChunk
            scannedDigits += i
        }
        if (matchingPattern) return false
    }

    return true
}

fun sumOfInvalidIdRanges(idRanges: String): Long {
    val validIds = buildList {
        idRanges.split(",")
            .map { parseIdRange(it) }
            .map { idRange ->
                for (id in idRange.first..idRange.second) {
                    id.takeIf { !validateId(it) }?.let { add(it) }
                    if (!validateId(id)) println("$id - ${validateId(id)}")
                }
            }
    }

    return validIds.sum()
}