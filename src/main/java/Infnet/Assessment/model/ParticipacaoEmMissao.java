package Infnet.Assessment.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import Infnet.Assessment.enums.PapelAventureiroMissao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "participacao_missao", schema = "operacoes")
public class ParticipacaoEmMissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "missao_id", nullable = false)
    private Missao missao;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "aventureiro_id", nullable = false)
    private Aventureiro aventureiro;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "papel", nullable = false)
    private PapelAventureiroMissao papel; 

    @Column(name = "recompensa_ouro")
    private BigDecimal recompensaOuro;


    @NotNull
    @Column(name = "destaque", nullable = false)
    private Boolean mvp;

    @Column(name = "data_registro", nullable = false, updatable = false)
    @NotNull
    private LocalDateTime dataRegistro;

    @PrePersist
    protected void onCreate() {
        this.dataRegistro = LocalDateTime.now();
    }
}