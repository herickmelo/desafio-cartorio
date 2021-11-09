package br.docket.cartorio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

/**
 * Classe que representa uma certidão
 * @author Herick
 *
 */
@Table
@Entity
public class Certidao extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Cada certidao possui um identificador
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CERTIDAO")
	@SequenceGenerator(name = "SEQ_CERTIDAO", sequenceName = "seq_certidao", allocationSize = 1)
	@Expose(serialize = true)
	private Long id;
	
	/**
	 * Nome da certidão
	 */
	private String nome;
	
	/**
	 * getters ans setters
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
}
