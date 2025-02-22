package problem

import kotlin.time.Duration

/**
 * @property memory The maximum amount of memory, specified in kilobytes.
 */
data class Resources(val time: Duration, val memory: Int)
