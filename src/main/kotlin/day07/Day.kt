package day07

import parseInput
import java.util.Scanner

class Day(val input: Scanner) {
    private val cards = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')

    fun starOne(): Int = parseInput(input).map { it.split(" ") }.toList().sortedWith { (a), (b) ->
        val typeA = countCards(a).getType()
        val typeB = countCards(b).getType()
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
/*        val noJoker = cards.filter { it != 'J' }.toList()
        return parseInput(input).map { it.split(" ") }.toList().map { (hand, bet) ->
            if (!hand.contains('J')) {
                return@map listOf(hand, bet)
            }

            val arr = hand.toCharArray()
            var max: Pair<String, Int> = "" to 0
            hand.indices.forEach { i ->
                noJoker.forEach { card ->
                    val curr = arr.clone()
                    curr[i] = card
                    val maxScore = maxOf(max.second, getType(countCards(curr.toString())))
                    if (maxScore > max.second) {
                        max = curr.toString() to maxScore
                    }
                }
            }

            listOf(max.first, bet)
        }.toList().sortedWith { (a), (b) ->
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
        }.map { (_, bet) -> bet.toInt() }.foldIndexed(0) { i, acc, curr -> acc + curr * (i + 1) }*/
        return 0
    }

    private fun countCards(cards: String) = cards.groupingBy { it }.eachCount()

    private fun Map<Char, Int>.getType() = when {
        containsValue(5)                     -> 7
        containsValue(4)                     -> 6
        containsValue(3) && containsValue(2) -> 5
        containsValue(3)                     -> 4
        values.filter { it == 2 }.size == 2  -> 3
        values.filter { it == 2 }.size == 1  -> 2
        else                                 -> 1
    }

}
