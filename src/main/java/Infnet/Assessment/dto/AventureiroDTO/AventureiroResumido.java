package Infnet.Assessment.dto.AventureiroDTO;
import Infnet.Assessment.enums.ClasseAventureiro;

public record AventureiroResumido(
    Long id,
    String nome,
    ClasseAventureiro classe,
    Integer nivel,
    Boolean ativo
) {}