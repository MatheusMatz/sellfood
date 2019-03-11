package br.com.sabores.tests;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.sabores.ApplicationTest;
import br.com.sabores.entities.CardapioEntity;
import br.com.sabores.entities.IngredienteEntity;
import br.com.sabores.services.CardapioService;
import br.com.sabores.services.IngredienteService;

@Transactional
public class CardapioTest extends ApplicationTest{

	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private CardapioService cardapioService;
	
	private CardapioEntity cardapio;
	
	private IngredienteEntity bacon;
	private IngredienteEntity hamburger;
	private IngredienteEntity ovo;
	private IngredienteEntity queijo;
	
	private BigDecimal valor;
	
	@Before
	public void setUp() {
		
		cardapio = cardapioService.buscarCardapio("Lanches");
		
		this.bacon = ingredienteService.buscarIngrediente("Bacon");
		this.hamburger = ingredienteService.buscarIngrediente("Hambúrguer de carne");
		this.ovo = ingredienteService.buscarIngrediente("Ovo");
		this.queijo = ingredienteService.buscarIngrediente("Queijo");

		
	}
	
	@Test
	public void xBaconTesteValor() {

		valor = new BigDecimal(0);
		List<IngredienteEntity> ingredientes = new ArrayList<>();
		
		ingredientes.add(hamburger);
		ingredientes.add(bacon);
		ingredientes.add(queijo);
		
		ingredientes.forEach(ingrediente -> { 
			valor = valor.add(ingrediente.getValor());
		});
		
		BigDecimal valorXBacon = cardapio.getLanches().stream().filter(lanche -> lanche.getNome().equals("X-Bacon")).findFirst().get().getValor();
		
		Assert.assertEquals(valorXBacon, valor);
		
	}
	
	@Test
	public void xBurgerTesteValor(){
		valor = new BigDecimal(0);
		List<IngredienteEntity> ingredientes = new ArrayList<>();
		
		ingredientes.add(hamburger);
		ingredientes.add(queijo);
		
		ingredientes.forEach(ingrediente -> { 
			valor = valor.add(ingrediente.getValor());
		});
		
		BigDecimal valorXBurger = cardapio.getLanches().stream().filter(lanche -> lanche.getNome().equals("X-Burger")).findFirst().get().getValor();
		
		Assert.assertEquals(valorXBurger, valor);
	}
	
	@Test
	public void xEggTesteValor(){
		valor = new BigDecimal(0);
		List<IngredienteEntity> ingredientes = new ArrayList<>();
		
		ingredientes.add(hamburger);
		ingredientes.add(queijo);
		ingredientes.add(ovo);
		
		ingredientes.forEach(ingrediente -> { 
			valor = valor.add(ingrediente.getValor());
		});
		
		BigDecimal valorXEgg = cardapio.getLanches().stream().filter(lanche -> lanche.getNome().equals("X-Egg")).findFirst().get().getValor();
		
		Assert.assertEquals(valorXEgg, valor);
	}
	
	@Test
	public void xEggBaconTesteValor(){
		valor = new BigDecimal(0);
		List<IngredienteEntity> ingredientes = new ArrayList<>();
		
		ingredientes.add(hamburger);
		ingredientes.add(queijo);
		ingredientes.add(ovo);
		ingredientes.add(bacon);
		
		ingredientes.forEach(ingrediente -> { 
			valor = valor.add(ingrediente.getValor());
		});
		
		BigDecimal valorxEggBacon = cardapio.getLanches().stream().filter(lanche -> lanche.getNome().equals("X-Egg Bacon")).findFirst().get().getValor();
		
		Assert.assertEquals(valorxEggBacon, valor);
	}
	
	
}
