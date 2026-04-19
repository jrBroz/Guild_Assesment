package Infnet.Assessment.dto.MissaoDTO;
import Infnet.Assessment.dto.AventureiroDTO.AventureiroResumido;
import Infnet.Assessment.enums.StatusMissao;
import java.util.List;

public record MissaoResponseDTO(
    
    Long id,
    String titulo,
    String nomeOrganizacao, 
    StatusMissao status,
    List<AventureiroResumido> participantes 
) {}