package Infnet.Assessment.dto.AventureiroDTO;
import Infnet.Assessment.dto.CompanheiroDTO.CompanheiroRequestDTO;
import Infnet.Assessment.enums.ClasseAventureiro;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AventureiroRequestDTO (

    @NotBlank(message = "O nome é obrigatorio") 
    String nomeAventureiro,
    
    @NotNull(message = "A classe é obrigatoria") 
    ClasseAventureiro classeAventureiro,
    
    @NotNull (message = "O nível é obrigatoria") 
    @Min(1)
    Integer nivelAventureiro,

    @Valid
    CompanheiroRequestDTO  companheiroRequestDTO
    
){}