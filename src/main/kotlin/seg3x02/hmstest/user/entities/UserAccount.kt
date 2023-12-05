package seg3x02.hmstest.user.entities

import jakarta.persistence.*

@Entity
class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    val employeeNumber: Number = 0
    var loginPassword: String = ""
    var firstName: String = ""
    var lastName: String = ""
    var email: String = ""
    

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var role: UserRole = UserRole()
}