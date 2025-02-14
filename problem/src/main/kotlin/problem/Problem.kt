package problem

typealias Markdown = String
typealias ProblemID = Number

data class Problem(
    val id: ProblemID,
    val slug: String,
    val name: String,
    val body: Markdown,
    val projects: Collection<Project>
) {

    fun getProject(lang: Language): Project? = projects.find { it.lang == lang }

    data class Project(
        val lang: Language,
        val files: ProjectFiles,
        val limits: Resources?,
    )

    data class ProjectFiles(
        val compile: ProjectFile,
        var execute: ProjectFile,
        val snippet: ProjectFile,
        val validator: ProjectFile,
        val resources: List<ProjectFile> = emptyList(),
    )

    data class ProjectFile(val name: String, val content: String)

    enum class Language(val key: String, val ext: String = key) {
        C("c"),
        CPP("cpp", "cc"),
        CSHARP("cs"),
        DART("dart"),
        ELIXIR("elixir", "exs"),
        ERLANG("erlang", "erl"),
        GO("go"),
        JAVA("java"),
        JAVA_SCRIPT("js"),
        KOTLIN("kotlin", "kt"),
        PHP("php"),
        PYTHON3("python3", "py"),
        RUBY("ruby", "rb"),
        RUST("rust", "rs"),
        SWIFT("swift"),
        TYPE_SCRIPT("ts"),
        ;
    }

}
