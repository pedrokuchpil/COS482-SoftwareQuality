package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.akip.service.dto.ProcessInstanceDTO;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ReadProcess} entity.
 */
public class ReadProcessDTO implements Serializable {

    private Long id;

    private ProcessInstanceDTO processInstance;

    private EmprestimoDTO emprestimo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProcessInstanceDTO getProcessInstance() {
        return processInstance;
    }

    public void setProcessInstance(ProcessInstanceDTO processInstance) {
        this.processInstance = processInstance;
    }

    public EmprestimoDTO getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(EmprestimoDTO emprestimo) {
        this.emprestimo = emprestimo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReadProcessDTO)) {
            return false;
        }

        ReadProcessDTO readProcessDTO = (ReadProcessDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, readProcessDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReadProcessDTO{" +
            "id=" + getId() +
            ", processInstance=" + getProcessInstance() +
            ", emprestimo=" + getEmprestimo() +
            "}";
    }
}
