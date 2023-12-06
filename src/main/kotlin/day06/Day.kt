package day06

import parseInput
import java.util.Scanner

class Day(val input: Scanner) {
    fun starOne(): Int = parseInput(input).map { it.split(" ").filter(String::isNotEmpty).drop(1) }
        .toList().let { (times, distances) ->
            distances.foldIndexed(1) { i, acc, distance ->
                val time = times[i].toInt()
                acc * (0..time).reduce { counter, iter ->
                    if (iter * (time - iter) > distance.toInt()) counter + 1 else counter
                }
            }
        }

    fun starTwo(): Long = parseInput(input).map { it ->
        Regex(" [0-9]+").findAll(it).map { it.value.trim() }.joinToString("")
    }.map(String::toLong).toList().let { (time, distance) ->
        (0.toLong()..time).reduce { acc, iter -> if (iter * (time - iter) > distance) acc + 1 else acc }
    }
}
