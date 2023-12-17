import java.util.*

val directions = listOf(-1 to 0, 0 to 1, 0 to -1, 1 to 0) // up, down, left, right

fun parseInput(input: Scanner) = sequence {
    while (input.hasNext()) yield(input.nextLine())
}

fun <T> isOob(pos: Pair<Int, Int>, grid: List<List<T>>) =
    pos.first < 0 || pos.second < 0 || pos.first >= grid.size || pos.second >= grid[0].size
