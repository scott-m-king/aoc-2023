package day02

import parseInput
import java.util.Scanner

class Day(val input: Scanner) {
    private val mapping = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14
    )

    fun starOne(): Int {
        val input = parseInput(input)
        var result = 0
        for (line in input) {
            var (red, green, blue) = mutableListOf(0, 0, 0)
            val (id, game) = line.split(":")
            val hands = game.split(";")
            for (hand in hands) {
                val cubes = hand.split(",")
                for (cube in cubes) {
                    val (valueStr, colour) = cube.trim().split(" ")
                    val value = valueStr.toInt()
                    when (colour) {
                        "red" -> red = red.coerceAtLeast(value)
                        "green" -> green = green.coerceAtLeast(value)
                        "blue" -> blue = blue.coerceAtLeast(value)
                    }
                }
            }
            if (checkHand(red, green, blue)) {
                result += id.split(" ")[1].toInt()
            }
        }
        return result
    }

    private fun checkHand(red: Int, green: Int, blue: Int): Boolean {
        return red <= mapping["red"]!! && green <= mapping["green"]!! && blue <= mapping["blue"]!!
    }

    fun starTwo() {
        TODO()
    }
}
