package seg3x02.hmstest.patient.entities

import jakarta.persistence.*
import java.util.*

@Entity
class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000")
    var insuranceNumber: Number = 0
    var firstName: String = ""
    var lastName: String = ""
    var address: Address = Address()
    var dob: Date = Date()
    var gender: String = ""
    val maritalStatus: String = ""
    val externalDoctor: String = ""
    val nokFName: String = ""
    val nokLName: String = ""
    val nokRelation: String = ""
    val nokAddress: Address = Address()
    val nokPhoneNumber: String = ""
    val email: String = ""



    // @OneToMany
    // var prescription: MutableList<Prescription> = ArrayList()
}
