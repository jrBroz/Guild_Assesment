package Infnet.Assessment.service;

import Infnet.Assessment.dto.AventureiroDTO.*;
import Infnet.Assessment.dto.RankingDTO.RankingDTO;
import Infnet.Assessment.dto.CompanheiroDTO.CompanheiroCriacaoDTO;
import Infnet.Assessment.exceptions.BusinessException;
import Infnet.Assessment.exceptions.EntidadeNaoLocalizadaException;
import Infnet.Assessment.model.*;
import Infnet.Assessment.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AventureiroService {

    private final AventureiroRepository aventureiroRepo;
    private final CompanheiroRepository companheiroRepo;
    private final OrganizacaoRepository orgRepo;
    private final UsuarioRepository usuarioRepo;
    private final ParticipacaoEmMissaoRepository participacaoRepo;

    // --- CONSULTAS ---

    @Transactional(readOnly = true)
    public Page<AventureiroResumido> listarComFiltros(Boolean ativo, String classe, Integer nivelMin, Pageable pageable) {
        return aventureiroRepo.findByAtivoAndClasseAndNivelGreaterThanEqual(ativo, classe, nivelMin, pageable)
                .map(a -> new AventureiroResumido(a.getId(), a.getNome(), a.getClasse(), a.getNivel(), a.getAtivo()));
    }

    @Transactional(readOnly = true)
    public Page<AventureiroResumido> buscarPorNome(String nome, Pageable pageable) {
        return aventureiroRepo.findByNomeContainingIgnoreCase(nome, pageable)
                .map(a -> new AventureiroResumido(a.getId(), a.getNome(), a.getClasse(), a.getNivel(), a.getAtivo()));
    }

    @Transactional(readOnly = true)
    public AventureiroResponseDTO obterPerfilCompleto(Long id) {
        Aventureiro aventureiro = aventureiroRepo.findById(id)
                .orElseThrow(() -> new EntidadeNaoLocalizadaException("Aventureiro não encontrado"));

        // Busca a participação mais recente para extrair a última missão
        Missao ultimaMissao = participacaoRepo
                .findFirstByAventureiro_IdOrderByMissao_CreatedAtDesc(id)
                .map(ParticipacaoEmMissao::getMissao)
                .orElse(null);

        Long totalParticipacoes = participacaoRepo.countByAventureiro_Id(id);
        
        // No seu CompanheiroRepository, o método deve ser findByAventureiroId
        Companheiro companheiro = companheiroRepo.findByAventureiroId(id).orElse(null);

        return new AventureiroResponseDTO(
                aventureiro.getId(),
                aventureiro.getNome(),
                aventureiro.getClasse(),
                aventureiro.getNivel(),
                aventureiro.getAtivo(),
                aventureiro.getCreatedAt(),
                totalParticipacoes,
                ultimaMissao,
                companheiro
        );
    }

    // --- CRUD E STATUS ---

    @Transactional
    public AventureiroResponseDTO criar(AventureiroRequestDTO dto) {
        Organizacao org = orgRepo.findById(dto.organizacaoId())
                .orElseThrow(() -> new BusinessException("Organização não encontrada"));
        Usuario user = usuarioRepo.findById(dto.usuarioId())
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        Aventureiro novo = new Aventureiro();
        novo.setOrganizacao(org);
        novo.setUsuarioResponsavel(user);
        novo.setNome(dto.nomeAventureiro());
        novo.setClasse(dto.classeAventureiro());
        novo.setNivel(dto.nivelAventureiro());
        novo.setAtivo(true);

        Aventureiro salvo = aventureiroRepo.save(novo);
        return obterPerfilCompleto(salvo.getId());
    }

    @Transactional
    public void encerrarVinculo(Long id) {
        Aventureiro aventureiro = aventureiroRepo.findById(id)
                .orElseThrow(() -> new EntidadeNaoLocalizadaException("Aventureiro não encontrado"));
        aventureiro.setAtivo(false);
        aventureiroRepo.save(aventureiro);
    }

    // --- REGRAS DE NEGÓCIO E RELATÓRIOS ---

    @Transactional
    public AventureiroResponseDTO atribuirCompanheiro(Long idAventureiro, CompanheiroCriacaoDTO dto) {
        Aventureiro aventureiro = aventureiroRepo.findById(idAventureiro)
                .orElseThrow(() -> new EntidadeNaoLocalizadaException("Aventureiro não encontrado"));

        // Remove o antigo se existir
        companheiroRepo.findByAventureiroId(idAventureiro).ifPresent(c -> {
            companheiroRepo.delete(c);
            companheiroRepo.flush();
        });

        Companheiro novoCompanheiro = new Companheiro();
        // Usando os setters exatos do seu model Companheiro:
        novoCompanheiro.setAventureiro(aventureiro); 
        novoCompanheiro.setNome(dto.nome().trim());
        novoCompanheiro.setEspecie(dto.especie()); 
        novoCompanheiro.setNivelLealdade(dto.lealdade()); 

        companheiroRepo.save(novoCompanheiro);
        return obterPerfilCompleto(idAventureiro);
    }

    @Transactional(readOnly = true)
    public Page<RankingDTO> gerarRanking(LocalDateTime inicio, LocalDateTime fim, Pageable pageable) {
        LocalDateTime dataDe = (inicio != null) ? inicio : LocalDateTime.now().minusYears(10);
        LocalDateTime dataAte = (fim != null) ? fim : LocalDateTime.now();
        return aventureiroRepo.obterRanking(dataDe, dataAte, pageable);
    }
}