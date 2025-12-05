package day02

fun parseIdRange(idRange: String): Pair<String, String> {
    return idRange.split("-").let { Pair(it[0], it[1]) }
}

fun validateId(id: String): Boolean {
    // If the ID is an odd number of digits we can assume it will always be valid
    if (id.length % 2 > 0) {
        return true
    }

    if (id.first() != id[1]) return true

    return false
}