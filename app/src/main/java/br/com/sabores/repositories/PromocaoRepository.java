package br.com.sabores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.sabores.entities.PromocaoEntity;

@Repository
public interface PromocaoRepository extends CrudRepository<PromocaoEntity, Long>{

	PromocaoEntity findByNome(String nome);

	Boolean deleteByNome(String nome);

}
