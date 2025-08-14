package forum.hub.api.domain.resposta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
    Resposta findReferebceById(Long id);

    Page<Resposta> findAll(Pageable pageable);
}
