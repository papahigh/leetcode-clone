package main.backend.common

import io.hypersistence.tsid.TSID
import io.hypersistence.tsid.TSID.Factory.THREAD_LOCAL_RANDOM_FUNCTION

import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PrePersist


@Serdeable
@MappedSuperclass
abstract class IdentifiableModel {

    @Id
    @Column(
        name = "id",
        nullable = false,
        updatable = false,
        columnDefinition = "CHAR(13)"
    )
    var id: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::javaClass != other::javaClass) return false
        return id != null && id == (other as IdentifiableModel).id
    }

    override fun hashCode(): Int {
        return this::javaClass.hashCode()
    }

    override fun toString(): String {
        return "${this::javaClass}(id=$id)"
    }

    @PrePersist
    fun prePersist() {
        TSID_FACTORY.generate().let { id = id ?: it.toString() }
    }

    private companion object {
        val TSID_FACTORY = TSID.Factory.builder()
            .withRandomFunction(THREAD_LOCAL_RANDOM_FUNCTION)
            .build()!!
    }
}
