package problem

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class Resources(val memory: Memory, val time: Time) {

    companion object {
        inline val Int.bytes get() = Memory.ofBytes(this)
        inline val Int.kilobytes get() = Memory.ofBytes(this * 1024)
        inline val Int.megabytes get() = Memory.ofBytes(this * 1024 * 1024)

        inline val Int.millis get() = Time.ofMillis(this)
        inline val Int.seconds get() = Time.ofMillis(this * 1000)
        inline val Int.minutes get() = Time.ofMillis(this * 60 * 1000)
    }

    class Memory internal constructor(
        private val value: Int
    ) : Comparable<Memory> {

        @JsonValue
        fun bytes(): Int = value
        fun kilobytes(): Int = value / 1024
        fun megabytes(): Int = value / 1024 / 1024

        override fun compareTo(other: Memory): Int = value.compareTo(other.value)

        override fun equals(other: Any?): Boolean = other is Memory && other.value == value
        override fun hashCode(): Int = value.hashCode()
        override fun toString(): String = "${value}b"

        companion object {
            @JsonCreator
            fun ofBytes(bytes: Int): Memory = Memory(bytes)
        }
    }

    class Time internal constructor(
        private val value: Int
    ) : Comparable<Time> {

        @JsonValue
        fun millis(): Int = value
        fun seconds(): Int = value / 1000
        fun minutes(): Int = value / (60 * 1000)

        override fun compareTo(other: Time): Int = value.compareTo(other.value)

        override fun equals(other: Any?): Boolean = other is Time && other.value == value
        override fun hashCode(): Int = value.hashCode()
        override fun toString(): String = "${value}ms"

        companion object {
            @JsonCreator
            fun ofMillis(millis: Int): Time = Time(millis)
        }
    }
}
