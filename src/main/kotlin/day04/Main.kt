package day04

fun main() {

}

fun getAccessibleRolls(rows: List<String>): Int {
    return rows[0].count { it == '@' }
}