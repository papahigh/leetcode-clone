package problem

interface ProblemRepository {

    fun findAll(): Collection<Problem>

    fun findById(id: ProblemID): Problem?

    fun findBySlug(slug: String): Problem?

}
