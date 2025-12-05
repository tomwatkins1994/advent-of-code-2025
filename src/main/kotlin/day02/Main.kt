package day02

fun parseIdRange(idRange: String): Pair<Int, Int> {
    return idRange.split("-").let {
        Pair(it[0].toInt(), it[1].toInt())
    }
}