package Infnet.Assessment.dto.MetricasMissaoDTO;
import Infnet.Assessment.enums.NivelPerigoMissao;
import Infnet.Assessment.enums.StatusMissao;

public record MissaoMetricaDTO(
    String titulo,
    StatusMissao status,
    NivelPerigoMissao nivelPerigo,
    Long qtdParticipantes,
    Double totalRecompensas
) {