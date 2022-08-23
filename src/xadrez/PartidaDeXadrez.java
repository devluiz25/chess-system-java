package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	private boolean xeque;
	private boolean xequeMate;
	private PecaDeXadrez anPassant;

	List<Peca> pecasNoTabuleiro = new ArrayList<>();
	List<Peca> pecasCapturadas = new ArrayList<>();

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

	public boolean getXeque() {
		return xeque;
	}

	public boolean getXequeMate() {
		return xequeMate;
	}

	public PecaDeXadrez getAnPassant() {
		return anPassant;
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

	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}

	private void novaPosicaoPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.localPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca);
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
		novaPosicaoPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		novaPosicaoPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		novaPosicaoPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		novaPosicaoPeca('d', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		novaPosicaoPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		novaPosicaoPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		novaPosicaoPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		novaPosicaoPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO, this));

		// PEÇAS PRETAS
		novaPosicaoPeca('e', 8, new Rei(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('d', 8, new Dama(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('a', 7, new Peao(tabuleiro, Cor.PRETO, this));
		novaPosicaoPeca('b', 7, new Peao(tabuleiro, Cor.PRETO, this));
		novaPosicaoPeca('c', 7, new Peao(tabuleiro, Cor.PRETO, this));
		novaPosicaoPeca('d', 7, new Peao(tabuleiro, Cor.PRETO, this));
		novaPosicaoPeca('e', 7, new Peao(tabuleiro, Cor.PRETO, this));
		novaPosicaoPeca('f', 7, new Peao(tabuleiro, Cor.PRETO, this));
		novaPosicaoPeca('g', 7, new Peao(tabuleiro, Cor.PRETO, this));
		novaPosicaoPeca('h', 7, new Peao(tabuleiro, Cor.PRETO, this));

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

		if (testarXeque(vezJogador)) {
			desfazerMovimento(origem, destino, capturarPeca);
			throw new ExcecaoXadrez("VOCE NAO PODE SE COLOCAR EM XEQUE!");
		}

		PecaDeXadrez moverPeca = (PecaDeXadrez) tabuleiro.peca(destino);

		xeque = (testarXeque(oponente(vezJogador))) ? true : false;

		if (testarXequeMate(oponente(vezJogador))) {
			xequeMate = true;
		} else {
			proximoTurno();
		}

		// MOVIMENTO ESPECIAL ANPASSANT
		if (moverPeca instanceof Peao
				&& (destino.getLinha() == origem.getLinha() - 2 || destino.getLinha() == origem.getLinha() + 2)) {
			anPassant = moverPeca;
		} else {
			anPassant = null;
		}
		return (PecaDeXadrez) capturarPeca;
	}

	private Peca fazerMovimento(Posicao origem, Posicao destino) {
		PecaDeXadrez p = (PecaDeXadrez) tabuleiro.removerPeca(origem);
		p.incrementarContagemMov();
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.localPeca(p, destino);

		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		// MOVIMENTO ESPECIAL ANPASSANT
		if (p instanceof Peao) {
			if (origem.getColuna() != destino.getColuna() && pecaCapturada == null) {
				Posicao posicaoPeao;
				if (p.getCor() == Cor.BRANCO) {
					posicaoPeao = new Posicao(destino.getLinha() + 1, destino.getColuna());
				} else {
					posicaoPeao = new Posicao(destino.getLinha() - 1, destino.getColuna());
				}
				pecaCapturada = tabuleiro.removerPeca(posicaoPeao);
				pecasCapturadas.add(pecaCapturada);
				pecasNoTabuleiro.remove(pecaCapturada);

			}
		}

		return pecaCapturada;
	}

	private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		PecaDeXadrez p = (PecaDeXadrez) tabuleiro.removerPeca(destino);
		p.decrementarContagemMov();
		tabuleiro.localPeca(p, origem);

		if (pecaCapturada != null) {
			tabuleiro.localPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}

		// MOVIMENTO ESPECIAL ANPASSANT
		if (p instanceof Peao) {
			if (origem.getColuna() != destino.getColuna() && pecaCapturada == anPassant) {
				PecaDeXadrez peao = (PecaDeXadrez)tabuleiro.removerPeca(destino);
				Posicao posicaoPeao;
				if (p.getCor() == Cor.BRANCO) {
					posicaoPeao = new Posicao(3, destino.getColuna());
				} else {
					posicaoPeao = new Posicao(4, destino.getColuna());
				}
				tabuleiro.localPeca(peao, posicaoPeao);
			}
		}
	}

	private void validarPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.existePeca(posicao)) {
			throw new ExcecaoXadrez("NAO HA PECA NA POSICAO DE ORIGEM!");
		}

		if (vezJogador != ((PecaDeXadrez) tabuleiro.peca(posicao)).getCor()) {
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

	private PecaDeXadrez rei(Cor cor) {
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca p : list) {
			if (p instanceof Rei) {
				return (PecaDeXadrez) p;
			}
		}
		throw new IllegalStateException("NÃO HA NENHUM REI DA COR " + cor + " NO TABULEIRO!");
	}

	private boolean testarXeque(Cor cor) {
		Posicao posicaoRei = rei(cor).getPosicaoXadrez().toPosicao();
		List<Peca> pecaOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez) x).getCor() == oponente(cor))
				.collect(Collectors.toList());
		for (Peca p : pecaOponente) {
			boolean[][] mat = p.movimentosPossiveis();
			if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}

	private boolean testarXequeMate(Cor cor) {
		if (!testarXeque(cor)) {
			return false;
		}
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca p : list) {
			boolean[][] mat = p.movimentosPossiveis();
			for (int i = 0; i < tabuleiro.getLinhas(); i++) {
				for (int j = 0; j < tabuleiro.getColunas(); j++) {
					if (mat[i][j]) {
						Posicao origem = ((PecaDeXadrez) p).getPosicaoXadrez().toPosicao();
						Posicao destino = new Posicao(i, j);
						Peca pecaCapturada = fazerMovimento(origem, destino);
						boolean testXeque = testarXeque(cor);
						desfazerMovimento(origem, destino, pecaCapturada);
						if (!testXeque) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

}
