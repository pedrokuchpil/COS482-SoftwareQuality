package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.ReceiverProcess;
import com.mycompany.myapp.repository.EmprestimoRepository;
import com.mycompany.myapp.repository.ReceiverProcessRepository;
import com.mycompany.myapp.service.dto.ReceiverProcessDTO;
import com.mycompany.myapp.service.mapper.ReceiverProcessMapper;
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
 * Service Implementation for managing {@link ReceiverProcess}.
 */
@Service
@Transactional
public class ReceiverProcessService {

    public static final String BPMN_PROCESS_DEFINITION_ID = "ReceiverProcess";

    private final Logger log = LoggerFactory.getLogger(ReceiverProcessService.class);

    private final ProcessInstanceService processInstanceService;

    private final EmprestimoRepository emprestimoRepository;

    private final ReceiverProcessRepository receiverProcessRepository;

    private final ReceiverProcessMapper receiverProcessMapper;

    public ReceiverProcessService(
        ProcessInstanceService processInstanceService,
        EmprestimoRepository emprestimoRepository,
        ReceiverProcessRepository receiverProcessRepository,
        ReceiverProcessMapper receiverProcessMapper
    ) {
        this.processInstanceService = processInstanceService;
        this.emprestimoRepository = emprestimoRepository;
        this.receiverProcessRepository = receiverProcessRepository;
        this.receiverProcessMapper = receiverProcessMapper;
    }

    /**
     * Save a receiverProcess.
     *
     * @param receiverProcessDTO the entity to save.
     * @return the persisted entity.
     */
    public ReceiverProcessDTO create(ReceiverProcessDTO receiverProcessDTO) {
        log.debug("Request to save ReceiverProcess : {}", receiverProcessDTO);

        ReceiverProcess receiverProcess = receiverProcessMapper.toEntity(receiverProcessDTO);

        //Saving the domainEntity
        emprestimoRepository.save(receiverProcess.getEmprestimo());

        //Creating the process instance in the Camunda and setting it in the process entity
        ProcessInstance processInstance = processInstanceService.create(
            BPMN_PROCESS_DEFINITION_ID,
            "Emprestimo#" + receiverProcess.getEmprestimo().getId(),
            receiverProcess
        );
        receiverProcess.setProcessInstance(processInstance);

        //Saving the process entity
        receiverProcess = receiverProcessRepository.save(receiverProcess);
        return receiverProcessMapper.toDto(receiverProcess);
    }

    /**
     * Get all the receiverProcesss.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ReceiverProcessDTO> findAll() {
        log.debug("Request to get all ReceiverProcesss");
        return receiverProcessRepository
            .findAll()
            .stream()
            .map(receiverProcessMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one receiverProcess by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ReceiverProcessDTO> findOne(Long id) {
        log.debug("Request to get ReceiverProcess : {}", id);
        return receiverProcessRepository.findById(id).map(receiverProcessMapper::toDto);
    }

    /**
     * Get one receiverProcess by id.
     *
     * @param processInstanceId the id of the processInstance associated to the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ReceiverProcessDTO> findByProcessInstanceId(Long processInstanceId) {
        log.debug("Request to get ReceiverProcess by  processInstanceId: {}", processInstanceId);
        return receiverProcessRepository.findByProcessInstanceId(processInstanceId).map(receiverProcessMapper::toDto);
    }
}
