package seg3x02.hmstest.user.entities

import jakarta.persistence.*

@Entity
class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    val role: String = ""
}