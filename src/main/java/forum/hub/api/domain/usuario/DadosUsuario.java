package forum.hub.api.domain.usuario;

public record DadosUsuario(Long id, String nome, String email) {

    public DadosUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }

}
