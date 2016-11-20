package br.com.ufrn.bti.aplicacao;

import br.com.ufrn.bti.dao.CartaoBancarioDAO;
import br.com.ufrn.bti.dominio.CartaoBancario;
import br.com.ufrn.bti.dominio.Pessoa;

public class Teste {

	public Teste() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf("12345678910");
		
		CartaoBancario cartao = new CartaoBancarioDAO().getCartaoDa(pessoa);
		
		System.out.println(cartao.getLimite());
	}

}
