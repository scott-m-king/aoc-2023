package day12

import parseInput
import java.util.Scanner

class Day(val input: Scanner) {
    private val memo = mutableMapOf<String, List<String>>()

    fun starOne(): Int {
        val lines = parseInput(input)
            .map { it.split(" ") }
            .map { it[0].split(".").filter(String::isNotEmpty) to it[1].split(",").map(String::toInt) }
            .toList()

        var result = 0
        for ((groups, counts) in lines) {
            val combos = groups.map {
                val combinations = mutableListOf<String>()
                generateCombos(it, combinations, it.length)
                combinations
            }
            result += countValidCombos(combos, counts)
        }
        return result
    }

    private fun countValidCombos(combos: List<List<String>>, counts: List<Int>): Int =
        getCounts(generateFinalCombo(combos)).filter { it == counts }.size

    private fun generateFinalCombo(
        combos: List<List<String>>,
        row: Int = 0,
        path: String = "",
        result: MutableList<String> = mutableListOf()
    ): List<String> {
        when {
            row == combos.size -> result.add(path)
            else               -> combos[row].forEach { generateFinalCombo(combos, row + 1, "$path.$it", result) }
        }
        return result
    }

    private fun getCounts(products: List<String>) = products.map { it ->
        it.split(".").filter { it.isNotEmpty() }.map { it.length }
    }

    private fun generateCombos(
        group: String,
        result: MutableList<String>,
        size: Int,
        path: String = "",
    ) {
        when {
            path.length == size                   -> result.add(path)
            memo.containsKey(group)               -> result.addAll(memo[group]!!)
            group.isEmpty() || path.length > size -> return
            group.first() == '?'                  -> listOf('.', '#').forEach {
                generateCombos(group.drop(1), result, size, "$path$it")
            }

            else                                  -> generateCombos(
                group.drop(1),
                result,
                size,
                "$path${group.first()}"
            )

        }
    }

    fun starTwo(): Int {
        return 0
    }
}
