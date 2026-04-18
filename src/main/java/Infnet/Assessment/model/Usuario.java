package Infnet.Assessment.model;
import java.util.List;
import org.springframework.data.annotation.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario", schema = "audit") 
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome", nullable = false, length = 120)
    private String nome;
    
    @Column(name = "email", nullable = false, length = 180)
    private String email;

   @ManyToOne(fetch = FetchType.LAZY, optional = false)
   @JoinColumn(name = "organizacao_id", nullable = false, foreignKey = @ForeignKey(name = "fk_usuarios_org"))
    private Organizacao organizacao;

    
    @ManyToMany
    @JoinTable(
        name = "usuario_papel", 
        schema = "audit", 
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "papel_id")
    )
    private List<Role> papeis;
}