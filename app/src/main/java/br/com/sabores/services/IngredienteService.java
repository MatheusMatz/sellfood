package br.com.sabores.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.sabores.entities.IngredienteEntity;
import br.com.sabores.repositories.IngredienteRepository;

@Service
@Transactional
public class IngredienteService {

	@Autowired
	IngredienteRepository repository;

	@Autowired
	LancheService lancheService;

	public List<IngredienteEntity> buscarTodos() {
		return (List<IngredienteEntity>) repository.findAll();
	}

	public IngredienteEntity buscarIngrediente(String nome) {
		return repository.findByNome(nome);
	}

	public IngredienteEntity salvarIngrediente(IngredienteEntity ingrediente) {
		return repository.save(ingrediente);
	}

	public IngredienteEntity salvarTodos(List<IngredienteEntity> ingredientes) {
		return (IngredienteEntity) repository.saveAll(ingredientes);
	}
	
	public List<IngredienteEntity> buscarVarios(List<IngredienteEntity> ingredientes){
		List<IngredienteEntity> ingredientesEncontrados = new ArrayList<>();
		
		ingredientes.forEach(ingrediente -> {
			IngredienteEntity result = repository.findByNome(ingrediente.getNome());
			if (result != null) {
				ingredientesEncontrados.add(result);
			}
		});
		
		return ingredientesEncontrados;
	}

	public ResponseEntity<IngredienteEntity> atualizarIngrediente(String nomeIngrediente, IngredienteEntity novoIngrediente) {
		IngredienteEntity ingrediente = repository.findByNome(nomeIngrediente);

		if (!StringUtils.isEmpty(novoIngrediente.getNome())) {
			ingrediente.setNome(novoIngrediente.getNome());
		}

		if (!StringUtils.isEmpty(novoIngrediente.getValor())) {
			ingrediente.setValor(novoIngrediente.getValor());
		}
		
		repository.save(ingrediente);

		lancheService.atualizarValor(nomeIngrediente);

		return ResponseEntity.ok(ingrediente);
	}

	public boolean deletarIngrediente(String nomeIngrediente) {
		try {
			return repository.deleteByNome(nomeIngrediente);
		} catch (Exception e) {
			throw e;
		}

	}

}
