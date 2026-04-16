package Infnet.Assessment.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Infnet.Assessment.dto.AventureiroDTO.AventureiroRequestDTO;
import Infnet.Assessment.dto.AventureiroDTO.AventureiroUpdateDTO;
import Infnet.Assessment.exceptions.BusinessException;
import Infnet.Assessment.model.Aventureiro;
import Infnet.Assessment.model.Organizacao;
import Infnet.Assessment.model.Usuario;
import Infnet.Assessment.repository.AventureiroRepository;
import Infnet.Assessment.repository.OrganizacaoRepository;
import Infnet.Assessment.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class AventureiroService {
 
    @Autowired private AventureiroRepository aventureiroRepo;
    @Autowired private OrganizacaoRepository orgRepo;
    @Autowired private UsuarioRepository usuarioRepo;

    @Transactional
    public Aventureiro criarAventureiro(AventureiroRequestDTO dto) {
        Organizacao org = orgRepo.findById(dto.organizacaoId())
            .orElseThrow(() -> new BusinessException("Organização não encontrada."));
            
        Usuario user = usuarioRepo.findById(dto.usuarioId())
            .orElseThrow(() -> new BusinessException("Usuário não encontrado."));

        Aventureiro novo = new Aventureiro();
        novo.setNome(dto.nomeAventureiro());
        novo.setClasse(dto.classeAventureiro());
        novo.setNivel(dto.nivelAventureiro());
        novo.setOrganizacao(org);
        novo.setUsuarioResponsavel(user);
        
        return aventureiroRepo.save(novo);
    }

    public Aventureiro retornarAventureiro(Long id) {
        return aventureiroRepo.findById(id)
            .orElseThrow(() -> new BusinessException("Aventureiro não encontrado."));
    }

    @Transactional
    public Aventureiro alterarAventureiro(Long id, AventureiroUpdateDTO dto) {
        Aventureiro existente = retornarAventureiro(id);
        
        existente.setNome(dto.nome());
        existente.setClasse(dto.classe());
        existente.setNivel(dto.nivel());
        
        return aventureiroRepo.save(existente);
    }

    @Transactional
    public void deletarAventureiro(Long id) {
        Aventureiro existente = retornarAventureiro(id);
        aventureiroRepo.delete(existente);
    }    

    @Transactional
    public void encerrarVinculo(Long id) {
        Aventureiro existente = retornarAventureiro(id);
        existente.setAtivo(false); // Inativa o aventureiro
        aventureiroRepo.save(existente);
    } 

    @Transactional
    public void recrutarNovamente(Long id) {
        Aventureiro existente = retornarAventureiro(id);
        existente.setAtivo(true); // Reativa o aventureiro
        aventureiroRepo.save(existente);
    }
}