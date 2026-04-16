package Infnet.Assessment.dto.MissaoDTO;
import Infnet.Assessment.enums.NivelPerigoMissao;
import Infnet.Assessment.enums.StatusMissao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public record MissaoRequestDTO(
    
    // Recebemos apenas o ID da organização, não o objeto inteiro
    @NotNull(message = "A organização responsável é obrigatória.")
    Long organizacaoId,

    @NotBlank(message = "O título da missão não pode estar vazio.")
    @Size(max = 150, message = "O título deve ter no máximo 150 caracteres.")
    String titulo,

    @NotNull(message = "O nível de perigo é obrigatório.")
     NivelPerigoMissao nivelPerigo,

    @NotNull(message = "O status inicial é obrigatório.")
    StatusMissao status,

    // Campos Opcionais (não levam anotação de @NotNull)
    LocalDateTime dataInicio,
    LocalDateTime dataTermino
) {}