package Infnet.Assessment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;

@Entity
@Immutable // Proteção contra o Boss (DBA): Garante que o JPA nunca tentará atualizar essa view
@Table(name = "mv_painel_tatico_missao", schema = "operacoes")
@Data
public class PainelTaticoMissao {

    @Id
    private Long missaoId;

    private String titulo;
    
    // Tratando como String para evitar erro de parse caso o Enum não bata com a View
    private String status; 
    private String nivelPerigo;
    
    private Long organizacaoId;
    private Integer totalParticipantes;
    private Double nivelMedioEquipe;
    private Double totalRecompensa;
    private Integer totalMvps;
    private Integer participantesComCompanheiro;
    private LocalDateTime ultimaAtualizacao;
    private Double indiceProntidao;
}