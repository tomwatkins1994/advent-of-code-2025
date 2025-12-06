package day05

fun main() {

}

data class Ingredients(
    val fresh: Set<Int>,
    val available: Set<Int>
)

fun getFreshAndAvailableIngredients(input: String): Set<Int> {
    val ingredients = parseInput(input)
    return ingredients.available.filter { ingredients.fresh.contains(it) }.toSet()
}

fun parseInput(input: String): Ingredients {
    val fresh = mutableSetOf<Int>()
    val available = mutableSetOf<Int>()

    var inRanges = true
    for (line in input.lines()) {
        if (line == "") {
            inRanges = false
            continue
        }
        if (inRanges) {
            val range = line.split("-")
            for (ingredient in range.first().toInt()..range.last().toInt()) {
                fresh.add(ingredient)
            }
        } else {
            available.add(line.toInt())
        }
    }

    return Ingredients(fresh = fresh, available = available)
}