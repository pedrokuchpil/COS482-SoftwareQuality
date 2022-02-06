package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EmprestimoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Emprestimo} and its DTO {@link EmprestimoDTO}.
 */
@Mapper(componentModel = "spring", uses = { UsuarioMapper.class, BookMapper.class })
public interface EmprestimoMapper extends EntityMapper<EmprestimoDTO, Emprestimo> {
    @Mapping(target = "receiver", source = "receiver", qualifiedByName = "username")
    @Mapping(target = "book", source = "book", qualifiedByName = "id")
    EmprestimoDTO toDto(Emprestimo s);
}
