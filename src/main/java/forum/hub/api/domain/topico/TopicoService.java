package forum.hub.api.domain.topico;

import forum.hub.api.domain.curso.CursoRepository;
import forum.hub.api.domain.usuario.UsuarioLogadoService;
import forum.hub.api.domain.usuario.UsuarioRepository;
import forum.hub.api.infra.exception.ValidacaoException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TopicoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public DadosDetalhamentoTopico cadastrarTopico(DadosCadastroTopico dados) {

        if (topicoRepository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())) {
            throw new ValidacaoException("Já existe um tópico com este título e mensagem.");
        }

        Long idUsuarioLogado = usuarioLogadoService.getUsuarioLogado().getId();
        var usuario = usuarioRepository.findReferenceById(idUsuarioLogado);
        var curso = cursoRepository.findReferenceById(dados.cursoId());
        var data = LocalDateTime.now();

        var topico = new Topico(dados.titulo(), dados.mensagem(), curso, usuario, data);
        topicoRepository.save(topico);

        return new DadosDetalhamentoTopico(topico);
    }

    public DadosDetalhamentoTopico detalharTopico(Long id) {

        var topico = topicoRepository.findReferenceById(id);

        return new DadosDetalhamentoTopico(topico);
    }

    public Page<DadosDetalhamentoTopico> listarTopicos(Pageable paginacao) {
        return topicoRepository.findAllByStatusNot(Status.FECHADO, paginacao).map(DadosDetalhamentoTopico::new);
    }

    @Transactional
    public DadosDetalhamentoTopico atualizarTopico(Long id, DadosAtualizarTopico dados) {

        var topico = topicoRepository.findReferenceById(id);

        if (topicoRepository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())) {
            throw new ValidacaoException("Já existe um tópico com este título e mensagem.");
        }

        var usuario = usuarioLogadoService.validaUsuario(topico.getAutor().getId());
        var curso = cursoRepository.findReferenceById(dados.cursoId());
        var data = LocalDateTime.now();
        topico.atualizarTopico(dados.titulo(), dados.mensagem(), curso, usuario, dados.status(), data);

        return new DadosDetalhamentoTopico(topico);
    }

    @Transactional
    public void deletarTopico(Long id) {
        topicoRepository.deleteById(id);
    }
}
