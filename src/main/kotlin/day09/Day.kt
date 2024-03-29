package day09

import parseInput
import java.util.Scanner

class Day(val input: Scanner) {
    fun starOne(): Int = getHistories(input).sumOf { it.reversed().sumOf(List<Int>::last) }

    fun starTwo(): Int = getHistories(input).sumOf {
        (1..<it.size).reversed().fold(0) { acc, i -> it[i - 1].first() - acc }.toInt()
    }

    private fun getHistories(input: Scanner) =
        parseInput(input).map { it.split(" ").map(String::toInt) }.map(::generateDifferences)

    private fun generateDifferences(nums: List<Int>, result: List<List<Int>> = mutableListOf(nums)): List<List<Int>> =
        when {
            nums.all { it == 0 } -> result
            else                 -> (1..<nums.size)
                .fold(listOf<Int>()) { acc, i -> acc + listOf(nums[i] - nums[i - 1]) }
                .let { generateDifferences(it, result + listOf(it)) }
        }
}
