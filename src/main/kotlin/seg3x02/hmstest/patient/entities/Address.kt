package seg3x02.hmstest.patient.entities

import jakarta.persistence.*

@Entity
class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    var streetnum: Number = 0
    var street: String = ""
    var city: String = ""
    var country: String = ""
}