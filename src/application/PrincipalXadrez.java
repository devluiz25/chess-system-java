package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.ExcecaoXadrez;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;

public class PrincipalXadrez {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();

		while (true) {
			try {
				UI.limparTela();
				UI.printBoard(partidaDeXadrez.getPecas());
				System.out.println();
				System.out.print("ORIGEM: ");
				PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);

				System.out.println();
				System.out.print("DESTINO: ");
				PosicaoXadrez destino = UI.lerPosicaoXadrez(sc);

				PecaDeXadrez pecaCapturada = partidaDeXadrez.performanceMovimentoPeca(origem, destino);
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
	}

}
