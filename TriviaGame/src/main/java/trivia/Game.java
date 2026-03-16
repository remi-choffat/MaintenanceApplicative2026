package trivia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Game implements IGame {

	private final List<Player> players = new ArrayList<>();
	private int currentPlayerIndex = 0;
	private boolean isGettingOutOfPenaltyBox;

	private final Map<String, LinkedList<String>> questions = new HashMap<>();
	private static final String[] CATEGORIES = {"Pop", "Science", "Sports", "Rock"};

	public Game() {
		for (String category : CATEGORIES) {
			LinkedList<String> categoryQuestions = new LinkedList<>();
			for (int i = 0; i < 50; i++) {
				categoryQuestions.addLast(category + " Question " + i);
			}
			questions.put(category, categoryQuestions);
		}
	}

	@Override
	public boolean add(String playerName) {
		players.add(new Player(playerName));
		System.out.println(playerName + " was added");
		System.out.println("They are player number " + players.size());
		return true;
	}

	@Override
	public void roll(int roll) {
		Player player = players.get(currentPlayerIndex);
		System.out.println(player.name() + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (player.isInPenaltyBox()) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				System.out.println(player.name() + " is getting out of the penalty box");
				movePlayerAndAskQuestion(player, roll);
			} else {
				System.out.println(player.name() + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
			}
		} else {
			movePlayerAndAskQuestion(player, roll);
		}
	}

	private void movePlayerAndAskQuestion(Player player, int roll) {
		player.move(roll);
		System.out.println(player.name() + "'s new location is " + player.place());

		String category = CATEGORIES[(player.place() - 1) % 4];
		System.out.println("The category is " + category);
		System.out.println(questions.get(category).removeFirst());
	}

	@Override
	public boolean handleCorrectAnswer() {
		Player player = players.get(currentPlayerIndex);

		if (player.isInPenaltyBox() && !isGettingOutOfPenaltyBox) {
			nextPlayer();
			return true; // Le jeu continue
		}

		if (player.isInPenaltyBox() && isGettingOutOfPenaltyBox) {
			player.setInPenaltyBox(false); // Le BUG est corrigé ici
		}

		System.out.println("Answer was correct!!!!"); // La TYPO est corrigée ici
		player.addCoin();
		System.out.println(player.name() + " now has " + player.coins() + " Gold Coins.");

		boolean notAWinner = !player.hasWon();
		nextPlayer();
		return notAWinner;
	}

	@Override
	public boolean wrongAnswer() {
		Player player = players.get(currentPlayerIndex);
		System.out.println("Question was incorrectly answered");
		System.out.println(player.name() + " was sent to the penalty box");
		player.setInPenaltyBox(true);

		nextPlayer();
		return true;
	}

	private void nextPlayer() {
		currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
	}
}

/**
 * Cette classe gère l'état d'un joueur
 */
class Player {
	private final String name;
	private int place = 1;
	private int coins = 0;
	private boolean inPenaltyBox = false;

	public Player(String name) {
		this.name = name;
	}

	public String name() {
		return name;
	}

	public int place() {
		return place;
	}

	public int coins() {
		return coins;
	}

	public boolean isInPenaltyBox() {
		return inPenaltyBox;
	}

	public void setInPenaltyBox(boolean inPenaltyBox) {
		this.inPenaltyBox = inPenaltyBox;
	}

	public void move(int roll) {
		place += roll;
		if (place > 12) {
			place -= 12;
		}
	}

	public void addCoin() {
		coins++;
	}

	public boolean hasWon() {
		return coins == 6;
	}
}