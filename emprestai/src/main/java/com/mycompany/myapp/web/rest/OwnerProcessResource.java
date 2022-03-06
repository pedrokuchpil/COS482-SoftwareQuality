package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.OwnerProcessService;
import com.mycompany.myapp.service.dto.OwnerProcessDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.OwnerProcess}.
 */
@RestController
@RequestMapping("/api")
public class OwnerProcessResource {

    private final Logger log = LoggerFactory.getLogger(OwnerProcessResource.class);

    private static final String ENTITY_NAME = "ownerProcess";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OwnerProcessService ownerProcessService;

    public OwnerProcessResource(OwnerProcessService ownerProcessService) {
        this.ownerProcessService = ownerProcessService;
    }

    /**
     * {@code POST  /owner-processes} : Create a new ownerProcess.
     *
     * @param ownerProcessDTO the ownerProcessDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ownerProcessDTO, or with status {@code 400 (Bad Request)} if the ownerProcess has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/owner-processes")
    public ResponseEntity<OwnerProcessDTO> create(@RequestBody OwnerProcessDTO ownerProcessDTO) throws URISyntaxException {
        log.debug("REST request to save OwnerProcess : {}", ownerProcessDTO);
        if (ownerProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new ownerProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OwnerProcessDTO result = ownerProcessService.create(ownerProcessDTO);
        return ResponseEntity
            .created(new URI("/api/owner-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /owner-processes} : get all the ownerProcesss.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ownerProcesss in body.
     */
    @GetMapping("/owner-processes")
    public List<OwnerProcessDTO> getAllOwnerProcesss() {
        log.debug("REST request to get all OwnerProcesss");
        return ownerProcessService.findAll();
    }

    /**
     * {@code GET  /owner-processes/:id} : get the "id" ownerProcess.
     *
     * @param processInstanceId the id of the ownerProcessDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ownerProcessDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/owner-processes/{processInstanceId}")
    public ResponseEntity<OwnerProcessDTO> getByProcessInstanceId(@PathVariable Long processInstanceId) {
        log.debug("REST request to get OwnerProcess by processInstanceId : {}", processInstanceId);
        Optional<OwnerProcessDTO> ownerProcessDTO = ownerProcessService.findByProcessInstanceId(processInstanceId);
        return ResponseUtil.wrapOrNotFound(ownerProcessDTO);
    }
}
