package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.entities.Player;
import model.util.Utils;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		List<Player> players = new ArrayList<>();
		
		UI.clearScreen();
		
		UI.showTitle();
		
		System.out.print("Quantos jogadores jogarão? ");
		Integer n = Utils.tryParseToInt(sc.nextLine().trim());
		
		while(n == null || n > ProgramConstants.MAX_PLAYERS || n < ProgramConstants.MIN_PLAYERS) {
			
			System.out.print("Erro na entrada dos valores. Quantos jogadores jogarão? ");
			n = Utils.tryParseToInt(sc.nextLine().trim());
			
		}
		
		UI.clearScreen();
		
		for (int i = 0; i < n; i++) {
			
			UI.showPlayer(i);
			
			System.out.print("Digite o nome do jogador: ");
			String name = sc.nextLine().trim();
			name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
			
			Player player = new Player(name);
			
			players.add(player);
			
			UI.clearScreen();
			
		}
		
		List<String> deck = ProgramConstants.CARDS;
		
		while (!players.stream().filter(p -> p.isPlaying()).collect(Collectors.toList()).isEmpty() && !deck.isEmpty()) {
			
			for (Player player : players) {
				
				if (player.getPoints() <= ProgramConstants.TWENTY_ONE && player.isPlaying()) {
					
					UI.showPlayerTurn(player.getName());
					
					move(sc, deck, player);
					
					if (player.getPoints() > ProgramConstants.TWENTY_ONE) {
						
						player.setOvercomed(true);
						System.out.println("\nJogador(a) " + player.getName() + " já ultrapassou 21 pontos.");
						
					} else if (!player.isPlaying()) System.out.println("\nJogador(a) " + player.getName() + " decidiu parar de jogar.");
					
				}
				
			}
			
		}
		
		UI.clearScreen();
		
		UI.showChampion(players);
		
		sc.close();

	}

	private static void move(Scanner sc, List<String> deck, Player player) {
		
		System.out.print("Vc está com " + player.getPoints() + " ponto(s). Deseja continuar? (s/n) ");
		char answer = sc.next().trim().toLowerCase().charAt(0);
		
		if (answer == 's') {
			
			String[] cardValues = getCardValues(deck);
			
			player.increasePoints(Integer.parseInt(cardValues[1]));
			
			System.out.println("Foi sorteado a carta " + cardValues[0] + " cujo valor é " + cardValues[1] + ". Você agora está com " + player.getPoints() + " ponto(s).");
			
		} else {
			
			player.setSituation(false);
			
		}
		
	}

	private static String[] getCardValues(List<String> deck) {
		
		Random generator = new Random();
		
		int randomNumber = generator.nextInt(deck.size());
		
		String card = deck.remove(randomNumber);
		int cardValue = getCardValue(card);
		
		return new String[] {card, String.valueOf(cardValue)};
		
	}
	
	private static int getCardValue(String card) {
		
		Integer value = Utils.tryParseToInt(card);
		
		if (value == null) {
			
			switch (card) {
				
				case "A":
					return 1;
					
				case "J":
					return 11;
				
				case "Q":
					return 12;
					
				case "K":
					return 13;

			}
			
		}
		
		return value;
		
	}
	
}
