public class Card implements Comparable {
    private int suit, rank;

    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    //public Card(int suit, int rank, int id) {
    Card(int suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // Sort by rank
    @Override
    public int compareTo(Object o) {
        int comparerank = ((Card)o).getRank();
        return this.rank-comparerank;
    }
}
