package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ReadProcess;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ReadProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReadProcessRepository extends JpaRepository<ReadProcess, Long> {
    Optional<ReadProcess> findByProcessInstanceId(Long processInstanceId);
}
