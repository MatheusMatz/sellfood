package br.com.sabores.discounts;

import br.com.sabores.entities.LancheEntity;

public class CalculadorDeDesconto {
	
	public void calcula(LancheEntity lanche) {
		
		Desconto descontoCarne = new MuitaCarneDesconto();
		Desconto desconQueijo = new  MuitoQueijo();
		Desconto descontoLight = new LightDesconto();
		Desconto semDesconto = new SemDesconto();
		
		descontoCarne.setProximo(desconQueijo);
		desconQueijo.setProximo(descontoLight);
		descontoLight.setProximo(semDesconto);
		
		descontoCarne.desconta(lanche);
	}
}
