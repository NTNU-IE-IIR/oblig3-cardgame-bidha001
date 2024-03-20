package no.ntnu.idatx2003.oblig3.cardgame;

import no.ntnu.idatx2003.oblig3.cardgame.data.PlayingCard;
import no.ntnu.idatx2003.oblig3.cardgame.logic.DeckOfCards;
import no.ntnu.idatx2003.oblig3.cardgame.ui.CardGameApplication;

/**
 * The main class of the card game application.
 * This class contains the main method and is the entry point of the application.
 */
public class Main {
    public static void main(String[] args) {
        // Create an instance of no.ntnu.idatx2003.oblig3.cardgame.logic.DeckOfCards
        DeckOfCards deck = new DeckOfCards();

        // Shuffle the deck (optional)
        deck.shuffle();

        // Draw a card from the deck
        var hand = deck.dealHand();
        for (PlayingCard card : hand) {
            System.out.println("Drawn card: " + card.getAsString());

            // Get the number of remaining cards
            System.out.println("Number of remaining cards: " + deck.getRemainingCards());

            CardGameApplication.main(args);
        }
    }
}
