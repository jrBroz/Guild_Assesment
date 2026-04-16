package Infnet.Assessment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Infnet.Assessment.dto.ParticipacaoDTO.ParticipacaoRequestDTO;
import Infnet.Assessment.enums.StatusMissao;
import Infnet.Assessment.exceptions.BusinessException;
import Infnet.Assessment.model.Aventureiro;
import Infnet.Assessment.model.Missao;
import Infnet.Assessment.model.ParticipacaoMissao;
import Infnet.Assessment.repository.AventureiroRepository;
import Infnet.Assessment.repository.MissaoRepository;
import Infnet.Assessment.repository.ParticipacaoMissaoRepository;
import jakarta.transaction.Transactional;

@Service
public class ParticipacaoService {

     private MissaoRepository missaoRepo;
    private AventureiroRepository aventureiroRepo;
    private ParticipacaoMissaoRepository participacaoRepo;

    @Transactional
    public void registrarParticipacao(ParticipacaoRequestDTO dto) {
        Missao missao = missaoRepo.findById(dto.missaoId())
            .orElseThrow(() -> new BusinessException("Missão não encontrada."));
            
        Aventureiro aventureiro = aventureiroRepo.findById(dto.aventureiroId())
            .orElseThrow(() -> new BusinessException("Aventureiro não encontrado."));

        if (!aventureiro.getAtivo()) {
            throw new BusinessException("Aventureiro inativo não pode participar de missões.");
        }

        if (missao.getStatus() == StatusMissao.CONCLUIDA || missao.getStatus() == StatusMissao.CANCELADA) {
            throw new BusinessException("Missão já encerrada não aceita novos participantes.");
        }

        if (!aventureiro.getOrganizacao().getId().equals(missao.getOrganizacao().getId())) {
            throw new BusinessException("Aventureiro não pertence à organização desta missão.");
        }

        // --- PARTE NOVA: Salvando no banco ---
        ParticipacaoMissao participacao = new ParticipacaoMissao();
        participacao.setMissao(missao);
        participacao.setAventureiro(aventureiro);
        participacao.setPapel(dto.papel());
        participacao.setRecompensaOuro(dto.recompensaOuro());
        participacao.setMvp(dto.mvp());

        participacaoRepo.save(participacao);
    }
}