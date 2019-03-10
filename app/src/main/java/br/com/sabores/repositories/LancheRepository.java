package br.com.sabores.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.sabores.entities.IngredienteEntity;
import br.com.sabores.entities.LancheEntity;

@Repository
public interface LancheRepository extends CrudRepository<LancheEntity, Long> {

	LancheEntity findByNome(String nome);

	Boolean deleteByNome(String nome);

	List<LancheEntity> findByIngredientes(IngredienteEntity ingrediente);

}
