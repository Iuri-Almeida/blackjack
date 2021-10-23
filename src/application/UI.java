package application;

import java.util.Collections;
import java.util.List;

import model.entities.Player;

public class UI {
	
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");
	    System.out.flush();
	} 
	
	public static void showTitle() {
		System.out.println("### Seja bem vindo ao jogo Blackjack! ###\n");
	}
	
	public static void showPlayer(int i) {
		System.out.printf("\n----- Jogador %d -----\n", i + 1);
	}
	
	public static void showPlayerTurn(String name) {
		System.out.printf("\n----- Vez de %s -----\n", name);
	}
	
	public static void showChampion(List<Player> players) {
		
		Collections.sort(players);
		
		Player player = players.get(0);
		
		if (player.hasOvercomed()) System.out.println("\nTodos os jogadores perderam.");
		else {
			
			System.out.println("\n### Ganhador(a) ###\n");
			System.out.print("\n" + player.getName() + " com " + player.getPoints() + " ponto(s).\n\n");
			
		}
		
	}

}
