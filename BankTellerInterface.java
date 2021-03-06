import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
//import java.util.Date;

public class BankTellerInterface extends JFrame {
    java.sql.Date current = new java.sql.Date(java.lang.System.currentTimeMillis());
    
    JPanel gridButtons;
    JPanel userInterface;
    JPanel screen;
    JPanel enterCheckPanel;
    JPanel createAcctPanel;
    JPanel delTransPanel;
    JPanel customerIDPanel;
    JPanel setDatePanel;
    JPanel changeInterestRatePanel;
    JPanel createCustomerPanel;
    JPanel createAccountSettingsPanel;
    
    JButton checkTransButton;
    JButton genMSButton;
    JButton listClosedButton;
    JButton genDTERButton;
    JButton customerReportButton;
    JButton addInterestButton;
    JButton createAcctButton;
    JButton delAcctButton;
    JButton delTransButton;
    JButton setDateButton;
    JButton changeInterestButton;
    
    JTextField accountNumberTextField1;
    JTextField enterCheckTextField1;
    
    JTextField enterCustomerTextField2;
    
    JTextField transactionTextField3;
    
    JTextField customerTIDTextField4;
    
    JTextField setDateTextField5;
    
    JTextField accountTypeTextField6;
    JTextField interestRateTextField6;
    
    JTextField enterCustomerNameTextField7;
    JTextField enterAddressTextField7;
    JTextField enterPINTextField7;
    
    JTextField enterAcctNumberTextField8;
    JTextField enterAccountTypeTextField8;
    JTextField enterMoneyTextField8;
    JTextField enterCoOwnersTextField8;
    JTextField linkedAccountTextField8;
    
