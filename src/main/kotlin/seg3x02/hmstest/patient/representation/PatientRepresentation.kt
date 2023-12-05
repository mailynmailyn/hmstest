package seg3x02.hmstest.patient.representation

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.core.Relation
import java.util.*
import seg3x02.hmstest.patient.representation.AddressRepresentation

@Relation(collectionRelation = "user account")
@JsonInclude(JsonInclude.Include.NON_NULL)
class PatientRepresentation: RepresentationModel<PatientRepresentation>() {
    var id: Long = 0
    var insuranceNumber: Number = 0
    var firstName: String = ""
    var lastName: String = ""
    var address: AddressRepresentation = AddressRepresentation()
    var dob: Date = Date()
    var gender: String = ""
    var maritalStatus: String = ""
    var externalDoctor: String = ""
    var nokFName: String = ""
    var nokLName: String = ""
    var nokRelation: String = ""
    var nokAddress: AddressRepresentation = AddressRepresentation()
    var nokPhoneNumber: String = ""
    var email: String = ""
}