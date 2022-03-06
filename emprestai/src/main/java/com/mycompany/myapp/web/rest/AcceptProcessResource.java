package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.AcceptProcessService;
import com.mycompany.myapp.service.dto.AcceptProcessDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.AcceptProcess}.
 */
@RestController
@RequestMapping("/api")
public class AcceptProcessResource {

    private final Logger log = LoggerFactory.getLogger(AcceptProcessResource.class);

    private static final String ENTITY_NAME = "acceptProcess";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AcceptProcessService acceptProcessService;

    public AcceptProcessResource(AcceptProcessService acceptProcessService) {
        this.acceptProcessService = acceptProcessService;
    }

    /**
     * {@code POST  /accept-processes} : Create a new acceptProcess.
     *
     * @param acceptProcessDTO the acceptProcessDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new acceptProcessDTO, or with status {@code 400 (Bad Request)} if the acceptProcess has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/accept-processes")
    public ResponseEntity<AcceptProcessDTO> create(@RequestBody AcceptProcessDTO acceptProcessDTO) throws URISyntaxException {
        log.debug("REST request to save AcceptProcess : {}", acceptProcessDTO);
        if (acceptProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new acceptProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AcceptProcessDTO result = acceptProcessService.create(acceptProcessDTO);
        return ResponseEntity
            .created(new URI("/api/accept-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /accept-processes} : get all the acceptProcesss.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of acceptProcesss in body.
     */
    @GetMapping("/accept-processes")
    public List<AcceptProcessDTO> getAllAcceptProcesss() {
        log.debug("REST request to get all AcceptProcesss");
        return acceptProcessService.findAll();
    }

    /**
     * {@code GET  /accept-processes/:id} : get the "id" acceptProcess.
     *
     * @param processInstanceId the id of the acceptProcessDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the acceptProcessDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/accept-processes/{processInstanceId}")
    public ResponseEntity<AcceptProcessDTO> getByProcessInstanceId(@PathVariable Long processInstanceId) {
        log.debug("REST request to get AcceptProcess by processInstanceId : {}", processInstanceId);
        Optional<AcceptProcessDTO> acceptProcessDTO = acceptProcessService.findByProcessInstanceId(processInstanceId);
        return ResponseUtil.wrapOrNotFound(acceptProcessDTO);
    }
}
