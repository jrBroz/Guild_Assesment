package Infnet.Assessment.service;

import Infnet.Assessment.dto.MissaoDTO.MissaoRequestDTO;
import Infnet.Assessment.enums.NivelPerigoMissao;
import Infnet.Assessment.enums.StatusMissao;
import Infnet.Assessment.exceptions.BusinessException;
import Infnet.Assessment.model.Missao;
import Infnet.Assessment.model.Organizacao;
import Infnet.Assessment.repository.MissaoRepository;
import Infnet.Assessment.repository.OrganizacaoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class MissaoService {


    private MissaoRepository missaoRepo;


    private OrganizacaoRepository orgRepo;

    @Transactional
    public Missao criar(MissaoRequestDTO dto) {

        Organizacao org = orgRepo.findById(dto.organizacaoId())
                .orElseThrow(() -> new BusinessException("Organização não encontrada para esta missão."));

        Missao nova = new Missao();
        nova.setTitulo(dto.titulo());
        nova.setNivelPerigoMissao(dto.nivelPerigo());
        nova.setOrganizacao(org);
        nova.setStatus(StatusMissao.PLANEJADA); // Toda missão começa como planejada

        return missaoRepo.save(nova);
    }

    public Missao buscarPorId(Long id) {
        return missaoRepo.findById(id)
                .orElseThrow(() -> new BusinessException("Missão não encontrada."));
    }

    // Requisito: Listagem com filtros de Status, Perigo e Datas
    public Page<Missao> listarComFiltros(
            StatusMissao status,
            NivelPerigoMissao nivel,
            LocalDateTime inicio,
            LocalDateTime fim,
            Pageable pageable) {
        
        return missaoRepo.findByStatusAndNivelPerigoMissaoAndDataCriacaoBetween(
                status, nivel, inicio, fim, pageable);
    }
}
