package seg3x02.hmstest.division.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import seg3x02.hmstest.division.entities.Division

interface DivisionRepository: CrudRepository<Division, Long> {
    @Query(value="select div from Division div where div.isComplete = false")
    fun findAvailableDivisions(): List<Division>
}