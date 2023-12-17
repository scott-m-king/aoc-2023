package day11

import directions
import isOob
import parseInput
import java.util.Deque
import java.util.LinkedList
import java.util.Scanner
import kotlin.math.abs

class Day(val input: Scanner) {
    fun starOne(): Int {
        val lines = parseInput(input).map { it.toCharArray().toMutableList() }.toMutableList()
        val emptyRows = lines.foldIndexed(mutableListOf<Int>()) { i, acc, curr ->
            if (!curr.contains('#')) {
                acc.add(i)
            }
            acc
        }
        val emptyCols = lines[0].indices.fold(mutableListOf<Int>()) { acc, curr ->
            if (!lines.map { it[curr] }.contains('#')) {
                acc.add(curr)
            }
            acc
        }

        for (col in emptyCols.reversed()) {
            lines.forEach {
                it.add(col, '.')
            }
        }

        for (row in emptyRows.reversed()) {
            lines.add(row, lines[0].indices.map { '.' }.toMutableList())
        }

        val galaxies = mutableListOf<Pair<Int, Int>>()
        for (row in lines.indices) {
            for (col in lines[0].indices) {
                if (lines[row][col] == '#') {
                    galaxies.add(row to col)
                }
            }
        }

        var result = 0
        for (i in galaxies.indices) {
            val src = galaxies[i]
            for (j in i + 1..<galaxies.size) {
                val dest = galaxies[j]
                result += abs(src.first - dest.first) + abs(src.second - dest.second)
            }
        }

        return result
    }

    fun starTwo(): Int {
        TODO()
    }
}
