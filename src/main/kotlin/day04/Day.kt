package day04

import com.sun.xml.internal.fastinfoset.util.StringArray
import parseInput
import java.util.Scanner

class Day(val input: Scanner) {
    fun starOne(): Int = getRounds(input).sumOf { (card, winningNums) ->
        card.toSet().intersect(winningNums.toSet()).fold(0) { result, _ ->
            if (result == 0) 1 else result * 2
        }.toInt()
    }

    fun starTwo(): Int {
        val rounds = getRounds(input).toList()
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

    private fun getRounds(input: Scanner) = parseInput(input)
        .map { it.split("|") }
        .map { (left, right) ->
            listOf(
                left.split(":")[1].split(" ").filter { it.isNotEmpty() },
                right.split(" ").filter { it.isNotEmpty() }
            )
        }
}
