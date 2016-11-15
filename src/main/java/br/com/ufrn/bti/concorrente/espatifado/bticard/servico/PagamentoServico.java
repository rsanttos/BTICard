package br.com.ufrn.bti.concorrente.espatifado.bticard.servico;

import br.com.ufrn.bti.concorrente.espatifado.bticard.dao.CartaoBancarioDAO;
import br.com.ufrn.bti.concorrente.espatifado.bticard.dominio.Pessoa;

public class PagamentoServico {
	
	private CartaoBancarioDAO dao;

	public PagamentoServico() {
		dao = new CartaoBancarioDAO();
	}
	
	public boolean efetuarPagamento(Pessoa pessoa, double valorPagamento){
		double limiteCartao = dao.getLimiteCartao(pessoa);
		
		if(valorPagamento > limiteCartao){
			return false;	
		}
		
		return true;
	}
}
