package Infnet.Assessment.dto.RankingDTO;

import java.math.BigDecimal;

public record RankingDTO(
    Long id,
    String nome,
    Long totalParticipacoes,
    BigDecimal somaRecompensas,
    Long quantidadeDestaques
) {}