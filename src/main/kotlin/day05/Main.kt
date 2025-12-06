package day05

import java.io.File

fun main() {
    val result = getFreshAndAvailableIngredients(File("src/main/kotlin/day05/input.txt").readText())
    println("Fresh and available ingredients: ${result.size}")
}

data class IngredientRange(
    val from: Long,
    val to: Long
)

data class Ingredients(
    val fresh: List<IngredientRange>,
    val available: Set<Long>
)

fun getFreshAndAvailableIngredients(input: String): Set<Long> {
    val ingredients = parseInput(input)
    return setOf()
//    return ingredients.available.filter { ingredients.fresh.contains(it) }.toSet()
}

fun parseInput(input: String): Ingredients {
    val fresh = mutableListOf<IngredientRange>()
    val available = mutableSetOf<Long>()

    var inRanges = true
    for (line in input.lines()) {
        if (line == "") {
            inRanges = false
            continue
        }
        if (inRanges) {
            val range = line.split("-")
            fresh.add(IngredientRange(from = range.first().toLong(), to = range.last().toLong()))
        } else {
            available.add(line.toLong())
        }
    }

    return Ingredients(fresh = fresh, available = available)
}