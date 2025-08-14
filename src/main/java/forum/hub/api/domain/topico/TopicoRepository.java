package forum.hub.api.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    @Query("""
                SELECT t
                FROM Topico t
                WHERE t.id = :id AND t.status != 'FECHADO'
            """)
    Topico findReferenceById(Long id);

    Boolean existsByTituloAndMensagem(String titulo, String mensagem);

    Page<Topico> findAllByStatusNot(Status status, Pageable pageable);
}
