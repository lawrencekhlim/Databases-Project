import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
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
    Customer loggedCust = null;
    
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

        JLabel instruction  = new JLabel();
        JTextField userInput  = new JTextField();
        userInput.setPreferredSize(new Dimension(150,25));
        instruction.setText("Enter Pin: ");

        
        JButton submitButton = new JButton();
        submitButton.setText("Submit");
        submitButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                System.out.println("here");
                //gridButtons.setVisible(true);
                // ((CardLayout)userInterface.getLayout()).show (userInterface, "idleState");
                String pinID =  userInput.getText();
                System.out.println(pinID);
                ArrayList<String> allPins = new ArrayList<>();
                allPins = Customer.getAllPins();

                for(int i =0; i<allPins.size(); i++)
                {
                    if(allPins.get(i).equals(pinID))
                    {
                        System.out.println("We have a mach!");
                        loggedCust = new Customer(pinID);
                        System.out.print(loggedCust.getName());
                        gridButtons.setVisible(true);
                        idleView();
                    }
                }

                if(loggedCust==null)
                {
                    JOptionPane.showMessageDialog(null, "ERROR! Pin is not linked to an account.");

                }
            }
        });
        
      
        
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
                idleView();
            }
        });
        
        JButton confirmButton2 = new JButton ();
        confirmButton2.setText ("Confirm");
        confirmButton2.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                String acct = comboBox2.getSelectedItem().toString();
                String m = moneyChanged2.getText();
                   
                java.sql.Date date = new java.sql.Date( java.lang.System.currentTimeMillis());
                if(actionStatus==2)
                {
                    //deposit:Add money to the checking or savings account balance.
                    if( m==null || acct==null)
                    {
                        JOptionPane.showMessageDialog(null, "ERROR! Invalid Information.");
                        
                    }

                    
                    Transaction t = null;
                    try{
                        t = new Transaction(date,Float.parseFloat(m), 0,Integer.parseInt(acct),-1);
                    } catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "ERROR! Invalid Format.");
                       
                    }
                    boolean valid  = t.createTransaction();
                    if(!valid)
                    {
                        JOptionPane.showMessageDialog(null, "ERROR! Invalid Operation.");
                     
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "SUCCESS");
                          
                    }

                }
                else if(actionStatus==4)
                {

                    //withdrawl:subtract money to the checking or savings account balance.
                    if( m==null || acct==null)
                    {
                        JOptionPane.showMessageDialog(null, "ERROR! Invalid Information.");
                       
                    }

                    
                    Transaction t = null;
                    try{
                        t = new Transaction(date,Float.parseFloat(m), 2,-1,Integer.parseInt(acct));

                    } catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "ERROR! Invalid Format.");
                        
                    }
                    boolean valid  = t.createTransaction();
                    if(!valid)
                    {
                        JOptionPane.showMessageDialog(null, "ERROR! Invalid Operation.");
                        
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "SUCCESS");
                          
                    }
                }
                else if(actionStatus==5)
                {
                    //Purchase: Subtract money from the pocket account balance.
                    if(m==null || acct==null)
                    {
                        JOptionPane.showMessageDialog(null, "ERROR! Invalid Information.");
                        
                    }
                    Transaction t = null;
                    try{
                        t = new Transaction(date,Float.parseFloat(m), 3,-1,Integer.parseInt(acct));

                    } catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "ERROR! Invalid Format.");
                        
                    }
                    
                    boolean valid  = t.createTransaction();
                    if(!valid)
                    {
                        JOptionPane.showMessageDialog(null, "ERROR! Invalid Operation.");
                        
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "SUCCESS");
                          
                    }

                   
                }
                 idleView();

            }
        });
        
        singleAccountTrans.add (cancelButton2);
        singleAccountTrans.add (accounts2);
        singleAccountTrans.add (comboBox2);
        singleAccountTrans.add (dollars2);
        singleAccountTrans.add (moneyChanged2);
        singleAccountTrans.add (confirmButton2);
        singleAccountTrans.setVisible (true);
        
        // End singleAccountTrans
        
        
        // Beginning twoAccountTrans-------------------------------------------------------------------------
        
        twoAccountTrans = new JPanel ();
        JLabel accounts3 = new JLabel();
        accounts3.setText ("Accounts");
        JComboBox comboBox3 = new JComboBox();
        comboBox3.setMinimumSize (new Dimension (200, 30));
        JButton cancelButton3 = new JButton ();
        JLabel dollars3 = new JLabel ();
        dollars3.setText ("$");
        JTextField moneyChanged3 = new JTextField ();
        moneyChanged3.setPreferredSize(new Dimension(150,25));


        JComboBox sendToDropDown = new JComboBox();
        sendToDropDown.setMinimumSize (new Dimension (200, 30));
        
        JLabel sendTo3 = new JLabel ();
        sendTo3.setText ("Send To");

        JTextField sendToField3 = new JTextField ();
        sendToField3.setPreferredSize(new Dimension(150,25));
        
        cancelButton3.setText ("Cancel");
        cancelButton3.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                idleView ();

            }
        });
        
        JButton confirmButton3 = new JButton ();
        confirmButton3.setText ("Confirm");
        confirmButton3.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                ((CardLayout)userInterface.getLayout()).show (userInterface, "idleState");
                if(actionStatus==3)
                {
                    //top up: move money from linked checking/saving into pocket acct
                    String fromAcct = comboBox3.getSelectedItem().toString();
                    String toAcct = sendToDropDown.getSelectedItem().toString();
                    String m = moneyChanged3.getText();
                   
                    java.sql.Date date = new java.sql.Date( java.lang.System.currentTimeMillis());

                    if(fromAcct==null || m==null || toAcct==null)
                    {
                        JOptionPane.showMessageDialog(null, "ERROR! Invalid Information.");
                        
                    }

                    Transaction t = null;
                    try{
                        t = new Transaction(date,Float.parseFloat(m), 1,Integer.parseInt(toAcct),Integer.parseInt(fromAcct));
                    }
                    catch(NumberFormatException ex){

                    }

                    
                    boolean valid  = t.createTransaction();
                    if(!valid)
                    {
                        JOptionPane.showMessageDialog(null, "ERROR! Invalid Operation.");
                        
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "SUCCESS");
                         
                    }

                }
                else if(actionStatus==6)
                {
                    //transfer: move money from checking/savings to another checking/savings
                    //must have atleast one owner in common, cannot exced over 2000 dollars

                    //TODO: just a ? doesn't every bank transaction have to have an owner in common?? or are we transferring from my checking
                    //to someone elses checking... how is this diff from pay friend then? and how is it allowed?
                    String fromAcct = comboBox3.getSelectedItem().toString();
                    String toAcct = sendToDropDown.getSelectedItem().toString();
                    String m = moneyChanged3.getText();
                   
                    java.sql.Date date = new java.sql.Date( java.lang.System.currentTimeMillis());

                    if(fromAcct==null || m==null || toAcct==null)
                    {
                        JOptionPane.showMessageDialog(null, "ERROR! Invalid Information.");
                        
                    }

                    Transaction t = null;
                    try{
                         t = new Transaction(date,Float.parseFloat(m), 4,Integer.parseInt(toAcct),Integer.parseInt(fromAcct));
                    }catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "ERROR! Invalid Format.");
                        
                    }
                   
                    boolean valid  = t.createTransaction();
                    if (Float.parseFloat(m)>2000){
                        JOptionPane.showMessageDialog(null, "ERROR! Money transferred cannot be above $2000.");
                        
                    }
                    if(!valid)
                    {
                        JOptionPane.showMessageDialog(null, "ERROR! Invalid Operation.");
                        
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "SUCCESS");
                        
                    }
                }
                else if(actionStatus==7)
                {
                    //collect: Move money from the pocket account back to the linked checking/savings account, 
                    //there will be a 3% fee assessed.
                    String fromAcct = comboBox3.getSelectedItem().toString();
                    String toAcct = sendToDropDown.getSelectedItem().toString();
                    String m = moneyChanged3.getText();

                    java.sql.Date date = new java.sql.Date( java.lang.System.currentTimeMillis());
                    if(fromAcct==null || m==null || toAcct==null)
                    {
                        JOptionPane.showMessageDialog(null, "ERROR! Invalid Information.");
                        
                    } 

                      Transaction fee = null;
                    Transaction t = null;
                    float minMoney = 0;
                     try{
                     fee = new Transaction(date,(float)(Float.parseFloat(m)*0.03), 10,Integer.parseInt(toAcct),Integer.parseInt(fromAcct));
                        t = new Transaction(date,Float.parseFloat(m), 5,Integer.parseInt(toAcct),Integer.parseInt(fromAcct));   
                     minMoney = (float)(new Account(Integer.parseInt(fromAcct))).getMoney();
                   
                    }catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "ERROR! Invalid Format.");
                        
                    }
                    boolean valid = false;

                    if(t!=null && fee!=null)
                    {
                        valid  = t.createTransaction() && fee.createTransaction();
                    }
                    if(!valid || minMoney<(float)(Float.parseFloat(m)*1.03))
                    {
                        JOptionPane.showMessageDialog(null, "ERROR! Invalid Operation.");
                       
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "SUCCESS");
                          
                    }

                }
                else if(actionStatus==8)
                {
                    //wire: move $ from savings or checking account and add it to another.
                    //customer must be owner + 2% fee

                    String fromAcct = comboBox3.getSelectedItem().toString();
                    String toAcct = sendToDropDown.getSelectedItem().toString();
                    String m = moneyChanged3.getText();
                   
                    java.sql.Date date = new java.sql.Date( java.lang.System.currentTimeMillis());

                    if(fromAcct==null || m==null || toAcct==null)
                    {
                        JOptionPane.showMessageDialog(null, "ERROR! Invalid Information.");
                        
                    } 

                    Transaction fee = null;
                    Transaction t = null;
                    float minMoney = 0;
                     try{
                     fee = new Transaction(date,(float)(Float.parseFloat(m)*0.02), 10,Integer.parseInt(toAcct),Integer.parseInt(fromAcct));
                        t = new Transaction(date,Float.parseFloat(m), 7,Integer.parseInt(toAcct),Integer.parseInt(fromAcct));   
                     minMoney = (float)(new Account(Integer.parseInt(fromAcct))).getMoney();
                   
                    }catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "ERROR! Invalid Format.");
                        
                    }
                    boolean valid = false;

                    if(t!=null && fee!=null)
                    {
                        valid  = t.createTransaction() && fee.createTransaction();
                    }
                   
                    if(!valid || minMoney<(float)(Float.parseFloat(m)*1.02))
                    {
                        JOptionPane.showMessageDialog(null, "ERROR! Invalid Operation.");
                        
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "SUCCESS");
                          
                    }


                }
                else if(actionStatus==9)
                {
                    //payfriend: move from one pocket acct to another pocket acct
                    String fromAcct = comboBox3.getSelectedItem().toString();
                    String toAcct = sendToField3.getText();
                    String m = moneyChanged3.getText();

                    java.sql.Date date = new java.sql.Date( java.lang.System.currentTimeMillis());

                    if(fromAcct==null || m==null || toAcct==null)
                    {
                        JOptionPane.showMessageDialog(null, "ERROR! Invalid Information.");
                        
                    } 
                    Transaction t = null;
                    try{
                         t = new Transaction(date,Float.parseFloat(m), 6,Integer.parseInt(toAcct),Integer.parseInt(fromAcct));   

                    }catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "ERROR! Invalid Format.");
                       
                    }

                   
                    boolean valid  = t.createTransaction();
                    if(!valid)
                    {
                        JOptionPane.showMessageDialog(null, "ERROR! Invalid Operation.");
                      
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "SUCCESS");
                         
                    }


                }
                else
                {
                    JOptionPane.showMessageDialog(null, "ERROR! Invalid Information.");
                }
                idleView();
            }


        });
        
        twoAccountTrans.add (cancelButton3);
        twoAccountTrans.add (accounts3);
        twoAccountTrans.add (comboBox3);
        twoAccountTrans.add (dollars3);
        twoAccountTrans.add (moneyChanged3);
        twoAccountTrans.add (sendTo3);
        twoAccountTrans.add (sendToField3);
        twoAccountTrans.add (sendToDropDown);
        twoAccountTrans.add (confirmButton3);
        twoAccountTrans.setVisible (true);
        
        // End twoAccountTrans-----------------------------------------------------------------------------------------
        
        
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

        //depost: Add money to the checking or savings account balance.
        depositButton = new JButton();
        depositButton.setText("Deposit");
        depositButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                ((CardLayout)userInterface.getLayout()).show (userInterface, "singleAccountTrans");
                actionStatus = 2;
                setGridButtonsVisible (false);
                depositButton.setVisible (true);

                ArrayList<Account> allCheckingSavingAccts  = new ArrayList<>();
                allCheckingSavingAccts.addAll(loggedCust.getAccountOfType(0));
                allCheckingSavingAccts.addAll(loggedCust.getAccountOfType(1));
                allCheckingSavingAccts.addAll(loggedCust.getAccountOfType(2));

                ArrayList<Integer> allAcctIDs =  new ArrayList<>();
                for(int i =0; i<allCheckingSavingAccts.size(); i++)
                {
                    //System.out.println("we in: "+allCheckingSavingAccts.get(i).getAccountID());
                    allAcctIDs.add(allCheckingSavingAccts.get(i).getAccountID());
                }
                comboBox2.setModel(new DefaultComboBoxModel(allAcctIDs.toArray()));

            }
        });
        gridButtons.add(depositButton);


        //TOPUP: move money from linked checking/saving into pocket acct
        topUpButton = new JButton();
        topUpButton.setText("Top Up");
        gridButtons.add(topUpButton);
        topUpButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                ((CardLayout)userInterface.getLayout()).show (userInterface, "twoAccountTrans");
                actionStatus = 3;
                sendToField3.setVisible(false);
                setGridButtonsVisible (false);
                topUpButton.setVisible (true);
                sendToDropDown.setVisible(true);

                //TODO: so nothing works right now, but whenever we get all the account stuff to work,
                // we need to write better queries for differentiating between types for coOwner stuff
                //as well
                //POPULATION IN THE NATION
                ArrayList<Account> allCheckingSavingAccts  = new ArrayList<>();
                allCheckingSavingAccts.addAll(loggedCust.getAccountOfType(0));
                allCheckingSavingAccts.addAll(loggedCust.getAccountOfType(1));
                allCheckingSavingAccts.addAll(loggedCust.getAccountOfType(2));

                ArrayList<Integer> allAcctIDs =  new ArrayList<>();
                for(int i =0; i<allCheckingSavingAccts.size(); i++)
                {
                    //System.out.println("we in: "+allCheckingSavingAccts.get(i).getAccountID());
                    allAcctIDs.add(allCheckingSavingAccts.get(i).getAccountID());
                }
                comboBox3.setModel(new DefaultComboBoxModel(allAcctIDs.toArray()));

                 ArrayList<Integer> pocketAcctIDs  = new ArrayList<>();
                 ArrayList<Account> pocketAccts = loggedCust.getAccountOfType(3);
                 for(int i =0; i<pocketAccts.size(); i++)
                 {
                    //System.out.println("we in2: "+pocketAccts.get(i).getAccountID());
                    pocketAcctIDs.add(pocketAccts.get(i).getAccountID());
                 }
                 
                 sendToDropDown.setModel(new DefaultComboBoxModel(pocketAcctIDs.toArray()));




            }
        });

        //withdrawl: Subtract money from the checking or savings account balance.
        withdrawlButton = new JButton();
        withdrawlButton.setText("Withdrawal");
        gridButtons.add(withdrawlButton);
        withdrawlButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                ((CardLayout)userInterface.getLayout()).show (userInterface, "singleAccountTrans");
                actionStatus = 4;
                setGridButtonsVisible (false);
                withdrawlButton.setVisible (true);

                ArrayList<Account> allCheckingSavingAccts  = new ArrayList<>();
                allCheckingSavingAccts.addAll(loggedCust.getAccountOfType(0));
                allCheckingSavingAccts.addAll(loggedCust.getAccountOfType(1));
                allCheckingSavingAccts.addAll(loggedCust.getAccountOfType(2));

                ArrayList<Integer> allAcctIDs =  new ArrayList<>();
                for(int i =0; i<allCheckingSavingAccts.size(); i++)
                {
                    //System.out.println("we in: "+allCheckingSavingAccts.get(i).getAccountID());
                    allAcctIDs.add(allCheckingSavingAccts.get(i).getAccountID());
                }
                comboBox2.setModel(new DefaultComboBoxModel(allAcctIDs.toArray()));

            }
        });

        //purchase:  Subtract money from the pocket account balance.
        purchaseButton = new JButton();
        purchaseButton.setText("Purchase");
        gridButtons.add(purchaseButton);
        purchaseButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                ((CardLayout)userInterface.getLayout()).show (userInterface, "singleAccountTrans");
                actionStatus = 5;
                setGridButtonsVisible (false);
                purchaseButton.setVisible (true);

                 ArrayList<Integer> pocketAcctIDs  = new ArrayList<>();
                 ArrayList<Account> pocketAccts = loggedCust.getAccountOfType(3);
                 for(int i =0; i<pocketAccts.size(); i++)
                 {
                    //System.out.println("we in2: "+pocketAccts.get(i).getAccountID());
                    pocketAcctIDs.add(pocketAccts.get(i).getAccountID());
                 }
                 
                 comboBox2.setModel(new DefaultComboBoxModel(pocketAcctIDs.toArray()));


            }
        });


        //transfer: move money from checking/savings to another checking/savings
        //must have atleast one owner in common, cannot exced over 2000 dollars
        transferButton = new JButton();
        transferButton.setText("Transfer");
        gridButtons.add(transferButton);
        transferButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                ((CardLayout)userInterface.getLayout()).show (userInterface, "twoAccountTrans");
                actionStatus = 6;
                sendToField3.setVisible(false);
                setGridButtonsVisible (false);
                transferButton.setVisible (true);
                sendToDropDown.setVisible(true);


               ArrayList<Account> allCheckingSavingAccts  = new ArrayList<>();
                allCheckingSavingAccts.addAll(loggedCust.getAccountOfType(0));
                allCheckingSavingAccts.addAll(loggedCust.getAccountOfType(1));
                allCheckingSavingAccts.addAll(loggedCust.getAccountOfType(2));

                ArrayList<Integer> allAcctIDs =  new ArrayList<>();
                for(int i =0; i<allCheckingSavingAccts.size(); i++)
                {
                    //System.out.println("we in: "+allCheckingSavingAccts.get(i).getAccountID());
                    allAcctIDs.add(allCheckingSavingAccts.get(i).getAccountID());
                }
                comboBox3.setModel(new DefaultComboBoxModel(allAcctIDs.toArray()));
                sendToDropDown.setModel(new DefaultComboBoxModel(allAcctIDs.toArray()));
            }
        });

        //collect: Move a specified amount of money from the pocket account back to the linked checking/savings account, 
        //there will be a 3% fee assessed.
        collectButton = new JButton();
        collectButton.setText("Collect");
        gridButtons.add(collectButton);
        collectButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                ((CardLayout)userInterface.getLayout()).show (userInterface, "twoAccountTrans");
                actionStatus = 7;
                sendToField3.setVisible(false);
                setGridButtonsVisible (false);
                collectButton.setVisible (true);
                sendToDropDown.setVisible(true);


                 ArrayList<Account> allCheckingSavingAccts  = new ArrayList<>();
                allCheckingSavingAccts.addAll(loggedCust.getAccountOfType(0));
                allCheckingSavingAccts.addAll(loggedCust.getAccountOfType(1));
                allCheckingSavingAccts.addAll(loggedCust.getAccountOfType(2));

                ArrayList<Integer> allAcctIDs =  new ArrayList<>();
                for(int i =0; i<allCheckingSavingAccts.size(); i++)
                {
                    //System.out.println("we in: "+allCheckingSavingAccts.get(i).getAccountID());
                    allAcctIDs.add(allCheckingSavingAccts.get(i).getAccountID());
                }
                sendToDropDown.setModel(new DefaultComboBoxModel(allAcctIDs.toArray()));

                 ArrayList<Integer> pocketAcctIDs  = new ArrayList<>();
                 ArrayList<Account> pocketAccts = loggedCust.getAccountOfType(3);
                 for(int i =0; i<pocketAccts.size(); i++)
                 {
                    //System.out.println("we in2: "+pocketAccts.get(i).getAccountID());
                    pocketAcctIDs.add(pocketAccts.get(i).getAccountID());
                 }
                 
                 comboBox3.setModel(new DefaultComboBoxModel(pocketAcctIDs.toArray()));



            }
        });
        

        wireButton = new JButton();
        wireButton.setText("Wire");
        gridButtons.add(wireButton);
        wireButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                ((CardLayout)userInterface.getLayout()).show (userInterface, "twoAccountTrans");
                actionStatus = 8;
                sendToField3.setVisible(false);
                setGridButtonsVisible (false);
                wireButton.setVisible (true);
                sendToDropDown.setVisible(true);

                ArrayList<Account> allCheckingSavingAccts  = new ArrayList<>();
                allCheckingSavingAccts.addAll(loggedCust.getAccountOfType(0));
                allCheckingSavingAccts.addAll(loggedCust.getAccountOfType(1));
                allCheckingSavingAccts.addAll(loggedCust.getAccountOfType(2));

                ArrayList<Integer> allAcctIDs =  new ArrayList<>();
                for(int i =0; i<allCheckingSavingAccts.size(); i++)
                {
                    //System.out.println("we in: "+allCheckingSavingAccts.get(i).getAccountID());
                    allAcctIDs.add(allCheckingSavingAccts.get(i).getAccountID());
                }
                comboBox3.setModel(new DefaultComboBoxModel(allAcctIDs.toArray()));
                sendToDropDown.setModel(new DefaultComboBoxModel(allAcctIDs.toArray()));
            }
        });
        

        payButton = new JButton();
        payButton.setText("Pay Friend");
        gridButtons.add(payButton);
        payButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                ((CardLayout)userInterface.getLayout()).show (userInterface, "twoAccountTrans");
                actionStatus = 9;
                sendToDropDown.setVisible(false);
                sendToField3.setVisible(true);
                setGridButtonsVisible (false);
                payButton.setVisible(true);

                 ArrayList<Integer> pocketAcctIDs  = new ArrayList<>();
                 ArrayList<Account> pocketAccts = loggedCust.getAccountOfType(3);
                 for(int i =0; i<pocketAccts.size(); i++)
                 {
                    //System.out.println("we in2: "+pocketAccts.get(i).getAccountID());
                    pocketAcctIDs.add(pocketAccts.get(i).getAccountID());
                 }
                 
                 comboBox3.setModel(new DefaultComboBoxModel(pocketAcctIDs.toArray()));

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
    
    public void setGridButtonsVisible (boolean visible) {
        depositButton.setVisible (visible);
        topUpButton.setVisible (visible);
        withdrawlButton.setVisible (visible);
        purchaseButton.setVisible (visible);
        transferButton.setVisible (visible);
        collectButton.setVisible (visible);
        wireButton.setVisible (visible);
        payButton.setVisible (visible);
    }
    
    public void idleView () {
        ((CardLayout)userInterface.getLayout()).show (userInterface, "idleState");
        actionStatus = 1;
        setGridButtonsVisible(true);
    }
    
    public void actionPerformed(ActionEvent e){
        System.out.println("Oui!");}

    public static void main(String[] args) {
        ATMInterface ex = new ATMInterface();
        ex.setVisible(true);
    }
}
