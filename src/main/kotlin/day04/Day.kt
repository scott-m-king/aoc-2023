package day04

import com.sun.xml.internal.fastinfoset.util.StringArray
import parseInput
import java.util.Scanner

class Day(val input: Scanner) {
    fun starOne(): Int {
        val rounds = parseInput(input)
            .map { it.split("|") }
            .map { (left, right) ->
                listOf(
                    left.split(":")[1].split(" ").filter { it.isNotEmpty() },
                    right.split(" ").filter { it.isNotEmpty() }
                )
            }
            .toList()

        var result = 0
        for ((card, winningNums) in rounds) {
            val intersect = card.toSet().intersect(winningNums.toSet())
            if (intersect.isNotEmpty()) {
                var curr = 1
                for (i in 0..<intersect.size - 1) {
                    curr *= 2
                }
                result += curr
            }
        }

        return result
    }

    fun starTwo(): Int {
        val rounds = parseInput(input)
            .map { it.split("|") }
            .map { (left, right) ->
                listOf(
                    left.split(":")[1].split(" ").filter { it.isNotEmpty() },
                    right.split(" ").filter { it.isNotEmpty() }
                )
            }
            .toList()

        val allCards = IntArray(rounds.size) { 1 }

        rounds.forEachIndexed { index, (card, winningNums) ->
            val intersect = card.toSet().intersect(winningNums.toSet())
            val copies = allCards[index]
            for (i in 1..intersect.size) {
                allCards[index + i] += copies
            }
        }

        return allCards.sum()
    }
}
