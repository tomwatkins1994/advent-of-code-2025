package day05

import java.io.File

fun main() {
    val result = getFreshAndAvailableIngredients(File("src/main/kotlin/day05/input.txt").readText())
    println("Fresh and available ingredients: ${result.size}")
}

data class Ingredients(
    val fresh: Set<Long>,
    val available: Set<Long>
)

fun getFreshAndAvailableIngredients(input: String): Set<Long> {
    val ingredients = parseInput(input)
    return ingredients.available.filter { ingredients.fresh.contains(it) }.toSet()
}

fun parseInput(input: String): Ingredients {
    val fresh = mutableSetOf<Long>()
    val available = mutableSetOf<Long>()

    var inRanges = true
    for (line in input.lines()) {
        if (line == "") {
            inRanges = false
            continue
        }
        if (inRanges) {
            val range = line.split("-")
            for (ingredient in range.first().toLong()..range.last().toLong()) {
                fresh.add(ingredient)
            }
        } else {
            available.add(line.toLong())
        }
    }

    return Ingredients(fresh = fresh, available = available)
}