package seg3x02.hmstest.patient.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import seg3x02.hmstest.patient.entities.Patient

interface PatientRepository: CrudRepository<Patient, Long> {
    @Query(value="select patient from Patient p where p.firstName = :firstName and p.lastName = :lastName")
    fun findPatientsByName(firstName: String, lastName: String): List<Patient>
}