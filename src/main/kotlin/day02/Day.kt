package day02

import parseInput
import java.util.Scanner

class Day(val input: Scanner) {
    private val mapping = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14
    )

    fun starOne(): Int = parseInput(input).fold(0) { acc, line ->
        val (id, game) = line.split(":")
        val (red, green, blue) = getValues(game)
        acc + (if (checkHand(red, green, blue)) id.split(" ")[1].toInt() else 0)
    }

    fun starTwo(): Int = parseInput(input).fold(0) { acc, line ->
        val (_, game) = line.split(":")
        val (red, green, blue) = getValues(game)
        acc + red * green * blue
    }

    private fun getValues(game: String): List<Int> {
        var (red, green, blue) = mutableListOf(0, 0, 0)
        val hands = game.split(";")
        hands.forEach { hand ->
            hand.split(",").forEach { cube ->
                val (valueStr, colour) = cube.trim().split(" ")
                val value = valueStr.toInt()
                when (colour) {
                    "red" -> red = red.coerceAtLeast(value)
                    "green" -> green = green.coerceAtLeast(value)
                    "blue" -> blue = blue.coerceAtLeast(value)
                }
            }
        }
        return listOf(red, green, blue)
    }

    private fun checkHand(red: Int, green: Int, blue: Int): Boolean {
        return red <= mapping["red"]!! && green <= mapping["green"]!! && blue <= mapping["blue"]!!
    }
}
