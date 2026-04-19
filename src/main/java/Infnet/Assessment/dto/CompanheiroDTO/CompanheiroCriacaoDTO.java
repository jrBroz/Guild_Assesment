package Infnet.Assessment.dto.CompanheiroDTO;

import Infnet.Assessment.enums.EspecieCompanheiro;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record CompanheiroCriacaoDTO(

    @Size(max = 120)
    @NotBlank( message = "O nome é obrigatório.")
    String nome,
    
    
    @NotNull(message = "A espécie é obrigatória.")
    EspecieCompanheiro especie, 
    
    
    @Max(value = 100, message = "100 é o nivel máximo de lealdade")
    @PositiveOrZero(message = "lealdade não pode ser menor que 0 ")
    Integer lealdade
) {} 

