import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private final static int numberOfCardsInTheHand = 5;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Virtual Poker ...");
        while (true){
            playGame();

            System.out.println("Play again? [Y/N]");
            String userInput = sc.next();

            if (!userInput.toLowerCase().equals("y"))
                break;
        }

        sc.close();
    }

    // VP game flow ...
    private static void playGame() {
        Dealer dealer = new Dealer();
        Player player = new Player();
        dealer.buildNewDeck();

        // Draw initial hand:
        drawCards(numberOfCardsInTheHand, dealer, player);

        // Discard cards:
        int numberOfDiscardedCards = letPlayerDiscardCards(player);

        //Draw more cards:
        drawCards(numberOfDiscardedCards, dealer, player);

//        System.out.println("How many cards you want to discard? [1-5]");
//        int number = sc.nextInt();
//
//        for (int i = 0; i < number; i++) {
//            System.out.println("Which card you want to discard? [1-5]");
//            int index = sc.nextInt();
//            displayPlayersHand(player);
//            player.removeCard(index); //int number = player.discardCards();
//
//        }


        //dealer.dealCards(number);
//        for (int i = 0; i < number; i++) { //dealer.dealCards(5);
//            Card card = dealer.getCardFromDeck();
//            player.addCard(card);
//        }


        //eval hand:
//        player.sortHand(); // Do i even need this now?

        ArrayList<Card> playerHand = player.getHand();
        //Evaluate player's hand
        int score = evalPlayerHand(playerHand);

        // Display score
        System.out.println("Final score: " + score);
    }

    // Drawing card from the deck and placed in players hand ...
    private static void drawCards(int n, Dealer dealer, Player player) {
        for (int i = 0; i < n; i++) { //dealer.dealCards(5);
            Card card = dealer.getCardFromDeck();
            player.addCard(card);
        }

        displayPlayersHand(player);
    }

    private static int letPlayerDiscardCards(Player player) {
        System.out.println("How many cards you want to discard? [0-5]");
        int number = sc.nextInt();

        for (int i = 0; i < number; i++) {
            System.out.println("Which card you want to discard? [1-"
                    + player.getNumberOfCardsInTheHand() + "]");

            int index = sc.nextInt();
            index = index - 1;
            player.removeCard(index); //int number = player.discardCards();
            displayPlayersHand(player);

        }

        return number;
    }

    private static void displayPlayersHand(Player player) {
        //System.out.println("Your hand:");
        player.sortHand();
        player.displayHand();
    }

    // Evaluating player's hand:
    private static int evalPlayerHand(ArrayList<Card> playerHand) {
        int oCard[] = new int[14]; // 1-13, 0 never used
        int sCard[] = new int[5]; // 1-4, 0 never used

        for (Card card : playerHand){
            oCard[card.getRank()] = oCard[card.getRank()] + 1;
            sCard[card.getSuit()] = sCard[card.getSuit()] + 1;
        }


        // Order matters; Replace with enum
        if (checkRoyalFlush(oCard, sCard)){
            return 800;
        }

        if (checkStraightFlush(oCard, sCard)){
            return 50;
        }

        if (checkFourOfAKind(oCard)){
            return 25;
        }

        if (checkFullHouse(oCard)){
            return 9;
        }

        if (checkFlush(sCard)){
            return 6;
        }

        if (checkStraight(oCard, false)){
            return 4;
        }

        if (checkThreeOfAKind(oCard)){
            return 3;
        }

        if (checkTwoPair(oCard)){
            return 2;
        }

        if (checkJacksOrBetter(oCard)){
            return 1;
        }

        return 0;
    }

    // Checking player's combinations ...

    private static boolean checkJacksOrBetter(int[] oCard) {

        return checkHighPair(oCard);// || checkHighCard(oCard);
    }

    private static boolean checkHighPair(int[] oCard) {
        for (int i = 10; i < 14; i++) {
            if (oCard[i] == 2)
                return true;

        }
//
//
//        for (int occ : oCard){
//            if (occ == 2)
//                return true;
//        }

        return false;
    }

    private static boolean checkFullHouse(int[] oCard) {
        boolean threeCards = false;
        boolean pair = false;

        for (int occ : oCard){
            if (occ == 3)
                threeCards = true;

            if (occ == 2)
                pair = true;
        }

        return threeCards && pair;

    }

    private static boolean checkStraight(int[] oCard, boolean royal) {
        int nRow = 0;
        int i;

        if (!royal)
            i = 1;
        else
            i = 9;

        for (; i < 14; i++) {
            if (oCard[i] == 1){
                nRow++;
            } else
                nRow = 0;

            if (nRow == 5)
                return true;
        }

        return false;
    }

    private static boolean checkTwoPair(int[] oCard) {
        int numberOfPairs = 0;

        for (int i = 0; i < 14; i++) {
            if (oCard[i] > 1){
                numberOfPairs++;
            }
        }

        return numberOfPairs == 2;

    }

    private static boolean checkThreeOfAKind(int[] oCard) {
        return checkNOfKind(3, oCard);
    }

    private static boolean checkFourOfAKind(int[] oCard) {
        return checkNOfKind(4, oCard);
    }

    private static boolean checkNOfKind(int number, int[] oCard){
        for (int i = 1; i < 14; i++) {
            if (oCard[i] == number){
                return true;
            }
        }

        return false;

    }

    private static boolean checkFlush(int[] sCard) {
        for (int i = 1; i < 5; i++) {
            if (sCard[i] == 5)
                return true;
        }

        return false;
    }

    private static boolean checkStraightFlush(int[] oCard, int[] sCard) {
        return checkStraight(oCard, false) && checkFlush(sCard);
    }

    private static boolean checkRoyalFlush(int[] oCard, int[] sCard) {
        return checkStraight(oCard, true) && checkFlush(sCard); // && checkRoyal(oCard);
    }

//    private static boolean checkHighCard(int[] oCard) {
//        boolean higCard = false;
//
//        for (int i = 10; i < 14; i++) {
//            if (oCard[i] >= 1) {
//                higCard = true;
//                break;
//            }
//        }
//
//        return higCard;
//    }

    // ---------------------------------------------------------------
//    private static boolean checkRoyal(int[] oCard) {
//        return false;
//    }
}
