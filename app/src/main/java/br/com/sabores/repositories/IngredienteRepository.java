package br.com.sabores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.sabores.entities.IngredienteEntity;

@Repository
public interface IngredienteRepository extends CrudRepository<IngredienteEntity, Long> {

	IngredienteEntity findByNome(String nome);

	Boolean deleteByNome(String nome);

}
