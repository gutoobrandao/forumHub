package forum.hub.api.domain.curso;

import forum.hub.api.infra.exception.ValidacaoException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;

    @Transactional
    public DadosCurso cadastrarCurso(@RequestBody @Valid DadosCadastroCurso dados) {
        if (repository.findByNome(dados.nome()) != null) {
            throw new ValidacaoException("O curso já existe!.");
        }
        var curso = new Curso(dados);
        repository.save(curso);

        return new DadosCurso(curso);
    }

    public DadosCurso detalharCurso(Long id) {
        var curso = repository.findReferenceById(id);

        return new DadosCurso(curso);
    }

    public Page<DadosCurso> listarCursos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosCurso::new);
    }

    @Transactional
    public DadosAtualizarCurso atualizarCurso(Long id, DadosAtualizarCurso dados) {
        var curso = repository.findReferenceById(id);
        curso.atualizarCurso(dados.nome(), dados.categoria());
        repository.save(curso);

        return new DadosAtualizarCurso(curso);
    }

    @Transactional
    public void deletarCurso(Long id) {
        var curso = repository.findReferenceById(id);

        if (curso.getAtivo()) {
            curso.deletar();
        } else {
            throw new EntityNotFoundException("Curso já está inativo.");
        }
    }
}
