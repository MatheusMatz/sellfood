package br.com.sabores.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sabores.entities.PedidoEntity;
import br.com.sabores.models.Pedido;
import br.com.sabores.services.PedidoService;

@RestController
@RequestMapping("/pedido")
@CrossOrigin(origins = "*")
public class PedidoController {

	@Autowired
	PedidoService service;

	@PostMapping(path = "/efetuar", produces = "application/JSON", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoEntity salvarPedido(@RequestBody Pedido pedidos) {
		return service.salvarPedido(pedidos);
	}

}
