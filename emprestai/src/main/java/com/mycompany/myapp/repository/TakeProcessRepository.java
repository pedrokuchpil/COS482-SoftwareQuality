package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TakeProcess;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TakeProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TakeProcessRepository extends JpaRepository<TakeProcess, Long> {
    Optional<TakeProcess> findByProcessInstanceId(Long processInstanceId);
}
