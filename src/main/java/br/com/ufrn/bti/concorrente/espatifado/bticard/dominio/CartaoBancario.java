package br.com.ufrn.bti.concorrente.espatifado.bticard.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.gson.annotations.SerializedName;

@Entity
@Table(name="cartao_bancario", schema="public")
public class CartaoBancario {

	/**
	 * ID da entidade
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_CARTAO_BANCARIO")
	@SequenceGenerator(name="SEQ_CARTAO_BANCARIO", sequenceName="id_seq_cartao_bancario", allocationSize=1)
	@Column(name="id_cartao_bancario")
	@SerializedName(value = "idCartaoBancario")
	private int id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@PrimaryKeyJoinColumn
	private Pessoa pessoa;
	
	private long numeroCartao;

	private double limite;
	
	public CartaoBancario() {
		// TODO Auto-generated constructor stub
	}
	
	public CartaoBancario(Pessoa pessoa, long numeroCartao, double limite) {
		super();
		this.pessoa = pessoa;
		this.numeroCartao = numeroCartao;
		this.limite = limite;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public long getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(long numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

}
