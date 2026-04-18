package Infnet.Assessment.repository;
import Infnet.Assessment.dto.RankingDTO.RankingDTO;
import Infnet.Assessment.model.Aventureiro;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface AventureiroRepository extends JpaRepository<Aventureiro, Long> {

Page<Aventureiro> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

// Listagem com múltiplos filtros (Status, Classe, Nível Mínimo)
Page<Aventureiro> findByAtivoAndClasseAndNivelGreaterThanEqual(
    Boolean ativo, String classe, Integer nivelMinimo, Pageable pageable);

@Query("""
        SELECT new Infnet.Assessment.dto.RelatorioDTO.RankingDTO(
            p.aventureiro.nome,
            COUNT(p.id),
            SUM(p.recompensa),
            SUM(CASE WHEN p.destaque = true THEN 1 ELSE 0 END)
        )
        FROM ParticipacaoMissao p
        WHERE p.missao.dataCriacao BETWEEN :inicio AND :fim
        GROUP BY p.aventureiro.id, p.aventureiro.nome
        ORDER BY SUM(p.recompensa) DESC
    """)
    List<RankingDTO> obterRanking(LocalDateTime inicio, LocalDateTime fim);
 }

