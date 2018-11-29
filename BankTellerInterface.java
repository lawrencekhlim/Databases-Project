import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BankTellerInterface extends JFrame implements ActionListener{
    JPanel gridButtons;
    JPanel userInterface;
    JPanel screen;
    JPanel enterCheckPanel;
    JPanel createAcctPanel;
    JPanel delTransPanel;

	public BankTellerInterface() {
        initUI();
    }

    public final void initUI() {
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        setSize(500, 500);

	   JLabel title = new JLabel();
       title.setText("Bank Teller");
       title.setVisible(true);
       
       //screen -----------------------------------------------------
        screen = new JPanel();
        screen.setLayout(new FlowLayout());
        screen.setOpaque(false);
        screen.setBackground(Color.WHITE);
        screen.setSize(150,150);


        JLabel instruction  = new JLabel();
        JTextField userInput  = new JTextField();
        userInput.setVisible(false);
        userInput.setPreferredSize(new Dimension(150,25));
        instruction.setText("Choose an action");
        screen.add(instruction);
        screen.add(userInput);
        //screen.add(submitButton);
        screen.setVisible(true);
        //_-----------------------------------------------------------

        //enterCheckPanel --------------------------------------------
        enterCheckPanel = new JPanel();
        enterCheckPanel.setLayout(new FlowLayout());
        enterCheckPanel.setOpaque(false);
        enterCheckPanel.setBackground(Color.WHITE);
        enterCheckPanel.setSize(150,150);

        JLabel enterCheckLabel  = new JLabel();
        enterCheckLabel.setText("Enter Check Values:");

        enterCheckPanel.add(enterCheckLabel);
        //end enter check panel-----------------------------------------

        //createAcctPanrl --------------------------------------------
        createAcctPanel = new JPanel();
        createAcctPanel.setLayout(new FlowLayout());
        createAcctPanel.setOpaque(false);
        createAcctPanel.setBackground(Color.WHITE);
        createAcctPanel.setSize(150,150);

        JLabel createAcctLabel  = new JLabel();
        createAcctLabel.setText("Create Account:");

        enterCheckPanel.add(createAcctLabel);
        //end create acct panel-----------------------------------------

        //createAcctPanrl --------------------------------------------
        delTransPanel = new JPanel();
        delTransPanel.setLayout(new FlowLayout());
        delTransPanel.setOpaque(false);
        delTransPanel.setBackground(Color.WHITE);
        delTransPanel.setSize(150,150);

        JLabel delTransLabel  = new JLabel();
        delTransLabel.setText("Delete Transaction:");

        delTransPanel.add(delTransLabel);
        //end create acct panel-----------------------------------------




        // Beginning CardLayout-----------------------------------------------------------
        
        userInterface = new JPanel ();
        userInterface.setLayout (new CardLayout());
        userInterface.setSize (150, 150);
        userInterface.add (screen, "idleState");
        userInterface.add (enterCheckPanel, "enterCheckPanel");
        userInterface.add (createAcctPanel, "createAcctPanel");
        userInterface.add (delTransPanel, "delTransPanel");

        
        ((CardLayout)userInterface.getLayout()).show (userInterface, "idleState");
        // End CardLayout---------------------------------------------------------------------



        // Beginning GridButton-----------------------------------------------------------
        JPanel gridButtons = new JPanel();
        gridButtons.setLayout(new GridLayout(3,3));
        gridButtons.setVisible(true);


        JButton checkTransButton = new JButton();
        checkTransButton.setText("Enter Check Transaction");
        gridButtons.add(checkTransButton);
        checkTransButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                ((CardLayout)userInterface.getLayout()).show (userInterface, "enterCheckPanel");
                //actionStatus = 4;
                System.out.println("here");
            }
        });

        JButton genMSButton = new JButton();
        genMSButton.setText("Generate Monthly Statement");
        gridButtons.add(genMSButton);

        JButton listClosedButton = new JButton();
        listClosedButton.setText("List Closed Accounts");
        gridButtons.add(listClosedButton);

        JButton genDTERButton = new JButton();
        genDTERButton.setText("Generate DTER");
        gridButtons.add(genDTERButton);

        JButton customerReportButton = new JButton();
        customerReportButton.setText("Customer Report");
        gridButtons.add(customerReportButton);

        JButton addInterestButton = new JButton();
        addInterestButton.setText("Add Interest");
        gridButtons.add(addInterestButton);

        JButton createAcctButton = new JButton();
        createAcctButton.setText("Create Account");
        gridButtons.add(createAcctButton);

        JButton delAcctButton = new JButton();
        delAcctButton.setText("Delete Closed Accounts and Customers");
        gridButtons.add(delAcctButton);

        JButton delTransButton = new JButton();
        delTransButton.setText("Delete Transactions");
        gridButtons.add(delTransButton); 
        //end grid buttons

        getContentPane().add(title);
        getContentPane().add(gridButtons);
        getContentPane().add(screen);
  
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
		public void actionPerformed(ActionEvent e){
				System.out.println("Oui!");
		}
    public static void main(String[] args) {
        BankTellerInterface ex = new BankTellerInterface();
        ex.setVisible(true);
    }
}