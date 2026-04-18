package Infnet.Assessment.model;

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
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"missao_id", "aventureiro_id"})
})
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
    private PapelAventureiroMissao papel; 

    private Double recompensaOuro;

    @NotNull
    private Boolean mvp;

    @Column(nullable = false, updatable = false)
    @NotNull
    private LocalDateTime dataRegistro;

    @PrePersist
    protected void onCreate() {
        this.dataRegistro = LocalDateTime.now();
    }
}