package br.com.sabores.controllers;

import java.util.List;

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

import br.com.sabores.entities.LancheEntity;
import br.com.sabores.services.LancheService;

@RestController
@RequestMapping("/lanche")
@CrossOrigin(origins = "*")
public class LancheController {

	@Autowired
	LancheService service;

	@GetMapping("/todos")
	@ResponseBody
	public List<LancheEntity> buscarTodos() {
		return service.buscarTodos();
	}

	@GetMapping("/{nomeLanche}")
	@ResponseBody
	public LancheEntity buscarLanche(@PathVariable String nomeLanche) {
		return service.buscarLanche(nomeLanche);
	}

	@PostMapping(path = "/salvar", produces = "application/JSON")
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public LancheEntity novoLanche(@RequestBody LancheEntity lanche) {
		return service.salvarLanche(lanche);
	}
	

	@PutMapping(value = "/{nomeLanche}")
	public ResponseEntity<LancheEntity> atualizarLanche(@PathVariable String nomeLanche, @RequestBody LancheEntity novoLanche) {
		return service.atualizarLanche(nomeLanche, novoLanche);
	}

	@DeleteMapping("/{nome}")
	public boolean deletar(@PathVariable String nome) {
		return service.deletar(nome);
	}

}
