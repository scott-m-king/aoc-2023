package day10

import parseInput
import java.util.Scanner

class Day(val input: Scanner) {
    private val dirs = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1) // up, down, left, right

    fun starOne(): Int {
        val grid = parseInput(input).toList().map(String::toCharArray)
        val startingPos = getStartingPos(grid)
        return dfs(startingPos, grid, setOf(startingPos)).size / 2
    }

    fun starTwo(): Int {
        val grid = parseInput(input).toList().map(String::toCharArray)
        val startingPos = getStartingPos(grid)
        val loop = dfs(startingPos, grid, setOf(startingPos))
        printLoop(grid, loop)

//        for (r in grid.indices) {
//            for (c in grid[0].indices) {
//                if (loop.contains(r to c)) {
//                    grid[r][c] = 'x'
//                }
//            }
//        }
        return 0
    }

    private fun getStartingPos(grid: List<CharArray>): Pair<Int, Int> {
        for (row in grid.indices) {
            for (col in grid[row].indices) {
                if (grid[row][col] == 'S') {
                    return row to col
                }
            }
        }
        return 0 to 0
    }

    private fun getNextPos(pos: Pair<Int, Int>, grid: List<CharArray>, loop: Set<Pair<Int, Int>>): Pair<Int, Int> {
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

        for (dir in possibleDirs) {
            val (row, col) = dir
            val nextPos = currRow + row to currCol + col


            val (nextRow, nextCol) = nextPos
//            println("currCell: $currCell, dir: $dir, nextPos: $nextPos, nextCell: $nextCell")
            if (loop.isNotEmpty() && loop.contains(nextPos) || isOob(nextRow, nextCol, grid)) continue
            val nextCell = grid[nextRow][nextCol]

            when (dir) {
                up    -> if (listOf('|', '7', 'F').contains(nextCell)) return nextPos
                down  -> if (listOf('|', 'J', 'L').contains(nextCell)) return nextPos
                left  -> if (listOf('-', 'L', 'F').contains(nextCell)) return nextPos
                right -> if (listOf('-', '7', 'J').contains(nextCell)) return nextPos
            }
        }
        return 0 to 0
    }

    private fun printLoop(grid: List<CharArray>, loop: Set<Pair<Int, Int>>) {
        for (r in grid.indices) {
            for (c in grid[0].indices) {
                if (loop.contains(r to c)) {
                    grid[r][c] = 'x'
                }
            }
        }
        grid.map { String(it).also(::println) }

    }

    private tailrec fun dfs(
        currentPos: Pair<Int, Int>,
        grid: List<CharArray>,
        loop: Set<Pair<Int, Int>>
    ): Set<Pair<Int, Int>> {
        val (row, col) = currentPos
        if (isOob(row, col, grid)) return loop
        val next = getNextPos(currentPos, grid, loop)
        if (next == 0 to 0) return loop
        return dfs(next, grid, loop + next)
    }

    private fun isOob(row: Int, col: Int, grid: List<CharArray>) =
        row < 0 || col < 0 || row >= grid.size || col >= grid[0].size
}
