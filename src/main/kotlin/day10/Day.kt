package day10

import parseInput
import java.util.Scanner

class Day(val input: Scanner) {
    private val dirs = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1) // up, down, left, right

    fun starOne(): Int {
        val grid = parseInput(input).toList().map(String::toCharArray)
        var startingPos: Pair<Int, Int>? = null;
        for (row in grid.indices) {
            for (col in grid[row].indices) {
                if (grid[row][col] == 'S') {
                    startingPos = row to col
                    break
                }
            }
            if (startingPos != null) break
        }

        return dfs(startingPos!!, grid, listOf(startingPos)).size / 2
    }

    private fun getNextPos(pos: Pair<Int, Int>, grid: List<CharArray>, loop: List<Pair<Int, Int>>): Pair<Int, Int> {
        val currCell = grid[pos.first][pos.second]
        val (currRow, currCol) = pos
        val (up, down, left, right) = dirs

        val possibleDirs = when (currCell) {
            'S'  -> listOf(up, down, left, right)
            '|'  -> listOf(up, down)
            '-'  -> listOf(left, right)
            'L'  -> listOf(up, right)
            'J'  -> listOf(up, left)
            '7'  -> listOf(down, left)
            'F'  -> listOf(down, right)
            else -> listOf()
        }

//        println("currCell: $currCell, pos: $pos, possibleDirs: $possibleDirs")

        for (dir in possibleDirs) {
            val (row, col) = dir
            val nextPos = currRow + row to currCol + col

            val (nextRow, nextCol) = nextPos
            if (loop.isNotEmpty() && loop.contains(nextPos)) continue
//            println("loop: ${if (loop.isNotEmpty()) loop.last() else null}, nextPos: $nextPos")

            val nextCell = grid[nextRow][nextCol]
//            println("loop: $loop, nextCell: $nextCell, dir: $dir")

            when (dir) {
                up    -> if (listOf('|', '7', 'F').contains(nextCell)) return nextPos
                down  -> if (listOf('|', 'J', 'L').contains(nextCell)) return nextPos
                left  -> if (listOf('-', 'L', 'F').contains(nextCell)) return nextPos
                right -> if (listOf('-', '7', 'J').contains(nextCell)) return nextPos
            }
        }
        return 0 to 0
    }

    private tailrec fun dfs(
        currentPos: Pair<Int, Int>,
        grid: List<CharArray>,
        loop: List<Pair<Int, Int>>
    ): List<Pair<Int, Int>> {
        val (row, col) = currentPos
        if (isOob(row, col, grid)) return loop
        val next = getNextPos(currentPos, grid, loop)
        if (next == 0 to 0) return loop
        return dfs(next, grid, loop + next)
    }

    private fun isOob(row: Int, col: Int, grid: List<CharArray>) =
        row < 0 || col < 0 || row >= grid[0].size || col >= grid.size

    fun starTwo(): Int {
        TODO()
    }
}
