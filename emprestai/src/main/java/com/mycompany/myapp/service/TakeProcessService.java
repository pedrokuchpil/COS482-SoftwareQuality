package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.TakeProcess;
import com.mycompany.myapp.repository.EmprestimoRepository;
import com.mycompany.myapp.repository.TakeProcessRepository;
import com.mycompany.myapp.service.dto.TakeProcessDTO;
import com.mycompany.myapp.service.mapper.TakeProcessMapper;
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
 * Service Implementation for managing {@link TakeProcess}.
 */
@Service
@Transactional
public class TakeProcessService {

    public static final String BPMN_PROCESS_DEFINITION_ID = "TakeProcess";

    private final Logger log = LoggerFactory.getLogger(TakeProcessService.class);

    private final ProcessInstanceService processInstanceService;

    private final EmprestimoRepository emprestimoRepository;

    private final TakeProcessRepository takeProcessRepository;

    private final TakeProcessMapper takeProcessMapper;

    public TakeProcessService(
        ProcessInstanceService processInstanceService,
        EmprestimoRepository emprestimoRepository,
        TakeProcessRepository takeProcessRepository,
        TakeProcessMapper takeProcessMapper
    ) {
        this.processInstanceService = processInstanceService;
        this.emprestimoRepository = emprestimoRepository;
        this.takeProcessRepository = takeProcessRepository;
        this.takeProcessMapper = takeProcessMapper;
    }

    /**
     * Save a takeProcess.
     *
     * @param takeProcessDTO the entity to save.
     * @return the persisted entity.
     */
    public TakeProcessDTO create(TakeProcessDTO takeProcessDTO) {
        log.debug("Request to save TakeProcess : {}", takeProcessDTO);

        TakeProcess takeProcess = takeProcessMapper.toEntity(takeProcessDTO);

        //Saving the domainEntity
        emprestimoRepository.save(takeProcess.getEmprestimo());

        //Creating the process instance in the Camunda and setting it in the process entity
        ProcessInstance processInstance = processInstanceService.create(
            BPMN_PROCESS_DEFINITION_ID,
            "Emprestimo#" + takeProcess.getEmprestimo().getId(),
            takeProcess
        );
        takeProcess.setProcessInstance(processInstance);

        //Saving the process entity
        takeProcess = takeProcessRepository.save(takeProcess);
        return takeProcessMapper.toDto(takeProcess);
    }

    /**
     * Get all the takeProcesss.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TakeProcessDTO> findAll() {
        log.debug("Request to get all TakeProcesss");
        return takeProcessRepository.findAll().stream().map(takeProcessMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one takeProcess by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TakeProcessDTO> findOne(Long id) {
        log.debug("Request to get TakeProcess : {}", id);
        return takeProcessRepository.findById(id).map(takeProcessMapper::toDto);
    }

    /**
     * Get one takeProcess by id.
     *
     * @param processInstanceId the id of the processInstance associated to the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TakeProcessDTO> findByProcessInstanceId(Long processInstanceId) {
        log.debug("Request to get TakeProcess by  processInstanceId: {}", processInstanceId);
        return takeProcessRepository.findByProcessInstanceId(processInstanceId).map(takeProcessMapper::toDto);
    }
}
