import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//transaction types
//0 - deposit
//1 - top up
//2 - withdrawal
//3 -  purchase
//4 - transfer
//5 - collect
//6 - pay friend
//7 - wire
//8 - write-check
//9 - accrue interest
//10 - fees

public class ATMInterface extends JFrame implements ActionListener {
    JPanel gridButtons;
    JPanel userInterface;
    JPanel idlePanel;
    JPanel deposit;
    
    public ATMInterface() {
        initUI();
    }

    public void initUI() {
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        setSize(500, 500);
        
        // Beginning login screen
        
        JPanel screen = new JPanel();
        screen.setLayout(new FlowLayout());
        screen.setOpaque(false);
        screen.setBackground(Color.WHITE);
        screen.setSize(150,150);
        
        JButton submitButton = new JButton();
        submitButton.setText("Submit");
        submitButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                gridButtons.setVisible(true);
                ((CardLayout)userInterface.getLayout()).show (userInterface, "idleState");
            }
        });
        
        JLabel instruction  = new JLabel();
        JTextField userInput  = new JTextField();
        userInput.setPreferredSize(new Dimension(150,25));
        instruction.setText("Enter Pin: ");
        
        screen.add(instruction);
        screen.add(userInput);
        screen.add(submitButton);
        screen.setVisible(true);
        
        // End login
        
        
        // Beginning idle panel
        
        idlePanel = new JPanel ();
        JButton logout = new JButton ();
        logout.setText ("Log out");
        logout.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                gridButtons.setVisible(false);
                ((CardLayout)userInterface.getLayout()).show (userInterface, "login");
            }
        });
        
        
        idlePanel.add (logout);
        JLabel accounts = new JLabel();
        accounts.setText ("Accounts");
        JComboBox comboBox = new JComboBox();
        comboBox.setMinimumSize (new Dimension (100, 30));
        idlePanel.add (accounts);
        idlePanel.add (comboBox);
        idlePanel.setVisible (true);
        
        // End idle panel
        
        
        // Beginning Deposit
        
        deposit = new JPanel ();
        JLabel accounts2 = new JLabel();
        accounts2.setText ("Accounts");
        JComboBox comboBox2 = new JComboBox();
        comboBox2.setMinimumSize (new Dimension (100, 30));
        JButton cancelButton2 = new JButton ();
        JLabel dollars2 = new JLabel ();
        dollars2.setText ("$");
        JTextField moneyChanged2 = new JTextField ();
        moneyChanged2.setPreferredSize(new Dimension(150,25));
        
        cancelButton2.setText ("Cancel");
        cancelButton2.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                ((CardLayout)userInterface.getLayout()).show (userInterface, "idleState");
            }
        });
        
        JButton confirmButton2 = new JButton ();
        confirmButton2.setText ("Confirm");
        
        deposit.add (cancelButton2);
        deposit.add (accounts2);
        deposit.add (comboBox2);
        deposit.add (dollars2);
        deposit.add (moneyChanged2);
        deposit.add (confirmButton2);
        deposit.setVisible (true);
        
        // End Deposit
        
        //
        
        
        //
        
        
        // Beginning CardLayout
        
        userInterface = new JPanel ();
        userInterface.setLayout (new CardLayout());
        userInterface.setSize (150, 150);
        userInterface.add (screen, "login");
        userInterface.add (idlePanel, "idleState");
        userInterface.add (deposit, "deposit");
        
        ((CardLayout)userInterface.getLayout()).show (userInterface, "login");
        // End CardLayout
        
        
        //  Beginning gridButtons

        gridButtons = new JPanel();
        gridButtons.setLayout(new GridLayout(4,2));
        gridButtons.setVisible(false);

        JButton depositButton = new JButton();
        depositButton.setText("Deposit");
        depositButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                ((CardLayout)userInterface.getLayout()).show (userInterface, "deposit");
            }
        });
        gridButtons.add(depositButton);

        JButton topDownButton = new JButton();
        topDownButton.setText("Top Down");
        gridButtons.add(topDownButton);

        JButton withdrawlButton = new JButton();
        withdrawlButton.setText("Withdraw");
        gridButtons.add(withdrawlButton);

        JButton purchaseButton = new JButton();
        purchaseButton.setText("Purchase");
        gridButtons.add(purchaseButton);

        JButton transferButton = new JButton();
        transferButton.setText("Transfer");
        gridButtons.add(transferButton);

        JButton collectButton = new JButton();
        collectButton.setText("Collect");
        gridButtons.add(collectButton);

        JButton wireButton = new JButton();
        wireButton.setText("Wire");
        gridButtons.add(wireButton);


        JButton payButton = new JButton();
        payButton.setText("Pay Friend");
        gridButtons.add(payButton);

        // End gridButtons

       
        // Beginning title
        
        JLabel title = new JLabel();
        title.setText("ATM");
        title.setVisible(true);
       
        // End title
        
        

        getContentPane().add(title);
        getContentPane().add(userInterface);
        getContentPane().add(gridButtons);

        
    
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e){
        System.out.println("Oui!");
    }
    
    public static void main(String[] args) {
        ATMInterface ex = new ATMInterface();
        ex.setVisible(true);
    }
}
