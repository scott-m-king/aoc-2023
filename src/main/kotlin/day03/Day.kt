package day03

import parseInput
import java.lang.StringBuilder
import java.util.Scanner

class Day(val input: Scanner) {

    fun starOne(): Int {
        val matrix = parseInput(input).toList()
        val sb = StringBuilder()
        return matrix.foldIndexed(0) { row, acc, line ->
            "$line.".foldIndexed(acc) { col, result, char ->
                if (char.isDigit()) {
                    sb.append(char)
                    result
                } else {
                    val num = sb.toString().toIntOrNull()
                    val len = sb.length
                    sb.clear()
                    result + if (num != null && checkSurrounding(matrix, row, col - len, len)) {
                        num
                    } else {
                        0
                    }
                }
            }
        }
    }

    private fun checkSurrounding(matrix: List<String>, row: Int, col: Int, size: Int): Boolean {
        for ((cell) in getSurrounding(matrix, row, col, size)) {
            if (!cell.isDigit() && cell != '.') {
                return true
            }
        }
        return false
    }

    fun starTwo(): Int {
        val input = parseInput(input).toList()
        val gears: MutableMap<Pair<Int, Int>, MutableList<Int>> = HashMap()

        input.forEachIndexed { row, line ->
            var sb = ""
            "$line.".forEachIndexed { col, char ->
                sb = if (char.isDigit()) {
                    "${sb}${char}"
                } else {
                    val num = sb.toIntOrNull()
                    if (num != null) {
                        val gearCoords = getGearCoords(input, row, col - sb.length, sb.length)
                        if (gearCoords != null) {
                            gears.putIfAbsent(gearCoords, mutableListOf())
                            gears[gearCoords]?.add(num)
                        }
                    }
                    ""
                }
            }
        }

        return gears
            .filter { it.value.size == 2 }
            .map { it.value }
            .sumOf { it.reduce { acc, curr -> acc * curr }.toInt() }
    }

    private fun getGearCoords(matrix: List<String>, row: Int, col: Int, size: Int): Pair<Int, Int>? {
        for ((cell, x, y) in getSurrounding(matrix, row, col, size)) {
            if (cell == '*') {
                return x to y
            }
        }
        return null
    }

    private fun getSurrounding(matrix: List<String>, row: Int, col: Int, size: Int) = sequence {
        for (i in 0..<size) {
            for (r in -1..1) {
                for (c in -1..1) {
                    val x = row + r
                    val y = i + col + c
                    if (x >= 0 && y >= 0 && x < matrix[0].length && y < matrix.size) {
                        yield(Triple(matrix[x][y], x, y))
                    }
                }
            }
        }
    }
}
