package br.com.sabores.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.sabores.entities.IngredienteEntity;
import br.com.sabores.entities.LancheEntity;
import br.com.sabores.repositories.LancheRepository;

@Service
@Transactional
public class LancheService {

	@Autowired
	LancheRepository repository;

	@Autowired
	IngredienteService ingredientService;

	private BigDecimal valor;

	public List<LancheEntity> buscarTodos() {
		return (List<LancheEntity>) repository.findAll();
	}

	public LancheEntity buscarLanche(String nomeLanche) {
		return repository.findByNome(nomeLanche);
	}

	public LancheEntity salvarLanche(LancheEntity lanche) {
		this.atribuirInformacoes(lanche);
		return repository.save(lanche);
	}
	
	public ResponseEntity<LancheEntity> atualizarLanche(String nomeLanche, LancheEntity novoLanche) {
		LancheEntity lanche = repository.findByNome(nomeLanche);

		if (!StringUtils.isEmpty(novoLanche.getNome())) {
			lanche.setNome(novoLanche.getNome());
		}
		
		atribuirInformacoes(novoLanche);
		
		repository.save(lanche);
		
		return ResponseEntity.ok(lanche);
	}
 
	
	public boolean deletar(String nome) {
		try {
			return repository.deleteByNome(nome);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void atualizarValor(String nomeIngrediente) {
			
		IngredienteEntity ingrediente = ingredientService.buscarIngrediente(nomeIngrediente);
			
		repository.findByIngredientes(ingrediente).forEach(lanche -> {
			atribuirInformacoes(lanche);
			repository.save(lanche);
		});
	}

	private void atribuirInformacoes(LancheEntity lanche) {
		valor = new BigDecimal(0);
		List<IngredienteEntity> ingredientes = new ArrayList<IngredienteEntity>();
		
		if(StringUtils.isEmpty(lanche.getNome())) {
			lanche.setNome("Lanche");
		}

		lanche.getIngredientes().forEach(ingrediente -> {
			IngredienteEntity result = ingredientService.buscarIngrediente(ingrediente.getNome());
			if (result != null) {
				ingredientes.add(result);
				valor = valor.add(result.getValor());
			};
		});
		
		lanche.setIngredientes(ingredientes);
		lanche.setValor(valor);

	}

}
