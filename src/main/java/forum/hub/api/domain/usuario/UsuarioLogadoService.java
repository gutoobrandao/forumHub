package forum.hub.api.domain.usuario;

import forum.hub.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioLogadoService {

    @Autowired
    UsuarioRepository repository;

    public Usuario getUsuarioLogado() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof Usuario usuario)) {
            throw new ValidacaoException("Usuário não autenticado.");
        }

        return usuario;
    }

    public Usuario validaUsuario(Long id) {
        Usuario usuarioLogado = getUsuarioLogado();

        if (!usuarioLogado.getId().equals(id)) {
            throw new ValidacaoException("Você só pode atualizar seus próprios dados.");
        }

        return usuarioLogado;
    }
}
