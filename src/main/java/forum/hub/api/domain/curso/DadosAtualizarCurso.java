package forum.hub.api.domain.curso;

public record DadosAtualizarCurso(
        String nome,
        Categoria categoria
) {
    public DadosAtualizarCurso(Curso curso) {
        this(curso.getNome(), curso.getCategoria());
    }
}
