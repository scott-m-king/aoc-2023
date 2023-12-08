package day08

import parseInput
import java.util.Scanner

class Day(val input: Scanner) {
    private lateinit var originalInstruction: String;
    private lateinit var nodes: Map<String, Pair<String, String>>;

    fun starOne(): Int {
        val (instruction, network) = parseInput(input).toList().let { it.first() to it.drop(2) }
        originalInstruction = instruction
        nodes = network.fold(mutableMapOf()) { acc, curr ->
            val (key, value) = curr.split(" = ")
            val edges = value.split(", ").run { first().replace("(", "") to elementAt(1).replace(")", "") }
            acc[key] = edges
            acc
        }

        return countSteps("AAA", instruction)
    }

    private tailrec fun countSteps(position: String, instruction: String, count: Int = 0): Int {
        if (position == "ZZZ") return count
        if (instruction.isEmpty()) return countSteps(position, originalInstruction, count)
        val nextPos = when (instruction.first()) {
            'L' -> nodes[position]!!.first
            else -> nodes[position]!!.second
        }
        return countSteps(nextPos, instruction.drop(1), count + 1)
    }

    fun starTwo(): Int {
        TODO()
    }
}
