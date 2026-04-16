package Infnet.Assessment.repository;

import Infnet.Assessment.model.ParticipacaoMissao;
import Infnet.Assessment.enums.StatusMissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ParticipacaoMissaoRepository extends JpaRepository<ParticipacaoMissao, Long> {

    // 1. Busca a última missão de um aventureiro específico (para o perfil completo)
    @Query("SELECT p FROM ParticipacaoMissao p WHERE p.aventureiro.id = :id ORDER BY p.dataRegistro DESC LIMIT 1")
    ParticipacaoMissao findUltimaParticipacao(@Param("id") Long id);

    // 2. Conta quantas participações um aventureiro tem
    Long countByAventureiroId(Long id);

    // 3. RELATÓRIO: Ranking de Aventureiros (Com base em participações, ouro e destaques)
    // Usamos Object[] para capturar os resultados agregados (Soma e Contagem)
    @Query("""
        SELECT p.aventureiro.nome, 
               COUNT(p.id), 
               SUM(p.recompensaOuro), 
               COUNT(CASE WHEN p.mvp = true THEN 1 END)
        FROM ParticipacaoMissao p
        WHERE p.dataRegistro BETWEEN :inicio AND :fim
        AND (:status IS NULL OR p.missao.status = :status)
        GROUP BY p.aventureiro.id, p.aventureiro.nome
        ORDER BY COUNT(p.id) DESC
    """)
    List<Object[]> buscarRanking(@Param("inicio") LocalDateTime inicio, 
                                 @Param("fim") LocalDateTime fim, 
                                 @Param("status") StatusMissao status);

    // 4. RELATÓRIO: Métricas de Missões (Título, participantes e total distribuído)
    @Query("""
        SELECT p.missao.titulo, 
               p.missao.status, 
               p.missao.nivelPerigoMissao, 
               COUNT(p.aventureiro.id), 
               SUM(p.recompensaOuro)
        FROM ParticipacaoMissao p
        WHERE p.missao.dataCriacao BETWEEN :inicio AND :fim
        GROUP BY p.missao.id, p.missao.titulo, p.missao.status, p.missao.nivelPerigoMissao
    """)
    List<Object[]> buscarMetricasMissoes(@Param("inicio") LocalDateTime inicio, 
                                         @Param("fim") LocalDateTime fim);
}