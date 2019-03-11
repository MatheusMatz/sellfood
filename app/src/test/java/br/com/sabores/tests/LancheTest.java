package br.com.sabores.tests;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import br.com.sabores.ApplicationTest;
import br.com.sabores.entities.IngredienteEntity;
import br.com.sabores.entities.LancheEntity;
import br.com.sabores.services.IngredienteService;
import br.com.sabores.services.LancheService;

@Transactional
public class LancheTest extends ApplicationTest{

	@Autowired
	private LancheService lancheService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	private LancheEntity xBacon;
	private LancheEntity xBurger;
	private LancheEntity xEgg;
	private LancheEntity xEggBacon;
	
	private IngredienteEntity alface;
	private IngredienteEntity bacon;
	private IngredienteEntity hamburger;
	private IngredienteEntity ovo;
	private IngredienteEntity queijo;
	
	private BigDecimal valor;
	
	@Before
	public void setUp() {
		
		this.alface = ingredienteService.buscarIngrediente("Alface");
		this.bacon = ingredienteService.buscarIngrediente("Bacon");
		this.hamburger = ingredienteService.buscarIngrediente("Hambúrguer de carne");
		this.ovo = ingredienteService.buscarIngrediente("Ovo");
		this.queijo = ingredienteService.buscarIngrediente("Queijo");
		

		this.xBacon = lancheService.buscarLanche("X-Bacon");
		this.xBurger = lancheService.buscarLanche("X-Burger");
		this.xEgg = lancheService.buscarLanche("X-Egg");
		this.xEggBacon = lancheService.buscarLanche("X-Egg Bacon");
		

	}
	
	
	@Test
	public void xBaconValorTeste() {

		valor = new BigDecimal(0);
		List<IngredienteEntity> ingredientes = new ArrayList<>();
		
		ingredientes.add(hamburger);
		ingredientes.add(bacon);
		ingredientes.add(queijo);
		
		ingredientes.forEach(ingrediente -> { 
			valor = valor.add(ingrediente.getValor());
		});
		
		Assert.assertEquals(xBacon.getValor(), valor);
		
	}
	
	@Test
	public void xBurgerValorTeste(){
		valor = new BigDecimal(0);
		List<IngredienteEntity> ingredientes = new ArrayList<>();
		
		ingredientes.add(hamburger);
		ingredientes.add(queijo);
		
		ingredientes.forEach(ingrediente -> { 
			valor = valor.add(ingrediente.getValor());
		});
		
		Assert.assertEquals(xBurger.getValor(), valor);
	}
	
	@Test
	public void xEggValorTeste(){
		valor = new BigDecimal(0);
		List<IngredienteEntity> ingredientes = new ArrayList<>();
		
		ingredientes.add(hamburger);
		ingredientes.add(queijo);
		ingredientes.add(ovo);
		
		ingredientes.forEach(ingrediente -> { 
			valor = valor.add(ingrediente.getValor());
		});
		
		Assert.assertEquals(xEgg.getValor(), valor);
	}
	
	@Test
	public void xEggBaconValorTeste(){
		valor = new BigDecimal(0);
		List<IngredienteEntity> ingredientes = new ArrayList<>();
		
		ingredientes.add(hamburger);
		ingredientes.add(queijo);
		ingredientes.add(ovo);
		ingredientes.add(bacon);
		
		ingredientes.forEach(ingrediente -> { 
			valor = valor.add(ingrediente.getValor());
		});
		
		Assert.assertEquals(xEggBacon.getValor(), valor);
	}
	
	
	@Test
	@Rollback(true)
	public void personalizadoValorTeste(){
		valor = new BigDecimal(0);
		List<IngredienteEntity> ingredientes = new ArrayList<>();
		
		LancheEntity lancheTeste = new LancheEntity();
		
		
		ingredientes.add(new IngredienteEntity("Hambúrguer de carne"));
		ingredientes.add(new IngredienteEntity("Hambúrguer de carne"));
		ingredientes.add(new IngredienteEntity("Queijo"));
		ingredientes.add(new IngredienteEntity("Ovo"));
		ingredientes.add(new IngredienteEntity("Ovo"));
		ingredientes.add(new IngredienteEntity("Bacon"));
		
		ingredienteService.buscarVarios(ingredientes).forEach(ingrediente -> { 
			valor = valor.add(ingrediente.getValor());
		});
		
		lancheTeste.setIngredientes(ingredientes);
		lancheTeste.setNome("LancheTeste");
		
		lancheService.salvarLanche(lancheTeste);
		
		Assert.assertEquals(lancheService.buscarLanche("LancheTeste").getValor(), valor);
	}
	
	@Test
	@Rollback(true)
	public void inflacaoTeste(){
		
		hamburger.setValor(new BigDecimal(10.5));
		
		ingredienteService.atualizarIngrediente("Hambúrguer de carne", hamburger);
		
		valor = new BigDecimal(0);
		List<IngredienteEntity> ingredientes = new ArrayList<>();
		
		ingredientes.add(new IngredienteEntity("Hambúrguer de carne"));
		ingredientes.add(new IngredienteEntity("Queijo"));
		
		ingredienteService.buscarVarios(ingredientes).forEach(ingrediente -> { 
			valor = valor.add(ingrediente.getValor());
		});
		
		Assert.assertEquals(xBurger.getValor(), valor);
	}

	
}
