package Infnet.Assessment.service;

import Infnet.Assessment.model.PainelTaticoMissao;
import Infnet.Assessment.repository.PainelTaticoMissaoRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PainelTaticoMissaoService {

    private final PainelTaticoMissaoRepository repository;

    @Cacheable(value = "topMissoesTaticas") // ESTRATÉGIA APLICADA: Cache em memória
    @Transactional(readOnly = true) // Importante para otimizar leitura e garantir que nada será salvo
    public List<PainelTaticoMissao> obterTopMissoesTaticas() {
        
        // Regra de Negócio: Calcular a data de corte (15 dias atrás a partir de agora)
        LocalDateTime dataCorte = LocalDateTime.now().minusDays(15);
        
        // O repositório já se encarrega de trazer apenas o Top 10 ordenado pelo índice
        return repository.findTop10ByUltimaAtualizacaoAfterOrderByIndiceProntidaoDesc(dataCorte);
    }

}