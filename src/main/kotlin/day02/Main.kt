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
        var currentChunk = ""
        var matchingPattern = false
        for (position in 0..idString.length - 1 step chunkSize) {
            val nextChunk = idString.substring(position, position + chunkSize)
            matchingPattern = nextChunk == currentChunk
            if (!matchingPattern && position > 0) {
                break
            }
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