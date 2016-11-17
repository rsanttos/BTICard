package br.com.ufrn.bti.concorrente.espatifado.bticard.comunicacao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import br.com.ufrn.bti.concorrente.espatifado.bticard.comunicacao.conteudo.ConteudoRequisicaoPagamento;
import br.com.ufrn.bti.concorrente.espatifado.bticard.comunicacao.mensagem.MensagemRequisicao;
import br.com.ufrn.bti.concorrente.espatifado.bticard.comunicacao.mensagem.MensagemResposta;
import br.com.ufrn.bti.concorrente.espatifado.bticard.comunicacao.mensagem.TipoMensagem;
import br.com.ufrn.bti.concorrente.espatifado.bticard.servico.PagamentoServico;

public class TratadorRequisicoes implements Runnable {

	private Socket socket;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	private MensagemRequisicao<ConteudoRequisicaoPagamento> mensagem;

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
			this.mensagem = (MensagemRequisicao<ConteudoRequisicaoPagamento>) this.inputStream.readObject();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("ERRO na leitura do objeto recebido!");
			e.printStackTrace();
		}

		MensagemResposta<Boolean> mensagemResposta;

		if (this.mensagem.getTipoMensagem() != TipoMensagem.SOLICITA_PAGAMENTO) {
			mensagemResposta = new MensagemResposta<Boolean>(TipoMensagem.REQUISICAO_NAO_TRATAVEL, false, false);
		} else {
			ConteudoRequisicaoPagamento conteudo = this.mensagem.getConteudo();

			boolean resultadoPagamento = this.servico.efetuarPagamento(conteudo.getPessoa(),
					conteudo.getValorPagamento());
			
			TipoMensagem respostaTipoMensagem = resultadoPagamento ? TipoMensagem.SOLICITA_PAGAMENTO_SUCESSO
					: TipoMensagem.SOLICITA_PAGAMENTO_FALHA;

			mensagemResposta = new MensagemResposta<Boolean>(respostaTipoMensagem, resultadoPagamento, resultadoPagamento);
		}

		try {
			this.outputStream.writeObject(mensagemResposta);
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
