import javax.swing.*;
import java.awt.*;

/**
 * This class represents a single card
 */
public class Card
{
    private static Image[][][] icons;  // this is static so that I only load this list once and all Cards share the list.
    private final int CARD_WIDTH = 175;
    private final int CARD_HEIGHT = 100;

    // Each of these will be 0, 1, or 2. In the case of myCount, this will actually be one less than the number of icons
    //   on the card -- I've done it that way to keep these four values consistent.
    // They are "final" because you are setting them when you create the Card and the cards don't change.
    private final int myColor, myShape, myFill, myCount;
    // the icon that is used by this particular card. (Just one... it might be drawn several times.)
    private final Image myIcon;


    // Whether the card has been selected by the user - shows up as a red outline when drawn.
    private boolean isSelected;

    public Card(int shape, int color, int fill, int count)
    {
        if (icons == null)
            loadIcons();
        myShape = shape;
        myColor = color;
        myFill  = fill;
        myCount = count;
        myIcon = icons[myShape][myColor][myFill];
        isSelected = false;
    }

    public boolean isSelected()
    {
        return isSelected;
    }

    // Modifier methods for isSelected.
    public void setSelected(boolean b) {isSelected = b;}
    public void select() {setSelected(true);}
    public void deselect() {setSelected(false);}
    public void toggleSelection() {isSelected = !isSelected;}

    // Accessors for shape, color, fill, and count.
    public int getMyShape()
    {
        return myShape;
    }

    public int getMyColor()
    {
        return myColor;
    }

    public int getMyFill()
    {
        return myFill;
    }

    public int getMyCount()
    {
        return myCount;
    }

    // Code to load the image files for all the cards. Should only happen once, at start of program.
    public void loadIcons()
    {
        System.out.println("Loading Icons");
        icons = new Image[3][3][3];
        String[] shapeNames = {"diamond","roundRect","squiggle"};
        for (int shapeNum = 0; shapeNum < 3; shapeNum++)
            for (int colorNum = 0; colorNum < 3; colorNum++)
                for (int fillNum = 0; fillNum < 3; fillNum++)
                {
//                    System.out.println("set icons/" +shapeNames[shapeNum]+"-0"+(colorNum+1)+"-0"+(fillNum+1)+".gif");
                    ImageIcon temp = new ImageIcon("set icons/" +shapeNames[shapeNum]+"-0"+(colorNum+1)+"-0"+(fillNum+1)+".gif");
                    icons[shapeNum][colorNum][fillNum] = temp.getImage();
                }
    }

    /**
     * draws this card in the graphics context g, so that its upper left corner is at x,y.
     * @param g - the graphics context, the portion of the screen to draw and the tools to do so.
     * @param x - the x coordinate of the upper left corner of this card
     * @param y - the y coordinate of the upper left corner of this card
     */
    public void drawSelfAt(Graphics g, int x, int y)
    {
        g.setColor(Color.WHITE);
        g.fillRoundRect(x,y,CARD_WIDTH, CARD_HEIGHT, 20, 20);
        g.setColor(Color.BLACK);
        g.drawRoundRect(x,y,CARD_WIDTH, CARD_HEIGHT, 20, 20);
        if (isSelected)
        {
            g.setColor(Color.RED);
            g.drawRoundRect(x-5,y-5,CARD_WIDTH+10, CARD_HEIGHT+10, 25, 25);

        }

        int centerX = x+CARD_WIDTH/2;
        int centerY = y+CARD_HEIGHT/2;
        int startX = centerX - ((myCount+1)*50/2);
        int startY = centerY - 87/2;
        for (int i=0; i<myCount+1; i++)
            g.drawImage(myIcon,startX+i*50, startY, 50, 87, null);

    }

    @Override
    public String toString()
    {
        return "Card{" +
                "myColor=" + myColor +
                ", myShape=" + myShape +
                ", myFill=" + myFill +
                ", myCount=" + myCount +
                '}';
    }
}
