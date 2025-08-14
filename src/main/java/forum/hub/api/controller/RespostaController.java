package forum.hub.api.controller;

import forum.hub.api.domain.resposta.DadosAtualizarResposta;
import forum.hub.api.domain.resposta.DadosCadastroResposta;
import forum.hub.api.domain.resposta.DadosResposta;
import forum.hub.api.domain.resposta.RespostaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

    @Autowired
    private RespostaService respostaService;

    @PostMapping
    public ResponseEntity<DadosResposta> cadastrarResposta(@RequestBody @Valid DadosCadastroResposta dados, UriComponentsBuilder uriBuilder) {
        var resposta = respostaService.cadastrarResposta(dados);
        var uri = uriBuilder.path("/resposta/{id}").buildAndExpand(resposta.id()).toUri();

        return ResponseEntity.created(uri).body(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosResposta> detalharResposta(@PathVariable Long id) {
        var resposta = respostaService.detalharResposta(id);

        return ResponseEntity.ok(resposta);
    }

    @GetMapping
    public ResponseEntity<Page<DadosResposta>> listarResposta(@PageableDefault(size = 10, sort = "topicoId", direction = Sort.Direction.ASC) Pageable paginacao) {
        var page = respostaService.listarResposta(paginacao);

        return ResponseEntity.ok(page);
    }


    @PutMapping("/{id}")
    public ResponseEntity<DadosResposta> atualizarResposta(@PathVariable Long id, @RequestBody @Valid DadosAtualizarResposta dados) {
        var resposta = respostaService.atualizarResposta(id, dados);

        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarResposta(@PathVariable Long id) {
        respostaService.deletarResposta(id);

        return ResponseEntity.noContent().build();
    }
}
