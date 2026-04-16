package Infnet.Assessment.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Infnet.Assessment.dto.MissaoDTO.MissaoRequestDTO;
import Infnet.Assessment.dto.MissaoDTO.MissaoResponseDTO;
import Infnet.Assessment.service.MissaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/missoes")
public class MissaoController {

   
    private MissaoService service;

    @PostMapping
    public ResponseEntity<MissaoResponseDTO> criar(@Valid @RequestBody MissaoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<MissaoResponseDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }
}