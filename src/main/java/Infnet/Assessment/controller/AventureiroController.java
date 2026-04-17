package Infnet.Assessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Infnet.Assessment.dto.AventureiroDTO.AventureiroRequestDTO;
import Infnet.Assessment.dto.AventureiroDTO.AventureiroResponseDTO;
import Infnet.Assessment.model.Aventureiro;
import Infnet.Assessment.service.AventureiroService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/aventureiros")
public class AventureiroController { 
    private AventureiroService service;

    @PostMapping
    public ResponseEntity<AventureiroResponseDTO> criar(@Valid @RequestBody AventureiroRequestDTO dto) {
        // 2ª MUDANÇA: Use o nome EXATO que está na Service: criarAventureiro
        Aventureiro entidade = service.criarAventureiro(dto);
        
        // 3ª MUDANÇA: Transforme a Entidade em DTO antes de devolver
        AventureiroResponseDTO response = converterParaDTO(entidade);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AventureiroResponseDTO> buscarPorId(@PathVariable Long id) {
        Aventureiro entidade = service.retornarAventureiro(id);
        
        return ResponseEntity.ok(converterParaDTO(entidade));
    }

    // Método auxiliar para não repetir código de conversão
  private AventureiroResponseDTO converterParaDTO(Aventureiro aventureiro) {
    return new AventureiroResponseDTO(
        aventureiro.getId(),               // idAventureiro
        aventureiro.getNome(),             // nomeAventureiro
        aventureiro.getClasse(),           // classeAventureiro (já é o Enum)
        aventureiro.getNivel(),            // nivelAventureiro
        aventureiro.getAtivo(),            // ativo
        null                               //  vo dxa null pr encquantro
    );
}
}
