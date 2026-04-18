package Infnet.Assessment.repository;

import Infnet.Assessment.dto.RankingDTO.RankingDTO; 
import Infnet.Assessment.model.Aventureiro;

import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AventureiroRepository extends JpaRepository<Aventureiro, Long> {

    Page<Aventureiro> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    // Listagem com múltiplos filtros (Status, Classe, Nível Mínimo)
    Page<Aventureiro> findByAtivoAndClasseAndNivelGreaterThanEqual(
        Boolean ativo, String classe, Integer nivelMinimo, Pageable pageable);

    @Query("""
        SELECT new Infnet.Assessment.dto.RankingDTO.RankingDTO(
            p.aventureiro.nome,
            COUNT(p.id),
            SUM(p.recompensa),
            SUM(CASE WHEN p.destaque = true THEN 1L ELSE 0L END)
        )
        FROM ParticipacaoEmMissao p
        WHERE p.missao.createdAt BETWEEN :inicio AND :fim
        GROUP BY p.aventureiro.id, p.aventureiro.nome
        ORDER BY SUM(p.recompensa) DESC
    """)
    Page<RankingDTO> obterRanking(
        @Param("inicio") LocalDateTime inicio, 
        @Param("fim") LocalDateTime fim, 
        Pageable pageable
    );
}