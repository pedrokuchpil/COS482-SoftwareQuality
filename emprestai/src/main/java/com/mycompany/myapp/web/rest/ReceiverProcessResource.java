package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ReceiverProcessService;
import com.mycompany.myapp.service.dto.ReceiverProcessDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.ReceiverProcess}.
 */
@RestController
@RequestMapping("/api")
public class ReceiverProcessResource {

    private final Logger log = LoggerFactory.getLogger(ReceiverProcessResource.class);

    private static final String ENTITY_NAME = "receiverProcess";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReceiverProcessService receiverProcessService;

    public ReceiverProcessResource(ReceiverProcessService receiverProcessService) {
        this.receiverProcessService = receiverProcessService;
    }

    /**
     * {@code POST  /receiver-processes} : Create a new receiverProcess.
     *
     * @param receiverProcessDTO the receiverProcessDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new receiverProcessDTO, or with status {@code 400 (Bad Request)} if the receiverProcess has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/receiver-processes")
    public ResponseEntity<ReceiverProcessDTO> create(@RequestBody ReceiverProcessDTO receiverProcessDTO) throws URISyntaxException {
        log.debug("REST request to save ReceiverProcess : {}", receiverProcessDTO);
        if (receiverProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new receiverProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReceiverProcessDTO result = receiverProcessService.create(receiverProcessDTO);
        return ResponseEntity
            .created(new URI("/api/receiver-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /receiver-processes} : get all the receiverProcesss.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of receiverProcesss in body.
     */
    @GetMapping("/receiver-processes")
    public List<ReceiverProcessDTO> getAllReceiverProcesss() {
        log.debug("REST request to get all ReceiverProcesss");
        return receiverProcessService.findAll();
    }

    /**
     * {@code GET  /receiver-processes/:id} : get the "id" receiverProcess.
     *
     * @param processInstanceId the id of the receiverProcessDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the receiverProcessDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/receiver-processes/{processInstanceId}")
    public ResponseEntity<ReceiverProcessDTO> getByProcessInstanceId(@PathVariable Long processInstanceId) {
        log.debug("REST request to get ReceiverProcess by processInstanceId : {}", processInstanceId);
        Optional<ReceiverProcessDTO> receiverProcessDTO = receiverProcessService.findByProcessInstanceId(processInstanceId);
        return ResponseUtil.wrapOrNotFound(receiverProcessDTO);
    }
}
