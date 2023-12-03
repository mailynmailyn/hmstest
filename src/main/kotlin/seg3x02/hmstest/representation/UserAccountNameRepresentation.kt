package seg3x02.hmstest.representation

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.hateoas.RepresentationModel

@JsonInclude(JsonInclude.Include.NON_NULL)
class UserAccountNameRepresentation: RepresentationModel<UserAccountNameRepresentation>() {
    var firstName: String = ""
    var lastName: String = ""
}