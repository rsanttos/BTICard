package br.com.ufrn.bti.aplicacao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import br.com.ufrn.bti.comunicacao.conteudo.ConteudoRequisicaoPagamento;
import br.com.ufrn.bti.dao.UsuarioDAO;
import br.com.ufrn.bti.dominio.Pessoa;
import br.com.ufrn.bti.dominio.Usuario;
import br.com.ufrn.bti.servico.PagamentoServico;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		int porta = 6792;

		ServerSocket escuta = new ServerSocket(porta);

		System.out.println("*** Servidor ***");
		System.out.println("*** Porta de escuta (listen): " + porta);

		while (true) {
			Socket cliente = escuta.accept();
			System.out.println("*** conexao aceita de (remoto): " + cliente.getRemoteSocketAddress());

			boolean deuCerto = true;

			ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());

			ConteudoRequisicaoPagamento conteudo = (ConteudoRequisicaoPagamento) ois.readObject();

			Usuario usuario = new UsuarioDAO().buscarPeloLogin(conteudo.getUsuario().getLogin());

			if (usuario != null) {
				Pessoa pessoa = usuario.getPessoa();

				if (pessoa == null) {
					deuCerto = false;
				} else {
					PagamentoServico servico = new PagamentoServico();
					deuCerto = servico.efetuarPagamento(usuario.getPessoa(), conteudo.getValorPagamento());
				}
			} else {
				deuCerto = false;
			}
			
			System.out.println("Resposta do servidor");
			System.out.println(deuCerto);

			ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
			oos.writeObject(deuCerto);
		}
	}
}
