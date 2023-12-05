package seg3x02.hmstest.division.entities

import jakarta.persistence.*
import java.util.*

@Entity
class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    var identifier: String = ""
    var divName: String = ""
    var location: String = ""
    var numberOfBed: Number = 0
    var ext: Number = 0
    var bipper: Number = 0
    var isComplete: Boolean = false

}
