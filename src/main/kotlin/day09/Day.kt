package day09

import parseInput
import java.util.Scanner

class Day(val input: Scanner) {
    fun starOne(): Int = parseInput(input).map { it -> it.split(" ").map { it.toInt() } }
        .map(::generateDifferences)
        .sumOf { history -> history.reversed().sumOf { it.last() } }

    fun starTwo(): Int = parseInput(input).map { it -> it.split(" ").map { it.toInt() } }
        .map(::generateDifferences)
        .sumOf { history ->
            (1..<history.size).reversed().fold(0) { acc, i -> history[i - 1].first() - acc }.toInt()
        }

    private fun generateDifferences(nums: List<Int>, result: List<List<Int>> = mutableListOf(nums)): List<List<Int>> =
        when {
            nums.all { it == 0 } -> result
            else                 -> (1..<nums.size)
                .fold(listOf<Int>()) { acc, i -> acc + listOf(nums[i] - nums[i - 1]) }
                .let { diffs -> generateDifferences(diffs, result + listOf(diffs)) }
        }
}
