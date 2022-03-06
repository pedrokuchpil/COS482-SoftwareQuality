package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.AcceptProcess;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AcceptProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AcceptProcessRepository extends JpaRepository<AcceptProcess, Long> {
    Optional<AcceptProcess> findByProcessInstanceId(Long processInstanceId);
}
