package forum.hub.api.domain.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

    @Query("""
            SELECT u.ativo
            FROM Usuario u
            WHERE
                u.id = :id
            """)
    Usuario findReferenceById(Long id);

    Page<Usuario> findAllByAtivoTrue(Pageable paginacao);
}
