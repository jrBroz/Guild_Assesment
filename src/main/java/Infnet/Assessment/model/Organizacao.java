package Infnet.Assessment.model;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import java.util.List;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "organizacoes")
public class Organizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // O nome deve ser único: não podem existir duas "Guildas dos Lobos"
    @Column(nullable = false, unique = true, length = 100)
    private String nome;

    // OPCIONAL: Descrição da organização (ex: Guilda de Magos, Exército Real)
    private String descricao;

    // Relacionamento Inverso (Opcional, mas ajuda muito nas buscas)
    // Uma organização tem muitos aventureiros
    @OneToMany(mappedBy = "organizacao")
    private List<Aventureiro> aventureiros;

    // Uma organização tem muitas missões
    @OneToMany(mappedBy = "organizacao")
    private List<Missao> missoes;
}
