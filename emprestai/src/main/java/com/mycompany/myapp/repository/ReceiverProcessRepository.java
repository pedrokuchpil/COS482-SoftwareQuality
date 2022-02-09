package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ReceiverProcess;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ReceiverProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReceiverProcessRepository extends JpaRepository<ReceiverProcess, Long> {
    Optional<ReceiverProcess> findByProcessInstanceId(Long processInstanceId);
}
