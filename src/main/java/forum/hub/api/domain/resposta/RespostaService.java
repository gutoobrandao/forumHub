package forum.hub.api.domain.resposta;

import forum.hub.api.domain.topico.Topico;
import forum.hub.api.domain.topico.TopicoRepository;
import forum.hub.api.domain.usuario.UsuarioLogadoService;
import forum.hub.api.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RespostaService {

    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespostaRepository respostaRepository;

    @Transactional
    public DadosResposta cadastrarResposta(DadosCadastroResposta dados) {

        Long idUsuarioLogado = usuarioLogadoService.getUsuarioLogado().getId();
        var usuario = usuarioRepository.findReferenceById(idUsuarioLogado);
        var topico = topicoRepository.findReferenceById(dados.topicoId());
        var data = LocalDateTime.now();

        var resposta = new Resposta(dados.mensagem(), topico, data, usuario);
        respostaRepository.save(resposta);

        return new DadosResposta(resposta);
    }

    public DadosResposta detalharResposta(Long id) {
        var resposta = respostaRepository.findReferebceById(id);

        return new DadosResposta(resposta);
    }

    public Page<DadosResposta> listarResposta(Pageable paginacao) {
        return respostaRepository.findAll(paginacao).map(DadosResposta::new);
    }

    @Transactional
    public DadosResposta atualizarResposta(Long id, DadosAtualizarResposta dados) {

        var resposta = respostaRepository.findReferebceById(id);
        var autorId = resposta.getAutor().getId();

        usuarioLogadoService.validaUsuario(autorId);

        var topico = resposta.getTopico();
        var data = LocalDateTime.now();

        resposta.atualizarResposta(dados.mensagem(), topico, data);


        return new DadosResposta(resposta);
    }

    @Transactional
    public void deletarResposta(Long id) {
        respostaRepository.deleteById(id);
    }
}
