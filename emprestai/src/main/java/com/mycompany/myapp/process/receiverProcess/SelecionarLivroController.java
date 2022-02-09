package com.mycompany.myapp.process.receiverProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/receiver-process/selecionar-livro")
public class SelecionarLivroController {

    private final Logger log = LoggerFactory.getLogger(SelecionarLivroController.class);

    private final SelecionarLivroService selecionarLivroService;

    public SelecionarLivroController(SelecionarLivroService selecionarLivroService) {
        this.selecionarLivroService = selecionarLivroService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SelecionarLivroContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        SelecionarLivroContextDTO selecionarLivroContext = selecionarLivroService.loadContext(id);
        return ResponseEntity.ok(selecionarLivroContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<SelecionarLivroContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        SelecionarLivroContextDTO selecionarLivroContext = selecionarLivroService.claim(id);
        return ResponseEntity.ok(selecionarLivroContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody SelecionarLivroContextDTO selecionarLivroContext) {
        log.debug("REST request to complete ReceiverProcess.SelecionarLivro {}", selecionarLivroContext.getTaskInstance().getId());
        selecionarLivroService.complete(selecionarLivroContext);
        return ResponseEntity.noContent().build();
    }
}
