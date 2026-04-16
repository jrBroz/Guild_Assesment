package Infnet.Assessment.service;
import org.springframework.stereotype.Service;
import Infnet.Assessment.dto.AventureiroDTO.AventureiroRequestDTO;
import Infnet.Assessment.model.Aventureiro;
import Infnet.Assessment.repository.AventureiroRepository;

@Service
public class AventureiroService {
 
 
    private AventureiroRepository aventureiroRepository;

    public Aventureiro criarAventureiro(AventureiroRequestDTO dto) {}

    public Aventureiro deletarAventureiro(AventureiroRequestDTO dto) {}    

    public Aventureiro alterarAventureiro(AventureiroRequestDTO dto) {}

    public Aventureiro retornarAventureiro(AventureiroRequestDTO dto) {}


    public void encerrarVinculo(Long id) {} // Encerrar vinculo do aventureiro com guilda

    public void recrutarNovamente(Long id) {}












}