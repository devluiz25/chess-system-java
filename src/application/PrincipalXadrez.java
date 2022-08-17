package application;

import xadrez.PartidaDeXadrez;

public class PrincipalXadrez {

	public static void main(String[] args) {
	
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		UI.printBoard(partidaDeXadrez.getPecas());

	}

}
