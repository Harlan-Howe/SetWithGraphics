/**
 *  This class is the one that handles the game logic of our game - i.e., it is the one that makes the important
 *  decisions about the rules of the game. I often refer to it as the "Referee."
 */
public class SetGame
{
    // Member variables. These three are "final" because after the constructor their memory addresses won't be changed.
    //                   (Their contents might change, but they won't become new variables.)
    private final Card[] cardsToDisplay; // List of 15 cards on screen, some of which may be empty (null)

    private final Deck sourceDeck; // the deck of cards from which we will draw to add to the cards on the screen.

    private final SetPanel GUI; // the graphical user interface that shows all this.
    private final SetFrame window;

    public SetGame(Card[] cardsToDisplay, SetPanel gui, SetFrame frame)
    {
        this.cardsToDisplay = cardsToDisplay;
        GUI = gui;
        window = frame;
        sourceDeck = new Deck();
        dealFirst12Cards();
        window.setMessage("Welcome to Set.");
    }

    /**
     * called at the start of the game, this deals out 12 cards into spots 0-11.
     */
    public void dealFirst12Cards()
    {
        // I've written this one for you.
        for (int i=0; i<12; i++)
            cardsToDisplay[i] = sourceDeck.dealCard();
        GUI.repaint();
    }

    /**
     * for each non-null card shown on screen, tell it to deselect. Then tell the GUI to repaint.
     * Note: this is called if the user presses the deselect button.
     */
    public void clearAllSelections()
    {
        for (int i = 0; i<15; i++)
            if (cardsToDisplay[i] != null)
                cardsToDisplay[i].deselect();
        GUI.repaint();
    }

    /**
     * The user has clicked on a card, so (potentially) change its selection status with toggleSelection().
     * @param whichCard - the index (0-14) of the cardsToDisplay for the card the user clicked.
     */
    public void requestToggleCardSelection(int whichCard)
    {
        cardsToDisplay[whichCard].toggleSelection();

        // TODO - Optional, after you've done the core stuff: Only allow a maximum of 3 selected cards.

        // TODO - Optional, after you've done the core stuff: if you have exactly two cards selected - update the
        //  window's message with a description of what the third card would have to be to be a set.

        // the following will tell the screen to update - otherwise the change will only happen in memory.
        GUI.repaint();
    }

    /**
     * the user has pressed the Remove button. If there are three cards selected that make a set, remove all three. If
     * there are now fewer than 12 cards, deal 3 more.
     */
    public void handleRemoveButtonPressed()
    {
        // TODO - required:  replace the following line with code to implement this behavior.
        window.setMessage("Remove button pressed!");
    }

    /**
     * if there are fewer than 15 non-null cards displayed, and the deck has more cards to deal,
     * add three more cards to the cardsToDisplay, replacing spots
     * with "null" cards. If there are already 15 non-null cards or the deck is empty, do nothing.
     */
    public void deal3MoreCards()
    {
        // TODO - Required: you write this.
        // hint: look at the dealFirst12Cards() method, above.

        return; // replace with your code
    }

    /**
     * Determines whether the three cards given (a, b, c) are a set or not. Hint: there is a neat
     * math trick for this... see Mr. Howe if you are stumped.
     * @param a - a card
     * @param b - another card
     * @param c - another-another card
     * @return - whether these three cards make a set.
     * Throws an exception if two cards are the same or if any are null.
     */
    public boolean isASet(Card a, Card b, Card c)
    {
        if (a==b || b==c || a==c)
            throw new RuntimeException("Attempted to check for a set but two cards were identical.\n"+a+"\n"+b+"\n"+c);
        if ((a==null) || (b==null) || (c == null))
            throw new RuntimeException("Attempted to check for a set, but one card was null.\n"+a+"\n"+b+"\n"+c);
        // TODO - recommended: - write this method.

        /* hint: consider just one feature (0, 1, or 2), instead of all four.
        write down 4 card combinations: 4 groups of 3 values of (0, 1, or 2 for each) that DON'T make a set.
        write down 4 card combinations: 4 groups of 3 values of (0, 1, or 2 for each) that DO make a set.
        for each combination, find the total of its three values.
        Do you see a pattern? Can you extend this to a rule that will help you identify all combinations?
        Can you extend that to all four features?
         */
        return false;  // replace this with your code.
    }

    /**
     * overloads isASet(Card,Card,Card) to handle a short array of 3 Cards.
     * This is a "convenience function" -- just another way to call this method.
     * @param threeCards - an array of 3 cards
     * @return - whether these three cards are a set. Throws an exception if the list doesn't have three cards.
     */
    public boolean isASet(Card[] threeCards)
    {
        // This just calls the other version of isASet, so I've written it for you.
        if (threeCards.length != 3)
            throw new RuntimeException("Attempted to check for a set with an array of cards of length " +
                    threeCards.length+".");
        return isASet(threeCards[0],threeCards[1],threeCards[2]);
    }

    public int numSelectedCards()
    {
        // TODO - Recommended: count the number of non-null cards that are currently selected.
        return -1; // replace this with your code.
    }

    /**
     * If there are exactly 3 cards selected (presumably because the user has found a set), remove them from the board.
     */
    public void remove3SelectedCards()
    {
        // TODO - Recommended: "remove" the cards by setting their positions to null in cardsToDisplay.
    }

    /**
     * Assuming you have exactly 3 non-null cards on the board selected, returns an array with the indices of
     * those three cards.
     * @return - an array of three indices (int's) corresponding to which three cards are selected.
     */
    public int[] which3AreSelected()
    {
        int[] result = new int[3];
        // TODO - Recommended: you write this.
        // hint: careful - some cards might be null.
        return result;
    }
}

