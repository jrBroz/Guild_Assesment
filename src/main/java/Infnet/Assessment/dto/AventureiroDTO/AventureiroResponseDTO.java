package Infnet.Assessment.dto.AventureiroDTO;
import Infnet.Assessment.dto.CompanheiroDTO.CompanheiroResponseDTO;
import Infnet.Assessment.enums.ClasseAventureiro;

public record AventureiroResponseDTO (
    Long idAventureiro,          
    String nomeAventureiro,
    ClasseAventureiro classeAventureiro,
    Integer nivelAventureiro,
    Boolean ativo,                
    CompanheiroResponseDTO companheiro 
){}