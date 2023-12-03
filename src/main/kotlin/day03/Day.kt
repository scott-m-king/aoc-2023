package day03

import parseInput
import java.lang.StringBuilder
import java.util.Scanner

class Day(val input: Scanner) {
    private lateinit var symbols: Set<Char>

    fun starOne(): Int {
        val input = parseInput(input).toList()

        symbols = input.map { it -> it.filter { !it.isDigit() && it != '.' }.toList() }
            .flatten()
            .toSet()

        var result = 0

        input.forEachIndexed { row, line ->
            val sb = StringBuilder()
            "$line.".forEachIndexed { col, char ->
                if (char.isDigit()) {
                    sb.append(char)
                } else {
                    val num = sb.toString().toIntOrNull()
                    if (num != null) {
                        if (checkSurrounding(input, row, col - sb.length, sb.length)) {
                            result += num
                        }
                    }
                    sb.clear()
                }
            }
        }

        return result
    }

    private fun checkSurrounding(matrix: List<String>, row: Int, col: Int, size: Int): Boolean {
        for (i in 0..<size) {
            for (r in -1..1) {
                for (c in -1..1) {
                    val x = row + r
                    val y = i + col + c
                    if (x >= 0 && y >= 0 && x < matrix[0].length && y < matrix.size) {
                        val cell = matrix[x][y]
                        if (symbols.contains(cell)) {
                            return true
                        }
                    }
                }
            }
        }
        return false
    }

    fun starTwo(): Int {
        val input = parseInput(input).toList()
        val gears: MutableMap<Pair<Int, Int>, MutableList<Int>> = HashMap()

        input.forEachIndexed { row, line ->
            val sb = StringBuilder()
            "$line.".forEachIndexed { col, char ->
                if (char.isDigit()) {
                    sb.append(char)
                } else {
                    val num = sb.toString().toIntOrNull()
                    if (num != null) {
                        val gearCoords = getGearCoords(input, row, col - sb.length, sb.length)
                        if (gearCoords != null) {
                            gears.putIfAbsent(gearCoords, mutableListOf())
                            gears[gearCoords]?.add(num)
                        }
                    }
                    sb.clear()
                }
            }
        }

        return gears
            .filter { it.value.size == 2 }
            .map { it.value }
            .sumOf { it.fold(1) { acc, curr -> acc * curr }.toInt() }
    }

    private fun getGearCoords(matrix: List<String>, row: Int, col: Int, size: Int): Pair<Int, Int>? {
        for (i in 0..<size) {
            for (r in -1..1) {
                for (c in -1..1) {
                    val x = row + r
                    val y = i + col + c
                    if (x >= 0 && y >= 0 && x < matrix[0].length && y < matrix.size) {
                        val cell = matrix[x][y]
                        if (cell == '*') {
                            return x to y
                        }
                    }
                }
            }
        }
        return null
    }
}
