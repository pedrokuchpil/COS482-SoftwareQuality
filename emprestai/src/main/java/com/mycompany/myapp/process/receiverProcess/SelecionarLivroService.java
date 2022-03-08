package com.mycompany.myapp.process.receiverProcess;

import com.mycompany.myapp.repository.ReceiverProcessRepository;
import com.mycompany.myapp.service.EmprestimoService;
import com.mycompany.myapp.service.dto.EmprestimoDTO;
import com.mycompany.myapp.service.dto.ReceiverProcessDTO;
import com.mycompany.myapp.service.mapper.ReceiverProcessMapper;
import org.akip.repository.TaskInstanceRepository;
import org.akip.service.TaskInstanceService;
import org.akip.service.dto.TaskInstanceDTO;
import org.akip.service.mapper.TaskInstanceMapper;
import org.springframework.stereotype.Component;

@Component
public class SelecionarLivroService {

    private final TaskInstanceService taskInstanceService;

    private final EmprestimoService emprestimoService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final ReceiverProcessRepository receiverProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final SelecionarLivroMapper selecionarLivroMapper;

    private final ReceiverProcessMapper receiverProcessMapper;

    public SelecionarLivroService(
        TaskInstanceService taskInstanceService,
        EmprestimoService emprestimoService,
        TaskInstanceRepository taskInstanceRepository,
        ReceiverProcessRepository receiverProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        SelecionarLivroMapper selecionarLivroMapper,
        ReceiverProcessMapper receiverProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.emprestimoService = emprestimoService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.receiverProcessRepository = receiverProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.selecionarLivroMapper = selecionarLivroMapper;
        this.receiverProcessMapper = receiverProcessMapper;
    }

    public SelecionarLivroContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        ReceiverProcessDTO receiverProcess = receiverProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(selecionarLivroMapper::toReceiverProcessDTO)
            .orElseThrow();

        SelecionarLivroContextDTO selecionarLivroContext = new SelecionarLivroContextDTO();
        selecionarLivroContext.setTaskInstance(taskInstanceDTO);
        selecionarLivroContext.setReceiverProcess(receiverProcess);

        return selecionarLivroContext;
    }

    public SelecionarLivroContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(SelecionarLivroContextDTO selecionarLivroContext) {
        EmprestimoDTO emprestimoDTO = emprestimoService
            .findOne(selecionarLivroContext.getReceiverProcess().getEmprestimo().getId())
            .orElseThrow();
        emprestimoDTO.setDate(selecionarLivroContext.getReceiverProcess().getEmprestimo().getDate());
        emprestimoDTO.setBook(selecionarLivroContext.getReceiverProcess().getEmprestimo().getBook());
        emprestimoService.save(emprestimoDTO);
    }

    public void complete(SelecionarLivroContextDTO selecionarLivroContext) {
        save(selecionarLivroContext);
        ReceiverProcessDTO receiverProcess = receiverProcessRepository
            .findByProcessInstanceId(selecionarLivroContext.getReceiverProcess().getProcessInstance().getId())
            .map(receiverProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(selecionarLivroContext.getTaskInstance(), receiverProcess);
    }
}
