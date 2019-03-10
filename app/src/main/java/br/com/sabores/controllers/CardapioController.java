package br.com.sabores.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sabores.entities.CardapioEntity;
import br.com.sabores.services.CardapioService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/cardapio")
public class CardapioController {

	@Autowired
	CardapioService service;

	@GetMapping("/{tituloCardapio}")
	@ResponseBody
	public CardapioEntity buscarCardapio(@PathVariable String tituloCardapio) {
		return service.buscarCardapio(tituloCardapio);
	}

	@PostMapping(path = "/salvar", produces = "application/JSON")
	@ResponseStatus(HttpStatus.CREATED)
	public CardapioEntity salvarCardapio(@RequestBody CardapioEntity cardapio) {
		return service.salvarCardapio(cardapio);
	}

	@PutMapping(value = "/{tituloCardapio}")
	public ResponseEntity<CardapioEntity> updateLanche(@PathVariable String tituloCardapio,
			@RequestBody CardapioEntity novoCardapio) {
		return service.atualizarCardapio(tituloCardapio, novoCardapio);
	}

	@DeleteMapping(value = "/{tituloCardapio}")
	public boolean deletarCardapio(@PathVariable String tituloCardapio) {
		return service.deletarCardapio(tituloCardapio);
	}
}
