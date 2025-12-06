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
) {
    fun toMutable() = MutableIngredientRange(
        from = this.from,
        to = this.to
    )
}

data class MutableIngredientRange(
    var from: Long,
    var to: Long
) {
    fun toImmutable() = IngredientRange(
        from = this.from,
        to = this.to
    )
}

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
    return removeIngredientRangeOverlaps(ingredients.fresh).sumOf {
        println(it)
        (it.to - it.from) + 1
    }
}

fun removeIngredientRangeOverlaps(ingredientRanges: List<IngredientRange>): List<IngredientRange> {
    val newIngredientRanges = ingredientRanges.map { it.toMutable() }
    for ((index, ingredientRange) in newIngredientRanges.withIndex()) {
        for ((compareIndex, compareIngredientRange) in newIngredientRanges.withIndex()) {
            if (index == compareIndex) continue
            val fromInRange = (ingredientRange.from >= compareIngredientRange.from
                    && ingredientRange.from <= compareIngredientRange.to
                    )
            val toInRange = (ingredientRange.to >= compareIngredientRange.from
                    && ingredientRange.to <= compareIngredientRange.to)
            if (fromInRange && toInRange) {
                ingredientRange.from = 0
                ingredientRange.to = 0
            } else {
                if (
                    fromInRange
                ) {
                    ingredientRange.from = compareIngredientRange.to + 1
                }
                if (
                    toInRange
                ) {
                    ingredientRange.to = compareIngredientRange.from - 1
                }
            }
        }
    }

    return newIngredientRanges
        .filter { it.from > 0 && it.to > 0 }
        .map { it.toImmutable() }
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