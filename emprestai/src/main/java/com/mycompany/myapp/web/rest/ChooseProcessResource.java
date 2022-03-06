package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ChooseProcessService;
import com.mycompany.myapp.service.dto.ChooseProcessDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.ChooseProcess}.
 */
@RestController
@RequestMapping("/api")
public class ChooseProcessResource {

    private final Logger log = LoggerFactory.getLogger(ChooseProcessResource.class);

    private static final String ENTITY_NAME = "chooseProcess";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChooseProcessService chooseProcessService;

    public ChooseProcessResource(ChooseProcessService chooseProcessService) {
        this.chooseProcessService = chooseProcessService;
    }

    /**
     * {@code POST  /choose-processes} : Create a new chooseProcess.
     *
     * @param chooseProcessDTO the chooseProcessDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new chooseProcessDTO, or with status {@code 400 (Bad Request)} if the chooseProcess has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/choose-processes")
    public ResponseEntity<ChooseProcessDTO> create(@RequestBody ChooseProcessDTO chooseProcessDTO) throws URISyntaxException {
        log.debug("REST request to save ChooseProcess : {}", chooseProcessDTO);
        if (chooseProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new chooseProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChooseProcessDTO result = chooseProcessService.create(chooseProcessDTO);
        return ResponseEntity
            .created(new URI("/api/choose-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /choose-processes} : get all the chooseProcesss.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of chooseProcesss in body.
     */
    @GetMapping("/choose-processes")
    public List<ChooseProcessDTO> getAllChooseProcesss() {
        log.debug("REST request to get all ChooseProcesss");
        return chooseProcessService.findAll();
    }

    /**
     * {@code GET  /choose-processes/:id} : get the "id" chooseProcess.
     *
     * @param processInstanceId the id of the chooseProcessDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chooseProcessDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/choose-processes/{processInstanceId}")
    public ResponseEntity<ChooseProcessDTO> getByProcessInstanceId(@PathVariable Long processInstanceId) {
        log.debug("REST request to get ChooseProcess by processInstanceId : {}", processInstanceId);
        Optional<ChooseProcessDTO> chooseProcessDTO = chooseProcessService.findByProcessInstanceId(processInstanceId);
        return ResponseUtil.wrapOrNotFound(chooseProcessDTO);
    }
}
