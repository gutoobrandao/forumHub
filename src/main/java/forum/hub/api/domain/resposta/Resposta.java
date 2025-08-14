package forum.hub.api.domain.resposta;

import forum.hub.api.domain.topico.Topico;
import forum.hub.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Resposta")
@Table(name = "respostas")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensagem;
    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;
    private LocalDateTime dataCriacao;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    private Boolean solucao;

    public Resposta(String mensagem, Topico topico, LocalDateTime dataCriacao, Usuario autor) {
        this.mensagem = mensagem;

        if (topico != null) {
            this.topico = topico;
        }

        this.dataCriacao = dataCriacao;
        this.autor = autor;
        this.solucao = false;
    }

    public void atualizarResposta(String mensagem, Topico topico, LocalDateTime data) {
        if (mensagem != null && !mensagem.isBlank()) {
            this.mensagem = mensagem;
        }

        if (topico != null) {
            this.topico = topico;
        }

        if (data != null) {
            this.dataCriacao =data;
        }
    }
}
