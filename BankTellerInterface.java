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
                int TID = -1;
                boolean success = false;

                try { //Try to make the input into an integer
                    TID = Integer.parseInt(accountNumberTextField1.getText());
                    float increase = Float.parseFloat(enterCheckTextField1.getText());
                    Transaction trans = new Transaction (current, increase, 0, TID, -1);
                    success = trans.createTransaction();
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
            public void actionPerformed (ActionEvent e) {
                // TODO Create Account
                
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
                        System.out.println ("Here");
                        stmt.execute();
                        
                        System.out.println ("Here1");
                    } catch (Exception e) {
                        success = false;
                    }
                    System.out.println ("Here2");
                    
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
                
                
                JOptionPane.showMessageDialog(null, "DTER\nA\nB");
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
                java.sql.Date date = new java.sql.Date( java.lang.System.currentTimeMillis());
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
                    while (rs.next()) {
                        lastupdate = rs.getDate ("maxDate");
                        System.out.println("lastupdate "+lastupdate);
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
                    //INSERT VAL
                    System.out.println("ADDED VALUE");
                    DatabaseHelper.getInstance().openConnection();
                    PreparedStatement stmt = DatabaseHelper.getInstance ().createAction (insertVal);
                    try {
                         stmt.setDate (1, current);
                         stmt.execute();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "ERROR");
                        System.out.println("Erorr 1");
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
                            Transaction t = new Transaction(current,(float)(money*(intRate-1)),9,accountID, -1, false);
                            transactionsList.add(t);
                           
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "ERROR");
                         System.out.println("Erorr 2");
                        idleView();
                        return;
                    }
                    DatabaseHelper.getInstance().closeConnection();

                    for(int i =0; i<transactionsList.size(); i++)
                    {
                        transactionsList.get(i).createID();
                        transactionsList.get(i).createTransaction();
                    }

                }
                else if (!lastupdate.before(new java.sql.Date((current.getTime() - day30))))
                {
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
}
