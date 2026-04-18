package Infnet.Assessment.dto.RankingDTO;

public record RankingDTO(
    String nome,
    Long totalParticipacoes,
    Double somaRecompensas,
    Long quantidadeDestaques
) {}