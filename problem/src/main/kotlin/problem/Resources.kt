package problem

/**
 * @property time The maximum amount of time, specified in seconds.
 * @property memory The maximum amount of memory, specified in kilobytes.
 */
data class Resources(val time: Int, val memory: Int) {
    companion object {
        val NOT_AVAILABLE = Resources(-1, -1)
    }
}
