package main.backend.submission

import judge.FeedbackEvent
import judge.SubmissionEvent
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants.ComponentModel.JAKARTA
import org.mapstruct.ReportingPolicy.IGNORE


@Mapper(componentModel = JAKARTA, unmappedTargetPolicy = IGNORE, uses = [FeedbackMapper::class])
interface SubmissionMapper {
    fun toEvent(model: SubmissionModel): SubmissionEvent

    fun toSummary(model: SubmissionModel): SubmissionSummary

    fun toDetails(model: SubmissionModel): SubmissionDetails

    fun map(request: CreateSubmission): SubmissionModel
}

@Mapper(componentModel = JAKARTA)
interface FeedbackMapper {

    fun toDetails(model: FeedbackModel): FeedbackDetails

    @Mapping(source = "resources.time", target = "time")
    @Mapping(source = "resources.memory", target = "memory")
    fun map(event: FeedbackEvent): FeedbackModel

}
