public class SetRunner
{
    static void main()
    {
        // create the list of 15 slots (initially empty) that hold the cards we'll see onscreen.
        Card[] cardsToDisplay = new Card[15];

        // create the visual parts of the window.
        SetPanel mainPanel = new SetPanel(cardsToDisplay);
        SetFrame window = new SetFrame(mainPanel);

        // create the referee - the one that makes decisions when the user does stuff. It needs to know about the cards
        //     onscreen and the GUI to update if it makes changes.
        SetGame ref = new SetGame(cardsToDisplay, mainPanel, window);

        // now that we have a referee, tell the window and panel about it so they can tell it when the user clicks
        // stuff.
        // I'd have done this in the constructors on lines 9-10, but we didn't make the ref until line 14.
        // This is a chicken - egg situation.
        mainPanel.setReferee(ref);
        window.setReferee(ref);

        // show the window and start letting the user interact with it.
        window.setVisible(true);
    }
}
