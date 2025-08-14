package forum.hub.api.controller;

import forum.hub.api.domain.usuario.DadosAtualizarUsuario;
import forum.hub.api.domain.usuario.DadosCadastroUsuario;
import forum.hub.api.domain.usuario.DadosUsuario;
import forum.hub.api.domain.usuario.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<DadosUsuario> cadastrarUsuario(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {
        var dadosUsuario = usuarioService.cadastrarUsuario(dados);
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(dadosUsuario.id()).toUri();

        return ResponseEntity.created(uri).body(dadosUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosUsuario> detalharUsuario(@PathVariable Long id) {
        var dadosUsuario = usuarioService.detalharUsuario(id);

        return ResponseEntity.ok(dadosUsuario);
    }

    @GetMapping
    public ResponseEntity<Page<DadosUsuario>> listarUsuarios(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = usuarioService.listarUsuarios(paginacao);

        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosUsuario> atualizarUsuario(@PathVariable Long id, @RequestBody @Valid DadosAtualizarUsuario dados) {
        var usuario = usuarioService.atualizarUsuario(id, dados);

        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);

        return ResponseEntity.noContent().build();
    }
}