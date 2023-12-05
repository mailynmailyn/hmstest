package seg3x02.hmstest.user.assemblers

import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Component
import seg3x02.hmstest.controller.ApiController
import seg3x02.hmstest.user.entities.UserAccount
import seg3x02.hmstest.user.entities.UserRole
import seg3x02.hmstest.user.representation.UserRoleRepresentation
import seg3x02.hmstest.user.representation.UserAccountRepresentation
import java.util.*

@Component
class UserAccountModelAssembler: RepresentationModelAssemblerSupport<UserAccount,
        UserAccountRepresentation>(ApiController::class.java, UserAccountRepresentation::class.java) {
    override fun toModel(entity: UserAccount): UserAccountRepresentation {
        val useraccountRepresentation = instantiateModel(entity)
        useraccountRepresentation.add(linkTo(
            WebMvcLinkBuilder.methodOn(ApiController::class.java)
                .getUserAccountById(entity.id))
            .withSelfRel())
        useraccountRepresentation.add(linkTo(
            WebMvcLinkBuilder.methodOn(ApiController::class.java)
                .getUserRoleById(entity.id))
            .withRel("user role"))

        useraccountRepresentation.id = entity.id
        useraccountRepresentation.email = entity.email
        useraccountRepresentation.employeeNumber = entity.employeeNumber
        useraccountRepresentation.loginPassword = entity.loginPassword

        useraccountRepresentation.firstName = entity.firstName
        useraccountRepresentation.lastName = entity.lastName
        return useraccountRepresentation
    }


    override fun toCollectionModel(entities: Iterable<UserAccount>): CollectionModel<UserAccountRepresentation> {
        val useraccountRepresentations = super.toCollectionModel(entities)
        useraccountRepresentations.add(linkTo(
            WebMvcLinkBuilder.methodOn(
                ApiController::class.java).allUserAccounts()).withSelfRel())
        return useraccountRepresentations
    }

}