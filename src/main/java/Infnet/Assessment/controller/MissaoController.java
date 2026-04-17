package Infnet.Assessment.controller;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Infnet.Assessment.dto.AventureiroDTO.AventureiroResumoDTO;
import Infnet.Assessment.dto.MissaoDTO.MissaoRequestDTO;
import Infnet.Assessment.dto.MissaoDTO.MissaoResponseDTO;
import Infnet.Assessment.enums.NivelPerigoMissao;
import Infnet.Assessment.enums.StatusMissao;
import Infnet.Assessment.model.Missao;
import Infnet.Assessment.service.MissaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/missoes")
public class MissaoController {

    @Autowired // Adicione isso!
    private MissaoService service;

    @PostMapping
    public ResponseEntity<MissaoResponseDTO> criar(@Valid @RequestBody MissaoRequestDTO dto) {
        Missao missao = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(converterParaDTO(missao));
    }

    @GetMapping
    public ResponseEntity<Page<MissaoResponseDTO>> listar(
        @RequestParam(required = false) StatusMissao status,
        @RequestParam(required = false) NivelPerigoMissao nivel,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim,
        org.springframework.data.domain.Pageable pageable) { // Import correto!
        
        Page<Missao> pagina = service.listarComFiltros(status, nivel, inicio, fim, pageable);
        return ResponseEntity.ok(pagina.map(this::converterParaDTO));
    }

private MissaoResponseDTO converterParaDTO(Missao m) {
    // 1. Transforma a lista de participacoes em DTOs de 4 campos
    List<AventureiroResumoDTO> participantesDTO = m.getParticipacoes().stream()
        .map(p -> new AventureiroResumoDTO(
            p.getAventureiro().getId(),         // id
            p.getAventureiro().getNome(),       // nome
            p.getAventureiro().getClasse(),     // classe (NOVO)
            p.getAventureiro().getNivel()       // nivel (NOVO)
        ))
        .toList();

    // 2. Criar o DTO de resposta da Missão
    return new MissaoResponseDTO(
        m.getId(),
        m.getTitulo(),
        m.getOrganizacao().getNome(),
        m.getStatus(),
        participantesDTO
    );
}
}