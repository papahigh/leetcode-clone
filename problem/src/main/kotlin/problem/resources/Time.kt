package problem.resources

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

/**
 * Represents a duration of time measured in milliseconds.
 */
class Time internal constructor(
    private val value: Int
) : Comparable<Time> {

    @JsonValue
    fun millis(): Int = value
    fun seconds(): Double = value.toDouble() / 1000
    fun minutes(): Double = value.toDouble() / (60 * 1000)

    override fun compareTo(other: Time): Int = value.compareTo(other.value)

    override fun equals(other: Any?): Boolean = other is Time && other.value == value
    override fun hashCode(): Int = value.hashCode()
    override fun toString(): String = "${value}ms"

    companion object {

        @JsonCreator
        fun ofMillis(millis: Int): Time = Time(millis)

        inline val Int.millis get() = ofMillis(this)
        inline val Int.seconds get() = ofMillis(this * 1000)
        inline val Int.minutes get() = ofMillis(this * 60 * 1000)
    }
}
