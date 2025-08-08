package forum.hub.api.controller;

import forum.hub.api.domain.curso.CursoService;
import forum.hub.api.domain.curso.DadosAtualizarCurso;
import forum.hub.api.domain.curso.DadosCadastroCurso;
import forum.hub.api.domain.curso.DadosCurso;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<DadosCurso> cadastrarCurso(@RequestBody @Valid DadosCadastroCurso dados, UriComponentsBuilder uriBuilder) {
        var curso = cursoService.cadastrarCurso(dados);
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(curso.id()).toUri();

        return ResponseEntity.created(uri).body(curso);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosCurso> detalharCurso(@PathVariable Long id) {
        var dadosCurso = cursoService.detalharCurso(id);

        return ResponseEntity.ok(dadosCurso);
    }

    @GetMapping
    public ResponseEntity<Page<DadosCurso>> listarCursos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = cursoService.listarCursos(paginacao);

        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarCurso(@PathVariable Long id, @RequestBody @Valid DadosAtualizarCurso dados) {
        var curso = cursoService.atualizarCurso(id, dados);

        return ResponseEntity.ok().body("Curso atualizado com sucesso.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCurso(@PathVariable Long id) {
        cursoService.deletarCurso(id);

        return ResponseEntity.noContent().build();
    }
}
