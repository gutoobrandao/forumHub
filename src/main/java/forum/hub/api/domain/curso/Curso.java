package forum.hub.api.domain.curso;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Curso")
@Table(name = "cursos")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private Boolean ativo;

    public Curso(DadosCadastroCurso dados) {
        this.nome = dados.nome();
        this.categoria = dados.categoria();
        this.ativo = true;
    }

    public void atualizarCurso(String nome, Categoria categoria) {
        if (nome != null && !nome.isBlank()) {
            this.nome = nome;
        }
        if (categoria != null) {
            this.categoria = categoria;
        }
    }

    public void deletar() {
        this.ativo = false;
    }
}
