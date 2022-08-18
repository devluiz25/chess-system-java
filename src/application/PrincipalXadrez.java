package application;

import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;

public class PrincipalXadrez {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();

		while (true) {
			UI.printBoard(partidaDeXadrez.getPecas());
			System.out.println();
			System.out.print("ORIGEM: ");
			PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);
			
			System.out.println();
			System.out.print("DESTINO: ");
			PosicaoXadrez destino = UI.lerPosicaoXadrez(sc);
			
			PecaDeXadrez pecaCapturada = partidaDeXadrez.performanceMovimentoPeca(origem, destino);

		}
	}

}
