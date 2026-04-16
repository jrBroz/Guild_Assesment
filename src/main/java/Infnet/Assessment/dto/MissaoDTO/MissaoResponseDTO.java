package Infnet.Assessment.dto.MissaoDTO;

import Infnet.Assessment.dto.AventureiroDTO.AventureiroResumoDTO;
import Infnet.Assessment.enums.NivelPerigoMissao;
import Infnet.Assessment.enums.StatusMissao;
import java.time.LocalDateTime;
import java.util.List;

public record MissaoResponseDTO(
    
Long id,
    String titulo,
    String nomeOrganizacao, 
    StatusMissao status,
    List<AventureiroResumoDTO> participantes 
) {}