    int customerActionStatus = 0;

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
            public void actionPerformed (ActionEvent ae) {
                int acctId = -1;
                boolean success = false;

                try { //Try to make the input into an integer
                    acctId = Integer.parseInt(accountNumberTextField1.getText());
                    Account a  = new Account(acctId);
                    if(a.getDeleteDate()==null)
                    {
                        float increase = Float.parseFloat(enterCheckTextField1.getText());
                    Transaction trans = new Transaction (current, increase, 0, acctId, -1);
                    success = trans.createTransaction();
                    }
                    
                }
                catch(Exception e) {
                    success = false;
                }
                
                if (success) {
                    JOptionPane.showMessageDialog(null, "Deposit Success");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Deposit Failed");
                }
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
            public void actionPerformed (ActionEvent ae) {
                int i = -1;
                try {
                    i = Integer.parseInt (enterCustomerTextField2.getText());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Invalid TID format");
                    idleView();
                    return;
                }
                Customer c = new Customer (i);
                
                if (c.getID() == -1) {
                    ((CardLayout)userInterface.getLayout()).show (userInterface, "createCustomerPanel");
                }
                else {
                    ((CardLayout)userInterface.getLayout()).show (userInterface, "createAccountSettingsPanel");
                }
                
            }
        });
        
        createAcctPanel.add(cancel2);
        createAcctPanel.add(createAcctLabel2);
        createAcctPanel.add(enterCustomerTextField2);
        createAcctPanel.add(submit2);
        
        //end create acct panel-----------------------------------------
        
        //createCustomerPanel --------------------------------------------
        
        
        createCustomerPanel = new JPanel();
        createCustomerPanel.setLayout(new FlowLayout());
        /*
        JButton cancel7 = new JButton ("Cancel");
        cancel7.addActionListener ( new ActionListener (){
            public void actionPerformed (ActionEvent e) {
                idleView();
            }
        });
        */
        
        JLabel createAcctLabel7  = new JLabel();
        createAcctLabel7.setText("Customer Name: ");
        enterCustomerNameTextField7 = new JTextField();
        enterCustomerNameTextField7.setPreferredSize(new Dimension(150,25));
        
        JLabel addressLabel7  = new JLabel();
        addressLabel7.setText("Address: ");
        enterAddressTextField7 = new JTextField();
        enterAddressTextField7.setPreferredSize(new Dimension(150,25));
        
        
        JLabel PINLabel7  = new JLabel();
        PINLabel7.setText("Set PIN: ");
        enterPINTextField7 = new JTextField();
        enterPINTextField7.setPreferredSize(new Dimension(150,25));
        
        JButton submit7 = new JButton ("Submit");
        submit7.addActionListener ( new ActionListener () {
            public void actionPerformed (ActionEvent ae) {
                // TODO Create Account
                boolean success = true;
                try {
                    Customer c = new Customer (Integer.parseInt (enterCustomerTextField2.getText()), enterCustomerNameTextField7.getText(), enterAddressTextField7.getText(), Customer.encode(enterPINTextField7.getText()));
                    success = c.createCustomer();
                } catch (Exception e) {
                    success = false;
                }
                
                if (success) {
                    JOptionPane.showMessageDialog(null, "Create User Succeeded");
                    ((CardLayout)userInterface.getLayout()).show (userInterface, "createAccountSettingsPanel");
                } else {
                    JOptionPane.showMessageDialog(null, "Create User Failed");
                    idleView();
                }
                
            }
        });
        //createCustomerPanel.add(cancel7);
        createCustomerPanel.add(createAcctLabel7);
        createCustomerPanel.add(enterCustomerNameTextField7);
        createCustomerPanel.add(addressLabel7);
        createCustomerPanel.add(enterAddressTextField7);
        createCustomerPanel.add(PINLabel7);
        createCustomerPanel.add(enterPINTextField7);
        createCustomerPanel.add(submit7);
        
        //end create acct panel-----------------------------------------
        
        //createAcctPanel --------------------------------------------
        
        
         createAccountSettingsPanel = new JPanel();
         /*
         createAccountSettingsPanel.setLayout(new FlowLayout());
         JButton cancel8 = new JButton ("Cancel");
         cancel8.addActionListener ( new ActionListener (){
         public void actionPerformed (ActionEvent e) {
         idleView();
         }
         });
         */
        
        JLabel accountNumberLabel8 = new JLabel ();
        accountNumberLabel8.setText ("Account ID");
        enterAcctNumberTextField8 = new JTextField ();
        enterAcctNumberTextField8.setPreferredSize(new Dimension(150,25));
        
        JLabel createAcctLabel8  = new JLabel();
        createAcctLabel8.setText("Account Type: ");
        enterAccountTypeTextField8 = new JTextField();
        enterAccountTypeTextField8.setPreferredSize(new Dimension(150,25));
        
        JLabel moneyLabel8  = new JLabel();
        moneyLabel8.setText("(Non Pocket Account Only) $");
        enterMoneyTextField8 = new JTextField();
        enterMoneyTextField8.setPreferredSize(new Dimension(150,25));
        
        
        JLabel coOwnersLabel8 = new JLabel();
        coOwnersLabel8.setText("CoOwner TIDs (Must Be Existing): ");
        enterCoOwnersTextField8 = new JTextField();
        enterCoOwnersTextField8.setPreferredSize(new Dimension(150,25));
        
        JLabel linkedAccountLabel8 = new JLabel();
        linkedAccountLabel8.setText("Link Account ID (Only for Pocket Accounts): ");
        linkedAccountTextField8 = new JTextField();
        linkedAccountTextField8.setPreferredSize(new Dimension(150,25));
        
        JButton submit8 = new JButton ("Submit");
        submit8.addActionListener ( new ActionListener () {
            public void actionPerformed (ActionEvent ae) {
                // TODO Create Account
                int acctType = -1;
                double interest_rate = 1;
                String text = enterAccountTypeTextField8.getText();
                if (text.equals ("0") || text.equals ("InterestChecking")) {
                    acctType = 0;
                    interest_rate = 1.055;
                } else if (text.equals ("1") || text.equals ("StudentChecking")) {
                    acctType = 1;
                } else if (text.equals ("2") || text.equals ("Savings")) {
                    acctType = 2;
                    interest_rate = 1.075;
                } else if (text.equals ("3") || text.equals ("Pocket")) {
                    acctType = 3;
                } else {
                    JOptionPane.showMessageDialog(null, "Account Type Input Not Recognized \"" + text + "\"");
                }
                Account a = null;
                if (acctType == 3) {
                    boolean createdAccount = true;
                    try {
                        a = new Account (Integer.parseInt (enterAcctNumberTextField8.getText()), acctType, 0, null, interest_rate, Integer.parseInt (enterCustomerTextField2.getText()), false);
                        createdAccount = a.createAccount (current);
                    } catch (Exception e) {
                        createdAccount = false;
                        return;
                    }
                    if (createdAccount) {
                        JOptionPane.showMessageDialog(null, "Create Account Success");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Create Account Failed");
                        idleView();
                        return;
                    }
                    
                    boolean linkedAccountSuccess = true;
                    try {
                        linkedAccountSuccess = a.createLinkedAccount(Integer.parseInt (linkedAccountTextField8.getText()));
                    } catch (Exception e) {
                        linkedAccountSuccess = false;
                    }
                    
                    if (linkedAccountSuccess) {
                        JOptionPane.showMessageDialog(null, "Linked Account Success");
                        JOptionPane.showMessageDialog(null, "No Money was Transferred to New Account");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Link Account Failed");
                        
                        DatabaseHelper.getInstance().openConnection();
                        String query = "DELETE FROM Account A WHERE A.account_id=?";
                        PreparedStatement stmt = DatabaseHelper.getInstance().createAction (query);
                        
                        boolean deleteSuccess = true;
                        try {
                            stmt.setInt (1, a.getAccountID());
                            stmt.execute();
                        } catch (Exception e) {
                            deleteSuccess = false;
                        }
                        
                        if (deleteSuccess) {
                            JOptionPane.showMessageDialog(null, "Deleted Unlinked Account");
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Failed Delete Unlinked Account");
                        }
                        idleView();
                        return;
                    }
                }
                else if (acctType != -1) {
                    double money = 0;
                    try {
                        money = Double.parseDouble (enterMoneyTextField8.getText());
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Money Input Not Recognized \"" + enterMoneyTextField8.getText() + "\"");
                        idleView();
                        return;
                    }
                    boolean createdAccount = true;
                    try {
                        a = new Account (Integer.parseInt (enterAcctNumberTextField8.getText()), acctType, money, null, interest_rate, Integer.parseInt (enterCustomerTextField2.getText()), false);
                        createdAccount = a.createAccount (current);
                    } catch (Exception e) {
                        createdAccount = false;
                        return;
                    }
                    if (createdAccount) {
                        JOptionPane.showMessageDialog(null, "Create Account Success");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Create Account Failed");
                        idleView();
                        return;
                    }
                }
                
                if (acctType != -1) {
                    if (!enterCoOwnersTextField8.getText().equals("")) {
                        String [] arr = enterCoOwnersTextField8.getText().split(",");
                        for (int i = 0; i < arr.length; i++) {
                            boolean success = true;
                            Customer c = null;
                            try {
                                c = new Customer (Integer.parseInt(arr[i]));
                            } catch (Exception e) {
                                success = false;
                            }
                            
                            success = success && (c.getID () != -1);
                            if (!success) {
                                JOptionPane.showMessageDialog(null, "CoOwner Tuple Not Created: \""+arr[i]+"\" not a Customer");
                            } else if (arr[i].equals(enterCustomerTextField2.getText()))  {
                                JOptionPane.showMessageDialog(null, "CoOwner Tuple Not Created: \""+arr[i]+"\" is already Primary Owner");
                            } else {
                                DatabaseHelper.getInstance().openConnection();
                                String query = "INSERT INTO CoOwner (TID, account_id) VALUES (?, ?)";
                                PreparedStatement stmt = DatabaseHelper.getInstance().createAction (query);
                                try {
                                    stmt.setInt (1, Integer.parseInt(arr[i]));
                                    stmt.setInt (2, a.getAccountID());
                                    stmt.execute();
                                } catch (Exception e) {
                                    success = false;
                                }
                                DatabaseHelper.getInstance().closeConnection();
                                
                                if (success) {
                                    JOptionPane.showMessageDialog(null, "CoOwner Tuple Created With TID " + arr[i]);
                                } else {
                                    JOptionPane.showMessageDialog(null, "CoOwner Tuple Not Created");
                                }
                            }
                        }
                    }
                }
                
                idleView();
            }
        });
        //createAccountSettingsPanel.add(cancel7);
        createAccountSettingsPanel.add(accountNumberLabel8);
        createAccountSettingsPanel.add(enterAcctNumberTextField8);
        createAccountSettingsPanel.add(createAcctLabel8);
        createAccountSettingsPanel.add(enterAccountTypeTextField8);
        createAccountSettingsPanel.add(moneyLabel8);
        createAccountSettingsPanel.add(enterMoneyTextField8);
        createAccountSettingsPanel.add(coOwnersLabel8);
        createAccountSettingsPanel.add(enterCoOwnersTextField8);
        createAccountSettingsPanel.add(linkedAccountLabel8);
        createAccountSettingsPanel.add(linkedAccountTextField8);
        createAccountSettingsPanel.add(submit8);
        
        //end create acct panel-----------------------------------------

        
        //delTransPanrl --------------------------------------------
        
        delTransPanel = new JPanel();
        delTransPanel.setLayout(new FlowLayout());

        JButton cancel3 = new JButton ("Cancel");
        cancel3.addActionListener ( new ActionListener (){
            public void actionPerformed (ActionEvent e) {
                idleView();
            }
        });
        
        JLabel delTransLabel3 = new JLabel();
        delTransLabel3.setText("Delete Transaction:");
        transactionTextField3 = new JTextField();
        transactionTextField3.setPreferredSize(new Dimension(150,25));
        
        JButton submit3 = new JButton ("Submit");
        submit3.addActionListener ( new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                // TODO Delete Transactions
                
                idleView();
            }
        });
        
        delTransPanel.add (cancel3);
        delTransPanel.add (delTransLabel3);
        delTransPanel.add (transactionTextField3);
        delTransPanel.add (submit3);
        
        //delTransPanel-----------------------------------------

        
        
        // Beginning enter Customer ID Panel
        
        customerIDPanel = new JPanel ();
        
        JButton cancel4 = new JButton ("Cancel");
        cancel4.addActionListener ( new ActionListener (){
            public void actionPerformed (ActionEvent e) {
                idleView();
            }
        });
        
        JLabel customerTIDLabel4 = new JLabel();
        customerTIDLabel4.setText("Customer TID:");
        customerTIDTextField4 = new JTextField();
        customerTIDTextField4.setPreferredSize(new Dimension(150,25));
        
        JButton submit4 = new JButton ("Submit");
        submit4.addActionListener ( new ActionListener () {
            public void actionPerformed (ActionEvent ae) {
                Customer c = null;
                boolean fail = false;
                try {
                    c = new Customer (Integer.parseInt (customerTIDTextField4.getText()));
                } catch (Exception e) {
                    fail = true;
                }
                
                fail = fail || c == null || c.getID() == -1;
                
                if (!fail && customerActionStatus == 0) {
                    // TODO Generate Monthly Statement
                    ArrayList <Account> accounts = c.getAccounts ();
                    String output = "Monthly Statement\n";
                    for (Account account: accounts) {
                        ArrayList<Customer> customers = account.getAccountOwners ();
                        output+= "Account ID  " +account.getAccountID() +"\n";
                        for (Customer customer: customers) {
                            output+="Owner:  " + customer.getName()+ "  Address:  " + customer.getAddress() +"\n";
                        }
                        ArrayList <Transaction> transactions = account.getTransactions ();
                        double accountBalance = account.getMoney();
                        long day30 = 31l * 24 * 60 * 60 * 1000;
                        for (Transaction transaction: transactions) {
                            if (transaction.getTransactionDate().after(new Date(current.getTime() - day30))) {
                                output+="Transaction ID:  " + transaction.getTransactionID()+ "  Date:  " + transaction.getTransactionDate() +"\n";
                                if (transaction.getAccountIncreased() == account.getAccountID()) {
                                    accountBalance -= transaction.getMoneyTransferred();
                                }
                                if (transaction.getAccountDecreased() == account.getAccountID()) {
                                    accountBalance += transaction.getMoneyTransferred();
                                }
                            }
                        }
                        output += "Initial Balance :  " + accountBalance + "  Final Balance:  " + account.getMoney() +"\n\n";
                    }
                    JOptionPane.showMessageDialog(null, output);
                    
                } else if (!fail && customerActionStatus == 1) {
                    // TODO Customer Report
                    ArrayList <Account> accounts = c.getAccounts ();
                    String output = "Monthly Statement\n";
                    for (Account account: accounts) {
                        output += "Account ID:  " + account.getAccountID();
                        output += "  Account Type:  ";
                        if (account.getAccountType() == 0)
                            output += "Interest Checking";
                        else if (account.getAccountType() == 1)
                            output += "Student Checking";
                        else if (account.getAccountType() == 2)
                            output += "Savings";
                        else
                            output += "Pocket";
                        
                        if (account.getDeleteDate() != null)
                            output += "  Deleted Date:  " + account.getDeleteDate().toString();
                        output+= "\n";
                    }
                    JOptionPane.showMessageDialog(null, output);
                    
                }
                idleView();
            }
        });
        
        customerIDPanel.add (cancel4);
        customerIDPanel.add (customerTIDLabel4);
        customerIDPanel.add (customerTIDTextField4);
        customerIDPanel.add (submit4);
        customerIDPanel.setVisible(true);
        
        // End Customer ID Panel


        //setDatePanel --------------------------------------------
        
        setDatePanel = new JPanel();
        setDatePanel.setLayout(new FlowLayout());
        
        JButton cancel5 = new JButton ("Cancel");
        cancel5.addActionListener ( new ActionListener (){
            public void actionPerformed (ActionEvent e) {
                idleView();
            }
        });
        
        JLabel setDateLabel5 = new JLabel();
        setDateLabel5.setText("Date Format MM-DD-YYYY: ");
        setDateTextField5 = new JTextField();
        setDateTextField5.setPreferredSize(new Dimension(150,25));
        
        JButton submit5 = new JButton ("Change");
        submit5.addActionListener ( new ActionListener () {
            public void actionPerformed (ActionEvent ae) {
                boolean success = true;
                try {
                    java.text.DateFormat df = new java.text.SimpleDateFormat("MM-dd-yyyy");
                    current = new java.sql.Date(df.parse(setDateTextField5.getText()).getTime());
                } catch (Exception e) {
                    success = false;
                }
                
                if (success) {
                    JOptionPane.showMessageDialog(null, "Changed date to " + current.toString());
                }  else
                    JOptionPane.showMessageDialog(null, "Unable to change date to \"" + setDateTextField5.getText() +"\"");
                
                String queryAddInterest = "SELECT A.account_id, A.moneyVal, A.annualRate FROM Account A";
                String queryGetDate = "SELECT MAX(rateTrack.rateDate) AS maxDate FROM RateTrack";
                String querySetDate = "UPDATE rateTrack SET rateTrack.rateDate="+current;
                String insertVal = "INSERT INTO RateTrack (rateDate) VALUES (?)";
                boolean flag = false;
                long day30 = 31l * 24 * 60 * 60 * 1000;
                
                
                java.sql.Date lastupdate = null;
                
                //get last date
                DatabaseHelper.getInstance().openConnection();
                try {
                    ResultSet rs = DatabaseHelper.getInstance ().executeQuery(queryGetDate);
                    flag = false; // isNotEmpty
                    if (rs.next()) {
                        lastupdate = rs.getDate ("maxDate");
                        System.out.println("lastupdate "+lastupdate);
                        if (lastupdate != null)
                            flag = true;
                    }
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "ERROR");
                    idleView();
                    return;
                }
                DatabaseHelper.getInstance().closeConnection();
                
                //if the table is empty, insert val
                
                ArrayList<Transaction> transactionsList = new ArrayList<>();
                if(!flag || lastupdate.before(new java.sql.Date((current.getTime() - day30)))) {
                    addInterest(transactionsList);
                }
                idleView();
            }
        });
        
        setDatePanel.add (cancel5);
        setDatePanel.add (setDateLabel5);
        setDatePanel.add (setDateTextField5);
        setDatePanel.add (submit5);
        
        //setDatePanel-----------------------------------------
        
        
        //changeInterestRatePanel --------------------------------------------
        
        changeInterestRatePanel = new JPanel();
        changeInterestRatePanel.setLayout(new FlowLayout());
        
        JButton cancel6 = new JButton ("Cancel");
        cancel6.addActionListener ( new ActionListener (){
            public void actionPerformed (ActionEvent e) {
                idleView();
            }
        });
        
        JLabel setAccountTypeLabel6 = new JLabel();
        setAccountTypeLabel6.setText("Account Type: ");
        accountTypeTextField6 = new JTextField();
        accountTypeTextField6.setPreferredSize(new Dimension(150,25));
        
        JLabel setInterestRateLabel6 = new JLabel();
        setInterestRateLabel6.setText("Interest Rate: ");
        interestRateTextField6 = new JTextField();
        interestRateTextField6.setPreferredSize(new Dimension(150,25));
        
        
        JButton submit6 = new JButton ("Change");
        submit6.addActionListener ( new ActionListener () {
            public void actionPerformed (ActionEvent ae) {
                int acctType = -1;
                if (accountTypeTextField6.getText().equals ("0") || accountTypeTextField6.getText().equals ("InterestChecking")) {
                    acctType = 0;
                } else if (accountTypeTextField6.getText().equals ("1") || accountTypeTextField6.getText().equals ("StudentChecking")) {
                    acctType = 1;
                } else if (accountTypeTextField6.getText().equals ("2") || accountTypeTextField6.getText().equals ("Savings")) {
                    acctType = 2;
                } else if (accountTypeTextField6.getText().equals ("3") || accountTypeTextField6.getText().equals ("Pocket")) {
                    acctType = 3;
                } else {
                    JOptionPane.showMessageDialog(null, "Account Type Input Not Recognized \"" + accountTypeTextField6.getText() + "\"");
                }
                
                if (acctType != -1) {
                    DatabaseHelper.getInstance().openConnection();
                    boolean success = true;
                    try {
                        
                        System.out.println  (Float.parseFloat (interestRateTextField6.getText()));
                        //String updateQuery = "UPDATE Account SET annualRate="+Float.parseFloat (interestRateTextField6.getText()) + " WHERE account_type=" + acctType;
                        String updateQuery = "UPDATE Account SET annualRate=? WHERE account_type=?";
                        PreparedStatement stmt = DatabaseHelper.getInstance().createAction (updateQuery);
                        stmt.setFloat (1, Float.parseFloat (interestRateTextField6.getText()));
                        stmt.setInt (2, acctType);
                        //System.out.println ("Here");
                        stmt.execute();
                        
                        //System.out.println ("Here1");
                    } catch (Exception e) {
                        success = false;
                    }
                    //System.out.println ("Here2");
                    
                    DatabaseHelper.getInstance().closeConnection();
                    
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Update Success");
                    }  else
                        JOptionPane.showMessageDialog(null, "Update Failed");
                }
                idleView();
            }
        });
        
        changeInterestRatePanel.add (cancel6);
        changeInterestRatePanel.add (setAccountTypeLabel6);
        changeInterestRatePanel.add (accountTypeTextField6);
        changeInterestRatePanel.add (setInterestRateLabel6);
        changeInterestRatePanel.add (interestRateTextField6);
        changeInterestRatePanel.add (submit6);
        
        //changeInterestRatePanel-----------------------------------------
        
        

        // Beginning CardLayout-----------------------------------------------------------
        
        userInterface = new JPanel ();
        userInterface.setLayout (new CardLayout());
        userInterface.setSize (150, 150);
        userInterface.add (screen, "idleState");
        userInterface.add (enterCheckPanel, "enterCheckPanel");
        userInterface.add (createAcctPanel, "createAcctPanel");
        userInterface.add (createCustomerPanel, "createCustomerPanel");
        userInterface.add (createAccountSettingsPanel, "createAccountSettingsPanel");
        userInterface.add (delTransPanel, "delTransPanel");
        userInterface.add (customerIDPanel, "customerIDPanel");
        userInterface.add (setDatePanel, "setDatePanel");
        userInterface.add (changeInterestRatePanel, "changeInterestRatePanel");

        
        ((CardLayout)userInterface.getLayout()).show (userInterface, "idleState");
        // End CardLayout---------------------------------------------------------------------



        // Beginning GridButton-----------------------------------------------------------
        JPanel gridButtons = new JPanel();
        gridButtons.setLayout(new GridLayout(4,3));
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
            public void actionPerformed (ActionEvent ae) {
                customerActionStatus = 0;
                
                ((CardLayout)userInterface.getLayout()).show (userInterface, "customerIDPanel");
                setGridButtonsVisible (false);
                genMSButton.setVisible (true);
            }
        });

        listClosedButton = new JButton();
        listClosedButton.setText("List Closed Accounts");
        gridButtons.add(listClosedButton);
        listClosedButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent ae) {
                DatabaseHelper.getInstance().openConnection();
                ResultSet rs = DatabaseHelper.getInstance().executeQuery ("SELECT A.account_id, A.deletedDate FROM Account A");
                long day30 = 31l * 24 * 60 * 60 * 1000;
                HashMap <Integer, Date> hashMap = new HashMap <> ();
                try {
                    while (rs.next()) {
                        Date deleted = rs.getDate ("deletedDate");
                        if (!rs.wasNull() && deleted.after(new java.sql.Date((current.getTime() - day30)))) {
                            hashMap.put (rs.getInt ("account_id"), deleted);
                        }
                    }
                } catch (Exception e) {
                    
                }
                DatabaseHelper.getInstance().closeConnection();
                
                String output = "Closed Accounts In Last 31 Days of " +current.toString() + ":\n";
                for (Integer key: hashMap.keySet()) {
                    output += "Account ID  "+ key + "  deleted on  " + hashMap.get (key).toString() +"\n";
                    //output += key + "\t\t" +hashMap.get (key).toString() +"\n";
                }
                
                JOptionPane.showMessageDialog(null, output);
            }
        });

        genDTERButton = new JButton();
        genDTERButton.setText("Generate DTER");
        gridButtons.add(genDTERButton);
        genDTERButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                // TODO Generate DTER
                String query1 = " SELECT * FROM (SELECT TID, SUM (moneyVal) AS SumInc FROM (SELECT TID , moneyVal FROM Transaction T, Customer C, Account A WHERE C.TID = A.primOwner AND A.account_id = T.incrAcctID AND (T.transType= 0 OR  T.transType = 4  OR T.transType = 7)  UNION SELECT Y.TID, moneyVal FROM Transaction X, CoOwner Y, Account Z WHERE Y.TID = Z.primOwner AND Z.account_id = X.incrAcctID AND (X.transType= 0 OR  X.transType = 4 OR X.transType = 7) ) GROUP BY TID) WHERE SumInc > 10000";
                ArrayList<Integer> idsNames = new ArrayList<>();
                int acctID = 0;
                //get last date
                DatabaseHelper.getInstance().openConnection();
                 try {
                    ResultSet rs = DatabaseHelper.getInstance ().executeQuery(query1);
                    //flag = false; // isNotEmpty
                    while (rs.next()) {
                        acctID = rs.getInt ("TID");
                        //System.out.println("TID rich boiz "+acctID);
                        idsNames.add(acctID);
                        //flag = true;
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "ERROR");
                    idleView();
                    return;
                }
                DatabaseHelper.getInstance().closeConnection();
                //JOptionPane.showMessageDialog(null, "DTER\nA\nB");
                String output = "Drug and Tax Evasion Report" + ":\n";
                for(int i =0; i< idsNames.size(); i++)
                {
                    int id = idsNames.get(i);
                    Customer a = new Customer(id);
                    output += "Account ID  "+ id + "  Name " + a.getName() +"\n";

                }
                
                JOptionPane.showMessageDialog(null, output);
            }
        });

        customerReportButton = new JButton();
        customerReportButton.setText("Customer Report");
        gridButtons.add(customerReportButton);
        customerReportButton.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                customerActionStatus = 1;
                
                ((CardLayout)userInterface.getLayout()).show (userInterface, "customerIDPanel");
                setGridButtonsVisible (false);
                customerReportButton.setVisible (true);
            }
        });

        addInterestButton = new JButton();
        addInterestButton.setText("Add Interest");
        gridButtons.add(addInterestButton);
        addInterestButton.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                // TODO Add Interest (Must put in a value in the database!)
                //java.sql.Date date = new java.sql.Date( java.lang.System.currentTimeMillis());
                String queryAddInterest = "SELECT A.account_id, A.moneyVal, A.annualRate FROM Account A";
                String queryGetDate = "SELECT MAX(rateTrack.rateDate) AS maxDate FROM RateTrack";
                String querySetDate = "UPDATE rateTrack SET rateTrack.rateDate="+current;
                String insertVal = "INSERT INTO RateTrack (rateDate) VALUES (?)";
                boolean flag = false;
                long day30 = 31l * 24 * 60 * 60 * 1000;

               
                java.sql.Date lastupdate = null;

                //get last date
                DatabaseHelper.getInstance().openConnection();
                 try {
                    ResultSet rs = DatabaseHelper.getInstance ().executeQuery(queryGetDate);
                    flag = false; // isNotEmpty
                     if (rs.next()) {
                         lastupdate = rs.getDate ("maxDate");
                         System.out.println("lastupdate "+lastupdate);
                         if (lastupdate != null)
                             flag = true;
                     }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "ERROR");
                    idleView();
                    return;
                }
                DatabaseHelper.getInstance().closeConnection();

                //if the table is empty, insert val

                ArrayList<Transaction> transactionsList = new ArrayList<>();
                if(!flag || lastupdate.before(new java.sql.Date((current.getTime() - day30)))) {
                    addInterest(transactionsList);
                }
                else if (!lastupdate.before(new java.sql.Date((current.getTime() - day30)))) {
                        JOptionPane.showMessageDialog(null, "You already added Interest this month");
                    idleView();
                    return;
                }
               
                JOptionPane.showMessageDialog(null, "SUCCESS");
                idleView();
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
                // TODO Delete Closed Accounts
                DatabaseHelper.getInstance().openConnection();
                String query = " DELETE FROM Account T WHERE T.deletedDate IS NOT NULL";

        
               try {
                    ResultSet rs = DatabaseHelper.getInstance ().executeQuery(query);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "ERROR");
                    }
                DatabaseHelper.getInstance().closeConnection();

                DatabaseHelper.getInstance().openConnection();
                String query2 = "DELETE FROM Customer C WHERE C.TID NOT IN (SELECT primOwner FROM Account UNION SELECT TID FROM CoOwner)";
               try {
                    ResultSet rs = DatabaseHelper.getInstance ().executeQuery(query2);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "ERROR");
                    }
                DatabaseHelper.getInstance().closeConnection();

                JOptionPane.showMessageDialog(null, "SUCCESS");
                idleView();
            }
        });

        delTransButton = new JButton();
        delTransButton.setText("Delete Transactions");
        gridButtons.add(delTransButton);
        delTransButton.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                ((CardLayout)userInterface.getLayout()).show (userInterface, "idleState");
                setGridButtonsVisible (false);
                delTransButton.setVisible (true);

                DatabaseHelper.getInstance().openConnection();
                String query = "DELETE FROM Transaction";
        
               try {
                    ResultSet rs = DatabaseHelper.getInstance ().executeQuery(query);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "ERROR");
                    }
                DatabaseHelper.getInstance().closeConnection();


                JOptionPane.showMessageDialog(null, "SUCCESS");
                idleView();
            }
           
        });
        
        setDateButton = new JButton();
        setDateButton.setText("Debug: Set Date");
        gridButtons.add(setDateButton);
        setDateButton.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                ((CardLayout)userInterface.getLayout()).show (userInterface, "setDatePanel");
                setGridButtonsVisible (false);
                setDateButton.setVisible (true);
            }
        });
        
        changeInterestButton = new JButton ();
        changeInterestButton.setText("Debug: Change Interest");
        gridButtons.add(changeInterestButton);
        changeInterestButton.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                ((CardLayout)userInterface.getLayout()).show (userInterface, "changeInterestRatePanel");
                setGridButtonsVisible (false);
                changeInterestButton.setVisible (true);
            }
        });
        
        
        //end grid buttons

        getContentPane().add(title);
        getContentPane().add(userInterface);
        getContentPane().add(gridButtons);
  
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
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
        setDateButton.setVisible (visible);
        changeInterestButton .setVisible(visible);
    }
    
    public void idleView () {
        ((CardLayout)userInterface.getLayout()).show (userInterface, "idleState");
        setGridButtonsVisible(true);
    }
    
    public static void main(String[] args) {
        BankTellerInterface ex = new BankTellerInterface();
        ex.setVisible(true);
    }
    
    public void addInterest (ArrayList<Transaction> transactionsList) {
        String queryAddInterest = "SELECT A.account_id, A.moneyVal, A.annualRate FROM Account A";
        String insertVal = "INSERT INTO RateTrack (rateDate) VALUES (?)";
        System.out.println("ADDED VALUE");
        DatabaseHelper.getInstance().openConnection();
        PreparedStatement stmt = DatabaseHelper.getInstance ().createAction (insertVal);
        try {
            stmt.setDate (1, current);
            stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERROR");
            System.out.println("Error 1");
            idleView();
            return;
        }
        DatabaseHelper.getInstance().closeConnection();
        
        
        //ADD INTEREST
        DatabaseHelper.getInstance().openConnection();
        try {
            ResultSet rs = DatabaseHelper.getInstance ().executeQuery(queryAddInterest);
            while (rs.next()) {
                int accountID = rs.getInt("account_id");
                double money = rs.getDouble("moneyVal");
                double intRate =  rs.getDouble("annualRate");
                System.out.println(accountID+" "+money+" "+ intRate);
                Transaction t = new Transaction(current,(float)(money*(intRate-1)/12),9,accountID, -1, false);
                transactionsList.add(t);
                
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERROR");
            System.out.println("Error 2");
            idleView();
            return;
        }
        DatabaseHelper.getInstance().closeConnection();
        
        for(int i =0; i<transactionsList.size(); i++) {
            transactionsList.get(i).createID();
            transactionsList.get(i).createTransaction();
        }
    }
}
