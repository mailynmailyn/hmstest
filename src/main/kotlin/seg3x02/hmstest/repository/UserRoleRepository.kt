package seg3x02.hmstest.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import seg3x02.hmstest.entities.UserRole

interface UserRoleRepository: CrudRepository<UserRole, Long> 