package br.com.ufrn.bti.concorrente.espatifado.bticard.comunicacao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import br.com.ufrn.bti.concorrente.espatifado.bticard.servico.PagamentoServico;

public class TratadorRequisicoes implements Runnable {

	private Socket socket;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	private MensagemRequisicao mensagem;

	private PagamentoServico servico;

	public TratadorRequisicoes(Socket socket) {
		this.socket = socket;
		servico = new PagamentoServico();
	}

	@Override
	public void run() {
		try {
			this.inputStream = new ObjectInputStream(this.socket.getInputStream());
			this.outputStream = new ObjectOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("ERRO no estabelecimento da comunicação!");
			e.printStackTrace();
		}

		try {
			this.mensagem = (MensagemRequisicao) this.inputStream.readObject();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("ERRO na leitura do objeto recebido!");
			e.printStackTrace();
		}

		MensagemResposta resposta = new MensagemResposta(
				this.servico.efetuarPagamento(this.mensagem.getPessoa(), this.mensagem.getValorPagamento()));

		try {
			this.outputStream.writeObject(resposta);
		} catch (IOException e) {
			System.out.println("ERRO no envio da resposta!");
			e.printStackTrace();
		}
		
		try {
			this.socket.close();
		} catch (IOException e) {
			System.out.println("ERRO ao fechar o socket!"); 
			e.printStackTrace();
		}
	}

}
