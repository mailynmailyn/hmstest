package seg3x02.hmstest.patient.representation

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.core.Relation

@Relation(collectionRelation = "addresses")
@JsonInclude(JsonInclude.Include.NON_NULL)
class AddressRepresentation: RepresentationModel<AddressRepresentation>() {
    var id: Long = 0
    var streetnum: Number = 0
    var street: String = ""
    var city: String = ""
    var country: String = ""
}