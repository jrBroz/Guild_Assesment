package Infnet.Assessment.model;

import java.time.LocalDateTime;
import java.util.List;

import Infnet.Assessment.enums.NivelPerigoMissao;
import Infnet.Assessment.enums.StatusMissao;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Missao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "organizacao_id", nullable = false)
    private Organizacao organizacao;

    @Column(nullable = false, length = 150)
    private String titulo;

     @Column(nullable = false, length = 150)
    private String descricao;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NivelPerigoMissao nivelPerigoMissao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusMissao status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    private LocalDateTime dataInicio;
    private LocalDateTime dataTermino;

    // --- ADICIONADO: Lista de participantes ---
    // Uma missão tem muitos aventureiros e um aventureiro pode estar em várias missões
    @ManyToMany
    @JoinTable(
        name = "missao_participantes",
        joinColumns = @JoinColumn(name = "missao_id"),
        inverseJoinColumns = @JoinColumn(name = "aventureiro_id")
    )
    @OneToMany(mappedBy = "missao") 
    private List<ParticipacaoEmMissao> participacoes;




    @PrePersist
    protected void onCreate() {
        // Registro automático da data de criação
        this.dataCriacao = LocalDateTime.now();
        
        // Garante que toda missão comece como PLANEJADA se não for enviado status
        if (this.status == null) {
            this.status = StatusMissao.PLANEJADA;
        }
    }


    @PreUpdate
    protected void onUpdate() {
        // Se o status mudar para CONCLUIDA ou CANCELADA, podemos carimbar a data de término
        if ((this.status == StatusMissao.CONCLUIDA || this.status == StatusMissao.CANCELADA) 
            && this.dataTermino == null) {
            this.dataTermino = LocalDateTime.now();
        }
    }

    public List<ParticipacaoEmMissao> getParticipacoes() {
        return participacoes;
    }
}