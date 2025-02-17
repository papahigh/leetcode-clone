package problem.stub

import problem.Problem
import problem.Problem.Language
import problem.Problem.Project
import problem.ProblemID
import problem.ProblemRepository
import problem.ProjectRepository


class ProblemRepositoryStub() : ProblemRepository, ProjectRepository {

    override fun findAll(): Collection<Problem> {
        return allProblems
    }

    override fun findById(id: ProblemID): Problem? {
        return allProblems.find { it.id == id }
    }

    override fun findBySlug(slug: String): Problem? {
        return allProblems.find { it.slug == slug }
    }

    override fun findProject(problemID: ProblemID, lang: Language): Project? {
        return findById(problemID)?.projects?.find { it.lang == lang }
    }

    private companion object {
        val allProblems = listOf(
            problem.stub.problem1.PROBLEM,
        )
    }
}