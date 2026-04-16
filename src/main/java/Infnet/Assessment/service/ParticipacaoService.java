package Infnet.Assessment.service;

import org.springframework.stereotype.Service;

import Infnet.Assessment.dto.ParticipacaoDTO.ParticipacaoRequestDTO;
import Infnet.Assessment.enums.StatusMissao;
import Infnet.Assessment.exceptions.BusinessException;
import Infnet.Assessment.model.Aventureiro;
import Infnet.Assessment.model.Missao;

@Service
public class ParticipacaoService {

    public void registrarParticipacao(ParticipacaoRequestDTO dto) {
        Missao missao = missaoRepo.findById(dto.missaoId()).orElseThrow();
        Aventureiro aventureiro = aventureiroRepo.findById(dto.aventureiroId()).orElseThrow();

        // 1. Regra: Aventureiro inativo não pode participar
        if (!aventureiro.getAtivo()) {
            throw new BusinessException("Aventureiro inativo não pode participar de missões.");
        }

        // 2. Regra: Missão deve estar em estado compatível (Planejada ou Em Andamento)
        if (missao.getStatus() == StatusMissao.CONCLUIDA || missao.getStatus() == StatusMissao.CANCELADA) {
            throw new BusinessException("Missão já encerrada não aceita novos participantes.");
        }

        // 3. Regra: Restrição Organizacional (Aventura e Missão devem ser da mesma Org)
        if (!aventureiro.getOrganizacao().getId().equals(missao.getOrganizacao().getId())) {
            throw new BusinessException("Aventureiro não pertence à organização desta missão.");
        }

        // Se passar em tudo, salva...
    }
}