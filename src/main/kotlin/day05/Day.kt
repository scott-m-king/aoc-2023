package day05

import parseInput
import java.util.Scanner

class Day(val input: Scanner) {
    private val positionMapping = mapOf(
        "seed" to "soil",
        "soil" to "fertilizer",
        "fertilizer" to "water",
        "water" to "light",
        "light" to "temperature",
        "temperature" to "humidity",
        "humidity" to "location"
    )

    fun starOne(): Long {
        val lines = parseInput(input).toList()
        val seeds = lines[0]
            .split(":")[1].split(" ")
            .map(String::trim)
            .filter(String::isNotEmpty)
            .map(String::toLong)

        val maps = lines.drop(1).fold(mutableListOf<MutableList<String>>()) { acc, curr ->
            if (curr.isEmpty()) {
                acc.add(mutableListOf())
                return@fold acc
            }
            acc.last().add(curr)
            acc
        }.fold(mutableMapOf<String, Map<Long, Long>>()) { acc, curr ->
            val key = curr[0].split(" ")[0].split("-to-")[1]
            val mappings = curr.drop(1).map {
                it.split(" ").filter(String::isNotEmpty).let { mapping ->
                    println(mapping)
                    val (dest, source, range) = mapping.map(String::toLong)
                    listOf(dest, source, range)
                }
            }
            val map = mutableMapOf<Long, Long>()
            mappings.forEach { (dest, source, range) ->
                (0..<range).forEach { map[source + it] = dest + it }
            }
            acc[key] = map
            acc
        }
        return 0

//        var minLocation = Long.MAX_VALUE
//        var position: String? = "soil"
//
//        for (seed in seeds) {
//            var curr = seed
//            while (position != null) {
//                curr = maps[position]!!.getOrDefault(curr, curr)
//                position = positionMapping[position]
//            }
//            position = "soil"
//            minLocation = minOf(minLocation, curr)
//        }
//
//        return minLocation
    }

    fun starTwo(): Int {
        TODO()
    }
}
