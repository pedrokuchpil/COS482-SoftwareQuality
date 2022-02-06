package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.EmprestimoProcess;
import com.mycompany.myapp.repository.EmprestimoProcessRepository;
import com.mycompany.myapp.repository.EmprestimoRepository;
import com.mycompany.myapp.service.dto.EmprestimoProcessDTO;
import com.mycompany.myapp.service.mapper.EmprestimoProcessMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.akip.domain.ProcessInstance;
import org.akip.service.ProcessInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EmprestimoProcess}.
 */
@Service
@Transactional
public class EmprestimoProcessService {

    public static final String BPMN_PROCESS_DEFINITION_ID = "EmprestimoProcess";

    private final Logger log = LoggerFactory.getLogger(EmprestimoProcessService.class);

    private final ProcessInstanceService processInstanceService;

    private final EmprestimoRepository emprestimoRepository;

    private final EmprestimoProcessRepository emprestimoProcessRepository;

    private final EmprestimoProcessMapper emprestimoProcessMapper;

    public EmprestimoProcessService(
        ProcessInstanceService processInstanceService,
        EmprestimoRepository emprestimoRepository,
        EmprestimoProcessRepository emprestimoProcessRepository,
        EmprestimoProcessMapper emprestimoProcessMapper
    ) {
        this.processInstanceService = processInstanceService;
        this.emprestimoRepository = emprestimoRepository;
        this.emprestimoProcessRepository = emprestimoProcessRepository;
        this.emprestimoProcessMapper = emprestimoProcessMapper;
    }

    /**
     * Save a emprestimoProcess.
     *
     * @param emprestimoProcessDTO the entity to save.
     * @return the persisted entity.
     */
    public EmprestimoProcessDTO create(EmprestimoProcessDTO emprestimoProcessDTO) {
        log.debug("Request to save EmprestimoProcess : {}", emprestimoProcessDTO);

        EmprestimoProcess emprestimoProcess = emprestimoProcessMapper.toEntity(emprestimoProcessDTO);

        //Saving the domainEntity
        emprestimoRepository.save(emprestimoProcess.getEmprestimo());

        //Creating the process instance in the Camunda and setting it in the process entity
        ProcessInstance processInstance = processInstanceService.create(
            BPMN_PROCESS_DEFINITION_ID,
            "Emprestimo#" + emprestimoProcess.getEmprestimo().getId(),
            emprestimoProcess
        );
        emprestimoProcess.setProcessInstance(processInstance);

        //Saving the process entity
        emprestimoProcess = emprestimoProcessRepository.save(emprestimoProcess);
        return emprestimoProcessMapper.toDto(emprestimoProcess);
    }

    /**
     * Get all the emprestimoProcesss.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EmprestimoProcessDTO> findAll() {
        log.debug("Request to get all EmprestimoProcesss");
        return emprestimoProcessRepository
            .findAll()
            .stream()
            .map(emprestimoProcessMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one emprestimoProcess by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EmprestimoProcessDTO> findOne(Long id) {
        log.debug("Request to get EmprestimoProcess : {}", id);
        return emprestimoProcessRepository.findById(id).map(emprestimoProcessMapper::toDto);
    }

    /**
     * Get one emprestimoProcess by id.
     *
     * @param processInstanceId the id of the processInstance associated to the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EmprestimoProcessDTO> findByProcessInstanceId(Long processInstanceId) {
        log.debug("Request to get EmprestimoProcess by  processInstanceId: {}", processInstanceId);
        return emprestimoProcessRepository.findByProcessInstanceId(processInstanceId).map(emprestimoProcessMapper::toDto);
    }
}
