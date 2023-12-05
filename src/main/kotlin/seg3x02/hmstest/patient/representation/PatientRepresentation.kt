package seg3x02.hmstest.patient.representation

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.core.Relation
import java.util.*
import seg3x02.hmstest.patient.representation.AddressRepresentation

@Relation(collectionRelation = "user account")
@JsonInclude(JsonInclude.Include.NON_NULL)
class PatientRepresentation: RepresentationModel<PatientRepresentation>() {
    var id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000")
    var insuranceNumber: Number = 0
    var firstName: String = ""
    var lastName: String = ""
    var address: AddressRepresentation = AddressRepresentation()
    var dob: Date = Date()
    var gender: String = ""
    val maritalStatus: String = ""
    val externalDoctor: String = ""
    val nokFName: String = ""
    val nokLName: String = ""
    val nokRelation: String = ""
    val nokAddress: AddressRepresentation = AddressRepresentation()
    val nokPhoneNumber: String = ""
    val email: String = ""
}