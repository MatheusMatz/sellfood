package br.com.sabores.entities;

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
@Table(name = "cardapio")
public class CardapioEntity {

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "Titulo")
	private String titulo;

	@ManyToMany
	@JoinTable(name = "lanches_do_cardapio", joinColumns = { @JoinColumn(name = "cardapio_id") }, inverseJoinColumns = {
			@JoinColumn(name = "lanche_id") })
	private List<LancheEntity> lanches;

	@ManyToMany
	@JoinTable(name = "promocoes_do_cardapio", joinColumns = {
			@JoinColumn(name = "cardapio_id") }, inverseJoinColumns = { @JoinColumn(name = "promocao_id") })
	private List<PromocaoEntity> promocoes;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<LancheEntity> getLanches() {
		return lanches;
	}

	public void setLanches(List<LancheEntity> lanches) {
		this.lanches = lanches;
	}

	public List<PromocaoEntity> getPromocoes() {
		return promocoes;
	}

	public void setPromocoes(List<PromocaoEntity> promocoes) {
		this.promocoes = promocoes;
	}

}
