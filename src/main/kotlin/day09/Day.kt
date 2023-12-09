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
    ): List<List<Int>> {
        if (nums.all { it == 0 }) return result

        val differences: MutableList<Int> = mutableListOf()
        (1..<nums.size).forEach { differences.add(nums[it] - nums[it - 1]) }
        result.add(differences)

        return generateDifferences(differences, result)
    }

    fun starTwo(): Int {
        TODO()
    }
}
