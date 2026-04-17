package Infnet.Assessment.repository;
import Infnet.Assessment.enums.NivelPerigoMissao;
import Infnet.Assessment.enums.StatusMissao;
import Infnet.Assessment.model.Missao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
public interface MissaoRepository extends JpaRepository<Missao, Long> {

    // O Spring lê esse nome e monta o filtro + paginação no banco de dados
    Page<Missao> findByStatusAndNivelPerigoMissaoAndDataCriacaoBetween(
            StatusMissao status, 
            NivelPerigoMissao nivel, 
            LocalDateTime inicio, 
            LocalDateTime fim, 
            Pageable pageable
    );
}