package main.backend.submission

import judge.SubmissionEvent
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants.ComponentModel.JAKARTA
import org.mapstruct.ReportingPolicy.IGNORE


@Mapper(componentModel = JAKARTA, unmappedTargetPolicy = IGNORE)
interface SubmissionMapper {
    fun toEvent(model: SubmissionModel): SubmissionEvent

    fun toDetails(model: SubmissionModel): SubmissionDetails

    @Mapping(target = "status", expression = "java(judge.FeedbackEvent.Status.EVALUATION_PENDING)")
    fun map(request: CreateSubmission): SubmissionModel
}

