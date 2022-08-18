package xadrez;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
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

	private void initialSetup() {
		// PEÇAS BRANCAS
		tabuleiro.localPeca(new Torre(tabuleiro, Cor.BRANCO), new Posicao(7, 0));
		tabuleiro.localPeca(new Torre(tabuleiro, Cor.BRANCO), new Posicao(7, 7));
		tabuleiro.localPeca(new Bispo(tabuleiro, Cor.BRANCO), new Posicao(7, 2));
		tabuleiro.localPeca(new Bispo(tabuleiro, Cor.BRANCO), new Posicao(7, 5));
		tabuleiro.localPeca(new Cavalo(tabuleiro, Cor.BRANCO), new Posicao(7, 1));
		tabuleiro.localPeca(new Cavalo(tabuleiro, Cor.BRANCO), new Posicao(7, 6));
		tabuleiro.localPeca(new Peao(tabuleiro, Cor.BRANCO), new Posicao(6, 0));
		tabuleiro.localPeca(new Peao(tabuleiro, Cor.BRANCO), new Posicao(6, 1));
		tabuleiro.localPeca(new Peao(tabuleiro, Cor.BRANCO), new Posicao(6, 2));
		tabuleiro.localPeca(new Peao(tabuleiro, Cor.BRANCO), new Posicao(6, 3));
		tabuleiro.localPeca(new Peao(tabuleiro, Cor.BRANCO), new Posicao(6, 4));
		tabuleiro.localPeca(new Peao(tabuleiro, Cor.BRANCO), new Posicao(6, 5));
		tabuleiro.localPeca(new Peao(tabuleiro, Cor.BRANCO), new Posicao(6, 6));
		tabuleiro.localPeca(new Peao(tabuleiro, Cor.BRANCO), new Posicao(6, 7));
		tabuleiro.localPeca(new Rei(tabuleiro, Cor.BRANCO), new Posicao(7, 4));
		tabuleiro.localPeca(new Rainha(tabuleiro, Cor.BRANCO), new Posicao(7, 3));

		// PEÇAS PRETAS
		tabuleiro.localPeca(new Rei(tabuleiro, Cor.PRETO), new Posicao(0, 4));
		tabuleiro.localPeca(new Rainha(tabuleiro, Cor.PRETO), new Posicao(0, 3));
		tabuleiro.localPeca(new Torre(tabuleiro, Cor.PRETO), new Posicao(0, 0));
		tabuleiro.localPeca(new Torre(tabuleiro, Cor.PRETO), new Posicao(0, 7));
		tabuleiro.localPeca(new Bispo(tabuleiro, Cor.PRETO), new Posicao(0, 2));
		tabuleiro.localPeca(new Bispo(tabuleiro, Cor.PRETO), new Posicao(0, 5));
		tabuleiro.localPeca(new Cavalo(tabuleiro, Cor.PRETO), new Posicao(0, 1));
		tabuleiro.localPeca(new Cavalo(tabuleiro, Cor.PRETO), new Posicao(0, 6));
		tabuleiro.localPeca(new Peao(tabuleiro, Cor.BRANCO), new Posicao(1, 0));
		tabuleiro.localPeca(new Peao(tabuleiro, Cor.BRANCO), new Posicao(1, 1));
		tabuleiro.localPeca(new Peao(tabuleiro, Cor.BRANCO), new Posicao(1, 2));
		tabuleiro.localPeca(new Peao(tabuleiro, Cor.BRANCO), new Posicao(1, 3));
		tabuleiro.localPeca(new Peao(tabuleiro, Cor.BRANCO), new Posicao(1, 4));
		tabuleiro.localPeca(new Peao(tabuleiro, Cor.BRANCO), new Posicao(1, 5));
		tabuleiro.localPeca(new Peao(tabuleiro, Cor.BRANCO), new Posicao(1, 6));
		tabuleiro.localPeca(new Peao(tabuleiro, Cor.BRANCO), new Posicao(1, 7));
		

	}
}
