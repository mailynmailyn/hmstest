package seg3x02.hmstest.user.representation

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.core.Relation

@Relation(collectionRelation = "user roles")
@JsonInclude(JsonInclude.Include.NON_NULL)
class UserRoleRepresentation: RepresentationModel<UserRoleRepresentation>() {
    var id: Long = 0
    var role: String = ""
}