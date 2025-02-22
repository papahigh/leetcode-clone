package problem

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.spec.style.DescribeSpec

abstract class ProblemSpec(body: DescribeSpec.() -> Unit) : DescribeSpec(body) {

    class ProjectConfig : AbstractProjectConfig()

}