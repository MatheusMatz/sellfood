package br.com.sabores.discounts;

import java.math.BigDecimal;
import java.util.Collections;

import javax.money.MonetaryAmount;

import br.com.sabores.entities.IngredienteEntity;
import br.com.sabores.entities.LancheEntity;
import br.com.sabores.utils.DinheiroUtil;

public class MuitaCarneDesconto implements Desconto {
	
	private Desconto proximo;

	public BigDecimal desconto(int qtdDesconto, BigDecimal valorDoDesconto, BigDecimal valorDoLanche) {
		MonetaryAmount desconto = DinheiroUtil.bigDecimalToMonetaryAmount(valorDoDesconto).multiply(qtdDesconto);
		MonetaryAmount valor = DinheiroUtil.bigDecimalToMonetaryAmount(valorDoLanche);
		BigDecimal valorComDesconto = DinheiroUtil.monetaryAmountToBigDecimal(valor.subtract(desconto));
		return valorComDesconto;
	}

	@Override
	public void desconta(LancheEntity lanche) {

		int frequency = Collections.frequency(lanche.getIngredientes(), new IngredienteEntity("Hambúrguer de carne"));
		int qtdDesconto = (frequency / 3);

		if (qtdDesconto > 0) {
			BigDecimal valorDoDesconto = lanche.getIngredientes()
												.stream().filter(ingrediente -> ingrediente.getNome().equals("Hambúrguer de carne"))
												.findFirst().get().getValor();
			
			MonetaryAmount desconto = DinheiroUtil.bigDecimalToMonetaryAmount(valorDoDesconto).multiply(qtdDesconto);
			MonetaryAmount valor = DinheiroUtil.bigDecimalToMonetaryAmount(lanche.getValor());
			BigDecimal valorComDesconto = DinheiroUtil.monetaryAmountToBigDecimal(valor.subtract(desconto));
			lanche.setValor(valorComDesconto);
		}
		else {
			this.proximo.desconta(lanche);
		}

	}

	@Override
	public void setProximo(Desconto proximo) {
		this.proximo = proximo;
	}

}
