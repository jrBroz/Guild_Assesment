package Infnet.Assessment.service;

import Infnet.Assessment.model.Usuario;
import Infnet.Assessment.model.Organizacao;
import Infnet.Assessment.repository.UsuarioRepository;
import Infnet.Assessment.repository.OrganizacaoRepository;
import Infnet.Assessment.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired 
    private UsuarioRepository usuarioRepo;

    @Autowired 
    private OrganizacaoRepository orgRepo;

    // REQUISITO: Listar usuários com suas roles
    public List<Usuario> listarTodos() {
        // Como o relacionamento ManyToMany está mapeado, 
        // o JPA já traz as roles automaticamente se configurado corretamente.
        return usuarioRepo.findAll();
    }

    // REQUISITO: Persistir um novo usuário associado a uma organização existente
    @Transactional
    public Usuario salvarUsuario(Usuario usuario, Long organizacaoId) {
        // 1. Validar se a organização do banco legado existe
        Organizacao org = orgRepo.findById(organizacaoId)
            .orElseThrow(() -> new BusinessException("A organização informada não existe no banco."));

        // 2. Vincular o usuário à organização
        usuario.setOrganizacao(org);

        // 3. Salvar no schema audit
        return usuarioRepo.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepo.findById(id)
            .orElseThrow(() -> new BusinessException("Usuário não encontrado."));
    }
}