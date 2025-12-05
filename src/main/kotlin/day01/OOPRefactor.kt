package day01

import java.io.File
import kotlin.math.abs
import kotlin.properties.Delegates

fun main() {
    val safe = Safe(50)
    File("src/main/kotlin/day01/input.txt").forEachLine {
        safe.turnDial(it)
    }
    val combination = safe.getCombination()
    println("Combination: $combination")
}

class Safe {
    var currentPosition by Delegates.notNull<Int>()
        private set;
    private var numZeroes = 0

    constructor(startingPos: Int) {
        currentPosition = startingPos
    }

    fun getCombination(): Int {
        return numZeroes
    }

    fun turnDial(rotation: String) {
        val totalTurns = getTurns(rotation)
        val completeTurns = abs(totalTurns.div(100))
        this.numZeroes += completeTurns
        val turns = totalTurns % 100
        (this.currentPosition + turns).let { newPosition ->
            if ((newPosition <= 0 || newPosition > 99) && this.currentPosition != 0) numZeroes++
            this.currentPosition = when {
                newPosition < 0 -> 100 - abs(newPosition)
                newPosition > 99 -> newPosition - 100
                else -> newPosition
            }
        }
    }
}