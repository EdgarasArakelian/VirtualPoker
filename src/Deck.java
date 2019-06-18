import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    //int index = 0;
    ArrayList<Card> deck = new ArrayList<>(); //Card [] deck = new Card[52];

    // Creating 52 cards
    void buildDeck(){
        for (int i = 1; i < 5; i++) { // H, S, P, C
            for (int j = 1; j < 14; j++) { // 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A
                Card card = new Card(i, j);
                //index++;
                deck.add(card);
            }
        }
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    // Randomizing elements in the arrayList
    void shuffleDeck(){
        Collections.shuffle(deck);
    }

    // I take 1st card from the deck and remove it from the deck
    public Card getCardFromDeck(){
        if (!deck.isEmpty()){
            Card card = deck.get(0);
            deck.remove(card);
            return card;
        }

        // Something is wrong ...
        System.err.println("No more cards in the deck ...");
        System.exit(21);
        return null;
    }
}
