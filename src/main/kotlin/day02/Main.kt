package day02

fun parseIdRange(idRange: String): Pair<String, String> {
    return idRange.split("-").let { Pair(it[0], it[1]) }
}

fun validateId(id: String): Boolean {
    // If the ID is an odd number of digits we can assume it will always be valid
    if (id.length % 2 > 0) {
        return true
    }

    val numbersInHalf = id.length / 2
    val firstHalf = id.substring(0, numbersInHalf)
    val secondHalf = id.substring(numbersInHalf)
    return firstHalf != secondHalf
}

fun sumOfInvalidIdRanges(idRanges: String): Int {
    val validIds = buildList {
        idRanges.split(",")
            .map { parseIdRange(it) }
            .map { idRange ->
                for (id in idRange.first.toInt()..idRange.second.toInt()) {
                    id.takeIf { !validateId(it.toString()) }?.let { add(it) }
                }
            }
    }

    return validIds.sum()
}