package problem

import problem.Problem.Language
import problem.Problem.Project

interface ProjectRepository {

    fun findProject(problemID: ProblemID, lang: Language): Project?

}
