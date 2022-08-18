package xadrez.pecas;

import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaDeXadrez;

public class Dama extends PecaDeXadrez{
	
	public Dama(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	public String toString() {
		return "D";
	}

}
