package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ReadProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ReadProcess} and its DTO {@link ReadProcessDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class, EmprestimoMapper.class })
public interface ReadProcessMapper extends EntityMapper<ReadProcessDTO, ReadProcess> {
    @Mapping(target = "processInstance", source = "processInstance")
    @Mapping(target = "emprestimo", source = "emprestimo")
    ReadProcessDTO toDto(ReadProcess s);
}
