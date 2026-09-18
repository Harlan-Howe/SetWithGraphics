import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * This class represents a portion of the window, specifically a custom area showing our cards.
 */
public class SetPanel extends JPanel implements MouseListener
{
    // constants
    public static final int MARGIN = 20;
    public static final int VERTICAL_SPACING = 125;
    public static final int HORIZONTAL_SPACING = 200;
    public static final int CARDS_PER_ROW = 3;

    private final boolean SHOW_CARD_NUMBERS = true; // handy for debugging.


    // variables
    private final Card[] cardsToDisplay;
    private SetGame referee; // a reference to the class making the decisions about the game, so we can tell it when the
                             //  user clicks on a card.


    public SetPanel(Card[] cards)
    {
        super();
        cardsToDisplay = cards;
        referee = null;
        addMouseListener(this);
    }

    public void setReferee(SetGame referee)
    {
        this.referee = referee;
    }

    /**
     * draws this pane of the window - basically refreshes the view of the cards. Never call this directly - instead,
     * call repaint(), which will call this method the next time it is convenient, usually in a few milliseconds.
     * @param g - the graphics context containing what portion of the screen to draw in, and the tools to do so.
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (cardsToDisplay != null)
        {
            for (int i=0; i<cardsToDisplay.length; i++)
            {
                int x = MARGIN + (i % CARDS_PER_ROW)*HORIZONTAL_SPACING;
                int y = MARGIN + (i / CARDS_PER_ROW)*VERTICAL_SPACING;
                if (cardsToDisplay[i] != null)
                    cardsToDisplay[i].drawSelfAt(g,x,y);
                else
                {   // draw the dotted lines for empty cells. (fancy)
                    g.setColor(Color.GRAY);
                    Stroke plain = ((Graphics2D)g).getStroke();
                    float[] pattern = {8.0f, 8.0f};
                    Stroke dotted = new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, pattern, 0);
                    ((Graphics2D)g).setStroke(dotted);
                    g.drawRoundRect(x,y,175,100,20,20);
                    ((Graphics2D)g).setStroke(plain);
                }
                if (SHOW_CARD_NUMBERS)
                {
                    g.setColor(new Color(200, 112, 11));
                    g.drawString("" + i, x + 7, y + 20);
                }
            }
        }
    }

    /**
     * The user has clicked the screen at (x, y). Decide what to do about this.
     * Note: this method is called by mouseReleased(), below.
     * @param x - the x location (in pixels) of this click within this panel
     * @param y - the y location (in pixels) of this click within this panel
     */
    public void handleMouseClickAt(int x, int y)
    {

        int row = (y- MARGIN)/ VERTICAL_SPACING;  // using int division
        int col = (x- MARGIN)/ HORIZONTAL_SPACING;  // using int division

        int whichCard = CARDS_PER_ROW * row + col;

        if (cardsToDisplay[whichCard] != null)
            referee.requestToggleCardSelection(whichCard);
        repaint();
    }

    // ----------------------------------------------------------------------------------------------------------------
    // everything below this point is required to track the user's mouse clicks, though not all do anything.
    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        handleMouseClickAt(e.getX(), e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}
