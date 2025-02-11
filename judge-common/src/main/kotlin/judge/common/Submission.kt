package judge.common

data class Submission(
    val id: String,
    val limits: Resources,
    val project: Project,
    val priority: Int,
    val solution: ProgramFile,
) {

    data class Project(val language: Language, val files: List<ProgramFile>)

}
