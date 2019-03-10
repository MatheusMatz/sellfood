package br.com.sabores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.sabores.entities.PedidoEntity;

@Repository
public interface PedidoRepository extends CrudRepository<PedidoEntity, Long>{

}
