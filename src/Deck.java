/**
 * This class represents the initial, shuffled stack of cards that we will use to supply the window with cards.
 */
public class Deck
{
    private final Card[] myCards;

    // the location of the "top" of the deck, initially space 0. We aren't ever going to really remove any cards from
    //  our list; instead we will say any cards before startPoint are "used" and pretend they are removed. As we deal
    //  more cards from the deck, we'll move startPoint forward.
    private int startPoint;

    public Deck()
    {
        myCards = new Card[81];
        // TODO - required: make the 81 different cards and put them in myCards!

        shuffleAndReset();
    }

    /**
     * equivalent to breaking out a new deck of cards, shuffling it and getting ready to start dealing from the top of
     * the deck.
     */
    public void shuffleAndReset()
    {
        for (int i = 0; i<200; i++)
        {
            int a = (int)(Math.random()*81);
            int b = (int)(Math.random()*81);
            Card temp = myCards[a];
            myCards[a] = myCards[b];
            myCards[b] = temp;
        }
        startPoint = 0;
    }

    public boolean hasAnotherCard()  // as yet unused, but it might be handy.
    {
        return startPoint<81;
    }

    public int cardsRemaining()  // as yet unused, but it might be handy.
    {
        return 81-startPoint;
    }

    /**
     * equivalent to removing the top card off the deck and giving it to whomever asked for it.
     * @return the "top card" on the deck, or null if the deck is finished.
     */
    public Card dealCard()
    {
       if (startPoint >= 81)
           return null;

       // Note: we aren't actually removing any of the cards, just returning a copy of the top one. Instead, we are
        // changing what point in the deck we are calling the "top" - All cards before startPoint are considered
        // "used up," and all cards at or after startPoint are still "in the deck."
       startPoint++;
       return myCards[startPoint-1];
    }

}
