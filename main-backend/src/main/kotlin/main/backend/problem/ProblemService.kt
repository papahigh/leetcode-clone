package main.backend.problem

import jakarta.inject.Singleton
import problem.ProblemRepository


interface ProblemService {
    fun findAll(): Collection<ProblemSummary>
    fun findBySlug(slug: String): ProblemDetails?
}

@Singleton
class ProblemServiceImpl(
    private val mapper: ProblemMapper,
    private val repository: ProblemRepository,
) : ProblemService {

    override fun findAll(): Collection<ProblemSummary> {
        var model = repository.findAll()
        return model.map { mapper.toSummary(it) }
    }

    override fun findBySlug(slug: String): ProblemDetails? {
        var model = repository.findBySlug(slug)
        return model?.let { mapper.toDetails(it) }
    }
}
