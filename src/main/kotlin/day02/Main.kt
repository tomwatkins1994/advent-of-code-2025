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
    for (chunkSize in 1..idString.length / 2) {
        if (idString.length % chunkSize > 0) {
            continue
        }
        var scannedDigits = 0
        var currentChunk = ""
        var matchingPattern = false
        while (scannedDigits < idString.length) {
            val nextChunk = idString.substring(scannedDigits, scannedDigits + chunkSize)
            matchingPattern = nextChunk == currentChunk
            if (!matchingPattern && scannedDigits > 0) {
                break
            }
            scannedDigits += chunkSize
            currentChunk = nextChunk
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
                }
            }
    }

    return validIds.sum()
}