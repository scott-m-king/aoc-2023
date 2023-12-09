package day09

import parseInput
import java.util.Scanner

class Day(val input: Scanner) {
    fun starOne(): Int {
        val lines = parseInput(input).map { it -> it.split(" ").map { it.toInt() } }.toList()
        val res = lines.map(::generateDifferences)

        var sum = 0
        res.forEach {
            it.indices.reversed().forEach { i ->
                sum += it[i].last()
            }
        }

        return sum
    }

    private fun generateDifferences(nums: List<Int>, result: MutableList<List<Int>> = mutableListOf(nums)): List<List<Int>> {
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
