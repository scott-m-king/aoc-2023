package day07

import parseInput
import java.util.Scanner

class Day(val input: Scanner) {
    private val cards = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')

    fun starOne(): Int = parseInput(input).map { it.split(" ") }.toList().sortedWith { (a), (b) ->
        val typeA = getType(countCards(a))
        val typeB = getType(countCards(b))
        when {
            typeA > typeB -> return@sortedWith 1
            typeA < typeB -> return@sortedWith -1
        }
        a.indices.forEach { i ->
            val cardARank = cards.indexOf(a[i])
            val cardBRank = cards.indexOf(b[i])
            when {
                cardARank < cardBRank -> return@sortedWith 1
                cardARank > cardBRank -> return@sortedWith -1
            }
        }
        0
    }.map { (_, bet) -> bet.toInt() }.foldIndexed(0) { i, acc, curr -> acc + curr * (i + 1) }

    fun starTwo(): Int {
        return 0
    }

    private fun countCards(cards: String) = cards.groupingBy { it }.eachCount()

    private fun getType(hand: Map<Char, Int>) = hand.run {
        when {
            containsValue(5)                     -> 7
            containsValue(4)                     -> 6
            containsValue(3) && containsValue(2) -> 5
            containsValue(3)                     -> 4
            values.filter { it == 2 }.size == 2  -> 3
            values.filter { it == 2 }.size == 1  -> 2
            else                                 -> 1
        }
    }
}
