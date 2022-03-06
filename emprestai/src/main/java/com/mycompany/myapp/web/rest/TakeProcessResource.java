package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.TakeProcessService;
import com.mycompany.myapp.service.dto.TakeProcessDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TakeProcess}.
 */
@RestController
@RequestMapping("/api")
public class TakeProcessResource {

    private final Logger log = LoggerFactory.getLogger(TakeProcessResource.class);

    private static final String ENTITY_NAME = "takeProcess";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TakeProcessService takeProcessService;

    public TakeProcessResource(TakeProcessService takeProcessService) {
        this.takeProcessService = takeProcessService;
    }

    /**
     * {@code POST  /take-processes} : Create a new takeProcess.
     *
     * @param takeProcessDTO the takeProcessDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new takeProcessDTO, or with status {@code 400 (Bad Request)} if the takeProcess has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/take-processes")
    public ResponseEntity<TakeProcessDTO> create(@RequestBody TakeProcessDTO takeProcessDTO) throws URISyntaxException {
        log.debug("REST request to save TakeProcess : {}", takeProcessDTO);
        if (takeProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new takeProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TakeProcessDTO result = takeProcessService.create(takeProcessDTO);
        return ResponseEntity
            .created(new URI("/api/take-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /take-processes} : get all the takeProcesss.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of takeProcesss in body.
     */
    @GetMapping("/take-processes")
    public List<TakeProcessDTO> getAllTakeProcesss() {
        log.debug("REST request to get all TakeProcesss");
        return takeProcessService.findAll();
    }

    /**
     * {@code GET  /take-processes/:id} : get the "id" takeProcess.
     *
     * @param processInstanceId the id of the takeProcessDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the takeProcessDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/take-processes/{processInstanceId}")
    public ResponseEntity<TakeProcessDTO> getByProcessInstanceId(@PathVariable Long processInstanceId) {
        log.debug("REST request to get TakeProcess by processInstanceId : {}", processInstanceId);
        Optional<TakeProcessDTO> takeProcessDTO = takeProcessService.findByProcessInstanceId(processInstanceId);
        return ResponseUtil.wrapOrNotFound(takeProcessDTO);
    }
}
