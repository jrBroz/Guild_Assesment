package Infnet.Assessment.model;
import Infnet.Assessment.enums.EspecieCompanheiro;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Validação de Restrição


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Companheiro {
    
@Id
    private Long id; // Este ID será o mesmo do Aventureiro

    @OneToOne
    @MapsId // Diz ao JPA que o ID desta entidade vem da relação abaixo
    @JoinColumn(name = "aventureiro_id")
    private Aventureiro aventureiro;

    @Column(nullable = false, length = 120)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EspecieCompanheiro especie;

    @Column(nullable = false)
    @Min(0)
    @Max(100)
    private Integer nivelLealdade;
}