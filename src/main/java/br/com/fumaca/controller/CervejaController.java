package br.com.fumaca.controller;

import br.com.fumaca.controller.AbstractController;
import br.com.fumaca.dto.CervejaRequestDTO;
import br.com.fumaca.dto.CervejaResponseDTO;
import br.com.fumaca.service.CervejaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cervejas")
public class CervejaController extends AbstractController {

    @Autowired
    private CervejaService service;

    @PostMapping
    public ResponseEntity<CervejaResponseDTO> create(@RequestBody CervejaRequestDTO dto) {
        CervejaResponseDTO salvo = service.salvar(dto);
        return ResponseEntity.created(URI.create("/cervejas/" + salvo.getId())).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<CervejaResponseDTO>> findAll() {
        List<CervejaResponseDTO> lista = service.buscaTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("{id}")
    public ResponseEntity<CervejaResponseDTO> findById(@PathVariable Long id) {
        CervejaResponseDTO dto = service.buscaPorId(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PutMapping("{id}")
    public ResponseEntity<CervejaResponseDTO> update(@PathVariable Long id, @RequestBody CervejaRequestDTO dto) {
        CervejaResponseDTO alterado = service.alterar(id, dto);
        if (alterado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(alterado);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
