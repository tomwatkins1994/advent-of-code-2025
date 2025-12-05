package day02

fun parseIdRange(idRange: String): Pair<Int, Int> {
    return idRange.split("-").let {
        Pair(it[0].toInt(), it[1].toInt())
    }
}

fun validateId(id: Int): Boolean {
    val idString = id.toString()

    // If the ID is an odd number of digits we can assume it will always be valid
    if (idString.length % 2 > 0) {
        return true
    }

    val numbersInHalf = idString.length / 2
    val firstHalf = idString.substring(0, numbersInHalf)
    val secondHalf = idString.substring(numbersInHalf)
    return firstHalf != secondHalf
}

fun sumOfInvalidIdRanges(idRanges: String): Int {
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