package day09

import parseInput
import java.util.Scanner

class Day(val input: Scanner) {
    fun starOne(): Int = parseInput(input).map { it -> it.split(" ").map { it.toInt() } }.toList()
        .map(::generateDifferences)
        .sumOf { row -> row.reversed().sumOf { it.last() } }

    private fun generateDifferences(
        nums: List<Int>,
        result: MutableList<List<Int>> = mutableListOf(nums)
    ): List<List<Int>> = when {
        nums.all { it == 0 } -> result
        else                 -> (1..<nums.size).fold(mutableListOf()) { acc: MutableList<Int>, i ->
            acc.add(nums[i] - nums[i - 1])
            acc
        }.let { generateDifferences(it, result.apply { add(it) }) }
    }

    fun starTwo(): Int {
        TODO()
    }
}
