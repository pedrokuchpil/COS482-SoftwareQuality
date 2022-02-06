package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.EmprestimoProcessService;
import com.mycompany.myapp.service.dto.EmprestimoProcessDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.EmprestimoProcess}.
 */
@RestController
@RequestMapping("/api")
public class EmprestimoProcessResource {

    private final Logger log = LoggerFactory.getLogger(EmprestimoProcessResource.class);

    private static final String ENTITY_NAME = "emprestimoProcess";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmprestimoProcessService emprestimoProcessService;

    public EmprestimoProcessResource(EmprestimoProcessService emprestimoProcessService) {
        this.emprestimoProcessService = emprestimoProcessService;
    }

    /**
     * {@code POST  /emprestimo-processes} : Create a new emprestimoProcess.
     *
     * @param emprestimoProcessDTO the emprestimoProcessDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new emprestimoProcessDTO, or with status {@code 400 (Bad Request)} if the emprestimoProcess has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/emprestimo-processes")
    public ResponseEntity<EmprestimoProcessDTO> create(@RequestBody EmprestimoProcessDTO emprestimoProcessDTO) throws URISyntaxException {
        log.debug("REST request to save EmprestimoProcess : {}", emprestimoProcessDTO);
        if (emprestimoProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new emprestimoProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmprestimoProcessDTO result = emprestimoProcessService.create(emprestimoProcessDTO);
        return ResponseEntity
            .created(new URI("/api/emprestimo-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /emprestimo-processes} : get all the emprestimoProcesss.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of emprestimoProcesss in body.
     */
    @GetMapping("/emprestimo-processes")
    public List<EmprestimoProcessDTO> getAllEmprestimoProcesss() {
        log.debug("REST request to get all EmprestimoProcesss");
        return emprestimoProcessService.findAll();
    }

    /**
     * {@code GET  /emprestimo-processes/:id} : get the "id" emprestimoProcess.
     *
     * @param processInstanceId the id of the emprestimoProcessDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the emprestimoProcessDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/emprestimo-processes/{processInstanceId}")
    public ResponseEntity<EmprestimoProcessDTO> getByProcessInstanceId(@PathVariable Long processInstanceId) {
        log.debug("REST request to get EmprestimoProcess by processInstanceId : {}", processInstanceId);
        Optional<EmprestimoProcessDTO> emprestimoProcessDTO = emprestimoProcessService.findByProcessInstanceId(processInstanceId);
        return ResponseUtil.wrapOrNotFound(emprestimoProcessDTO);
    }
}
