package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.ChooseProcess;
import com.mycompany.myapp.repository.ChooseProcessRepository;
import com.mycompany.myapp.repository.EmprestimoRepository;
import com.mycompany.myapp.service.dto.ChooseProcessDTO;
import com.mycompany.myapp.service.mapper.ChooseProcessMapper;
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
 * Service Implementation for managing {@link ChooseProcess}.
 */
@Service
@Transactional
public class ChooseProcessService {

    public static final String BPMN_PROCESS_DEFINITION_ID = "ChooseProcess";

    private final Logger log = LoggerFactory.getLogger(ChooseProcessService.class);

    private final ProcessInstanceService processInstanceService;

    private final EmprestimoRepository emprestimoRepository;

    private final ChooseProcessRepository chooseProcessRepository;

    private final ChooseProcessMapper chooseProcessMapper;

    public ChooseProcessService(
        ProcessInstanceService processInstanceService,
        EmprestimoRepository emprestimoRepository,
        ChooseProcessRepository chooseProcessRepository,
        ChooseProcessMapper chooseProcessMapper
    ) {
        this.processInstanceService = processInstanceService;
        this.emprestimoRepository = emprestimoRepository;
        this.chooseProcessRepository = chooseProcessRepository;
        this.chooseProcessMapper = chooseProcessMapper;
    }

    /**
     * Save a chooseProcess.
     *
     * @param chooseProcessDTO the entity to save.
     * @return the persisted entity.
     */
    public ChooseProcessDTO create(ChooseProcessDTO chooseProcessDTO) {
        log.debug("Request to save ChooseProcess : {}", chooseProcessDTO);

        ChooseProcess chooseProcess = chooseProcessMapper.toEntity(chooseProcessDTO);

        //Saving the domainEntity
        emprestimoRepository.save(chooseProcess.getEmprestimo());

        //Creating the process instance in the Camunda and setting it in the process entity
        ProcessInstance processInstance = processInstanceService.create(
            BPMN_PROCESS_DEFINITION_ID,
            "Emprestimo#" + chooseProcess.getEmprestimo().getId(),
            chooseProcess
        );
        chooseProcess.setProcessInstance(processInstance);

        //Saving the process entity
        chooseProcess = chooseProcessRepository.save(chooseProcess);
        return chooseProcessMapper.toDto(chooseProcess);
    }

    /**
     * Get all the chooseProcesss.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ChooseProcessDTO> findAll() {
        log.debug("Request to get all ChooseProcesss");
        return chooseProcessRepository.findAll().stream().map(chooseProcessMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one chooseProcess by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ChooseProcessDTO> findOne(Long id) {
        log.debug("Request to get ChooseProcess : {}", id);
        return chooseProcessRepository.findById(id).map(chooseProcessMapper::toDto);
    }

    /**
     * Get one chooseProcess by id.
     *
     * @param processInstanceId the id of the processInstance associated to the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ChooseProcessDTO> findByProcessInstanceId(Long processInstanceId) {
        log.debug("Request to get ChooseProcess by  processInstanceId: {}", processInstanceId);
        return chooseProcessRepository.findByProcessInstanceId(processInstanceId).map(chooseProcessMapper::toDto);
    }
}
