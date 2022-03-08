package com.mycompany.myapp.process.receiverProcess;

import com.mycompany.myapp.domain.Book;
import com.mycompany.myapp.domain.Emprestimo;
import com.mycompany.myapp.domain.ReceiverProcess;
import com.mycompany.myapp.service.dto.BookDTO;
import com.mycompany.myapp.service.dto.EmprestimoDTO;
import com.mycompany.myapp.service.dto.ReceiverProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface SelecionarLivroMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    ReceiverProcessDTO toReceiverProcessDTO(ReceiverProcess receiverProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "book", source = "book")
    EmprestimoDTO toEmprestimoDTO(Emprestimo emprestimo);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    BookDTO toBookDTO(Book title);
}
