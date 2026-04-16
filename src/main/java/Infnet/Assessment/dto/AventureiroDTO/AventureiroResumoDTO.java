package Infnet.Assessment.dto.AventureiroDTO;

import Infnet.Assessment.enums.ClasseAventureiro;

public record AventureiroResumoDTO(

    Long id,
    String nome,
    ClasseAventureiro classe,
    Integer nivel


){}