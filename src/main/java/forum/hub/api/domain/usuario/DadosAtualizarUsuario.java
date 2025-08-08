package forum.hub.api.domain.usuario;

import jakarta.validation.constraints.Email;

public record DadosAtualizarUsuario(
        String nome,
        @Email
        String email,
        String senha) {
}
