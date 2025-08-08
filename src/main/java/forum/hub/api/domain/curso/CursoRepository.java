package forum.hub.api.domain.curso;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    @Query("""
                SELECT c
                FROM Curso c
                WHERE c.id = :id AND c.ativo = true
            """)
    Curso findReferenceById(Long id);

    Page<Curso> findAllByAtivoTrue(Pageable paginacao);

    Curso findByNome(String nome);
}
