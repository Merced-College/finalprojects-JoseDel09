/* Jose Delgadillo
 blackJack 21 project 
 04/28/2025 */
 
import java.util.*;

// single playing card
class Card {
    String suit;
    String rank;
    int value;

    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    public String toString() {
        return rank + " of " + suit;
    }
}

// deck of 52 playing cards
class Deck {
    private List<Card> cards;
    private String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
    private String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    private int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};

    public Deck() {
        cards = new ArrayList<>();
        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                cards.add(new Card(suits[i], ranks[j], values[j]));
            }
        }
        shuffle();
    }

//chatGPT helped me with the utility method "Collections.shuffle(cards)" to shuffle the cards 
    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        return cards.remove(0);
    }
}

// Main game class
public class BlackjackGame {
    Scanner scanner = new Scanner(System.in);
    Deck deck;
    List<Card> playerHand;

    public BlackjackGame() {
        deck = new Deck();  // Initialize a new shuffled deck
        playerHand = new ArrayList<>();
    }

    // Start a new game
    public void startGame() {
        System.out.println("Welcome to Blackjack!");
        playerHand.add(deck.dealCard());
        playerHand.add(deck.dealCard());
        System.out.println("Your hand: " + playerHand);

    }
    
    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame();
        game.startGame();
    }
}