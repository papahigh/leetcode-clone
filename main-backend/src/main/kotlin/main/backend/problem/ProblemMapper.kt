package main.backend.problem

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants.ComponentModel.JAKARTA
import problem.Problem
import problem.Problem.Project


@Mapper(componentModel = JAKARTA, uses = [ProblemSnippetMapper::class])
interface ProblemMapper {
    fun toSummary(model: Problem): ProblemSummary

    @Mapping(source = "projects", target = "snippets")
    fun toDetails(model: Problem): ProblemDetails
}

@Mapper(componentModel = JAKARTA)
interface ProblemSnippetMapper {

    @Mapping(source = "files.solution.content", target = "code")
    fun map(project: Project): ProblemSnippet

}
