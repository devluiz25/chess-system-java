package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Dama;
import xadrez.pecas.Peao;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {

	private int turno;
	private Cor vezJogador;
	private Tabuleiro tabuleiro;

	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		vezJogador = Cor.BRANCO;
		initialSetup();
	}
	
	public int getTurno() {
		return turno;
	}
	
	public Cor getVezJogador() {
		return vezJogador;
	}

	public PecaDeXadrez[][] getPecas() {
		PecaDeXadrez[][] mat = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaDeXadrez) tabuleiro.peca(i, j);
			}
		}

		return mat;
	}

	private void novaPosicaoPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.localPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
	}

	private void initialSetup() {
		// PEÇAS BRANCAS
		novaPosicaoPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO));
		novaPosicaoPeca('d', 1, new Dama(tabuleiro, Cor.BRANCO));
		novaPosicaoPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		novaPosicaoPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		novaPosicaoPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		novaPosicaoPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		novaPosicaoPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
		novaPosicaoPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
		novaPosicaoPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO));
		novaPosicaoPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO));
		novaPosicaoPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO));
		novaPosicaoPeca('d', 2, new Peao(tabuleiro, Cor.BRANCO));
		novaPosicaoPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO));
		novaPosicaoPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO));
		novaPosicaoPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO));
		novaPosicaoPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO));

		// PEÇAS PRETAS0
		novaPosicaoPeca('e', 8, new Rei(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('d', 8, new Dama(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('a', 7, new Peao(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('b', 7, new Peao(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('c', 7, new Peao(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('d', 7, new Peao(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('e', 7, new Peao(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('f', 7, new Peao(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('g', 7, new Peao(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('h', 7, new Peao(tabuleiro, Cor.PRETO));

	}

	public boolean[][] possiveisMovimentos(PosicaoXadrez posicaoOrigem) {
		Posicao posicao = posicaoOrigem.toPosicao();
		validarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
	}

	public PecaDeXadrez performanceMovimentoPeca(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Peca capturarPeca = fazerMovimento(origem, destino);
		proximoTurno();
		return (PecaDeXadrez) capturarPeca;
	}

	private Peca fazerMovimento(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.localPeca(p, destino);
		return pecaCapturada;
	}

	private void validarPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.existePeca(posicao)) {
			throw new ExcecaoXadrez("NAO HA PECA NA POSICAO DE ORIGEM!");
		}
		
		if(vezJogador != ((PecaDeXadrez)tabuleiro.peca(posicao)).getCor()) {
			throw new ExcecaoXadrez("A PECA ESCOLHIDA NAO E SUA!");
		}
		
		if (!tabuleiro.peca(posicao).haAlgumMovimentoPossivel()) {
			throw new ExcecaoXadrez("NAO HA MOVIMENTOS POSSIVEIS PARA A PECA ESCOLHIDA!");
		}
	}

	private void validarPosicaoDestino(Posicao origem, Posicao destino) {
		if (!tabuleiro.peca(origem).movimentosPossiveis(destino)) {
			throw new ExcecaoXadrez("HA PECA ESCOLHIDA NAO PODE SE MOVER PARA POSICAO DE DESTINO!");
		}
	}
	
	public void proximoTurno() {
		turno++;
		vezJogador = (vezJogador == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
}
