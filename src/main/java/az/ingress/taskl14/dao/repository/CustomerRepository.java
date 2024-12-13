package az.ingress.taskl14.dao.repository;

import az.ingress.taskl14.dao.entity.CustomerEntity;
import az.ingress.taskl14.model.enums.CustomerStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByIdAndStatus(Long id, CustomerStatus status);

    @Query(nativeQuery = true,
    value = """
            SELECT * FROM customers WHERE name =: name
            """)
    Optional<CustomerEntity> findByName(String name);

    @Query(value = "SELECT name FROM CustomerEntity WHERE birthDate=: birthDate")
    Optional<CustomerEntity> findNameByBirthDate(LocalDate birthDate);
}
