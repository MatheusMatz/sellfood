package br.com.sabores.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.sabores.discounts.CalculadorDeDesconto;
import br.com.sabores.entities.IngredienteEntity;
import br.com.sabores.entities.LancheEntity;
import br.com.sabores.entities.PedidoEntity;
import br.com.sabores.models.Ingrediente;
import br.com.sabores.models.Lanche;
import br.com.sabores.models.Pedido;
import br.com.sabores.repositories.IngredienteRepository;
import br.com.sabores.repositories.LancheRepository;
import br.com.sabores.repositories.PedidoRepository;

@Service
@Transactional
public class PedidoService {
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	LancheRepository lancheRepository;
	
	@Autowired
	IngredienteRepository ingredienteRepository;
	
	private BigDecimal valorPedido;
	private BigDecimal valorLanche;
	private PedidoEntity pedidoEntity;
	private CalculadorDeDesconto calculadorDeDesconto = new CalculadorDeDesconto();
	

	public PedidoEntity salvarPedido(Pedido pedido) {
		pedidoEntity = new PedidoEntity();
	
		preencherPedido(pedidoEntity, pedido.getLanches());
		
		return pedidoRepository.save(pedidoEntity);
	}
	
	private void preencherPedido(PedidoEntity entity,  List<Lanche> lanches) {
		
		entity.setData(Calendar.getInstance());
		valorPedido = new BigDecimal(0);
		
		lanches.forEach(lanche -> {
			if(!StringUtils.isEmpty(lanche.getNome())) {
				LancheEntity result = lancheRepository.findByNome(lanche.getNome());
				for (int i = 0; i < lanche.getQuantidade(); i++) {
					entity.getLanches().add(result);
					valorPedido = valorPedido.add(result.getValor());
				}
			}
			else {
				LancheEntity personalizado = geraLanchePersonalizado(lanche.getIngredientes());
				for (int i = 0; i < lanche.getQuantidade(); i++) {
					entity.getLanches().add(personalizado);
					valorPedido = valorPedido.add(personalizado.getValor());
				}
			}
		});
		
		entity.setValor(valorPedido);
		
	}
	
	private LancheEntity geraLanchePersonalizado(List<Ingrediente> ingredientes) {
		LancheEntity lanche = new LancheEntity();
		List<IngredienteEntity> novosIngredientes = new ArrayList<IngredienteEntity>();
		valorLanche = new BigDecimal(0);
		
		ingredientes.forEach(ingrediente -> {
			IngredienteEntity result = ingredienteRepository.findByNome(ingrediente.getNome());
				if (result != null) {
					for (int i = 0; i < ingrediente.getQuantidade(); i++) {
						novosIngredientes.add(result);
						valorLanche = valorLanche.add(result.getValor());
				};
			}
		});
		
		lanche.setNome("Lanche");
		lanche.setIngredientes(novosIngredientes);
		lanche.setValor(valorLanche);
		
		calculadorDeDesconto.calcula(lanche);
		
		return lanche;
	}
	
}
