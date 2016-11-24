package br.com.ufrn.bti.aplicacao;

import br.com.ufrn.bti.dao.CartaoBancarioDAO;
import br.com.ufrn.bti.dao.PessoaDAO;
import br.com.ufrn.bti.dao.UsuarioDAO;
import br.com.ufrn.bti.dominio.CartaoBancario;
import br.com.ufrn.bti.dominio.Pessoa;
import br.com.ufrn.bti.dominio.Usuario;

public class Teste {

	public Teste() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf("12345678910");
		pessoa.setNome("Inácio Medeiros");
		
		Usuario usuario = new Usuario();
		usuario.setLogin("boyinacio");
		usuario.setSenha("123");
		usuario.setPessoa(pessoa);
		
		CartaoBancario cartao = new CartaoBancario(pessoa, 123, 0, 2000);
		
		new PessoaDAO().salvarOuAtualizar(pessoa);
		new UsuarioDAO().salvarOuAtualizar(usuario);
		new CartaoBancarioDAO().salvarOuAtualizar(cartao);
		
		CartaoBancario card = new CartaoBancarioDAO().getCartaoDa(pessoa);
		
		System.out.println(card.getLimite());
	}

}
