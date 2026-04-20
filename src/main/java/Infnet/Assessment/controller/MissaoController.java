package Infnet.Assessment.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; // Adicionado import limpo
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Infnet.Assessment.dto.AventureiroDTO.AventureiroResumido; // Usando apenas este!
import Infnet.Assessment.dto.MissaoDTO.MissaoRequestDTO;
import Infnet.Assessment.dto.MissaoDTO.MissaoResponseDTO;
import Infnet.Assessment.enums.NivelPerigoMissao;
import Infnet.Assessment.enums.StatusMissao;
import Infnet.Assessment.model.Missao;
import Infnet.Assessment.service.MissaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/missoes")
@RequiredArgsConstructor 
public class MissaoController {

    // private final MissaoService service;

    // @PostMapping
    // public ResponseEntity<MissaoResponseDTO> criar(@Valid @RequestBody MissaoRequestDTO dto) {
    //     Missao missao = service.criar(dto);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(converterParaDTO(missao));
    // }

    // @GetMapping
    // public ResponseEntity<Page<MissaoResponseDTO>> listar(
    //     @RequestParam(required = false) StatusMissao status,
    //     @RequestParam(required = false) NivelPerigoMissao nivel,
    //     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
    //     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim,
    //     Pageable pageable) { 
        
    //     Page<Missao> pagina = service.listarComFiltros(status, nivel, inicio, fim, pageable);
    //     return ResponseEntity.ok(pagina.map(this::converterParaDTO));
    // }

    // private MissaoResponseDTO converterParaDTO(Missao m) {
    //     List<AventureiroResumido> participantesDTO = m.getParticipacoes().stream()
    //         .map(p -> new AventureiroResumido(
    //             p.getAventureiro().getId(),         
    //             p.getAventureiro().getNome(),       
    //             p.getAventureiro().getClasse(),     
    //             p.getAventureiro().getNivel(),       
    //             p.getAventureiro().getAtivo()       
    //         ))
    //         .toList();

    //     return new MissaoResponseDTO(
    //         m.getId(),
    //         m.getTitulo(),
    //         m.getOrganizacao().getNome(),
    //         m.getStatus(),
    //         participantesDTO
    //     );
    // }
}
