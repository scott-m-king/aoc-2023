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
        }.fold(mutableMapOf<String, List<List<Long>>>()) { acc, curr ->
            val key = curr[0].split(" ")[0].split("-to-")[1]
            val mappings = curr.drop(1).map {
                it.split(" ").filter(String::isNotEmpty).map(String::toLong)
            }
            acc[key] = mappings
            acc
        }
        var minLocation = Long.MAX_VALUE
        var position: String? = "soil"

        for (i in seeds.indices) {
            var curr = seeds[i]
            while (position != null) {
                for ((dest, source, range) in maps[position]!!) {
                    if (curr >= source && curr <= source + range) {
                        curr = (curr - source) + dest
                        break
                    }
                }
                position = positionMapping[position]
            }
            position = "soil"
            minLocation = minOf(minLocation, curr)
        }

        return minLocation
    }

    fun starTwo(): Int {
        TODO()
    }
}
