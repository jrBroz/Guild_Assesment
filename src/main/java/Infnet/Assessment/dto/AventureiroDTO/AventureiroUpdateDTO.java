package Infnet.Assessment.dto.AventureiroDTO;

import Infnet.Assessment.enums.ClasseAventureiro;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AventureiroUpdateDTO(
    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 120, message = "O nome deve ter no máximo 120 caracteres.")
    String nome,

    @NotNull(message = "A classe é obrigatória.")
    ClasseAventureiro classe,

    @NotNull(message = "O nível é obrigatório.")
    @Min(value = 1, message = "O nível mínimo é 1")
    @Max(value = 100, message = "O nível máximo é 100")
    Integer nivel
) {}