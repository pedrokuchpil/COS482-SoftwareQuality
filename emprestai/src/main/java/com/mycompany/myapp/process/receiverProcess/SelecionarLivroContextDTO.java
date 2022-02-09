package com.mycompany.myapp.process.receiverProcess;

import com.mycompany.myapp.service.dto.ReceiverProcessDTO;
import org.akip.service.dto.TaskInstanceDTO;

public class SelecionarLivroContextDTO {

    private ReceiverProcessDTO receiverProcess;
    private TaskInstanceDTO taskInstance;

    public ReceiverProcessDTO getReceiverProcess() {
        return receiverProcess;
    }

    public void setReceiverProcess(ReceiverProcessDTO receiverProcess) {
        this.receiverProcess = receiverProcess;
    }

    public TaskInstanceDTO getTaskInstance() {
        return taskInstance;
    }

    public void setTaskInstance(TaskInstanceDTO taskInstance) {
        this.taskInstance = taskInstance;
    }
}
