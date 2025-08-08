package forum.hub.api.domain.usuario;

import forum.hub.api.infra.exception.ValidacaoException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioLogadoService {

    public Usuario getUsuarioLogado() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof Usuario usuario)) {
            throw new ValidacaoException("Usuário não autenticado.");
        }

        return usuario;
    }
}
