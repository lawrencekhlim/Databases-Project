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
    
    JButton checkTransButton;
    JButton genMSButton;
    JButton listClosedButton;
    JButton genDTERButton;
    JButton customerReportButton;
    JButton addInterestButton;
    JButton createAcctButton;
    JButton delAcctButton;
    JButton delTransButton;
    
    JTextField accountNumberTextField1;
    JTextField enterCheckTextField1;
    
    JTextField enterCustomerTextField2;
    

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
        /*
        enterCheckPanel.setOpaque(false);
        enterCheckPanel.setBackground(Color.WHITE);
        enterCheckPanel.setSize(150,150);
        */
        JButton cancel1 = new JButton ("Cancel");
        cancel1.addActionListener ( new ActionListener (){
            public void actionPerformed (ActionEvent e) {
                idleView();
            }
        });
        JLabel accountNumberLabel1 = new JLabel ("Account Number");
        accountNumberTextField1 = new JTextField ();
        accountNumberTextField1.setPreferredSize(new Dimension(150,25));
        
        JLabel enterCheckLabel1  = new JLabel();
        enterCheckLabel1.setText("Check Value: $");
        enterCheckTextField1 = new JTextField();
        enterCheckTextField1.setPreferredSize(new Dimension(150,25));
        
        JButton submit1 = new JButton ("Submit");
        submit1.addActionListener ( new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                idleView();
            }
        });
        
        enterCheckPanel.add(cancel1);
        enterCheckPanel.add(accountNumberLabel1);
        enterCheckPanel.add(accountNumberTextField1);
        enterCheckPanel.add(enterCheckLabel1);
        enterCheckPanel.add(enterCheckTextField1);
        enterCheckPanel.add(submit1);
        
        //end enter check panel-----------------------------------------

        //createAcctPanel --------------------------------------------
        
        createAcctPanel = new JPanel();
        createAcctPanel.setLayout(new FlowLayout());
        /*
        createAcctPanel.setOpaque(false);
        createAcctPanel.setBackground(Color.WHITE);
        createAcctPanel.setSize(150,150);
        */
        JButton cancel2 = new JButton ("Cancel");
        cancel2.addActionListener ( new ActionListener (){
            public void actionPerformed (ActionEvent e) {
                idleView();
            }
        });
        
        JLabel createAcctLabel2  = new JLabel();
        createAcctLabel2.setText("Customer TID");
        enterCustomerTextField2 = new JTextField();
        enterCustomerTextField2.setPreferredSize(new Dimension(150,25));
        JButton submit2 = new JButton ("Submit");
        submit2.addActionListener ( new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                idleView();
            }
        });
        createAcctPanel.add(cancel2);
        createAcctPanel.add(createAcctLabel2);
        createAcctPanel.add(enterCustomerTextField2);
        createAcctPanel.add(submit2);
        //end create acct panel-----------------------------------------

        
        //delTransPanrl --------------------------------------------
        
        delTransPanel = new JPanel();
        delTransPanel.setLayout(new FlowLayout());
        /*
        delTransPanel.setOpaque(false);
        delTransPanel.setBackground(Color.WHITE);
        delTransPanel.setSize(150,150);
        */
        
        JLabel delTransLabel  = new JLabel();
        delTransLabel.setText("Delete Transaction:");

        delTransPanel.add(delTransLabel);
        
        //delTransPanel-----------------------------------------




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


        checkTransButton = new JButton();
        checkTransButton.setText("Enter Check Transaction");
        gridButtons.add(checkTransButton);
        checkTransButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                ((CardLayout)userInterface.getLayout()).show (userInterface, "enterCheckPanel");
                setGridButtonsVisible (false);
                checkTransButton.setVisible (true);
            }
        });

        genMSButton = new JButton();
        genMSButton.setText("Generate Monthly Statement");
        gridButtons.add(genMSButton);
        genMSButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {

            }
        });

        listClosedButton = new JButton();
        listClosedButton.setText("List Closed Accounts");
        gridButtons.add(listClosedButton);
        listClosedButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                
            }
        });

        genDTERButton = new JButton();
        genDTERButton.setText("Generate DTER");
        gridButtons.add(genDTERButton);
        genDTERButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                
            }
        });

        customerReportButton = new JButton();
        customerReportButton.setText("Customer Report");
        gridButtons.add(customerReportButton);
        customerReportButton.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                
            }
        });

        addInterestButton = new JButton();
        addInterestButton.setText("Add Interest");
        gridButtons.add(addInterestButton);
        addInterestButton.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                
            }
        });

        createAcctButton = new JButton();
        createAcctButton.setText("Create Account");
        gridButtons.add(createAcctButton);
        createAcctButton.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                ((CardLayout)userInterface.getLayout()).show (userInterface, "createAcctPanel");
                setGridButtonsVisible (false);
                createAcctButton.setVisible (true);
            }
        });

        delAcctButton = new JButton();
        delAcctButton.setText("Delete Closed Accounts and Customers");
        gridButtons.add(delAcctButton);
        delAcctButton.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                
            }
        });

        delTransButton = new JButton();
        delTransButton.setText("Delete Transactions");
        gridButtons.add(delTransButton);
        delTransButton.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                
            }
        });
        //end grid buttons

        getContentPane().add(title);
        getContentPane().add(userInterface);
        getContentPane().add(gridButtons);
  
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e){
            System.out.println("Oui!");
    }
    
    public void setGridButtonsVisible (boolean visible) {
        checkTransButton.setVisible (visible);
        genMSButton.setVisible (visible);
        listClosedButton.setVisible (visible);
        genDTERButton.setVisible (visible);
        customerReportButton.setVisible (visible);
        addInterestButton.setVisible (visible);
        createAcctButton.setVisible (visible);
        delAcctButton.setVisible (visible);
        delTransButton.setVisible (visible);
    }
    
    public void idleView () {
        ((CardLayout)userInterface.getLayout()).show (userInterface, "idleState");
        setGridButtonsVisible(true);
    }
    
    public static void main(String[] args) {
        BankTellerInterface ex = new BankTellerInterface();
        ex.setVisible(true);
    }
}
