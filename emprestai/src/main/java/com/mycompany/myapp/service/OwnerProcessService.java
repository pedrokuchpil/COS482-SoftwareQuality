package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.OwnerProcess;
import com.mycompany.myapp.repository.EmprestimoRepository;
import com.mycompany.myapp.repository.OwnerProcessRepository;
import com.mycompany.myapp.service.dto.OwnerProcessDTO;
import com.mycompany.myapp.service.mapper.OwnerProcessMapper;
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
 * Service Implementation for managing {@link OwnerProcess}.
 */
@Service
@Transactional
public class OwnerProcessService {

    public static final String BPMN_PROCESS_DEFINITION_ID = "OwnerProcess";

    private final Logger log = LoggerFactory.getLogger(OwnerProcessService.class);

    private final ProcessInstanceService processInstanceService;

    private final EmprestimoRepository emprestimoRepository;

    private final OwnerProcessRepository ownerProcessRepository;

    private final OwnerProcessMapper ownerProcessMapper;

    public OwnerProcessService(
        ProcessInstanceService processInstanceService,
        EmprestimoRepository emprestimoRepository,
        OwnerProcessRepository ownerProcessRepository,
        OwnerProcessMapper ownerProcessMapper
    ) {
        this.processInstanceService = processInstanceService;
        this.emprestimoRepository = emprestimoRepository;
        this.ownerProcessRepository = ownerProcessRepository;
        this.ownerProcessMapper = ownerProcessMapper;
    }

    /**
     * Save a ownerProcess.
     *
     * @param ownerProcessDTO the entity to save.
     * @return the persisted entity.
     */
    public OwnerProcessDTO create(OwnerProcessDTO ownerProcessDTO) {
        log.debug("Request to save OwnerProcess : {}", ownerProcessDTO);

        OwnerProcess ownerProcess = ownerProcessMapper.toEntity(ownerProcessDTO);

        //Saving the domainEntity
        emprestimoRepository.save(ownerProcess.getEmprestimo());

        //Creating the process instance in the Camunda and setting it in the process entity
        ProcessInstance processInstance = processInstanceService.create(
            BPMN_PROCESS_DEFINITION_ID,
            "Emprestimo#" + ownerProcess.getEmprestimo().getId(),
            ownerProcess
        );
        ownerProcess.setProcessInstance(processInstance);

        //Saving the process entity
        ownerProcess = ownerProcessRepository.save(ownerProcess);
        return ownerProcessMapper.toDto(ownerProcess);
    }

    /**
     * Get all the ownerProcesss.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<OwnerProcessDTO> findAll() {
        log.debug("Request to get all OwnerProcesss");
        return ownerProcessRepository.findAll().stream().map(ownerProcessMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ownerProcess by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OwnerProcessDTO> findOne(Long id) {
        log.debug("Request to get OwnerProcess : {}", id);
        return ownerProcessRepository.findById(id).map(ownerProcessMapper::toDto);
    }

    /**
     * Get one ownerProcess by id.
     *
     * @param processInstanceId the id of the processInstance associated to the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OwnerProcessDTO> findByProcessInstanceId(Long processInstanceId) {
        log.debug("Request to get OwnerProcess by  processInstanceId: {}", processInstanceId);
        return ownerProcessRepository.findByProcessInstanceId(processInstanceId).map(ownerProcessMapper::toDto);
    }
}
