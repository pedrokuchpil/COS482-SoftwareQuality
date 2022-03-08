package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ReadProcessService;
import com.mycompany.myapp.service.dto.ReadProcessDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.ReadProcess}.
 */
@RestController
@RequestMapping("/api")
public class ReadProcessResource {

    private final Logger log = LoggerFactory.getLogger(ReadProcessResource.class);

    private static final String ENTITY_NAME = "readProcess";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReadProcessService readProcessService;

    public ReadProcessResource(ReadProcessService readProcessService) {
        this.readProcessService = readProcessService;
    }

    /**
     * {@code POST  /read-processes} : Create a new readProcess.
     *
     * @param readProcessDTO the readProcessDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new readProcessDTO, or with status {@code 400 (Bad Request)} if the readProcess has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/read-processes")
    public ResponseEntity<ReadProcessDTO> create(@RequestBody ReadProcessDTO readProcessDTO) throws URISyntaxException {
        log.debug("REST request to save ReadProcess : {}", readProcessDTO);
        if (readProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new readProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReadProcessDTO result = readProcessService.create(readProcessDTO);
        return ResponseEntity
            .created(new URI("/api/read-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /read-processes} : get all the readProcesss.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of readProcesss in body.
     */
    @GetMapping("/read-processes")
    public List<ReadProcessDTO> getAllReadProcesss() {
        log.debug("REST request to get all ReadProcesss");
        return readProcessService.findAll();
    }

    /**
     * {@code GET  /read-processes/:id} : get the "id" readProcess.
     *
     * @param processInstanceId the id of the readProcessDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the readProcessDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/read-processes/{processInstanceId}")
    public ResponseEntity<ReadProcessDTO> getByProcessInstanceId(@PathVariable Long processInstanceId) {
        log.debug("REST request to get ReadProcess by processInstanceId : {}", processInstanceId);
        Optional<ReadProcessDTO> readProcessDTO = readProcessService.findByProcessInstanceId(processInstanceId);
        return ResponseUtil.wrapOrNotFound(readProcessDTO);
    }
}
