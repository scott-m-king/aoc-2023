package day06

import parseInput
import java.util.Scanner

class Day(val input: Scanner) {
    fun starOne(): Int = parseInput(input).map { it.split(" ").filter(String::isNotEmpty).drop(1) }
        .toList()
        .let { (times, distances) ->
            times.foldIndexed(1) { i, acc, _ ->
                val time = times[i].toInt()
                val distance = distances[i].toInt()
                acc * (0..time).foldIndexed(0) { iter, counter, _ ->
                    if (iter * (time - iter) > distance) counter + 1 else counter
                }
            }
        }

    fun starTwo(): Long = parseInput(input).map { it ->
        Regex(" [0-9]+").findAll(it).map { it.value.trim() }.joinToString("")
    }.map(String::toLong).toList().let { (time, distance) ->
        (0..time).foldIndexed(0.toLong()) { iter, acc, _ -> if (iter * (time - iter) > distance) acc + 1 else acc }
    }
}
