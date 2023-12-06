package seg3x02.hmstest.division.assemblers

import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Component
import seg3x02.hmstest.controller.ApiController
import seg3x02.hmstest.division.entities.Division
import seg3x02.hmstest.division.representation.DivisionRepresentation
import java.util.*

@Component
class DivisionModelAssembler: RepresentationModelAssemblerSupport<Division,
        DivisionRepresentation>(ApiController::class.java, DivisionRepresentation::class.java) {
    override fun toModel(entity: Division): DivisionRepresentation {
        val divisionRepresentation = instantiateModel(entity)
        divisionRepresentation.add(linkTo(
            WebMvcLinkBuilder.methodOn(ApiController::class.java)
                .getDivisionById(entity.id))
            .withSelfRel())

        divisionRepresentation.id = entity.id
        divisionRepresentation.identifier = entity.identifier
        divisionRepresentation.location = entity.location
        divisionRepresentation.numberOfBed = entity.numberOfBed
        divisionRepresentation.ext = entity.ext
        divisionRepresentation.bipper = entity.bipper
        divisionRepresentation.isComplete = entity.isComplete

      
        return divisionRepresentation
    }


    override fun toCollectionModel(entities: Iterable<Division>): CollectionModel<DivisionRepresentation> {
        val divisionRepresentations = super.toCollectionModel(entities)
        divisionRepresentations.add(linkTo(
            WebMvcLinkBuilder.methodOn(
                ApiController::class.java).allDivisions()).withSelfRel())
        return divisionRepresentations
    }

}