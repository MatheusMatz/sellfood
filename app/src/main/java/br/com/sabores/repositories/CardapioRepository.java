package br.com.sabores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.sabores.entities.CardapioEntity;

@Repository
public interface CardapioRepository extends CrudRepository<CardapioEntity, Long>{

	CardapioEntity findByTitulo(String titulo);
	
	boolean deleteByTitulo (String titulo);
}
