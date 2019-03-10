package br.com.sabores.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pedidos")
public class PedidoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "lanches_do_pedido", 
		joinColumns = { @JoinColumn(name = "pedido_id") }, 
		inverseJoinColumns = { @JoinColumn(name = "lanche_id") })
	private List<LancheEntity> lanches = new ArrayList<LancheEntity>();

	@Column(name = "valor", scale=2, precision=12)
	private BigDecimal valor;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;

	public List<LancheEntity> getLanches() {
		return lanches;
	}

	public void setLanches(List<LancheEntity> lanches) {
		this.lanches = lanches;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	
}
