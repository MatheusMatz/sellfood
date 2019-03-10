package br.com.sabores.discounts;

import br.com.sabores.entities.LancheEntity;

public interface Desconto {

	void desconta(LancheEntity lanche);

	void setProximo(Desconto proximo);

}
