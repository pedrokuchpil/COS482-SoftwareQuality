package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Emprestimo;
import com.mycompany.myapp.repository.EmprestimoRepository;
import com.mycompany.myapp.service.dto.EmprestimoDTO;
import com.mycompany.myapp.service.mapper.EmprestimoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Emprestimo}.
 */
@Service
@Transactional
public class EmprestimoService {

    private final Logger log = LoggerFactory.getLogger(EmprestimoService.class);

    private final EmprestimoRepository emprestimoRepository;

    private final EmprestimoMapper emprestimoMapper;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, EmprestimoMapper emprestimoMapper) {
        this.emprestimoRepository = emprestimoRepository;
        this.emprestimoMapper = emprestimoMapper;
    }

    /**
     * Save a emprestimo.
     *
     * @param emprestimoDTO the entity to save.
     * @return the persisted entity.
     */
    public EmprestimoDTO save(EmprestimoDTO emprestimoDTO) {
        log.debug("Request to save Emprestimo : {}", emprestimoDTO);
        Emprestimo emprestimo = emprestimoMapper.toEntity(emprestimoDTO);
        emprestimo = emprestimoRepository.save(emprestimo);
        return emprestimoMapper.toDto(emprestimo);
    }

    /**
     * Partially update a emprestimo.
     *
     * @param emprestimoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EmprestimoDTO> partialUpdate(EmprestimoDTO emprestimoDTO) {
        log.debug("Request to partially update Emprestimo : {}", emprestimoDTO);

        return emprestimoRepository
            .findById(emprestimoDTO.getId())
            .map(
                existingEmprestimo -> {
                    emprestimoMapper.partialUpdate(existingEmprestimo, emprestimoDTO);
                    return existingEmprestimo;
                }
            )
            .map(emprestimoRepository::save)
            .map(emprestimoMapper::toDto);
    }

    /**
     * Get all the emprestimos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EmprestimoDTO> findAll() {
        log.debug("Request to get all Emprestimos");
        return emprestimoRepository.findAll().stream().map(emprestimoMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one emprestimo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EmprestimoDTO> findOne(Long id) {
        log.debug("Request to get Emprestimo : {}", id);
        return emprestimoRepository.findById(id).map(emprestimoMapper::toDto);
    }

    /**
     * Delete the emprestimo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Emprestimo : {}", id);
        emprestimoRepository.deleteById(id);
    }
}
