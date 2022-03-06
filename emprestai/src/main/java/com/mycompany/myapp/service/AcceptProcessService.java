package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.AcceptProcess;
import com.mycompany.myapp.repository.AcceptProcessRepository;
import com.mycompany.myapp.repository.EmprestimoRepository;
import com.mycompany.myapp.service.dto.AcceptProcessDTO;
import com.mycompany.myapp.service.mapper.AcceptProcessMapper;
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
 * Service Implementation for managing {@link AcceptProcess}.
 */
@Service
@Transactional
public class AcceptProcessService {

    public static final String BPMN_PROCESS_DEFINITION_ID = "AcceptProcess";

    private final Logger log = LoggerFactory.getLogger(AcceptProcessService.class);

    private final ProcessInstanceService processInstanceService;

    private final EmprestimoRepository emprestimoRepository;

    private final AcceptProcessRepository acceptProcessRepository;

    private final AcceptProcessMapper acceptProcessMapper;

    public AcceptProcessService(
        ProcessInstanceService processInstanceService,
        EmprestimoRepository emprestimoRepository,
        AcceptProcessRepository acceptProcessRepository,
        AcceptProcessMapper acceptProcessMapper
    ) {
        this.processInstanceService = processInstanceService;
        this.emprestimoRepository = emprestimoRepository;
        this.acceptProcessRepository = acceptProcessRepository;
        this.acceptProcessMapper = acceptProcessMapper;
    }

    /**
     * Save a acceptProcess.
     *
     * @param acceptProcessDTO the entity to save.
     * @return the persisted entity.
     */
    public AcceptProcessDTO create(AcceptProcessDTO acceptProcessDTO) {
        log.debug("Request to save AcceptProcess : {}", acceptProcessDTO);

        AcceptProcess acceptProcess = acceptProcessMapper.toEntity(acceptProcessDTO);

        //Saving the domainEntity
        emprestimoRepository.save(acceptProcess.getEmprestimo());

        //Creating the process instance in the Camunda and setting it in the process entity
        ProcessInstance processInstance = processInstanceService.create(
            BPMN_PROCESS_DEFINITION_ID,
            "Emprestimo#" + acceptProcess.getEmprestimo().getId(),
            acceptProcess
        );
        acceptProcess.setProcessInstance(processInstance);

        //Saving the process entity
        acceptProcess = acceptProcessRepository.save(acceptProcess);
        return acceptProcessMapper.toDto(acceptProcess);
    }

    /**
     * Get all the acceptProcesss.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AcceptProcessDTO> findAll() {
        log.debug("Request to get all AcceptProcesss");
        return acceptProcessRepository.findAll().stream().map(acceptProcessMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one acceptProcess by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AcceptProcessDTO> findOne(Long id) {
        log.debug("Request to get AcceptProcess : {}", id);
        return acceptProcessRepository.findById(id).map(acceptProcessMapper::toDto);
    }

    /**
     * Get one acceptProcess by id.
     *
     * @param processInstanceId the id of the processInstance associated to the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AcceptProcessDTO> findByProcessInstanceId(Long processInstanceId) {
        log.debug("Request to get AcceptProcess by  processInstanceId: {}", processInstanceId);
        return acceptProcessRepository.findByProcessInstanceId(processInstanceId).map(acceptProcessMapper::toDto);
    }
}
