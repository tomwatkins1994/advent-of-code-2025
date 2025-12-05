package day02

fun parseIdRange(idRange: String): Pair<String, String> {
    return idRange.split("-").let { Pair(it[0], it[1]) }
}

fun validateId(id: Int): Boolean {
    // If the ID is an odd number of digits we can assume it will always be valid
    if (id.toString().length % 2 > 0) {
        return true
    }



    return false
}