package forum.hub.api.domain.topico;

import forum.hub.api.domain.curso.Curso;
import forum.hub.api.domain.resposta.Resposta;
import forum.hub.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;
    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resposta> respostas = new ArrayList<>();

    public Topico(String titulo, String mensagem, Curso curso, Usuario autor, LocalDateTime dataCriacao) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.curso = curso;
        this.autor = autor;
        this.status = Status.NAO_SOLUCIONADO;
        this.dataCriacao = dataCriacao;
    }

    public void atualizarTopico(String titulo, String mensagem, Curso curso, Usuario autor, Status status, LocalDateTime dataCriacao) {
        if (titulo != null && !titulo.isBlank()) {
            this.titulo = titulo;
        }

        if (mensagem != null && !mensagem.isBlank()) {
            this.mensagem = mensagem;
        }

        if (curso != null) {
            this.curso = curso;
        }

        if (autor != null) {
            this.autor = autor;
        }

        if (status != null) {
            this.status = status;
        }

        if (dataCriacao != null) {
            this.dataCriacao = dataCriacao;
        }
    }
}
