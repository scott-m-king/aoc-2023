package day10

import parseInput
import java.util.Scanner

class Day(val input: Scanner) {
    private val dirs = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1) // up, down, left, right
    private val mazeChars = setOf('╬', '║', '═', '╚', '╝', '╗', '╔', 'x', ' ')

    fun starOne(): Int {
        val grid = parseInput(input).toList().map(String::toCharArray)
        val startingPos = getStartingPos(grid)
        return dfs(startingPos, grid, setOf(startingPos)).size / 2
    }

    fun starTwo(): Int {
        val grid = parseInput(input).toList().map(String::toCharArray)
        val startingPos = getStartingPos(grid)
        val loop = dfs(startingPos, grid, setOf(startingPos))
        visualizeLoop(grid, loop)
        dfs2(0 to 0, grid)
        grid.map { String(it).also(::println) }
        return 0
    }


    private fun dfs2(pos: Pair<Int, Int>, grid: List<CharArray>) {
        if (isOob(pos, grid) || mazeChars.contains(grid[pos.first][pos.second])) return
        grid[pos.first][pos.second] = ' '

        for ((row, col) in dirs) {
            val nextPos = pos.first + row to pos.second + col
            dfs2(nextPos, grid)
        }
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
        val (currRow, currCol) = pos
        val (up, down, left, right) = dirs
        when (grid[currRow][currCol]) {
            'S'  -> listOf(up, down, left, right)
            '|'  -> listOf(up, down)
            '-'  -> listOf(left, right)
            'L'  -> listOf(up, right)
            'J'  -> listOf(up, left)
            '7'  -> listOf(down, left)
            'F'  -> listOf(down, right)
            else -> listOf()
        }.forEach {
            val nextPos = currRow + it.first to currCol + it.second
            if (loop.isNotEmpty() && loop.contains(nextPos) || isOob(nextPos, grid)) return@forEach

            val nextCell = grid[nextPos.first][nextPos.second]

            when (it) {
                up    -> if (listOf('|', '7', 'F').contains(nextCell)) return nextPos
                down  -> if (listOf('|', 'J', 'L').contains(nextCell)) return nextPos
                left  -> if (listOf('-', 'L', 'F').contains(nextCell)) return nextPos
                right -> if (listOf('-', '7', 'J').contains(nextCell)) return nextPos
            }
        }
        return 0 to 0
    }

    private fun visualizeLoop(grid: List<CharArray>, loop: Set<Pair<Int, Int>>) {
        for (r in grid.indices) {
            for (c in grid[0].indices) {
                if (loop.contains(r to c)) {
                    grid[r][c] = when (grid[r][c]) {
                        'S'  -> '╬'
                        '|'  -> '║'
                        '-'  -> '═'
                        'L'  -> '╚'
                        'J'  -> '╝'
                        '7'  -> '╗'
                        'F'  -> '╔'
                        else -> 'x'
                    }
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
        if (isOob(currentPos, grid)) return loop
        val next = getNextPos(currentPos, grid, loop)
        if (next == 0 to 0) return loop
        return dfs(next, grid, loop + next)
    }

    private fun isOob(pos: Pair<Int, Int>, grid: List<CharArray>) =
        pos.first < 0 || pos.second < 0 || pos.first >= grid.size || pos.second >= grid[0].size
}
