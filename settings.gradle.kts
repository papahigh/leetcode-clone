plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "leetcode-clone"

include("judge")
include("judge-service")
include("main-backend")
