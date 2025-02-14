package problem

interface ProblemService {

    fun findAll(): Collection<Problem>

    fun findById(id: ProblemID): Problem?

    fun findBySlug(slug: String): Problem?

}
