package forum.hub.api.domain.resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarResposta(
        @NotBlank
        String mensagem,
        @NotNull
        Long topicoId) {
}
