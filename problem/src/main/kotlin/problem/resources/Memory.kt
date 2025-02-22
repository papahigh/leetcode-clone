package problem.resources

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

/**
 * Represents a memory size in bytes with utility functions to convert between units.
 */
class Memory internal constructor(
    private val value: Int,
) : Comparable<Memory> {

    @JsonValue
    fun bytes() = value
    fun kilobytes(): Double = value.toDouble() / 1000.0
    fun megabytes(): Double = value.toDouble() / 1000.0 / 1000.0

    override fun compareTo(other: Memory): Int = value.compareTo(other.value)

    override fun equals(other: Any?): Boolean = other is Memory && other.value == value
    override fun hashCode(): Int = value.hashCode()
    override fun toString(): String = "${value}b"

    companion object {

        @JsonCreator
        fun ofBytes(bytes: Int): Memory = Memory(bytes)

        inline val Int.bytes get() = ofBytes(this)
        inline val Int.kilobytes get() = ofBytes(this * 1000)
        inline val Int.megabytes get() = ofBytes(this * 1000 * 1000)
    }
}
