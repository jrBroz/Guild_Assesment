package Infnet.Assessment.dto.ParticipacaoDTO;

import Infnet.Assessment.enums.PapelAventureiroMissao;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ParticipacaoRequestDTO(
    @NotNull(message = "O ID da missão é obrigatório")
    Long missaoId,

    @NotNull(message = "O ID do aventureiro é obrigatório")
    Long aventureiroId,

    @NotNull(message = "O papel do aventureiro na missão é obrigatório")
    PapelAventureiroMissao papel,

    @Min(value = 0, message = "A recompensa não pode ser negativa")
    Double recompensaOuro,

    @NotNull(message = "É necessário definir se é destaque (MVP)")
    Boolean mvp
) {}