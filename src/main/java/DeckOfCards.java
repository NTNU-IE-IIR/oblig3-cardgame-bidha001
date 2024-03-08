import no.ntnu.idatx2003.oblig3.cardgame.PlayingCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckOfCards {
    private final char[] suits = { 'S', 'H', 'D', 'C' };
    private List<PlayingCard> cards;

    public DeckOfCards(){
        cards = new ArrayList<>();

        for (char suit : suits) {
            for (int face = 1; face <= 13; face++){
                cards.add(new PlayingCard(suit, face));
            }
        }
        shuffle();
    }
    public void shuffle(){
        Collections.shuffle(cards);
    }

    public PlayingCard drawCard(){
        if(cards.isEmpty()){
            throw new IllegalArgumentException("The deck of card is empty");
        }
        return cards.remove(0);
    }

    public static void main(String[] args) {
        DeckOfCards deck = new DeckOfCards();

        System.out.println("Number of remainning card:" + deck.getRemainingCards());
    }

    private String getRemainingCards() {
        return getRemainingCards();
    }

}
