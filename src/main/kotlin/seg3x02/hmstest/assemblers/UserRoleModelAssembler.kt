package seg3x02.hmstest.assemblers

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.stereotype.Component
import seg3x02.hmstest.controller.ApiController
import seg3x02.hmstest.entities.UserRole
import seg3x02.hmstest.representation.UserRoleRepresentation

@Component
class UserRoleModelAssembler : RepresentationModelAssemblerSupport<UserRole,
        UserRoleRepresentation>(ApiController::class.java, UserRoleRepresentation::class.java) {
    override fun toModel(entity: UserRole): UserRoleRepresentation {
        val roleRepresentation = instantiateModel(entity)
        roleRepresentation.add(
            WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(ApiController::class.java)
                .getUserRoleById(entity.id))
            .withSelfRel())
        roleRepresentation.id = entity.id
        roleRepresentation.role = entity.role
        return roleRepresentation
    }
}
