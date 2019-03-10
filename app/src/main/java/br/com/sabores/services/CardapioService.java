package br.com.sabores.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.sabores.entities.CardapioEntity;
import br.com.sabores.entities.LancheEntity;
import br.com.sabores.entities.PromocaoEntity;
import br.com.sabores.repositories.CardapioRepository;
import br.com.sabores.repositories.LancheRepository;
import br.com.sabores.repositories.PromocaoRepository;

@Service
@Transactional
public class CardapioService {

	@Autowired
	CardapioRepository repository;

	@Autowired
	LancheRepository lancheRepository;
	
	@Autowired
	PromocaoRepository promocaoRepository;
	
	private List<LancheEntity> lanches;
	private List<PromocaoEntity> promocoes;
	
	public CardapioEntity buscarCardapio(String titulo) {
		return repository.findByTitulo(titulo);
	}

	public CardapioEntity salvarCardapio(CardapioEntity cardapio) {
		
		lanches = new ArrayList<LancheEntity>();
		promocoes = new ArrayList<PromocaoEntity>();
		
		cardapio.getLanches().forEach(lanche -> {
			LancheEntity result = lancheRepository.findByNome(lanche.getNome());
			if ( result != null ) {
				lanches.add(result);
			}
		});
		
		cardapio.getPromocoes().forEach(promocao -> {
			PromocaoEntity result = promocaoRepository.findByNome(promocao.getNome());
			if ( result != null ) {
				promocoes.add(result);
			}
		});
		
		cardapio.setLanches(lanches);
		cardapio.setPromocoes(promocoes);
		
		return repository.save(cardapio);
	}

	public ResponseEntity<CardapioEntity> atualizarCardapio(String titulo, CardapioEntity cardapioNovo) {
		
		CardapioEntity cardapio = repository.findByTitulo(titulo);
		
		lanches = new ArrayList<LancheEntity>();
		promocoes = new ArrayList<PromocaoEntity>();
		
		if (!StringUtils.isEmpty(cardapioNovo.getTitulo())) {
			cardapio.setTitulo(cardapioNovo.getTitulo());
		}

		cardapioNovo.getLanches().forEach(lanche -> {
			LancheEntity result = lancheRepository.findByNome(lanche.getNome());
			if ( result != null ) {
				lanches.add(result);
			}
		});
		
		cardapioNovo.getPromocoes().forEach(promocao -> {
			PromocaoEntity result = promocaoRepository.findByNome(promocao.getNome());
			if ( result != null ) {
				promocoes.add(result);
			}
		});
		
		cardapio.setLanches(lanches);
		cardapio.setPromocoes(promocoes);
		
		repository.save(cardapio);
		
		return ResponseEntity.ok(cardapio);
	}
	
	public boolean deletarCardapio (String titulo) {
		try {
			return repository.deleteByTitulo(titulo);
		} catch (Exception e) {
			throw e;
		}
	}
}
