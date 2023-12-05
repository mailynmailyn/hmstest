package seg3x02.hmstest.user.representation

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.core.Relation

@Relation(collectionRelation = "user account")
@JsonInclude(JsonInclude.Include.NON_NULL)
class UserAccountRepresentation: RepresentationModel<UserAccountRepresentation>() {
    var id: Long = 0
    var employeeNumber: Number = 0
    var loginPassword: String = ""
    var firstName: String = ""
    var lastName: String = ""
    var email: String = ""
    val role: UserRoleRepresentation = UserRoleRepresentation()
}