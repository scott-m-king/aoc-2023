package day06

import parseInput
import java.util.Scanner

class Day(val input: Scanner) {
    fun starOne(): Int {
        val races = parseInput(input).map { it.split(" ").filter(String::isNotEmpty).drop(1) }.toList()
        val result = races[0].indices.map { i ->
            var counter = 0
            val time = races[0][i].toInt()
            val distance = races[1][i].toInt()
            (0..time).forEach { iter ->
                val diff = time - iter
                val traveled = iter * diff
                if (traveled > distance) counter++
            }
            counter
        }
        return result.fold(1) { acc, curr -> acc * curr }
    }

    fun starTwo(): Long {
        val races = parseInput(input).map { it ->
            Regex(" [0-9]+").findAll(it).map { it.value.trim() }.joinToString("")
        }.toList()

        var counter: Long = 0
        val time = races[0].toLong()
        val distance = races[1].toLong()
        println("time: $time, distance: $distance")
        (0..time).forEach { iter ->
            val diff = time - iter
            val traveled = iter * diff
            if (traveled > distance) counter++
        }
        return counter
    }
}
