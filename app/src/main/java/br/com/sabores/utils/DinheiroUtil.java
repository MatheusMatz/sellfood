package br.com.sabores.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;

public final class DinheiroUtil {

	private static CurrencyUnit moeda = Monetary.getCurrency("BRL");
	
	public static MonetaryAmount bigDecimalToMonetaryAmount(BigDecimal valor) {
		MonetaryAmount valorMonetario = Money.of(valor, moeda);
		return valorMonetario;
	}

	public static BigDecimal monetaryAmountToBigDecimal(MonetaryAmount valor) {
		BigDecimal valorMonetario = valor.getNumber().numberValue(BigDecimal.class).setScale(2, RoundingMode.HALF_EVEN);
		return valorMonetario;
	}

}
