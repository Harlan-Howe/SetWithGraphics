import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class manages the window (a.k.a. frame) our game appears in.
 */
public class SetFrame extends JFrame implements ActionListener
{
    private SetGame referee;
    private JButton removeButton, deselectButton, deal3MoreButton;
    private JLabel messageLabel;

    public SetFrame(SetPanel mainPanel)
    {
        super("Set"); // sets up the window with default window behavior and sets the title to "Set."
        setSize(650,760);
        setResizable(false);
        buildLayout(mainPanel);  // see this method, below.

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // quits the program when you close the window.
        referee = null;  // we'll update this with setReferee() in a little bit.
    }

    /**
     * breaks up the window's content area into regions and puts stuff int them.
     * @param mainPanel the SetPanel we've been asked to add to this window.
     */
    private void buildLayout(SetPanel mainPanel)
    {
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel();
        removeButton = new JButton("Remove Triplet");
        removeButton.addActionListener(this);
        deselectButton = new JButton("Deselect All");
        deselectButton.addActionListener(this);
        deal3MoreButton = new JButton("Deal 3 more cards");
        deal3MoreButton.addActionListener(this);

        bottomPanel.add(removeButton);
        bottomPanel.add(deselectButton);
        bottomPanel.add(deal3MoreButton);

        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        JPanel topPanel = new JPanel();
        topPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        messageLabel = new JLabel("");
        topPanel.add(messageLabel);
        getContentPane().add(topPanel, BorderLayout.NORTH);
    }

    public void setReferee(SetGame referee)
    {
        this.referee = referee;
    }

    /**
     * updates the message at the top of the window.
     * @param s - the message to display
     */
    public void setMessage(String s)
    {
        messageLabel.setText(s);
        repaint();
    }

    @Override
    /**
     * the user has pressed one of the buttons. Now we need to decide what to do about it.
     */
    public void actionPerformed(ActionEvent e)
    {
        if (referee == null)
            return;   // if we don't have a ref, then there's nobody to tell.
        if (e.getSource() == removeButton)
            referee.handleRemoveButtonPressed();

        if (e.getSource() == deselectButton)
            referee.clearAllSelections();

        if (e.getSource() == deal3MoreButton)
            referee.deal3MoreCards();
    }
}
