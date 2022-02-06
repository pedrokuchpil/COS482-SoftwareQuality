package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.EmprestimoRepository;
import com.mycompany.myapp.service.EmprestimoService;
import com.mycompany.myapp.service.dto.EmprestimoDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Emprestimo}.
 */
@RestController
@RequestMapping("/api")
public class EmprestimoResource {

    private final Logger log = LoggerFactory.getLogger(EmprestimoResource.class);

    private final EmprestimoService emprestimoService;

    private final EmprestimoRepository emprestimoRepository;

    public EmprestimoResource(EmprestimoService emprestimoService, EmprestimoRepository emprestimoRepository) {
        this.emprestimoService = emprestimoService;
        this.emprestimoRepository = emprestimoRepository;
    }

    /**
     * {@code GET  /emprestimos} : get all the emprestimos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of emprestimos in body.
     */
    @GetMapping("/emprestimos")
    public List<EmprestimoDTO> getAllEmprestimos() {
        log.debug("REST request to get all Emprestimos");
        return emprestimoService.findAll();
    }

    /**
     * {@code GET  /emprestimos/:id} : get the "id" emprestimo.
     *
     * @param id the id of the emprestimoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the emprestimoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/emprestimos/{id}")
    public ResponseEntity<EmprestimoDTO> getEmprestimo(@PathVariable Long id) {
        log.debug("REST request to get Emprestimo : {}", id);
        Optional<EmprestimoDTO> emprestimoDTO = emprestimoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(emprestimoDTO);
    }
}
