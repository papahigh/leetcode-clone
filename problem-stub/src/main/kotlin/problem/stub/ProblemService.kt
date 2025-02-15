package problem.stub

import problem.Problem
import problem.ProblemID
import problem.ProblemService


class ProblemServiceStub : ProblemService {

    override fun findAll(): Collection<Problem> {
        return allProblems
    }

    override fun findById(id: ProblemID): Problem? {
        return allProblems.find { it.id == id }
    }

    override fun findBySlug(slug: String): Problem? {
        return allProblems.find { it.slug == slug }
    }

    private companion object {
        var allProblems = listOf<Problem>(
            problem.stub.problem1.PROBLEM,
        )
    }
}