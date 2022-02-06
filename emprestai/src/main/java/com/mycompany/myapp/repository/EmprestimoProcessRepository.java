package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.EmprestimoProcess;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EmprestimoProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmprestimoProcessRepository extends JpaRepository<EmprestimoProcess, Long> {
    Optional<EmprestimoProcess> findByProcessInstanceId(Long processInstanceId);
}
