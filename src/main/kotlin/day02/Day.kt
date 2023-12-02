package day02

import parseInput
import java.util.Scanner

class Day(val input: Scanner) {
    private val mapping = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14
    )

    fun starOne(): Int = parseInput(input).sumOf { line ->
        val (id, game) = line.split(":")
        val (red, green, blue) = getValues(game)
        if (checkHand(red, green, blue)) id.split(" ")[1].toInt() else 0
    }

    fun starTwo(): Int = parseInput(input).sumOf { line ->
        val (_, game) = line.split(":")
        val (red, green, blue) = getValues(game)
        red * green * blue
    }

    private fun getValues(game: String): Triple<Int, Int, Int> {
        var (red, green, blue) = mutableListOf(0, 0, 0)
        game.split(";").forEach { cubes ->
            cubes.split(",").forEach { cube ->
                val (valueStr, colour) = cube.trim().split(" ")
                val value = valueStr.toInt()
                when (colour) {
                    "red"   -> red = maxOf(red, value)
                    "green" -> green = maxOf(green, value)
                    "blue"  -> blue = maxOf(blue, value)
                }
            }
        }
        return Triple(red, green, blue)
    }

    private fun checkHand(red: Int, green: Int, blue: Int): Boolean {
        return red <= mapping["red"]!! && green <= mapping["green"]!! && blue <= mapping["blue"]!!
    }
}
