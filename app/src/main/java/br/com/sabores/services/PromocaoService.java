package br.com.sabores.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.sabores.entities.PromocaoEntity;
import br.com.sabores.repositories.PromocaoRepository;

@Service
@Transactional
public class PromocaoService {

	@Autowired
	PromocaoRepository repository;

	public List<PromocaoEntity> buscarTodas() {
		return (List<PromocaoEntity>) repository.findAll();
	}

	public PromocaoEntity salvarPromocao(PromocaoEntity promocao) {
		return repository.save(promocao);
	}
	
	public List<PromocaoEntity> salvarTodas (List<PromocaoEntity> promocoes) {
		return (List<PromocaoEntity>) repository.saveAll(promocoes);
	}

	public ResponseEntity<PromocaoEntity> atualizarPromocao(String nomePromocao, PromocaoEntity novaPromocao) {
		PromocaoEntity promocao = repository.findByNome(nomePromocao);

		if (!StringUtils.isEmpty(novaPromocao.getNome())) {
			promocao.setNome(novaPromocao.getNome());
		}
		if (!StringUtils.isEmpty(novaPromocao.getDescricao())) {
			promocao.setDescricao(novaPromocao.getDescricao());
		}

		repository.save(promocao);

		return ResponseEntity.ok(promocao);

	}

	public Boolean deletarPromocao(String nome) {

		try {
			return repository.deleteByNome(nome);
		} catch (Exception e) {
			return false;
		}
	}

}
