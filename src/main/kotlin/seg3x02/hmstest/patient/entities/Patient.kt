package seg3x02.hmstest.patient.entities

import jakarta.persistence.*
import java.util.*

@Entity
class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    var insuranceNumber: Number = 0
    var firstName: String = ""
    var lastName: String = ""
    var address: String = ""
    var dob: Date = Date()
    var gender: String = ""
    var maritalStatus: String = ""
    var externalDoctor: String = ""
    var nokFName: String = ""
    var nokLName: String = ""
    var nokRelation: String = ""
    var nokAddress: String = ""
    var nokPhoneNumber: String = ""
    var email: String = ""



    // @OneToMany
    // var prescription: MutableList<Prescription> = ArrayList()
}
