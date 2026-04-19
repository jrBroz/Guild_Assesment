package Infnet.Assessment.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import Infnet.Assessment.dto.RankingDTO.RankingDTO;

public class RankingRepositoryTest {

    
    private AventureiroRepository aventureiroRepository;

    @Test
    void validarSqlRanking() {
        // Isso vai disparar a query complexa com JOIN, GROUP BY e SUM
        Page<RankingDTO> ranking = aventureiroRepository.obterRanking(
            LocalDateTime.now().minusYears(1), 
            LocalDateTime.now(), 
            PageRequest.of(0, 5)
        );
        assertNotNull(ranking);
    }
}