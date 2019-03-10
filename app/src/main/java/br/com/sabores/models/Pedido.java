package br.com.sabores.models;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

	private List<Lanche> lanches = new ArrayList<>();

	public List<Lanche> getLanches() {
		return lanches;
	}

	public void setLanches(List<Lanche> lanchePersonalizado) {
		this.lanches = lanchePersonalizado;
	}

}
