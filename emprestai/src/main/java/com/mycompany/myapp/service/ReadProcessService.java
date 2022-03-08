package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.ReadProcess;
import com.mycompany.myapp.repository.EmprestimoRepository;
import com.mycompany.myapp.repository.ReadProcessRepository;
import com.mycompany.myapp.service.dto.ReadProcessDTO;
import com.mycompany.myapp.service.mapper.ReadProcessMapper;
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
 * Service Implementation for managing {@link ReadProcess}.
 */
@Service
@Transactional
public class ReadProcessService {

    public static final String BPMN_PROCESS_DEFINITION_ID = "ReadProcess";

    private final Logger log = LoggerFactory.getLogger(ReadProcessService.class);

    private final ProcessInstanceService processInstanceService;

    private final EmprestimoRepository emprestimoRepository;

    private final ReadProcessRepository readProcessRepository;

    private final ReadProcessMapper readProcessMapper;

    public ReadProcessService(
        ProcessInstanceService processInstanceService,
        EmprestimoRepository emprestimoRepository,
        ReadProcessRepository readProcessRepository,
        ReadProcessMapper readProcessMapper
    ) {
        this.processInstanceService = processInstanceService;
        this.emprestimoRepository = emprestimoRepository;
        this.readProcessRepository = readProcessRepository;
        this.readProcessMapper = readProcessMapper;
    }

    /**
     * Save a readProcess.
     *
     * @param readProcessDTO the entity to save.
     * @return the persisted entity.
     */
    public ReadProcessDTO create(ReadProcessDTO readProcessDTO) {
        log.debug("Request to save ReadProcess : {}", readProcessDTO);

        ReadProcess readProcess = readProcessMapper.toEntity(readProcessDTO);

        //Saving the domainEntity
        emprestimoRepository.save(readProcess.getEmprestimo());

        //Creating the process instance in the Camunda and setting it in the process entity
        ProcessInstance processInstance = processInstanceService.create(
            BPMN_PROCESS_DEFINITION_ID,
            "Emprestimo#" + readProcess.getEmprestimo().getId(),
            readProcess
        );
        readProcess.setProcessInstance(processInstance);

        //Saving the process entity
        readProcess = readProcessRepository.save(readProcess);
        return readProcessMapper.toDto(readProcess);
    }

    /**
     * Get all the readProcesss.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ReadProcessDTO> findAll() {
        log.debug("Request to get all ReadProcesss");
        return readProcessRepository.findAll().stream().map(readProcessMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one readProcess by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ReadProcessDTO> findOne(Long id) {
        log.debug("Request to get ReadProcess : {}", id);
        return readProcessRepository.findById(id).map(readProcessMapper::toDto);
    }

    /**
     * Get one readProcess by id.
     *
     * @param processInstanceId the id of the processInstance associated to the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ReadProcessDTO> findByProcessInstanceId(Long processInstanceId) {
        log.debug("Request to get ReadProcess by  processInstanceId: {}", processInstanceId);
        return readProcessRepository.findByProcessInstanceId(processInstanceId).map(readProcessMapper::toDto);
    }
}
