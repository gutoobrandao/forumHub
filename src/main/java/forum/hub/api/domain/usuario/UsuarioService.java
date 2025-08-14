package forum.hub.api.domain.usuario;

import forum.hub.api.infra.exception.ValidacaoException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    @Transactional
    public DadosUsuario cadastrarUsuario(DadosCadastroUsuario dados) {
        if (repository.findByEmail(dados.email()) != null) {
            throw new ValidacaoException("E-mail já está em uso.");
        }

        String senhaCriptografada = passwordEncoder.encode(dados.senha());
        var usuario = new Usuario(dados.nome(), dados.email(), senhaCriptografada);
        repository.save(usuario);

        return new DadosUsuario(usuario);
    }

    public DadosUsuario detalharUsuario(Long id) {
        var usuario = repository.findReferenceById(id);

        return new DadosUsuario(usuario);
    }

    public Page<DadosUsuario> listarUsuarios(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosUsuario::new);

        return page;
    }

    @Transactional
    public DadosUsuario atualizarUsuario(Long id, DadosAtualizarUsuario dados) {
        var usuario = usuarioLogadoService.validaUsuario(id);

        String senhaCriptografada = null;

        if (dados.senha() != null) {
            senhaCriptografada = passwordEncoder.encode(dados.senha());
        }

        usuario.atualizarDados(dados.nome(), dados.email(), senhaCriptografada);

        return new DadosUsuario(usuario);
    }

    @Transactional
    public void deletarUsuario(Long id) {
        var usuario = usuarioLogadoService.validaUsuario(id);

        if (usuario.getAtivo()) {
            usuario.deletar();
        } else {
            throw new ValidacaoException("Usuário já está inativo.");
        }
    }
}
