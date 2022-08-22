package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.ExcecaoXadrez;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;

public class PrincipalXadrez {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		List<PecaDeXadrez> capturar = new ArrayList<>();

		while (!partidaDeXadrez.getXequeMate()) {
			try {
				UI.limparTela();
				UI.printPartida(partidaDeXadrez, capturar);
				System.out.println();
				System.out.print("ORIGEM: ");
				PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);
				
				boolean[][] movimentosPossiveis = partidaDeXadrez.possiveisMovimentos(origem);
				UI.limparTela();
				UI.printTabuleiro(partidaDeXadrez.getPecas(), movimentosPossiveis);
				
				System.out.println();
				System.out.print("DESTINO: ");
				PosicaoXadrez destino = UI.lerPosicaoXadrez(sc);

				PecaDeXadrez pecaCapturada = partidaDeXadrez.performanceMovimentoPeca(origem, destino);
				
				if(pecaCapturada != null) {
					capturar.add(pecaCapturada);
				}
			} 
			catch (ExcecaoXadrez e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.limparTela();
		UI.printPartida(partidaDeXadrez, capturar);
	}

}
