package br.docket.cartorio.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import br.docket.cartorio.entity.AbstractEntity;
import br.docket.cartorio.repository.GenericRepository;

/**
 * 
 * @author Herick Melo
 *
 */
public class GenericService<T extends AbstractEntity> {

	@Autowired
	protected GenericRepository<T> repository;

	public GenericService() {
	}

	public void salvar(T obj) {
		repository.save(obj);
	}

	public void salvarTodos(List<T> objs) {
		repository.saveAll(objs);
	}

	public void remover(T obj) {
		repository.delete(obj);
	}

	public void remover(Long id) {
		repository.deleteById(id);
	}

	public Optional<T> findById(Long id) {
		return repository.findById(id);
	}

	public List<T> findAll() {
		return repository.findAll();
	}

	public long countAtivo() {
		return repository.countActive();
	}
}
