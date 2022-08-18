package xadrez;

import tabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Dama;
import xadrez.pecas.Peao;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {

	private Tabuleiro tabuleiro;

	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		initialSetup();
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
		//PEÇAS BRANCAS
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
	
		
		//PEÇAS PRETAS0
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
}
