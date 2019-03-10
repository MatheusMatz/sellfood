package br.com.sabores.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "lanches")
public class LancheEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "valor", scale=2, precision=12)
	private BigDecimal valor;

	@ManyToMany
	@JoinTable(name = "ingredientes_do_lanche", 
			joinColumns = { @JoinColumn(name = "lanche_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "ingrediente_id") })
	private List<IngredienteEntity> ingredientes;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor.setScale(2, RoundingMode.HALF_EVEN); 
		
	}

	public List<IngredienteEntity> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<IngredienteEntity> ingredientes) {
		this.ingredientes = ingredientes;
	}

	@Override
	public String toString() {
		return "nome : " + this.nome + " valor :" + this.valor;
	}
}
