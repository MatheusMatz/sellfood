package br.com.sabores.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import br.com.sabores.entities.PromocaoEntity;
import br.com.sabores.services.PromocaoService;

@RestController
@RequestMapping("/promocao")
public class PromocaoController {

	@Autowired
	PromocaoService service;
	
	@GetMapping("/todos")
	@ResponseBody
	public List<PromocaoEntity> buscarTodas() {
		return service.buscarTodas();
	}

	@PostMapping(path = "/salvar", produces = "application/JSON")
	@ResponseStatus(HttpStatus.CREATED)
	public PromocaoEntity salvarPromocao(@RequestBody PromocaoEntity promocao) {
		return service.salvarPromocao(promocao);
	}
	
	@PostMapping(path = "/salvarTodas", produces = "application/JSON")
	@ResponseStatus(HttpStatus.CREATED)
	public List<PromocaoEntity> salvarTodas(@RequestBody List<PromocaoEntity> promocoes) {
		return service.salvarTodas(promocoes);
	}

	@PutMapping(value = "/{nomePromocao}")
	public ResponseEntity<PromocaoEntity> atualizarPromocao(@PathVariable String nomePromocao, @RequestBody PromocaoEntity promocao) {
		return service.atualizarPromocao(nomePromocao, promocao);
	}

	@DeleteMapping("/{nome}")
	public boolean deletarPromocao(@PathVariable String nome) {
		return service.deletarPromocao(nome);
	}

	
}
