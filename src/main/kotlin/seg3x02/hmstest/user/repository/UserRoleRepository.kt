package seg3x02.hmstest.user.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import seg3x02.hmstest.user.entities.UserRole

interface UserRoleRepository: CrudRepository<UserRole, Long> 