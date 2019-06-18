import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private ArrayList<Card> hand;

    Player() {
        hand = new ArrayList<>();
    }

    // Adding card to the hand
    void addCard (Card card){
        hand.add(card);
    }

    // Removing card from the hand by index
    void removeCard(int index){
        hand.remove(index);
    }

    // Number of the cards in the hand
    int getNumberOfCardsInTheHand(){
        return hand.size();
    }

    void displayHand(){
        int cardNumber = 1;
        System.out.println("CN| R | Suit");
        for (Card card : hand){
            String suit = convertToSuit(card.getSuit());
            String rank = convertToRank(card.getRank());
            System.out.println(cardNumber + " | " + rank + " | " + suit);
            cardNumber++;
        }
        System.out.println();
    }

    // Human readable format:
    private String convertToSuit(int suitNo) {
        String suit = "";

        switch (suitNo){
            case 1:
                suit = "DIAMONDS";
                break;
            case 2:
                suit = "CLUBS";
                break;
            case 3:
                suit = "HEARTS";
                break;
            case 4:
                suit = "SPADES";
                break;
        }

        return suit;
    }
    private String convertToRank(int rankNo) {
        String card = "";
        switch (rankNo){
            case 1:
                card = "2";
                break;
            case 2:
                card = "3";
                break;
            case 3:
                card = "4";
                break;
            case 4:
                card = "5";
                break;
            case 5:
                card = "6";
                break;
            case 6:
                card = "7";
                break;
            case 7:
                card = "8";
                break;
            case 8:
                card = "9";
                break;
            case 9:
                card = "10";
                break;
            case 10:
                card = "J";
                break;
            case 11:
                card = "Q";
                break;
            case 12:
                card = "K";
                break;
            case 13:
                card = "A";
                break;
        }

        return card;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    // Sorted by rank
    void sortHand(){
//        for (Card card : hand){
//            System.out.print(card.getRank() + " ");
//        }

        Collections.sort(hand);

//        System.out.println();
//        for (Card card : hand){
//            System.out.print(card.getRank() + " ");
//        }
//        System.out.println();
    }
}
