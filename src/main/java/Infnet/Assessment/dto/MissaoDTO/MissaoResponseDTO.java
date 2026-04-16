package Infnet.Assessment.dto.MissaoDTO;

import Infnet.Assessment.enums.NivelPerigoMissao;
import Infnet.Assessment.enums.StatusMissao;
import java.time.LocalDateTime;

public record MissaoResponseDTO(
    
    Long id, 
    Long organizacaoId, 
    String titulo,
    NivelPerigoMissao nivelPerigo,
    StatusMissao status,
    LocalDateTime dataCriacao, 
    LocalDateTime dataInicio,  
    LocalDateTime dataTermino  
) {}