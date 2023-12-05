package seg3x02.hmstest.patient.assemblers

import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Component
import seg3x02.hmstest.controller.ApiController
import seg3x02.hmstest.patient.entities.Patient
import seg3x02.hmstest.patient.entities.Address
import seg3x02.hmstest.patient.representation.PatientRepresentation
import seg3x02.hmstest.patient.representation.AddressRepresentation
import java.util.*

@Component
class PatientModelAssembler: RepresentationModelAssemblerSupport<Patient,
        PatientRepresentation>(ApiController::class.java, PatientRepresentation::class.java) {
    override fun toModel(entity: Patient): PatientRepresentation {
        val patientRepresentation = instantiateModel(entity)
        patientRepresentation.add(linkTo(
            WebMvcLinkBuilder.methodOn(ApiController::class.java)
                .getPatientById(entity.id))
            .withSelfRel())
        patientRepresentation.add(linkTo(
            WebMvcLinkBuilder.methodOn(ApiController::class.java)
                .getPatientById(entity.id))
            .withRel("user role"))

        patientRepresentation.id = entity.id
        patientRepresentation.email = entity.email
        patientRepresentation.employeeNumber = entity.employeeNumber
        patientRepresentation.loginPassword = entity.loginPassword

        patientRepresentation.firstName = entity.firstName
        patientRepresentation.lastName = entity.lastName
        return patientRepresentation
    }


    override fun toCollectionModel(entities: Iterable<Patient>): CollectionModel<PatientRepresentation> {
        val patientRepresentations = super.toCollectionModel(entities)
        patientRepresentations.add(linkTo(
            WebMvcLinkBuilder.methodOn(
                ApiController::class.java).allPatients()).withSelfRel())
        return patientRepresentations
    }

}