package seg3x02.hmstest.user.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import seg3x02.hmstest.user.entities.UserAccount

interface UserAccountRepository: CrudRepository<UserAccount, Long> {
    @Query(value="select user from UserAccount user where user.firstName = :firstName and user.lastName = :lastName")
    fun findUserAccountsByName(firstName: String, lastName: String): List<UserAccount>
}