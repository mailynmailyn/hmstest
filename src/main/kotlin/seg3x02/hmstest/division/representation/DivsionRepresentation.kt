package seg3x02.hmstest.division.representation

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.core.Relation
import java.util.*

@Relation(collectionRelation = "division")
@JsonInclude(JsonInclude.Include.NON_NULL)
class DivisionRepresentation: RepresentationModel<DivisionRepresentation>() {
    var id: Long = 0
    var identifier: String = ""
    var divName: String = ""
    var location: String = ""
    var numberOfBed: Number = 0
    var ext: Number = 0
    var bipper: Number = 0
    var isComplete: Boolean = false
}