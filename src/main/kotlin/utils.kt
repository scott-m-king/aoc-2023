import java.util.*

fun parseInput(input: Scanner) = sequence {
    while (input.hasNext()) yield(input.nextLine())
}