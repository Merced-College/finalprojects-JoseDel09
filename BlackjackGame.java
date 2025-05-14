/* Jose Delgadillo
 blackJack 21 project 
 05/13/2025 */
 
import java.util.*;

// Single playing card
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

// Deck of 52 playing cards
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
    List<Card> dealerHand;

    public BlackjackGame() {
        deck = new Deck();
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
    }
	
    public void startGame() {
        System.out.println("Welcome to Blackjack!");
        playerHand.add(deck.dealCard());
        playerHand.add(deck.dealCard());
        dealerHand.add(deck.dealCard());
        dealerHand.add(deck.dealCard());

        System.out.println("Your hand: " + playerHand);
        System.out.println("Dealer shows: " + dealerHand.get(0));

        // Player's turn
        while (true) {
            int playerValue = calculateHandValue(playerHand);
            System.out.println("Your hand value: " + playerValue);

            if (playerValue > 21) {
                System.out.println("You busted! Dealer wins.");
                return;
            }

            System.out.print("Do you want to (h)it or (s)tand? ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("h")) {
                playerHand.add(deck.dealCard());
                System.out.println("Your hand: " + playerHand);
            } else if (choice.equalsIgnoreCase("s")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter 'h' or 's'.");
            }
        }

        // Dealer's turn
        System.out.println("Dealer's hand: " + dealerHand);
        while (calculateHandValue(dealerHand) < 17) {
            dealerHand.add(deck.dealCard());
            System.out.println("Dealer hits: " + dealerHand);
        }

        int dealerValue = calculateHandValue(dealerHand);
        System.out.println("Dealer's final hand value: " + dealerValue);

        // Determine winner
        int playerValue = calculateHandValue(playerHand);
        if (dealerValue > 21 || playerValue > dealerValue) {
            System.out.println("You win!");
        } else if (playerValue < dealerValue) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    // Utility method to calculate hand value
    public int calculateHandValue(List<Card> hand) {
        int value = 0;
        int numAces = 0;

        for (Card card : hand) {
            value += card.value;
            if (card.rank.equals("Ace")) {
                numAces++;
            }
        }

        while (value > 21 && numAces > 0) {
            value -= 10;
            numAces--;
        }

        return value;
    }

    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame();
        game.startGame();
    }
}