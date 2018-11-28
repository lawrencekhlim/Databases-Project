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
    JPanel singleAccountTrans;
    JPanel twoAccountTrans;
    
    JButton depositButton;
    JButton topUpButton;
    JButton withdrawlButton;
    JButton purchaseButton;
    JButton transferButton;
    JButton collectButton;
    JButton wireButton;
    JButton payButton;
    
    int actionStatus = 0;
    
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
                actionStatus = 1;
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
                actionStatus = 0;
                depositButton.setVisible (true);
                topUpButton.setVisible (true);
                withdrawlButton.setVisible (true);
                purchaseButton.setVisible (true);
                transferButton.setVisible (true);
                collectButton.setVisible (true);
                wireButton.setVisible (true);
                payButton.setVisible (true);
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
        
        
        // Beginning singleAccountTrans
        
        singleAccountTrans = new JPanel ();
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
                actionStatus = 1;
                depositButton.setVisible (true);
                topUpButton.setVisible (true);
                withdrawlButton.setVisible (true);
                purchaseButton.setVisible (true);
                transferButton.setVisible (true);
                collectButton.setVisible (true);
                wireButton.setVisible (true);
                payButton.setVisible (true);
            }
        });
        
        JButton confirmButton2 = new JButton ();
        confirmButton2.setText ("Confirm");
        
        singleAccountTrans.add (cancelButton2);
        singleAccountTrans.add (accounts2);
        singleAccountTrans.add (comboBox2);
        singleAccountTrans.add (dollars2);
        singleAccountTrans.add (moneyChanged2);
        singleAccountTrans.add (confirmButton2);
        singleAccountTrans.setVisible (true);
        
        // End singleAccountTrans
        
        
        // Beginning twoAccountTrans
        
        twoAccountTrans = new JPanel ();
        JLabel accounts3 = new JLabel();
        accounts3.setText ("Accounts");
        JComboBox comboBox3 = new JComboBox();
        comboBox3.setMinimumSize (new Dimension (100, 30));
        JButton cancelButton3 = new JButton ();
        JLabel dollars3 = new JLabel ();
        dollars3.setText ("$");
        JTextField moneyChanged3 = new JTextField ();
        moneyChanged3.setPreferredSize(new Dimension(150,25));
        
        JLabel sendTo3 = new JLabel ();
        sendTo3.setText ("Send To");
        JTextField sendToField3 = new JTextField ();
        sendToField3.setPreferredSize(new Dimension(150,25));
        
        cancelButton3.setText ("Cancel");
        cancelButton3.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                ((CardLayout)userInterface.getLayout()).show (userInterface, "idleState");
                actionStatus = 1;
                depositButton.setVisible (true);
                topUpButton.setVisible (true);
                withdrawlButton.setVisible (true);
                purchaseButton.setVisible (true);
                transferButton.setVisible (true);
                collectButton.setVisible (true);
                wireButton.setVisible (true);
                payButton.setVisible (true);
            }
        });
        
        JButton confirmButton3 = new JButton ();
        confirmButton3.setText ("Confirm");
        
        twoAccountTrans.add (cancelButton3);
        twoAccountTrans.add (accounts3);
        twoAccountTrans.add (comboBox3);
        twoAccountTrans.add (dollars3);
        twoAccountTrans.add (moneyChanged3);
        twoAccountTrans.add (sendTo3);
        twoAccountTrans.add (sendToField3);
        twoAccountTrans.add (confirmButton3);
        twoAccountTrans.setVisible (true);
        
        // End twoAccountTrans
        
        
        // Beginning CardLayout
        
        userInterface = new JPanel ();
        userInterface.setLayout (new CardLayout());
        userInterface.setSize (150, 150);
        userInterface.add (screen, "login");
        userInterface.add (idlePanel, "idleState");
        userInterface.add (singleAccountTrans, "singleAccountTrans");
        userInterface.add (twoAccountTrans, "twoAccountTrans");
        
        ((CardLayout)userInterface.getLayout()).show (userInterface, "login");
        // End CardLayout
        
        
        //  Beginning gridButtons

        gridButtons = new JPanel();
        gridButtons.setLayout(new GridLayout(4,2));
        gridButtons.setVisible(false);

        depositButton = new JButton();
        depositButton.setText("Deposit");
        depositButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                ((CardLayout)userInterface.getLayout()).show (userInterface, "singleAccountTrans");
                actionStatus = 2;
                topUpButton.setVisible (false);
                withdrawlButton.setVisible (false);
                purchaseButton.setVisible (false);
                transferButton.setVisible (false);
                collectButton.setVisible (false);
                wireButton.setVisible (false);
                payButton.setVisible (false);
            }
        });
        gridButtons.add(depositButton);

        topUpButton = new JButton();
        topUpButton.setText("Top Up");
        gridButtons.add(topUpButton);
        topUpButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                ((CardLayout)userInterface.getLayout()).show (userInterface, "twoAccountTrans");
                actionStatus = 3;
                depositButton.setVisible (false);
                withdrawlButton.setVisible (false);
                purchaseButton.setVisible (false);
                transferButton.setVisible (false);
                collectButton.setVisible (false);
                wireButton.setVisible (false);
                payButton.setVisible (false);
            }
        });

        withdrawlButton = new JButton();
        withdrawlButton.setText("Withdrawal");
        gridButtons.add(withdrawlButton);
        withdrawlButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                ((CardLayout)userInterface.getLayout()).show (userInterface, "singleAccountTrans");
                actionStatus = 4;
                depositButton.setVisible (false);
                topUpButton.setVisible (false);
                purchaseButton.setVisible (false);
                transferButton.setVisible (false);
                collectButton.setVisible (false);
                wireButton.setVisible (false);
                payButton.setVisible (false);
            }
        });

        purchaseButton = new JButton();
        purchaseButton.setText("Purchase");
        gridButtons.add(purchaseButton);
        purchaseButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                ((CardLayout)userInterface.getLayout()).show (userInterface, "singleAccountTrans");
                actionStatus = 5;
                depositButton.setVisible (false);
                topUpButton.setVisible (false);
                withdrawlButton.setVisible (false);
                transferButton.setVisible (false);
                collectButton.setVisible (false);
                wireButton.setVisible (false);
                payButton.setVisible (false);
            }
        });

        transferButton = new JButton();
        transferButton.setText("Transfer");
        gridButtons.add(transferButton);
        transferButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                ((CardLayout)userInterface.getLayout()).show (userInterface, "twoAccountTrans");
                actionStatus = 6;
                depositButton.setVisible (false);
                topUpButton.setVisible (false);
                withdrawlButton.setVisible (false);
                purchaseButton.setVisible (false);
                collectButton.setVisible (false);
                wireButton.setVisible (false);
                payButton.setVisible (false);
            }
        });

        collectButton = new JButton();
        collectButton.setText("Collect");
        gridButtons.add(collectButton);
        collectButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                ((CardLayout)userInterface.getLayout()).show (userInterface, "twoAccountTrans");
                actionStatus = 7;
                depositButton.setVisible (false);
                topUpButton.setVisible (false);
                withdrawlButton.setVisible (false);
                purchaseButton.setVisible (false);
                transferButton.setVisible (false);
                wireButton.setVisible (false);
                payButton.setVisible (false);
            }
        });
        

        wireButton = new JButton();
        wireButton.setText("Wire");
        gridButtons.add(wireButton);
        wireButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                ((CardLayout)userInterface.getLayout()).show (userInterface, "twoAccountTrans");
                actionStatus = 7;
                depositButton.setVisible (false);
                topUpButton.setVisible (false);
                withdrawlButton.setVisible (false);
                purchaseButton.setVisible (false);
                transferButton.setVisible (false);
                collectButton.setVisible (false);
                payButton.setVisible (false);
            }
        });
        

        payButton = new JButton();
        payButton.setText("Pay Friend");
        gridButtons.add(payButton);
        payButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                ((CardLayout)userInterface.getLayout()).show (userInterface, "twoAccountTrans");
                actionStatus = 8;
                depositButton.setVisible (false);
                topUpButton.setVisible (false);
                withdrawlButton.setVisible (false);
                purchaseButton.setVisible (false);
                transferButton.setVisible (false);
                collectButton.setVisible (false);
                wireButton.setVisible (false);
            }
        });

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
