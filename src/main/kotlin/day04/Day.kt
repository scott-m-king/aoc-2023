package day04

import parseInput
import java.util.Scanner

class Day(val input: Scanner) {
    fun starOne(): Int = getMatchingNums(input).sumOf {
        it.fold(0) { result, _ -> if (result == 0) 1 else result * 2 }.toInt()
    }

    fun starTwo(): Int = getMatchingNums(input).toList().let {
        it.foldIndexed(IntArray(it.size) { 1 }) { index, acc, intersection ->
            intersection.forEachIndexed { i, _ -> acc[index + i + 1] += acc[index] }
            acc
        }.sum()
    }

    private fun getMatchingNums(input: Scanner) = parseInput(input)
        .map { it.split("|") }
        .map { (left, right) ->
            left.split(":")[1].split(" ").filter { it.isNotEmpty() }.toSet()
                .intersect(right.split(" ").filter { it.isNotEmpty() }.toSet())
        }
}
