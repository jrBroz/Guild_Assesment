package Infnet.Assessment.dto.AventureiroDTO;
import java.time.OffsetDateTime;
import Infnet.Assessment.enums.ClasseAventureiro;
import Infnet.Assessment.model.Companheiro;
import Infnet.Assessment.model.Missao;

public record AventureiroResponseDTO (
    Long id,
    String nome,
    ClasseAventureiro classe,
    Integer nivel,
    Boolean ativo,
    OffsetDateTime data_criacao,
    Long totalParticipacoes,
    Missao ultimaMissao,
    Companheiro companheiro
){}