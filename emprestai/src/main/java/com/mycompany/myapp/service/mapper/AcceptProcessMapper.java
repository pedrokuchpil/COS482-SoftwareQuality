package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.AcceptProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AcceptProcess} and its DTO {@link AcceptProcessDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class, EmprestimoMapper.class })
public interface AcceptProcessMapper extends EntityMapper<AcceptProcessDTO, AcceptProcess> {
    @Mapping(target = "processInstance", source = "processInstance")
    @Mapping(target = "emprestimo", source = "emprestimo")
    AcceptProcessDTO toDto(AcceptProcess s);
}
