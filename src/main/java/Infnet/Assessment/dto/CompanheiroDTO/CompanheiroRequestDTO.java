package Infnet.Assessment.dto.CompanheiroDTO;
import Infnet.Assessment.enums.EspecieCompanheiro;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CompanheiroRequestDTO {

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotNull (message = "A espécie é obrigatória.")
    private EspecieCompanheiro especieCompanheiro;

    @NotBlank(message = "O nível de lealdade é obrigatório.")
    @Min(0)
    @Max(100)
    private Integer nivelLealdade;
}
