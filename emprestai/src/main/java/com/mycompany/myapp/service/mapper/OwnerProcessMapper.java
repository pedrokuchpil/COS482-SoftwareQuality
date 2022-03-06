package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.OwnerProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link OwnerProcess} and its DTO {@link OwnerProcessDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class, EmprestimoMapper.class })
public interface OwnerProcessMapper extends EntityMapper<OwnerProcessDTO, OwnerProcess> {
    @Mapping(target = "processInstance", source = "processInstance")
    @Mapping(target = "emprestimo", source = "emprestimo")
    OwnerProcessDTO toDto(OwnerProcess s);
}
