package forum.hub.api.controller;

import forum.hub.api.domain.topico.DadosAtualizarTopico;
import forum.hub.api.domain.topico.DadosCadastroTopico;
import forum.hub.api.domain.topico.DadosDetalhamentoTopico;
import forum.hub.api.domain.topico.TopicoService;
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
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    TopicoService topicoService;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoTopico> cadastrarTopico(@RequestBody @Valid DadosCadastroTopico dadosCadastroTopico, UriComponentsBuilder uriBuilder) {
        var topico = topicoService.cadastrarTopico(dadosCadastroTopico);
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.id()).toUri();

        return ResponseEntity.created(uri).body(topico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTopico> detalharTopico(@PathVariable Long id) {
        var topico = topicoService.detalharTopico(id);

        return ResponseEntity.ok(topico);
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoTopico>> listarTopicos(@PageableDefault(size = 10, sort = "dataCriacao", direction = Sort.Direction.ASC) Pageable paginacao) {
        var page = topicoService.listarTopicos(paginacao);

        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTopico> atualizarTopico(@PathVariable Long id, @RequestBody DadosAtualizarTopico dados) {
        var topico = topicoService.atualizarTopico(id, dados);

        return ResponseEntity.ok(topico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTopico(@PathVariable Long id) {
        topicoService.deletarTopico(id);

        return ResponseEntity.noContent().build();
    }
}
