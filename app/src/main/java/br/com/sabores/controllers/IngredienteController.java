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

import br.com.sabores.entities.IngredienteEntity;
import br.com.sabores.services.IngredienteService;

@RestController
@RequestMapping(path = "/ingrediente")
@CrossOrigin(origins = "*")
public class IngredienteController {

	@Autowired
	IngredienteService service;

	@GetMapping("/todos")
	@ResponseBody
	public List<IngredienteEntity> buscarTodos() {
		return service.buscarTodos();
	}

	@GetMapping("/nome/{nomeIngrediente}")
	@ResponseBody
	public IngredienteEntity buscarIngrediente(@PathVariable String nomeIngrediente) {
		return service.buscarIngrediente(nomeIngrediente);
	}

	@PostMapping(path = "/salvar", produces = "application/JSON")
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public IngredienteEntity salvarIngrediente(@RequestBody IngredienteEntity ingrediente) {
		return service.salvarIngrediente(ingrediente);
	}

	@PutMapping(value = "/{nomeIngrediente}")
	public ResponseEntity<IngredienteEntity> atualizarIngrediente(@PathVariable String nomeIngrediente,
			@RequestBody IngredienteEntity ingrediente) {

		return service.atualizarIngrediente(nomeIngrediente, ingrediente);

	}

	@DeleteMapping("/{nomeIngrediente}")
	public boolean deletarIngrediente(@PathVariable String nomeIngrediente) {
		return service.deletarIngrediente(nomeIngrediente);
	}

}
