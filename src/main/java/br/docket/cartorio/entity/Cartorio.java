package br.docket.cartorio.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

/**
 * Classe que representa um cartório
 * @author Herick
 *
 */
@Table(name="cartorio")
@Entity
public class Cartorio extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Cada cartório possui um identificador
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CARTORIO")
	@SequenceGenerator(name = "SEQ_CARTORIO", sequenceName = "seq_cartorio", allocationSize = 1)
	@Expose(serialize = true)
	private Long id;
	
	/**
	 * Nome do cartório
	 */
	private String nome;
	
	/**
	 * Endereço do cartório
	 */
	private String endereco;
	
	/**
	 * Certidões emitidas pelo cartório
	 */
	@OneToMany(targetEntity=Certidao.class, cascade=CascadeType.ALL, fetch = FetchType.LAZY) 
	private List<Certidao> certidoes;

	/**
	 * getters and setters
	 */
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Certidao> getCertidoes() {
		return certidoes;
	}

	public void setCertidoes(List<Certidao> certidoes) {
		this.certidoes = certidoes;
	}
}
