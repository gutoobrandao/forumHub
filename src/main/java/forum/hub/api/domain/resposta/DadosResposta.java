package forum.hub.api.domain.resposta;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DadosResposta(
        Long id,
        String mensagem,
        Long topicoId,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
        LocalDateTime dataCriacao,
        String autor,
        Boolean solucao) {
    public DadosResposta(Resposta resposta) {
        this(resposta.getId(), resposta.getMensagem(), resposta.getTopico().getId(), resposta.getDataCriacao(), resposta.getAutor().getNome(), resposta.getSolucao());
    }
}
