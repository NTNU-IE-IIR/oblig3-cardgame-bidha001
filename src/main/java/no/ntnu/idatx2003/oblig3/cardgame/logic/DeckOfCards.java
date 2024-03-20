package no.ntnu.idatx2003.oblig3.cardgame.logic;

import no.ntnu.idatx2003.oblig3.cardgame.data.PlayingCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A class representing a deck of playing cards.
 */
public class DeckOfCards {
    private final char[] suits = { 'S', 'H', 'D', 'C' };
    private List<PlayingCard> cards;

    /**
     * Constructs a new deck of cards containing all possible playing cards.
     * The deck is shuffled immediately after creation.
     */
    public DeckOfCards(){
        cards = new ArrayList<>();

        // Generate all possible playing cards and add them to the deck
        for (char suit : suits) {
            for (int face = 1; face <= 13; face++){
                cards.add(new PlayingCard(suit, face));
            }
        }
        // Shuffle the deck
        shuffle();
    }
    /**
     * Shuffles the deck of cards.
     */
    public void shuffle(){
        Collections.shuffle(cards);
    }

    /**
     * Draws a card from the deck.
     *
     * @return The drawn card.
     * @throws IllegalArgumentException if the deck is empty.
     */
    public PlayingCard drawCard(){
        if(cards.isEmpty()){
            throw new IllegalArgumentException("The deck of card is empty");
        }
        return cards.remove(0);
    }

    /**
     * Gets the number of remaining cards in the deck.
     *
     * @return The number of remaining cards.
     */
    public int getRemainingCards() {
        return cards.size();
    }

    /**
     * Deals a hand of cards from the deck.
     *
     * @return A list of dealt cards.
     * @throws IllegalArgumentException if n is not between 1 and 52 (inclusive) or
     * if there are not enough cards left in the deck.
     */
    public List<PlayingCard> dealHand() {
        final int numCardsToDeal = 5;
        if (numCardsToDeal >cards.size()) {
            throw new IllegalArgumentException("Parameter n must be a number between 1 to 52");
        }

        List<PlayingCard> hand = new ArrayList<>();
        for (int i = 0; i < numCardsToDeal; i++) {
            hand.add(cards.remove(0));
            System.out.println(i);
        }
        return hand;
    }
}
