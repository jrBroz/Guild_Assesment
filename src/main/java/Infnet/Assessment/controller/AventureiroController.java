package Infnet.Assessment.controller;
import Infnet.Assessment.dto.AventureiroDTO.AventureiroRequestDTO;
import Infnet.Assessment.dto.AventureiroDTO.AventureiroResponseDTO;
import Infnet.Assessment.dto.AventureiroDTO.AventureiroResumido;
import Infnet.Assessment.dto.RankingDTO.RankingDTO;
import Infnet.Assessment.dto.CompanheiroDTO.CompanheiroCriacaoDTO;
import Infnet.Assessment.service.AventureiroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/aventureiros")
@RequiredArgsConstructor 
public class AventureiroController {

    private final AventureiroService service; // 2. Deve ser 'final' para o RequiredArgsConstructor funcionar

    @PostMapping
    public ResponseEntity<AventureiroResponseDTO> criar(@Valid @RequestBody AventureiroRequestDTO dto) {
        // O método no service agora  chama 'criar' e já retorna o DTO completo
        AventureiroResponseDTO response = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AventureiroResponseDTO> buscarPorId(@PathVariable Long id) {
        // O método no service agora: 'obterPerfilCompleto'
        return ResponseEntity.ok(service.obterPerfilCompleto(id));
    }

    @GetMapping
    public ResponseEntity<Page<AventureiroResumido>> listar(
            @RequestParam(required = false) Boolean ativo,
            @RequestParam(required = false) String classe,
            @RequestParam(required = false) Integer nivelMin,
            Pageable pageable) {
        return ResponseEntity.ok(service.listarComFiltros(ativo, classe, nivelMin, pageable));
    }

    @PostMapping("/{id}/companheiro")
    public ResponseEntity<AventureiroResponseDTO> atribuirCompanheiro(
            @PathVariable Long id,
            @Valid @RequestBody CompanheiroCriacaoDTO dto) {
        return ResponseEntity.ok(service.atribuirCompanheiro(id, dto));
    }

    @GetMapping("/ranking")
    public ResponseEntity<Page<RankingDTO>> verRanking(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim,
            Pageable pageable) {
        return ResponseEntity.ok(service.gerarRanking(inicio, fim, pageable));
    }
}