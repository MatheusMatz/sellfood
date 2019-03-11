package br.com.sabores.discounts;

import java.math.BigDecimal;
import java.util.Collections;

import javax.money.MonetaryAmount;

import br.com.sabores.entities.IngredienteEntity;
import br.com.sabores.entities.LancheEntity;
import br.com.sabores.utils.DinheiroUtil;

public class LightDesconto implements Desconto {

	private Desconto proximo;
	
	public BigDecimal aplicaDesconto(BigDecimal valor) {
		MonetaryAmount amount = DinheiroUtil.bigDecimalToMonetaryAmount(valor);
		BigDecimal valorComDesconto = DinheiroUtil.monetaryAmountToBigDecimal(amount.multiply(0.10));
		return valorComDesconto;
	}

	@Override
	public void desconta(LancheEntity lanche) {
		int frequenciaBancon = Collections.frequency(lanche.getIngredientes(), new IngredienteEntity("Bacon"));
		int frequenciaAlface = Collections.frequency(lanche.getIngredientes(), new IngredienteEntity("Alface"));
		
		if(frequenciaBancon == 0 && frequenciaAlface > 0) {
			MonetaryAmount amount = DinheiroUtil.bigDecimalToMonetaryAmount(lanche.getValor());
			BigDecimal valorComDesconto = DinheiroUtil.monetaryAmountToBigDecimal(amount.subtract(amount.multiply(0.10)));
			lanche.setValor(valorComDesconto);
		} else {
			this.proximo.desconta(lanche);
		}

		
	}

	@Override
	public void setProximo(Desconto proximo) {
		this.proximo = proximo;
	}

}
