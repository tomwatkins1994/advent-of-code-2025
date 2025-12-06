package day05

import java.io.File

fun main() {
    val freshAndAvailable = getFreshAndAvailableIngredients(File("src/main/kotlin/day05/input.txt").readText())
    println("Fresh and available ingredients: ${freshAndAvailable.size}")

    val totalFresh = getTotalFreshIngredients(File("src/main/kotlin/day05/input.txt").readText())
    println("Total fresh ingredients: $totalFresh")
}

data class IngredientRange(
    val from: Long,
    val to: Long
)

data class MutableIngredientRange(
    var from: Long,
    var to: Long
)


data class Ingredients(
    val fresh: List<IngredientRange>,
    val available: Set<Long>
)

fun getFreshAndAvailableIngredients(input: String): Set<Long> {
    val ingredients = parseInput(input)
    return ingredients.available.filter { available ->
        ingredients.fresh.any { fresh -> available >= fresh.from && available <= fresh.to }
    }.toSet()
}

fun getTotalFreshIngredients(input: String): Long {
    val ingredients = parseInput(input)
    var totalFreshIngredients = 0L
    for ((index, ingredientRange) in ingredients.fresh.withIndex()) {
        val newIngredientRange = MutableIngredientRange(
            from = ingredientRange.from,
            to = ingredientRange.to
        )
        for ((compareIndex, compareIngredientRange) in ingredients.fresh.withIndex()) {
            if (index == compareIndex) continue
            if (
                newIngredientRange.from >= compareIngredientRange.from
                && newIngredientRange.from <= compareIngredientRange.to
            ) {
                newIngredientRange.from = compareIngredientRange.to + 1
            }
            if (
                newIngredientRange.to >= compareIngredientRange.from
                && newIngredientRange.to <= compareIngredientRange.to
            ) {
                newIngredientRange.to = compareIngredientRange.from - 1
            }
        }
        totalFreshIngredients += (newIngredientRange.to - newIngredientRange.from) + 1
    }

    return totalFreshIngredients
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