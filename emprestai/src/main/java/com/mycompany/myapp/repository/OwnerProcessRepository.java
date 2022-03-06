package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.OwnerProcess;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the OwnerProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OwnerProcessRepository extends JpaRepository<OwnerProcess, Long> {
    Optional<OwnerProcess> findByProcessInstanceId(Long processInstanceId);
}
