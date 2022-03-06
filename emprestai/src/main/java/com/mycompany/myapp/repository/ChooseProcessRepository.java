package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ChooseProcess;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ChooseProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChooseProcessRepository extends JpaRepository<ChooseProcess, Long> {
    Optional<ChooseProcess> findByProcessInstanceId(Long processInstanceId);
}
