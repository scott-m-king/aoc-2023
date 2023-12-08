package day08

import parseInput
import java.util.Scanner

class Day(val input: Scanner) {
    fun starOne(): Int {
        val (instruction, network) = parseInput(input).toList().let { it.first() to it.drop(2) }
        val nodes = network.fold(mutableMapOf<String, Pair<String, String>>()) { acc, curr ->
            val (key, value) = curr.split(" = ")
            val edges = value.split(", ").run { first().replace("(", "") to elementAt(1).replace(")", "") }
            acc[key] = edges
            acc
        }

        var steps = 0
        var position = "AAA"

        while (position != "ZZZ") {
            instruction.forEach {
                when (it) {
                    'L' -> position = nodes[position]!!.first
                    'R' -> position = nodes[position]!!.second
                }
                steps++
            }
        }

        return steps
    }

    fun starTwo(): Int {
        TODO()
    }
}
