package day01

import java.util.Scanner
import parseInput

class Day(val input: Scanner) {
    private val numbers = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
    )

    fun starOne(): Int = parseInput(input).fold(0) { acc, curr ->
        val firstDigit = curr.first { it.isDigit() }
        val lastDigit = curr.last { it.isDigit() }
        acc + "$firstDigit$lastDigit".toInt()
    }

    fun starTwo(): Int = parseInput(input).fold(0) { acc, curr ->
        val firstDigit = getDigit(curr, false)
        val lastDigit = getDigit(curr.reversed(), true)
        acc + "$firstDigit$lastDigit".toInt()
    }

    private fun getDigit(curr: String, isReversed: Boolean): Int {
        for (i in curr.indices) {
            if (curr[i].isDigit()) {
                return curr[i].digitToInt()
            }
            for (j in 0..<i) {
                var substr = curr.substring(j..i)

                if (isReversed) {
                    substr = substr.reversed()
                }

                if (numbers.containsKey(substr)) {
                    return numbers[substr]!!.toInt()
                }
            }
        }
        return 0
    }
}
