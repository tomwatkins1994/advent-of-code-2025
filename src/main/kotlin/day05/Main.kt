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
        (it.to - it.from) + 1
    }
}

fun removeIngredientRangeOverlaps(ingredientRanges: List<IngredientRange>): List<IngredientRange> {
    val newIngredientRanges = ingredientRanges.map { it.toMutable() }
    for ((currentIndex, ingredientRange) in newIngredientRanges.withIndex()) {
        for ((compareIndex, compareTo) in newIngredientRanges.withIndex()) {
            if (currentIndex == compareIndex) continue

            val fromWithinRange = (ingredientRange.from in compareTo.from..compareTo.to)
            val toWithinRange = (ingredientRange.to in compareTo.from..compareTo.to)

            if (fromWithinRange && toWithinRange) {
                // Set these to 0 so we filter them out later
                ingredientRange.from = 0
                ingredientRange.to = 0
                continue
            }

            if (fromWithinRange) ingredientRange.from = compareTo.to + 1
            if (toWithinRange) ingredientRange.to = compareTo.from - 1
        }
    }

    return newIngredientRanges
        .filter { it.from > 0 && it.to > 0 }
        .map { it.toImmutable() }
}

fun parseInput(input: String): Ingredients {
    val fresh = mutableListOf<IngredientRange>()
    val available = mutableSetOf<Long>()

    var inRangesSection = true
    for (line in input.lines()) {
        if (line == "") {
            inRangesSection = false
            continue
        }
        if (inRangesSection) {
            val range = line.split("-")
            fresh.add(IngredientRange(from = range.first().toLong(), to = range.last().toLong()))
        } else {
            available.add(line.toLong())
        }
    }

    return Ingredients(fresh = fresh, available = available)
}