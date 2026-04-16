package Infnet.Assessment.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Infnet.Assessment.dto.AventureiroDTO.AventureiroRequestDTO;
import Infnet.Assessment.dto.AventureiroDTO.AventureiroResponseDTO;
import Infnet.Assessment.dto.AventureiroDTO.AventureiroUpdateDTO;
import Infnet.Assessment.model.Aventureiro;
import Infnet.Assessment.service.AventureiroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/aventureiros")
public class AventureiroController {

 
    private AventureiroService service;

    @PostMapping
    public ResponseEntity<AventureiroResponseDTO> criar(@Valid @RequestBody AventureiroRequestDTO dto) {
        AventureiroResponseDTO novo = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AventureiroResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AventureiroResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody AventureiroUpdateDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}}