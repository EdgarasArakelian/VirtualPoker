public class Dealer {
    private Deck deck;

    void buildNewDeck(){
        deck = new Deck();
        deck.buildDeck();
        deck.shuffleDeck();
    }

    Card getCardFromDeck(){
        return deck.getCardFromDeck();
    }

}
