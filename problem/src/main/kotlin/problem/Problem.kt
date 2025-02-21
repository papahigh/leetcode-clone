package problem

typealias Markdown = String
typealias ProblemID = Int

data class Problem(
    val id: ProblemID,
    val slug: String,
    val name: String,
    val body: Markdown,
    val input: ProjectFile,
    val projects: Collection<Project>,
) {

    data class Project(
        val lang: Language,
        val files: ProjectFiles,
        val compile: ProjectAction? = null,
        val execute: ProjectAction,
    )

    data class ProjectFiles(
        val solution: ProjectFile,
        val validator: ProjectFile,
        val evaluator: ProjectFile,
        val resources: List<ProjectFile> = emptyList(),
    )

    data class ProjectFile(val name: String, val content: String)

    data class ProjectAction(val script: ProjectFile, val resources: Resources)

    enum class Language(val runtimeKey: String) {
        C("c"),
        CPP("cpp"),
        CSHARP("cs"),
        DART("dart"),
        ELIXIR("elixir"),
        ERLANG("erlang"),
        GO("go"),
        JAVA("java"),
        JAVA_SCRIPT("js"),
        KOTLIN("kotlin"),
        PHP("php"),
        PYTHON3("python3"),
        RUBY("ruby"),
        RUST("rust"),
        SWIFT("swift"),
        TYPE_SCRIPT("ts"),
        ;
    }

}
