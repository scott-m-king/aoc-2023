package day03

import parseInput
import java.lang.StringBuilder
import java.util.Scanner

class Day(val input: Scanner) {
    private lateinit var symbols: Set<Char>

    fun starOne(): Int {
        val input = parseInput(input).toList()

        symbols = input.map { it ->
            it.filter { !it.isDigit() && it != '.' }.toList()
        }
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
                            println(num)
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
//        println("row: $row, col: $col, size: $size")
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

    fun starTwo() {
        TODO()
    }
}
