package br.com.sabores.tests;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.money.MonetaryAmount;
import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.sabores.ApplicationTest;
import br.com.sabores.discounts.CalculadorDeDesconto;
import br.com.sabores.entities.IngredienteEntity;
import br.com.sabores.entities.LancheEntity;
import br.com.sabores.services.IngredienteService;
import br.com.sabores.utils.DinheiroUtil;

@Transactional
public class DescontoTest extends ApplicationTest{

	@Autowired
	private IngredienteService ingredienteService;

	private CalculadorDeDesconto calculadorDeDesconto = new CalculadorDeDesconto();

	private IngredienteEntity alface;
	private IngredienteEntity bacon;
	private IngredienteEntity hamburger;
	private IngredienteEntity ovo;
	private IngredienteEntity queijo;

	private BigDecimal valor;

	private BigDecimal valorComDescontos;

	@Before
	public void setUp() {

		this.alface = ingredienteService.buscarIngrediente("Alface");
		this.bacon = ingredienteService.buscarIngrediente("Bacon");
		this.hamburger = ingredienteService.buscarIngrediente("Hambúrguer de carne");
		this.ovo = ingredienteService.buscarIngrediente("Ovo");
		this.queijo = ingredienteService.buscarIngrediente("Queijo");

	}

	@Test
	public void lightDescontoTeste() {
		valor = new BigDecimal(0);
		List<IngredienteEntity> ingredientes = new ArrayList<>();

		LancheEntity lanchePersonalizado = new LancheEntity();

		ingredientes.add(hamburger);
		ingredientes.add(hamburger);
		ingredientes.add(queijo);
		ingredientes.add(ovo);
		ingredientes.add(ovo);
		ingredientes.add(alface);

		ingredientes.forEach(ingrediente -> {
			valor = valor.add(ingrediente.getValor());
		});

		MonetaryAmount amount = DinheiroUtil.bigDecimalToMonetaryAmount(valor);
		valorComDescontos = DinheiroUtil.monetaryAmountToBigDecimal(amount.subtract(amount.multiply(0.10)));

		lanchePersonalizado.setNome("Lanche");
		lanchePersonalizado.setIngredientes(ingredientes);
		lanchePersonalizado.setValor(valor);

		calculadorDeDesconto.calcula(lanchePersonalizado);

		Assert.assertEquals(lanchePersonalizado.getValor(), valorComDescontos);

	}

	@Test
	public void muitaCarneDescontoTeste() {
		valor = new BigDecimal(0);
		List<IngredienteEntity> ingredientes = new ArrayList<>();

		LancheEntity lanchePersonalizado = new LancheEntity();

		ingredientes.add(hamburger);
		ingredientes.add(hamburger);
		ingredientes.add(hamburger);
		ingredientes.add(hamburger);
		ingredientes.add(hamburger);
		ingredientes.add(queijo);
		ingredientes.add(ovo);
		ingredientes.add(ovo);
		ingredientes.add(alface);

		ingredientes.forEach(ingrediente -> {
			valor = valor.add(ingrediente.getValor());
		});

		int frequency = Collections.frequency(ingredientes, new IngredienteEntity("Hambúrguer de carne"));
		int qtdDesconto = (frequency / 3);
		
		MonetaryAmount desconto = DinheiroUtil.bigDecimalToMonetaryAmount(hamburger.getValor()).multiply(qtdDesconto);
		MonetaryAmount valorTotal = DinheiroUtil.bigDecimalToMonetaryAmount(valor);
		valorComDescontos = DinheiroUtil.monetaryAmountToBigDecimal(valorTotal.subtract(desconto));

		lanchePersonalizado.setIngredientes(ingredientes);
		lanchePersonalizado.setValor(valor);

		calculadorDeDesconto.calcula(lanchePersonalizado);

		Assert.assertEquals(lanchePersonalizado.getValor(), valorComDescontos);

	}

	@Test
	public void muitoQueijoDescontoTeste() {

		valor = new BigDecimal(0);
		List<IngredienteEntity> ingredientes = new ArrayList<>();

		LancheEntity lanchePersonalizado = new LancheEntity();

		ingredientes.add(hamburger);
		ingredientes.add(hamburger);
		ingredientes.add(queijo);
		ingredientes.add(queijo);
		ingredientes.add(queijo);
		ingredientes.add(ovo);
		ingredientes.add(ovo);
		ingredientes.add(alface);

		ingredientes.forEach(ingrediente -> {
			valor = valor.add(ingrediente.getValor());
		});

		int frequency = Collections.frequency(ingredientes, new IngredienteEntity("Queijo"));
		int qtdDesconto = (frequency / 3);
		
		MonetaryAmount desconto = DinheiroUtil.bigDecimalToMonetaryAmount(queijo.getValor()).multiply(qtdDesconto);
		MonetaryAmount valorTotal = DinheiroUtil.bigDecimalToMonetaryAmount(valor);
		valorComDescontos = DinheiroUtil.monetaryAmountToBigDecimal(valorTotal.subtract(desconto));

		lanchePersonalizado.setIngredientes(ingredientes);
		lanchePersonalizado.setValor(valor);

		calculadorDeDesconto.calcula(lanchePersonalizado);

		Assert.assertEquals(lanchePersonalizado.getValor(), valorComDescontos);

	}
	
	@Test
	public void semDescontoTeste() {

		valor = new BigDecimal(0);
		List<IngredienteEntity> ingredientes = new ArrayList<>();

		LancheEntity lanchePersonalizado = new LancheEntity();

		ingredientes.add(hamburger);
		ingredientes.add(hamburger);
		ingredientes.add(queijo);
		ingredientes.add(queijo);
		ingredientes.add(ovo);
		ingredientes.add(ovo);
		ingredientes.add(alface);
		ingredientes.add(alface);
		ingredientes.add(bacon);

		ingredientes.forEach(ingrediente -> {
			valor = valor.add(ingrediente.getValor());
		});

		lanchePersonalizado.setIngredientes(ingredientes);
		lanchePersonalizado.setValor(valor);

		calculadorDeDesconto.calcula(lanchePersonalizado);

		Assert.assertEquals(lanchePersonalizado.getValor(), valor);

	}

}
