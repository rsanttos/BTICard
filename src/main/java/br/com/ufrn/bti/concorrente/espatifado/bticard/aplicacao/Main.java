package br.com.ufrn.bti.concorrente.espatifado.bticard.aplicacao;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import br.com.ufrn.bti.concorrente.espatifado.bticard.comunicacao.TratadorRequisicoes;
import br.com.ufrn.bti.concorrente.espatifado.bticard.dao.CartaoBancarioDAO;
import br.com.ufrn.bti.concorrente.espatifado.bticard.dao.PessoaDAO;
import br.com.ufrn.bti.concorrente.espatifado.bticard.dominio.CartaoBancario;
import br.com.ufrn.bti.concorrente.espatifado.bticard.dominio.Pessoa;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int SAIDA_COM_ERRO = 1;
		int PORT = 8889;
		
		ServerSocket listener = null;
		
		try {
			listener = new ServerSocket(PORT);
		} catch (IOException e) {
			System.out.println("ERRO: na inicialização do escutamento na porta " + PORT);
			e.printStackTrace();
			System.exit(SAIDA_COM_ERRO);
		}
		
		Socket socket = null;
		while(true){
			try {
				socket = listener.accept();
			} catch (IOException e) {
				System.out.println("ERRO: na recepção de comunicação com o cliente!");
				e.printStackTrace();
			}
			
			new Thread(new TratadorRequisicoes(socket)).start();
		}
	}
}
