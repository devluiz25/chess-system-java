package xadrez;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {

	private Tabuleiro tabuleiro;
	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		initialSetup();
	}
	
	public PecaDeXadrez[][] getPecas(){
		PecaDeXadrez[][] mat = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for(int i = 0; i < tabuleiro.getLinhas(); i++) {
			for(int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaDeXadrez) tabuleiro.peca(i, j);
			}
		}
		
		return mat;
	}
	
	private void initialSetup() {
		tabuleiro.localPeca(new Torre(tabuleiro, Cor.BRANCO), new Posicao(0, 0));
		tabuleiro.localPeca(new Torre(tabuleiro, Cor.BRANCO), new Posicao(0, 7));
		tabuleiro.localPeca(new Torre(tabuleiro, Cor.PRETO), new Posicao(7, 0));
		tabuleiro.localPeca(new Torre(tabuleiro, Cor.BRANCO), new Posicao(7, 7));
		tabuleiro.localPeca(new Rei(tabuleiro, Cor.PRETO), new Posicao(0, 4));
		tabuleiro.localPeca(new Rei(tabuleiro, Cor.BRANCO), new Posicao(7, 4));
	}
}
