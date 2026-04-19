package Infnet.Assessment.controller;
import Infnet.Assessment.model.PainelTaticoMissao;
import Infnet.Assessment.service.PainelTaticoMissaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/missoes") 
@RequiredArgsConstructor
public class PainelTaticoMissaoController {

    private final PainelTaticoMissaoService service;

    // Respeitando o exemplo de rota do gloria: GET /missoes/top15dias
    @GetMapping("/top15dias")
    public ResponseEntity<List<PainelTaticoMissao>> listarTopMissoesTaticas() {
        List<PainelTaticoMissao> topMissoes = service.obterTopMissoesTaticas();
        return ResponseEntity.ok(topMissoes);
    }
}