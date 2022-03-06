package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ChooseProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ChooseProcess} and its DTO {@link ChooseProcessDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class, EmprestimoMapper.class })
public interface ChooseProcessMapper extends EntityMapper<ChooseProcessDTO, ChooseProcess> {
    @Mapping(target = "processInstance", source = "processInstance")
    @Mapping(target = "emprestimo", source = "emprestimo")
    ChooseProcessDTO toDto(ChooseProcess s);
}
