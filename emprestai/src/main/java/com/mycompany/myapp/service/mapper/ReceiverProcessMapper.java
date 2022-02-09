package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ReceiverProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ReceiverProcess} and its DTO {@link ReceiverProcessDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class, EmprestimoMapper.class })
public interface ReceiverProcessMapper extends EntityMapper<ReceiverProcessDTO, ReceiverProcess> {
    @Mapping(target = "processInstance", source = "processInstance")
    @Mapping(target = "emprestimo", source = "emprestimo")
    ReceiverProcessDTO toDto(ReceiverProcess s);
}
